package SOF_함께하는_효도;

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

    public void updateXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = { 0, -1, 0, 1 };
    private static int[] dy = { -1, 0, 1, 0 };
    private static int n;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        map = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String[] low = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(low[j]);
            }
        }
        List<XY> people = new ArrayList<>();
        // 처음 위치는 모두 먹음
        int sum = 0;
        for (int i = 0; i < m; i++) {
            String[] person = br.readLine().split(" ");
            int x = Integer.parseInt(person[0]) - 1;
            int y = Integer.parseInt(person[1]) - 1;
            people.add(new XY(x, y));
            sum += map[x][y];
            visited[x][y] = true;
        }
        backTracking(0, 0, people, sum);

        System.out.println(max);

        br.close();
    }

    private static void backTracking(int depth, int person, List<XY> people, int sum) {
        if (depth == 3) { // 3초 지나면 종료
            max = Math.max(max, sum);
            return;
        }
        if (person == people.size()) { // 사람 수만큼 돌았으면 1초 늘림
            backTracking(depth + 1, 0, people, sum);
            return;
        }
        XY p = people.get(person);
        int vx = p.getX();
        int vy = p.getY();
        // 4방향 모두 탐색
        for (int i = 0; i < 4; i++) {
            int nx = vx + dx[i]; int ny = vy + dy[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if (visited[nx][ny]) continue; // 이미 방문했으면 먹지 않음
            sum += map[nx][ny];
            visited[nx][ny] = true;
            p.updateXY(nx, ny); // 사람 이동
            backTracking(depth, person + 1, people, sum);
            sum -= map[nx][ny];
            visited[nx][ny] = false;
            p.updateXY(vx, vy);
        }
    }
}
