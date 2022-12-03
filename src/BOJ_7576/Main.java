package BOJ_7576;

import java.io.*;
import java.util.Queue;
import java.util.LinkedList;

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

public class Main {

    // 아래, 오른쪽, 위, 왼쪽
    int[] dx = { 1, 0, -1, 0 };
    int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int m = Integer.parseInt(input[0]);
        int n = Integer.parseInt(input[1]);
        int[][] tomato = new int[n][m];
        Queue<Node> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                tomato[i][j] = Integer.parseInt(row[j]);
                if (tomato[i][j] == 1) {
                    Node node = new Node(i, j);
                    q.add(node);
                }
            }
        }

        System.out.println(T.solution(tomato, n, m, q));

        br.close();
    }

    public int solution(int[][] tomato, int n, int m, Queue<Node> q) {
        while (!q.isEmpty()) {
            Node v = q.poll();
            int x = v.getX();
            int y = v.getY();

            for (int i = 0; i < 4; i++) {
                int vx = x + dx[i];
                int vy = y + dy[i];
                if (vx < 0 || vy < 0 || vx >= n || vy >= m) continue;
                if (tomato[vx][vy] != 0) continue; // 익지 않은 토마토일 경우만 방문
                tomato[vx][vy] = tomato[x][y] + 1;
                Node next = new Node(vx, vy);
                q.add(next);
            }
        }

        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tomato[i][j] == 0) return -1; // 익지 않은 토마토 존재 시 -1
                if (tomato[i][j] > max) max = tomato[i][j];
            }
        }

        return max - 1;
    }

}
