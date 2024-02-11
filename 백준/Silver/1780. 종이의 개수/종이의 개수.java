import java.io.*;

public class Main {

    int minus = 0;
    int zero = 0;
    int one = 0;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());
        int[][] paper = new int[num][num];
        for (int i = 0; i < num; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < num; j++) paper[i][j] = Integer.parseInt(input[j]);
        }

        T.solution(1, num, 1, num, paper);
        bw.write(T.getMinus() + "\n" + T.getZero() + "\n" + T.getOne());

        br.close();
        bw.close();
    }

    public int getMinus() {
        return minus;
    }

    public int getZero() {
        return zero;
    }

    public int getOne() {
        return one;
    }

    public Integer solution(int start_row, int end_row, int start_col, int end_col, int[][] paper) {
        if (start_row == end_row && start_col == end_col) { // base case
            int num = paper[start_row - 1][start_col - 1];
            switch (num) {
                case -1 : minus++; break;
                case 0 : zero++; break;
                case 1 : one++; break;
            }
            return num;
        }
        /**
         * 123
         * 456
         * 789
         */

        int row = (end_row - start_row) / 3 + 1;
        int col = (end_col - start_col) / 3 + 1;

        Integer section_one = solution(start_row, start_row + row - 1, start_col, start_col + col - 1, paper);
        Integer section_two = solution(start_row, start_row + row - 1, start_col + col, end_col - col, paper);
        Integer section_three = solution(start_row, start_row + row - 1, end_col - col + 1, end_col, paper);

        Integer section_four = solution(start_row + row, end_row - row, start_col, start_col + col - 1, paper);
        Integer section_five = solution(start_row + row, end_row - row, start_col + col, end_col - col, paper);
        Integer section_six = solution(start_row + row, end_row - row, end_col - col + 1, end_col, paper);

        Integer section_seven = solution(end_row - row + 1, end_row, start_col, start_col + col - 1, paper);
        Integer section_eight = solution(end_row - row + 1, end_row, start_col + col, end_col - col, paper);
        Integer section_nine = solution(end_row - row + 1, end_row, end_col - col + 1, end_col, paper);

        if (section_one != null &&
            section_one.equals(section_two) &&
            section_one.equals(section_three) &&
            section_one.equals(section_four) &&
            section_one.equals(section_five) &&
            section_one.equals(section_six)  &&
            section_one.equals(section_seven) &&
            section_one.equals(section_eight) &&
            section_one.equals(section_nine)) {
            int num = section_one;
            switch (num) {
                case -1 : minus -= 8; break;
                case 0 : zero -= 8; break;
                case 1 : one -= 8; break;
            }
            return num;
        } else return null;
    }

}