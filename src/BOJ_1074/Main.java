package BOJ_1074;

import java.io.*;

public class Main {

    int r, c;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        T.setR(Integer.parseInt(input[1]));
        T.setC(Integer.parseInt(input[2]));
        int size = 1;
        for (int i = 0; i < n; i++) size *= 2;
        bw.write(T.solution(1, size, 1, size, size * size).toString());

        br.close();
        bw.close();
    }

    public void setR(int r) {
        this.r = r + 1;
    }

    public void setC(int c) {
        this.c = c + 1;
    }

    public Integer solution(int start_row, int end_row, int start_col, int end_col, int visited) {
        if ((start_row == end_row) && (start_col == end_col)) {
            return visited - 1;
        }
        int mid_row = (start_row + end_row) / 2;
        int mid_col = (start_col + end_col) / 2;
        int size = (end_row - start_row + 1) * (end_col - start_col + 1) / 4;
        if (r <= mid_row) {
            if (c <= mid_col) { // 1번에 존재
                return solution(start_row, mid_row, start_col, mid_col, visited - size * 3);
            } else { // 2번에 존재
                return solution(start_row, mid_row, mid_col + 1, end_col, visited - size * 2);
            }
        } else {
            if (c <= mid_col) { // 3번에 존재
                return solution(mid_row + 1, end_row, start_col, mid_col, visited - size * 1);
            } else{ // 4번에 존재
                return solution(mid_row + 1, end_row, mid_col + 1, end_col, visited);
            }
        }
    }

}
