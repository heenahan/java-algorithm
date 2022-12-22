package BOJ_3190;

import java.io.*;

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

    int[][] sneak;
    Node head; Node tail;
    int d = 0;
    int[] dx = { 0, 1, 0, -1 }; // 우 하 좌 상
    int[] dy = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        boolean[][] apple = new boolean[n][n];
        T.setSneak(n);

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            apple[x - 1][y - 1] = true;
        }

        int[] move = new int[10001];
        int l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            if (input[1].equals("L")) move[x] = -1; // 왼쪽
            else move[x] = 1; // 오른쪽
        }

        System.out.println(T.solution(apple, move, n));

        br.close();
    }

    public void setSneak(int n) {
        this.sneak = new int[n][n];
        sneak[0][0] = 1;
        head = new Node(0, 0);
        tail = new Node(0, 0);
    }

    public int solution(boolean[][] apple, int[] move, int n) {
        for (int i = 1; i <= 10000; i++) {
            int x = head.getX();
            int y = head.getY();

            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n) return i; // 벽에 부딪히면 종료
            if (sneak[nx][ny] > 0) return i; // 몸에 부딪히면 종료

            sneak[nx][ny] = sneak[x][y] + 1; // 다음 머리 위치에 이전 머리 위치 + 1한 값 대입
            head = new Node(nx, ny); // 머리 교체

            if (!apple[nx][ny]) { // 다음 위치에 사과 없음 -> 다음 꼬리 찾아 이동
                int tx = tail.getX();
                int ty = tail.getY();
                int next = sneak[tx][ty] + 1;
                for (int j = 0; j < 4; j++) {
                    int ntx = tx + dx[j];
                    int nty = ty + dy[j];
                    if (ntx < 0 || nty < 0 || ntx >= n || nty >= n) continue;
                    if (sneak[ntx][nty] == next) tail = new Node(ntx, nty); // 꼬리 교체
                }
                sneak[tx][ty] = 0; // 이전 꼬리 0
            } else apple[nx][ny] = false; // 다음 위치에 사과 있음 -> 꼬리 가만히, 사과 삭제

            d = (d + move[i] + 4) % 4; // 초가 끝난 뒤에 회전
        }
        return -1;
    }
}
