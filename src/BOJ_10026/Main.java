package BOJ_10026;

import java.util.Queue;
import java.util.LinkedList;
import java.io.*;

class Node {
    int x; int y;

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

    boolean[][] visited;
    int[] dx = { 1, 0, -1, 0 };
    int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) grid[i][j] = input[j];
        }

        System.out.println(T.solution(grid, n));

        br.close();
    }

    public String solution(char[][] grid, int n) {
        visited = new boolean[n][n];
        int no = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    bfs(grid, i, j, n);
                    no++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'R') grid[i][j] = 'G';
            }
        }
        visited = new boolean[n][n];
        int yes = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    bfs(grid, i, j, n);
                    yes++;
                }
            }
        }
        return no + " " + yes;
    }

    public void bfs(char[][] grid, int start_x, int start_y, int n) {
        Queue<Node> q = new LinkedList<>();
        Node node = new Node(start_x, start_y);
        visited[start_x][start_y] = true;
        q.add(node);

        while (!q.isEmpty()) {
            Node v = q.poll();
            int x = v.getX();
            int y = v.getY();

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (visited[nx][ny] || grid[x][y] != grid[nx][ny]) continue;

                Node next = new Node(nx, ny);
                visited[nx][ny] = true;
                q.add(next);
            }
        }
    }
}
