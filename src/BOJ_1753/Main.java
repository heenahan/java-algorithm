package BOJ_1753;

import java.util.*;
import java.io.*;

class Node {
    private int v;
    private int w;

    public Node(int v, int w) {
        this.v = v;
        this.w = w;
    }

    public int getV() {
        return this.v;
    }

    public int getW() {
        return this.w;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] ve = br.readLine().split(" ");
        int v = Integer.parseInt(ve[0]);
        int e = Integer.parseInt(ve[1]);
        int k = Integer.parseInt(br.readLine());

        Map<Integer, List<Node>> g = new HashMap<>();
        for (int i = 1; i <= v; i++) {
            g.put(i, new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            String[] uvw = br.readLine().split(" ");
            int u = Integer.parseInt(uvw[0]);
            int vv = Integer.parseInt(uvw[1]);
            int w = Integer.parseInt(uvw[2]);
            g.get(u).add(new Node(vv, w));
        }

        int[] visited = new int[v + 1];
        Arrays.fill(visited, Integer.MAX_VALUE);
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparing(Node::getW));
        q.add(new Node(k, 0));
        visited[k] = 0;

        while (!q.isEmpty()) {
            Node n = q.poll();
            int nv = n.getV(); int nw = n.getW();

            for (Node next : g.get(nv)) {
                int vv = next.getV();
                int vw = nw + next.getW();
                if (visited[vv] > vw) {
                    q.add(new Node(vv, vw));
                    visited[vv] = vw;
                }
            }
        }
        for (int i = 1; i <= v; i++) {
            if (visited[i] == Integer.MAX_VALUE) {
                bw.write("INF\n");
                continue;
            }
            bw.write(String.valueOf(visited[i]));
            bw.newLine();
        }
        br.close(); bw.close();
    }
}
