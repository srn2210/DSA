class StringTrie {
    static class TrieNode {
        TrieNode[] arr;
        boolean isEnd;
        TrieNode() {
            arr = new TrieNode[26];
            isEnd = false;
        }
        TrieNode get(int idx) {
            return arr[idx];
        }
        void put(int idx, TrieNode node) {
            arr[idx] = node;
        }
        boolean isNull(int idx) {
            return arr[idx] == null;
        }
        boolean isEnd() {
            return isEnd;
        }
        void setEnd() {
            isEnd = true;
        }
    }
    TrieNode root;
    StringTrie() {
        root = new TrieNode();
    }
    void insert(String str) {
        var curr = root;
        for(char ch : str.toCharArray()) {
            int idx = ch - 'a';
            if(curr.isNull(idx)) curr.put(idx, new TrieNode());
            curr = curr.get(idx);
        }
        curr.setEnd();
    }
    boolean search(String str) {
        var curr = root;
        for(char ch : str.toCharArray()) {
            int idx = ch - 'a';
            if(curr.isNull(idx)) return false;
            curr = curr.get(idx);
        }
        return curr.isEnd();
    }

    public static void main(String[] args) {
        StringTrie trie = new StringTrie();
        trie.insert("apple");
        trie.insert("orange");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("mango"));
        System.out.println(trie.search("orange"));
    }
}
class XORTrie {
    static class TrieNode {
        TrieNode[] arr;
        boolean isEnd;
        TrieNode() {
            arr = new TrieNode[2];
            isEnd = false;
        }
        TrieNode get(int idx) {
            return arr[idx];
        }
        void put(int idx, TrieNode node) {
            arr[idx] = node;
        }
        boolean isNull(int idx) {
            return arr[idx] == null;
        }
    }
    TrieNode root;
    XORTrie() {
        root = new TrieNode();
    }
    void insert(int num) {
        var curr = root;
        for(int i=31; i>=0; i--) {
            int bit = ((num >> i) & 1);
            if(curr.isNull(bit)) curr.put(bit, new TrieNode());
            curr = curr.get(bit);
        }
    }
    int query(int num) {
        var curr = root;
        int ans = 0;
        for(int i=31; i>=0; i--) {
            int bit = ((num >> i) & 1);
            if((bit == 0 && !curr.isNull(1)) || (bit == 1 && curr.isNull(0))) {
                curr = curr.get(1);
                ans = ans | (1 << i);
            }
            else {
                curr = curr.get(0);
            }
        }
        return ans ^ num;
    }

    public static void main(String[] args) {
        XORTrie trie = new XORTrie();
        trie.insert(7);
        trie.insert(4);
        System.out.println(trie.query(8));
        System.out.println(trie.query(7));
    }
}
