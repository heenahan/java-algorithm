package PGS_경주로_건설;

import java.util.*;

// 3차원을 이용한 다익스트라 신기하다

class Car {
    private int x;
    private int y;
    private int price;
    private int dir;

    public Car(int x, int y, int price, int dir) {
        this.x = x;
        this.y = y;
        this.price = price;
        this.dir = dir;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getPrice() {
        return this.price;
    }

    public int getDir() {
        return this.dir;
    }
}

public class Solution {

    private int[][][] visited;
    private int[] dx = { 0, 1, 0, -1 }; // 오 아 왼 위
    private int[] dy = { 1, 0, -1, 0 };
    private int height;
    private int width;

    public int solution(int[][] board) {
        height = board.length;
        width = board[0].length;
        visited = new int[height][width][4];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }
        dijkstra(board);
        return Math.min(Math.min(visited[height-1][width-1][0], visited[height-1][width-1][1]),
            Math.min(visited[height-1][width-1][2], visited[height-1][width-1][3]));
    }

    public void dijkstra(int[][] board) {
        // 비용이 제일 싼 순서대로 정렬
        PriorityQueue<Car> q = new PriorityQueue<>(Comparator.comparing(Car::getPrice));

        for (int i = 0; i < 4; i++) {
            visited[0][0][i] = 0;
            q.add(new Car(0, 0, 0, i));
        }

        while (!q.isEmpty()) {
            Car v = q.poll();
            int vx = v.getX(); int vy = v.getY();
            int vprice = v.getPrice();
            int vd = v.getDir();

            for (int i = 0; i < 4; i++) {
                int nx = vx + dx[i]; int ny = vy + dy[i];
                if (nx < 0 || nx >= height || ny < 0 || ny >= width) continue;
                if (board[nx][ny] == 1) continue; // 벽이면 방문 안함
                int nprice = vprice + 100;
                // 방향 불일치시 꺾음
                if (vd != i) {
                    nprice += 500;
                }
                // 지금까지 구한 값보다 같거나 작으면 방문 (방향이 어디냐에 따라 비용이 작아질 수 있으므로 같아도 저장)
                if (visited[nx][ny][i] > nprice) {
                    visited[nx][ny][i] = nprice;
                    q.add(new Car(nx, ny, nprice, i));
                }
            }
        }
    }
}