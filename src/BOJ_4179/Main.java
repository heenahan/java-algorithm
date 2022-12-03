package BOJ_4179;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

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

    Node jihoon;
    int[][] fire_bfs;
    int[][] jihoon_bfs;
    int[] dx = { 1, 0, -1, 0 };
    int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int r = Integer.parseInt(input[0]);
        int c = Integer.parseInt(input[1]);

        char[][] maze = new char[r][c];
        T.setFire(r, c);
        Queue<Node> q = new LinkedList<>(); // 불의 위치 모두 담아둔다.

        for (int i = 0; i < r; i++) {
            char[] row = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                maze[i][j] = row[j];
                if (maze[i][j] == 'F') {
                    Node node = new Node(i, j);
                    q.add(node);
                }
                if (maze[i][j] == 'J') T.setJihoon(r, c, i, j);
            }
        }

        int answer = T.solution(q, maze, r, c);

        if (answer == -1) System.out.println("IMPOSSIBLE");
        else System.out.println(answer);

        br.close();
    }

    public void setFire(int r, int c) {
        fire_bfs = new int[r][c];
    }

    public void setJihoon(int r, int c, int i, int j) {
        jihoon = new Node(i, j);
        jihoon_bfs = new int[r][c];
    }

    public int solution(Queue<Node> q, char[][] maze, int r, int c) {
        while (!q.isEmpty()) {
            Node v = q.poll();
            int x = v.getX();
            int y = v.getY();

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                // 0보다 크면 방문 했었음. .만 방문하면 처음 화재난 곳, 치훈의 처음 위치, 벽 모두 방문 안함
                if (fire_bfs[nx][ny] > 0 || maze[nx][ny] != '.') continue;
                Node next = new Node(nx, ny);
                fire_bfs[nx][ny] = fire_bfs[x][y] + 1;
                q.add(next);
            }
        }

        q.add(jihoon);

        while (!q.isEmpty()) {
            Node v = q.poll();
            int x = v.getX();
            int y = v.getY();

            // 가장 자리면 탈출 성공
            if (x == 0 || y == 0 || x == r - 1 || y == c - 1) return jihoon_bfs[x][y] + 1;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                // 0보다 크면 이미 방문한 곳이므로 방문 안함. .만 방문하면 처음 지훈의 위치, 처음 화재난 곳, 벽 모두 방문 안함
                if (jihoon_bfs[nx][ny] > 0 || maze[nx][ny] != '.') continue;
                // 현재 혹은 이전에 불이 번졌던 곳은 가지 못한다.
                if (jihoon_bfs[x][y] + 1 >= fire_bfs[nx][ny] && fire_bfs[nx][ny] > 0) continue;

                Node next = new Node(nx, ny);
                jihoon_bfs[nx][ny] = jihoon_bfs[x][y] + 1;
                q.add(next);
            }
        }

        return -1;
    }
}
