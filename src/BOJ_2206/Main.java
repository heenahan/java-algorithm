package BOJ_2206;

import java.io.*;
import java.util.Queue;
import java.util.LinkedList;

class Node {
    private int x;
    private int y;
    private boolean crush;

    public Node(int x, int y, boolean crush) {
        this.x = x;
        this.y = y;
        this.crush = crush;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isCrush() {
        return crush;
    }
}

public class Main {

    int[][] wall;
    int[][] road;
    int[] dx = { 1, 0, -1, 0 };
    int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input1 = br.readLine().split(" ");
        int n = Integer.parseInt(input1[0]);
        int m = Integer.parseInt(input1[1]);
        int[][] maze = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] input2 = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) maze[i][j] = input2[j] - 48;
        }
        System.out.println(T.solution(n, m, maze));

        br.close();
    }

    public int solution(int n, int m, int[][] maze) {
        wall = new int[n][m];
        road = new int[n][m];

        Queue<Node> q = new LinkedList<>();
        Node start = new Node(0, 0, false);
        road[0][0] = 1;
        q.add(start);

        while(!q.isEmpty()) {
            Node v = q.poll();
            int x = v.getX();
            int y = v.getY();
            boolean crush = v.isCrush();

            if (x == n - 1 && y == m - 1) {
                if (crush) return wall[x][y];
                else return road[x][y];
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (crush && maze[nx][ny] == 1) continue; // 벽을 부순 상태고 벽이라면 방문 안함
                if (maze[nx][ny] == 1) { // 벽이라면
                    if (wall[nx][ny] > 0) continue; // 이미 방문했으면 종료
                    Node next = new Node(nx, ny, true);
                    wall[nx][ny] = road[x][y] + 1;
                    q.add(next);
                } else { // 벽이 아니고
                    if ((crush && wall[nx][ny] > 0) || (!crush && road[nx][ny] > 0)) continue;
                    Node next = new Node(nx, ny, crush);
                    if (crush) wall[nx][ny] = wall[x][y] + 1; // 벽을 부순 상태
                    else road[nx][ny] = road[x][y] + 1; // 벽을 부수지 않은 상태
                    q.add(next);
                }
            }
        }
        return -1;
    }

}
