package BOJ_14499;

import java.io.*;

public class Main {

    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int[] dice = new int[7]; // 주사위에 쓰여진 숫자
    int up = 1; // 윗면
    int down = 6; // 밑면
    int[] four = { 3, 4, 2, 5 }; // 오 왼 위 아래
    int[] dx = { 0, 0, -1, 1 };
    int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input1 = br.readLine().split(" ");
        int n = Integer.parseInt(input1[0]);
        int m = Integer.parseInt(input1[1]);
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] input2 = br.readLine().split(" ");
            for (int j = 0; j < m; j++) map[i][j] = Integer.parseInt(input2[j]);
        }

        int start_n = Integer.parseInt(input1[2]);
        int start_m = Integer.parseInt(input1[3]);

        int num = Integer.parseInt(input1[4]);
        String[] input3 = br.readLine().split(" ");
        int[] moves = new int[num];
        for (int i = 0; i < num; i++) moves[i] = Integer.parseInt(input3[i]);

        T.solution(n, m, start_n, start_m, map, moves);

        br.close();
    }

    public void solution(int n, int m, int start_n, int start_m, int[][] map, int[] moves) throws IOException {
        int x = start_n;
        int y = start_m;
        for (int move : moves) {
            int nx = x + dx[move - 1];
            int ny = y + dy[move - 1];
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue; // 범위 벗어남
            int temp_up = up;
            int temp_down = down;
            down = four[move - 1];
            four[move - 1] = temp_up;
            if (move % 2 == 0) { // 짝수일 경우 왼쪽, 아래 이동
                up = four[move - 2]; // 왼쪽일경우 오른쪽면 -> 위쪽면
                four[move - 2] = temp_down; // 왼쪽일경우 아랫면 -> 오른쪽면
            } else { // 홀수일 경우 오른쪽, 위 이동
                up = four[move]; // 오른쪽일경우 왼쪽 -> 위쪽
                four[move] = temp_down; // 아랫면 -> 왼쪽
            }
            if (map[nx][ny] == 0) map[nx][ny] = dice[down]; // 칸이 0이면 주사위값 넣음
            else {
                dice[down] = map[nx][ny];
                map[nx][ny] = 0;
            }
            bw.write(dice[up] + "\n");
            x = nx;
            y = ny;
        }
        bw.close();
    }
}
