package BOJ_20303;

import java.util.*;
import java.io.*;

class Node {
    private int f;
    private int c;

    public Node(int f, int c) {
        this.f = f;
        this.c = c;
    }

    public int getF() {
        return this.f;
    }

    public int getC() {
        return this.c;
    }
}

public class Main {

    static private boolean[] visited;
    static private int[] candy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmk = br.readLine().split(" ");
        int n = Integer.parseInt(nmk[0]);
        int m = Integer.parseInt(nmk[1]);
        int k = Integer.parseInt(nmk[2]);

        visited = new boolean[n + 1];
        candy = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            String[] ab = br.readLine().split(" ");
            int a = Integer.parseInt(ab[0]);
            int b = Integer.parseInt(ab[1]);

            graph.putIfAbsent(a, new ArrayList<>());
            graph.putIfAbsent(b, new ArrayList<>());

            graph.get(a).add(b); graph.get(b).add(a);
        }

        List<Node> nodes = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;
            nodes.add(bfs(graph, i));
        }

        int size = nodes.size();
        int[][] dp = new int[size][k + 1];
        for (int i = 0; i <= k; i++) {
            Node v = nodes.get(0);
            int vf = v.getF(); int vc = v.getC();
            if (i > vf) {
                dp[0][i] = vc;
            }
        }
        for (int i = 1; i < size; i++) {
            Node v = nodes.get(i);
            int vf = v.getF(); int vc = v.getC();
            for (int j = 0; j <= k; j++) {
                if (j > vf) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - vf] + vc);
                    continue;
                }
                dp[i][j] = dp[i - 1][j];
            }
        }

        System.out.print(dp[size - 1][k]);

        br.close();
    }

    private static Node bfs(Map<Integer, List<Integer>> graph, int st) {
        Queue<Integer> q = new LinkedList<>();
        q.add(st);
        visited[st] = true;
        int f = 1; int c = candy[st - 1];

        while(!q.isEmpty()) {
            int v = q.poll();

            for (int friend : graph.getOrDefault(v, new ArrayList<>())) {
                if (visited[friend]) continue;
                visited[friend] = true;
                q.add(friend);
                f++;
                c += candy[friend - 1];
            }
        }
        return new Node(f, c);
    }
}
