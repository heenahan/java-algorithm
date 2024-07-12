package BOJ_1941;
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

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof XY)) {
            return false;
        }

        XY xy = (XY) obj;
        return this.x == xy.x && this.y == xy.y;
    }
}

public class Main {

    private static int answer = 0;
    private static boolean[][] visited = new boolean[5][5];
    private static int[] dx = { 0, 1, 0, -1 };
    private static int[] dy = { 1, 0, -1, 0 };
    private static Set<Set<XY>> group = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[][] map = new String[5][5];
        for (int i = 0; i < 5; i++) {
            map[i] = br.readLine().split("");
        }

        backTracking(map, 0, 0);

        System.out.print(answer);

        br.close();
    }

    private static void backTracking(String[][] map, int depth, int idy) {
        if (depth == 7) {
            Set<XY> g = new HashSet<>();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (visited[i][j]) {
                        g.add(new XY(i, j));
                    }
                }
            }
            if (!group.contains(g)) {
                answer++;
                group.add(g);
            }
            return;
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                // 이미 방문한 곳은 pass
                if (visited[i][j]) continue;
                boolean isNext = depth == 0;
                if (depth > 0) {
                    // 인접된 곳이라면 true
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k]; int ny = j + dy[k];
                        if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
                        if (visited[nx][ny]) {
                            isNext = true;
                            break;
                        }
                    }
                }
                // 방문하면 임도연파가 4이상이 될 때
                if (Objects.equals(map[i][j], "Y") && idy + 1 >= 4) {
                    continue;
                }
                if (isNext) {
                    visited[i][j] = true;
                    if (Objects.equals(map[i][j], "Y")) backTracking(map, depth + 1, idy + 1);
                    else backTracking(map, depth + 1, idy);
                    visited[i][j] = false;
                }
            }
        }
    }
}