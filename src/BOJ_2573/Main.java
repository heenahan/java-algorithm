package BOJ_2573;

import java.io.*;
import java.util.Queue;
import java.util.LinkedList;

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
    int[][] melted;
    int[] dx = { 1, 0, -1, 0 };
    int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input1 = br.readLine().split(" ");
        int n = Integer.parseInt(input1[0]);
        int m = Integer.parseInt(input1[1]);
        int[][] ice = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] input2 = br.readLine().split(" ");
            for (int j = 0; j < m; j++) ice[i][j] = Integer.parseInt(input2[j]);
        }

        System.out.println(T.solution(ice, n, m));

        br.close();
    }

    public int solution(int[][] ice, int n, int m) {
        int time = 0;
        while (true) {
            int num = 0;
            melted = new int[n][m];
            visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (ice[i][j] > 0 && !visited[i][j]) {
                        dfs(ice, n, m, i, j);
                        num++;
                    }
                }
            }
            if (num > 1) break; // 두덩어리로 분리되면 종료
            else if (num < 1) { // 모두 녹으면 0 출력
                time = 0;
                break;
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) ice[i][j] = melted[i][j];
            }
            time++;
        }
        return time;
    }

    public void dfs(int[][] ice, int n, int m, int start_n, int start_m) {
        Queue<Node> q = new LinkedList<>();
        Node start = new Node(start_n, start_m);
        visited[start_n][start_m] = true;
        q.add(start);

        while (!q.isEmpty()) {
            Node v = q.poll();
            int x = v.getX();
            int y = v.getY();

            int water = 0;
            for (int i = 0; i < 4; i++) {
                // 가장자리는 항상 물
                if (ice[x + dx[i]][y + dy[i]] == 0) water++;
            }
            melted[x][y] = ice[x][y] - water;
            if (melted[x][y] < 0) melted[x][y] = 0;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visited[nx][ny] || ice[nx][ny] == 0) continue;

                Node next = new Node(nx, ny);
                visited[nx][ny] = true;
                q.add(next);
            }
        }
    }
}
