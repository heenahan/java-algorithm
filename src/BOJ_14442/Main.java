package BOJ_14442;

import java.util.*;
import java.io.*;

class XY {
    private int x;
    private int y;
    private int w;

    public XY(int x, int y, int w) {
        this.x = x;
        this.y = y;
        this.w = w;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getW() {
        return this.w;
    }
}

public class Main {

    private static int[][][] visited;
    private static int[] dx = { 0, 1, 0, -1 };
    private static int[] dy = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmk = br.readLine().split(" ");
        int n = Integer.parseInt(nmk[0]);
        int m = Integer.parseInt(nmk[1]);
        int k = Integer.parseInt(nmk[2]);

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        // visited[n][m][k] k개의 벽을 부수고 (n, m)으로 갈때 최단 거리
        visited = new int[n][m][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }

        bfs(map, n, m, k);

        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= k; i++) {
            min = Math.min(min, visited[n - 1][m - 1][i]);
        }

        if (min == Integer.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(min);
        }

        br.close();
    }

    private static void bfs(int[][] map, int n, int m, int k) {
        Queue<XY> q = new LinkedList<>();
        q.add(new XY(0, 0, 0));
        visited[0][0][0] = 1;

        while(!q.isEmpty()) {
            XY v = q.poll();
            int x = v.getX(); int y = v.getY();
            int w = v.getW();

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                int nw = w + map[nx][ny];
                // 부순 벽의 횟수가 k를 넘거나 이미 방문했다면
                if (nw > k || visited[nx][ny][nw] != Integer.MAX_VALUE) continue;
                q.add(new XY(nx, ny, nw));
                visited[nx][ny][nw] = visited[x][y][w] + 1;
            }
        }
    }
}
