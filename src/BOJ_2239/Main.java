package BOJ_2239;

import java.util.*;
import java.io.*;

public class Main {

    private static int[][] answer = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] sudoku = new int[9][9];
        for (int i = 0; i < 9; i++) {
            sudoku[i] = Arrays.stream(br.readLine().split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        solve(sudoku, 0, 0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                bw.write(String.valueOf(answer[i][j]));
            }
            bw.newLine();
        }

        br.close(); bw.close();
    }

    private static void solve(int[][] sudoku, int x, int y) {
        // 모두 돌았으면 종료
        if (x == 9 && y == 0) {
            if (answer[0][0] <= 0) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        answer[i][j] = sudoku[i][j];
                    }
                }
            }
            return;
        }
        // 행 증가
        if (y == 9) {
            x++; y = 0;
            solve(sudoku, x, y);
            return;
        }
        // 이미 푼 스도쿠라면 skip
        if (sudoku[x][y] > 0) {
            solve(sudoku, x, y + 1);
            return;
        }
        // 1부터 9의 숫자 중 들어갈 수 있는 것을 찾음
        for (int i = 1; i <= 9; i++) {
            boolean notSolve = false;
            for (int j = 0; j < 9; j++) {
                if (sudoku[x][j] == i || sudoku[j][y] == i) {
                    notSolve = true;
                    break;
                }
            }
            int stx = 3 * (x / 3); int sty = 3 * (y / 3);
            for (int j = stx; j < stx + 3; j++) {
                for (int k = sty; k < sty + 3; k++) {
                    if (sudoku[j][k] == i) {
                        notSolve = true;
                    }
                }
            }
            if (!notSolve) {
                sudoku[x][y] = i;
                // 계산했으면 빠져나옴
                if (answer[0][0] > 0) {
                    break;
                }
                solve(sudoku, x, y + 1);
                sudoku[x][y] = 0;
            }
        }
    }
}