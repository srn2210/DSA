import java.util.ArrayList;
import java.util.List;

public class Bridge {
    int[] disc;
    int[] low;
    int time;

    void dfs(List<List<Integer>> graph, List<List<Integer>> ans, int u, int p) {
        disc[u] = low[u] = ++time;

        for(int v : graph.get(u)) {
            if(v == p) continue;
            if(disc[v] == 0) {
                dfs(graph, ans, v, u);
                if(disc[u] < low[v]) {
                    ans.add(List.of(u, v));
                }
                low[u] = Math.min(low[u], low[v]);
            }
            else {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    List<List<Integer>> findBridges(List<List<Integer>> graph, int n) {
        disc = new int[n];
        low = new int[n];
        time = 0;
        var ans = new ArrayList<List<Integer>>();
        for(int i=0; i<n; i++) {
            if(disc[i] == 0) {
                dfs(graph, ans, i, i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        var graph = new ArrayList<List<Integer>>();
        graph.add(List.of(1, 2));
        graph.add(List.of(0, 2, 3));
        graph.add(List.of(1, 0));
        graph.add(List.of(1));
        int n = 4;

        System.out.println(new Bridge().findBridges(graph, n));
    }
}
