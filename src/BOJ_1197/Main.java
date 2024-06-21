package BOJ_1197;

import java.io.*;
import java.util.*;

class Node {
    private int st;
    private int ed;
    private int w;

    public Node(int st, int ed, int w) {
        this.st = st;
        this.ed = ed;
        this.w = w;
    }

    public int getSt() {
        return this.st;
    }

    public int getEd() {
        return this.ed;
    }

    public int getW() {
        return this.w;
    }
}

public class Main {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] ve = br.readLine().split(" ");
        int v = Integer.parseInt(ve[0]);
        int e = Integer.parseInt(ve[1]);

        // 부모 노드를 넣음
        parent = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }
        // 가중치가 작은 순서대로 넣음
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparing(Node::getW));
        for (int i = 0; i < e; i++) {
            String[] abc = br.readLine().split(" ");
            int a = Integer.parseInt(abc[0]);
            int b = Integer.parseInt(abc[1]);
            int c = Integer.parseInt(abc[2]);
            q.add(new Node(a, b, c));
        }

        int cnt = 0; int weight = 0;
        // 사이클 없고 모든 노드를 연결하는 것 간선 수 == 노드수 - 1
        while (cnt < v - 1) {
            Node n = q.poll();
            int st = n.getSt(); int ed = n.getEd();
            int w = n.getW();

            int stParent = getParent(st);
            int edParent = getParent(ed);
            // 부모가 같으면 사이클 생김
            if (stParent == edParent) {
                continue;
            }
            // 부모 합침
            if (stParent < edParent) parent[stParent] = edParent;
            else parent[edParent] = stParent;
            cnt++; weight += w;
        }
        System.out.println(weight);

        br.close();
    }

    private static int getParent(int v) {
        if (parent[v] == v) return v;
        return parent[v] = getParent(parent[v]);
    }
}