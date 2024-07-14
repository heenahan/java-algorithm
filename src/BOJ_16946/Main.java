package BOJ_16946;

import java.util.*;
import java.io.*;

class XY {
    private int x;
    private int y;

    public XY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}

public class Main {

    private static int[][] group;
    private static Map<Integer, Integer> g = new HashMap<>();
    private static int[] dx = { 0, 1, 0, -1 };
    private static int[] dy = { 1, 0, -1, 0 };
    private static int n;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        int[][] map = new int[n][m];
        group = new int[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        }
        int num = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0 && group[i][j] == 0) {
                    bfs(map, i, j, num++);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    bw.write("0");
                    continue;
                }
                int answer = 1;
                boolean[] visited = new boolean[num + 1];
                for (int k = 0; k < 4; k++) {
                    int ni = i + dx[k]; int nj = j + dy[k];
                    if (ni < 0 || ni >= n || nj < 0 || nj >= m) continue;
                    if (group[ni][nj] == 0 || visited[group[ni][nj]]) continue;
                    answer += g.get(group[ni][nj]);
                    answer %= 10;
                    visited[group[ni][nj]] = true;
                }
                bw.write(String.valueOf(answer));
            }
            bw.newLine();
        }

        br.close(); bw.close();
    }

    private static void bfs(int[][] map, int x, int y, int num) {
        Queue<XY> q = new LinkedList<>();
        q.add(new XY(x, y)); group[x][y] = num;
        int cnt = 0;

        while(!q.isEmpty()) {
            XY v = q.poll();
            int vx = v.getX(); int vy = v.getY();

            cnt++;

            for (int i = 0; i < 4; i++) {
                int nx = vx + dx[i]; int ny = vy + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                // 이미 속해있다면
                if (map[nx][ny] != 0 || group[nx][ny] > 0) continue;
                q.add(new XY(nx, ny));
                group[nx][ny] = num;
            }
        }
        g.put(num, cnt);
    }
}