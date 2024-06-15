package SOF_나무_섭지;

import java.io.*;
import java.util.*;

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

    private static int[] dx = { 1, 0, -1, 0 };
    private static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        String[][] map = new String[n][m];
        Queue<XY> ghost = new LinkedList<>();
        Queue<XY> namwoo = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = row[j];
                if (Objects.equals(map[i][j], "G")) ghost.add(new XY(i, j));
                if (Objects.equals(map[i][j], "N")) namwoo.add(new XY(i, j));
            }
        }
        int[][] ghostMove = new int[n][m];
        // 유령 bfs
        while (!ghost.isEmpty()) {
            XY v = ghost.poll();
            int vx = v.getX(); int vy = v.getY();

            for (int i = 0; i < 4; i++) {
                int nx = vx + dx[i]; int ny = vy + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (ghostMove[nx][ny] > 0) continue; // 이미 방문
                ghostMove[nx][ny] = ghostMove[vx][vy] + 1;
                ghost.add(new XY(nx, ny));
            }
        }
        int[][] namwooMove = new int[n][m];
        String answer = "No";
        // 남우 bfs
        while (!namwoo.isEmpty()) {
            XY v = namwoo.poll();
            int vx = v.getX(); int vy = v.getY();

            if (Objects.equals(map[vx][vy], "D")) {
                boolean isGhost = false;
                for (int i = 0; i < 4; i++) {
                    int nx = vx + dx[i]; int ny = vy + dy[i];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                    if (ghostMove[nx][ny] == namwooMove[vx][vy] - 1) isGhost = true;
                }
                if (!isGhost) answer = "Yes";
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = vx + dx[i]; int ny = vy + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                // 이미 방문 혹은 벽이면 방문 안함
                if (namwooMove[nx][ny] > 0 || Objects.equals(map[nx][ny], "#")) continue;
                // 현재 유령에 둘러싸여 있으면
                if (ghostMove[nx][ny] <= namwooMove[vx][vy]) continue;
                namwooMove[nx][ny] = namwooMove[vx][vy] + 1;
                namwoo.add(new XY(nx, ny));
            }
        }

        System.out.print(answer);

        br.close();
    }
}

