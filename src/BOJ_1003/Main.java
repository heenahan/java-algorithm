package BOJ_1003;

import java.io.*;

public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int[][] dp = new int[41][2];

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T.calc();

        int test = Integer.parseInt(br.readLine());
        for (int i = 0; i < test; i++) {
            int num = Integer.parseInt(br.readLine());
            T.solution(num);
        }

        br.close();
        bw.close();
    }

    public void calc() {
        dp[0][0] = 1; dp[1][1] = 1;

        for (int i = 2; i <= 40; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
            dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
        }
    }

    public void solution(int num) throws IOException {
        bw.write(dp[num][0] + " " + dp[num][1]);
        bw.newLine();
    }

}
