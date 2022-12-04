package BOJ_2447;

import java.io.*;

public class Main {

    char[][] draw;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        T.setDraw(num);
        T.solution(1, num, 1, num, num);
        T.print(num);

        br.close();
        bw.close();
    }

    public void setDraw(int num) {
        draw = new char[num][num];
    }

    public void print(int num) throws IOException {
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (draw[i][j] != '*') bw.write(" ");
                else bw.write(draw[i][j]);
            }
            bw.newLine();
        }
    }

    public void solution(int start_row, int end_row, int start_col, int end_col, int num) {
        if (start_row == end_row && start_col == end_col) {
            draw[start_row - 1][start_col - 1] = '*';
            return;
        }
        int row = (end_row - start_row) / 3 + 1;
        int col = (end_col - start_col) / 3 + 1;
        /*
         * 1 2 3
         * 4   5
         * 6 7 8
         */
        solution(start_row, start_row + row - 1, start_col, start_col + col - 1, num); // 1
        solution(start_row, start_row + row - 1, start_col + col, end_col - col, num); // 2
        solution(start_row, start_row + row - 1, end_col - col + 1, end_col, num); // 3

        solution(start_row + row, end_row - row, start_col, start_col + col - 1, num); // 4
        solution(start_row + row, end_row - row, end_col - col + 1, end_col, num); // 5

        solution(end_row - row + 1, end_row, start_col, start_col + col - 1, num); // 6
        solution(end_row - row + 1, end_row, start_col + col, end_col - col, num); // 7
        solution(end_row - row + 1, end_row, end_col - col + 1, end_col, num); // 8
    }
}
