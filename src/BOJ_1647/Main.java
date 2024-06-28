package BOJ_1647;

import java.io.*;
import java.util.*;

class Node {
    private int s;
    private int e;
    private int w;

    public Node(int s, int e, int w) {
        this.s = s;
        this.e = e;
        this.w = w;
    }

    public int getS() {
        return this.s;
    }

    public int getE() {
        return this.e;
    }

    public int getW() {
        return this.w;
    }
}

public class Main {

    private static int[] parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparing(Node::getW));
        for (int i = 0; i < m; i++) {
            String[] abc = br.readLine().split(" ");
            int a = Integer.parseInt(abc[0]);
            int b = Integer.parseInt(abc[1]);
            int c = Integer.parseInt(abc[2]);

            q.add(new Node(a, b, c));
        }

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        int weight = 0;
        int edge = 0;
        while(edge < n - 2) {
            Node v = q.poll();
            int s = v.getS(); int e = v.getE();
            int w = v.getW();

            if (isSame(s, e)) {
                continue;
            }
            union(s, e);
            weight += w;
            edge++;
        }
        System.out.print(weight);

        br.close();
    }

    private static void union(int s, int e) {
        int sParent = getParent(s);
        int eParent = getParent(e);

        if (sParent < eParent) {
            parent[eParent] = sParent;
            return;
        }
        parent[sParent] = eParent;
    }

    private static boolean isSame(int s, int e) {
        int sParent = getParent(s);
        int eParent = getParent(e);

        return sParent == eParent;
    }

    private static int getParent(int v) {
        if (parent[v] == v) {
            return v;
        }
        parent[v] = getParent(parent[v]);
        return parent[v];
    }
}

