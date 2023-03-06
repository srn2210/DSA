class UnionFind {
    
    // Maintains a forest of connected components, is also used for finding if a node is connected to another node.
    // Union connects two nodes together into a common set or a tree.
    // Find returns the parent or the root of the node queried.
    // isConnected answers the query of the two nodes being connected.

    // Time Complexity - O(n) for initializing, almost O(1) time for find, union and isConnected methods.
    // Space Complexity - O(n), for both the parent node array and the size array.

    int[] arr;                                                          // Array that stores the parent node as value, in the childs index.
    int[] sz;                                                           // Size array for the union by size method optimization.
    UnionFind(int n) {                                                  
        arr = new int[n];                                               // Initializing both the parent array and the size array.
        sz = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = i;                                                 // Assign each node itself as its parent.
            sz[i] = 1;                                                  // Assign size of 1 initially to all nodes.
        }
    }
    int find(int p) {                                                   // Function that returns the parent of the queried node.
        if(arr[p] == p) return p;                                       // If the parent array value and the node is the same, then its the root of the tree.
        return arr[p] = find(arr[p]);                                   // Path compression that lessens the levels in the tree.
    }
    void union(int p, int q) {                                          // Function to join two nodes into the same set/tree.
        int x = find(p);                                                // Find the parent of node1.
        int y = find(q);                                                // Find the parent of node2.
        if(isConnected(p, q)) return;                                   // If the nodes are connected, then they are already in the set.
        if(sz[x] < sz[y]) {                                             // Else we find out the bigger set/tree of the two, and assign the set/tree with -
            arr[x] = arr[y];                                            // - the smaller size as the child of the set/tree with the higher size.
            sz[y] += sz[x];                                             // We also update the size of the parent set/tree with the childs size added to it.
        }
        else {
            arr[y] = arr[x];
            sz[x] += sz[y];
        }
    }
    boolean isConnected(int p, int q) {                                 // Returns true if the two nodes are in the same set/tree, else false.
        return find(p) == find(q);                                      // Checks if the two nodes have the same parent, if so they are connected.
    }
    public static void main(String[] args) {
        UnionFind obj = new UnionFind(5);                               // Object of the UnionFind class.
        obj.union(0, 2);                                                // Joining two nodes.
        obj.union(2, 4);
        System.out.println(obj.find(4));                                // Finding the parent of the node.
        System.out.println(obj.isConnected(3, 1));
        System.out.println(obj.isConnected(2, 4));                      // Checking if the two nodes are connected.
    }
}