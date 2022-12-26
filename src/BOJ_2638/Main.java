package BOJ_2638;

import java.io.*;
import java.util.Queue;
import java.util.LinkedList;

class Node {
    private int x; private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }

    public int getY() { return y; }
}

public class Main {

    // 0 : 내부공기 1: 치즈 2 : 외부공기
    int[][] melted;
    boolean[][] visited;
    int[] dx= { 1, 0, -1, 0 };
    int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input1 = br.readLine().split(" ");
        int n = Integer.parseInt(input1[0]);
        int m = Integer.parseInt(input1[1]);
        int[][] cheese = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] input2 = br.readLine().split(" ");
            for (int j = 0; j < m; j++) cheese[i][j] = Integer.parseInt(input2[j]);
        }

        System.out.println(T.solution(cheese, n, m));

        br.close();
    }

    public int solution(int[][] cheese, int n, int m) {
        visited = new boolean[n][m];
        melted = new int[n][m];
        dfs(cheese, n, m, 0, 0); // 외부공기
        int time = 0;
        while (true) {
            int count = 0;
            for (int i = 1; i < n - 1; i++) { // 치즈 dfs
                for (int j = 1; j < m - 1; j++) {
                    if (!visited[i][j] && cheese[i][j] == 1) {
                        dfs(cheese, n, m, i, j);
                        count++;
                    }
                }
            }
            for (int i = 1; i < n - 1; i++) {
                for (int j = 1; j < m - 1; j++) {
                    if (!visited[i][j] && cheese[i][j] == 0) { // 내부 공기이면
                        for (int k = 0; k < 4; k++) {
                            if (melted[i + dx[k]][j + dy[k]] == 2) {
                                dfs(cheese, n, m, i, j);
                                break;
                            }
                        }
                    }
                }
            }
            if (count == 0) break;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) cheese[i][j] = melted[i][j];
            }
            visited = new boolean[n][m]; // 방문 초기화
            time++;
        }
        return time - 1;
    }

    public void dfs(int[][] cheese, int n, int m, int start_n, int start_m) {
        Queue<Node> q = new LinkedList<>();
        Node start = new Node(start_n, start_m);
        visited[start_n][start_m] = true;
        q.add(start);

        while (!q.isEmpty()) {
            Node v = q.poll();
            int x = v.getX();
            int y = v.getY();

            if (cheese[x][y] == 1) { // 치즈이면
                int count = 0;
                for (int i = 0; i < 4; i++) {
                    if (cheese[x + dx[i]][y + dy[i]] == 2) count++;
                }
                if (count >= 2) melted[x][y] = 2; // 2면 이상 외부공기와 접촉
                else melted[x][y] = 1;
            } else melted[x][y] = 2; // 치즈가 아니면 외부공기

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visited[nx][ny] || cheese[nx][ny] != cheese[x][y]) continue;

                Node next = new Node(nx, ny);
                visited[nx][ny] = true;
                q.add(next);
            }
        }
    }

}
