package 최소_비행료;

import java.util.*;

class Node {
    private int v;
    private int cost;
    private int transfer;

    public Node(int v, int cost, int transfer) {
        this.v = v;
        this.cost = cost;
        this.transfer = transfer;
    }

    public int getV() {
        return this.v;
    }

    public int getCost() {
        return this.cost;
    }

    public int getTransfer() {
        return this.transfer;
    }
}

class Solution {

    private int[] visited;

    public int solution(int n, int[][] flights, int s, int e, int k){
        visited = new int[n];
        Arrays.fill(visited, Integer.MAX_VALUE);
        Map<Integer, List<Node>> graph = new HashMap<>();
        for (int[] flight : flights) {
            graph.putIfAbsent(flight[0], new ArrayList<>());
            List<Node> list = graph.get(flight[0]);
            list.add(new Node(flight[1], flight[2], 0));
        }
        dijkstra(graph, s, e, k);
        if (visited[e] == Integer.MAX_VALUE) {
            return -1;
        }
        return visited[e];
    }

    public void dijkstra(Map<Integer, List<Node>> graph, int s, int e, int k) {
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparing(Node::getCost));
        q.add(new Node(s, 0, -1));
        visited[s] = 0;

        while (!q.isEmpty()) {
            Node v = q.poll();
            int vv = v.getV();
            int vcost = v.getCost();
            int vtransfer = v.getTransfer();

            List<Node> nodes = graph.getOrDefault(vv, new ArrayList<>());
            for (Node node : nodes) {
                int nv = node.getV();
                int ncost = vcost + node.getCost();
                int ntransfer = vtransfer + 1;
                // 지금까지 구한 cost보다 작고 최대 환승 횟수 이하라면
                if (visited[nv] > ncost && ntransfer <= k) {
                    q.add(new Node(nv, ncost, ntransfer));
                    visited[nv] = ncost;
                }
            }
        }
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(5, new int[][]{{0, 1, 10}, {1, 2, 20}, {0, 2, 70}, {0, 3, 100}, {1, 3, 80}, {2, 3, 10}, {2, 4, 30}, {3, 4, 10}}, 0, 3, 1));
        System.out.println(T.solution(4, new int[][]{{0, 1, 10}, {0, 2, 10}, {1, 3, 5}, {2, 3, 3}}, 0, 3, 0));
        System.out.println(T.solution(8, new int[][]{{0, 3, 10}, {1, 5, 10}, {1, 7, 100}, {0, 1, 10}, {0, 2, 10}, {5, 7, 30}, {3, 7, 10}, {1, 3, 5}, {2, 3, 3}}, 1, 7, 2));
        System.out.println(T.solution(10, new int[][]{{1, 8, 50}, {0, 8, 30}, {1, 0, 10}, {2, 8, 10}, {0, 3, 10}, {1, 5, 10}, {1, 7, 100}, {0, 1, 10}, {0, 2, 10}, {5, 7, 30}, {3, 7, 10}, {1, 3, 5}, {2, 3, 3}}, 1, 8, 2));
        System.out.println(T.solution(4, new int[][]{{0, 3, 59},{2, 0, 83}, {3, 1, 16}, {1, 3, 16}}, 3, 0, 3));
    }
}