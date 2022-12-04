package BOJ_7562;

import java.io.*;
import java.util.Queue;
import java.util.LinkedList;

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

    int[] dx = { -1, -2, -2, -1, 1, 2, 2, 1 };
    int[] dy = { -2, -1, 1, 2, 2, 1, -1, -2 };

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine());
        for (int i = 0; i < test; i++) {
            int size = Integer.parseInt(br.readLine());
            String[] input1 = br.readLine().split(" ");
            Node chess = new Node(Integer.parseInt(input1[0]), Integer.parseInt(input1[1]));
            String[] input2 = br.readLine().split(" ");
            Node goal = new Node(Integer.parseInt(input2[0]), Integer.parseInt(input2[1]));

            System.out.println(T.solution(size, chess, goal));
        }

        br.close();
    }

    public int solution(int size, Node chess, Node goal) {
        int[][] board = new int[size][size];
        Queue<Node> q = new LinkedList<>();
        q.add(chess);

        while (!q.isEmpty()) {
            Node v = q.poll();
            int x = v.getX();
            int y = v.getY();

            if (x == goal.getX() && y == goal.getY()) return board[x][y];

            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= size || ny >= size) continue;
                if (nx == chess.getX() && ny == chess.getY()) continue;
                if (board[nx][ny] > 0) continue;

                Node next = new Node(nx, ny);
                board[nx][ny] = board[x][y] + 1;
                q.add(next);
            }
        }

        return 0;
    }
}
