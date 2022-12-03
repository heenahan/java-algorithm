package BOJ_1926;

import java.io.*;
import java.util.Queue;
import java.util.LinkedList;

class Node {

    private int n;
    private int m;

    public Node(int n, int m) {
        this.n = n;
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

}

public class Main {

    boolean[][] visited;
    int[][] papers;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] num = br.readLine().split(" ");
        int n = Integer.parseInt(num[0]); // 세로
        int m = Integer.parseInt(num[1]); // 가로

        bw.write(T.solution(n, m, br));

        br.close();
        bw.close();

    }

    public String solution(int n, int m, BufferedReader br) throws IOException {

        papers = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < m; j++) papers[i][j] = Integer.parseInt(input[j]);
        }

        int pictures = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (papers[i][j] == 1 && !visited[i][j]) {
                    int nums = bfs(n, m, i, j);
                    if (nums > max) max = nums;
                    pictures++;
                }
            }
        }

        return pictures + "\n" + max;
    }

    private int bfs(int n, int m, int start_n, int start_m) {
        Queue<Node> q = new LinkedList<>();
        Node node = new Node(start_n, start_m);
        q.add(node);
        visited[start_n][start_m] = true;
        int count = 0;

        // 큐가 빌 때까지 탐색
        while (q.size() > 0) {
            Node v = q.poll();
            int vn = v.getN();
            int vm = v.getM();

            // 위쪽 탐색 vn - 1, vm
            if (vn > 0 && papers[vn - 1][vm] == 1 && !visited[vn - 1][vm]) {
                Node up = new Node(vn - 1, vm);
                q.add(up);
                visited[vn - 1][vm] = true;
            }
            // 아래쪽 탐색 vn + 1, vm
            if (vn < n - 1 && papers[vn + 1][vm] == 1 && !visited[vn + 1][vm]) {
                Node down = new Node(vn + 1, vm);
                q.add(down);
                visited[vn + 1][vm] = true;
            }
            // 왼쪽 탐색 vn, vm - 1
            if (vm > 0 && papers[vn][vm - 1] == 1 && !visited[vn][vm - 1]) {
                Node left = new Node(vn, vm - 1);
                q.add(left);
                visited[vn][vm - 1] = true;
            }
            // 오른쪽 탐색 vn, vm + 1
            if (vm < m - 1 && papers[vn][vm + 1] == 1 && !visited[vn][vm + 1]) {
                Node right = new Node(vn, vm + 1);
                q.add(right);
                visited[vn][vm + 1] = true;
            }

            count++;
        }

        return count;
    }

}
