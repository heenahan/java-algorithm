package BOJ_2579;

import java.io.*;

public class Main {

    int[][] dp;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        T.setDpAndStep(num);
        int[] scores = new int[num + 1];
        for (int i = 1; i <= num; i++) scores[i] = Integer.parseInt(br.readLine());

        System.out.println(T.solution(num, scores));

        br.close();
    }

    public void setDpAndStep(int num) {
        dp = new int[num + 1][3];
    }

    public int solution(int num, int[] scores) {
        dp[1][1] = scores[1];
        for (int i = 2; i <= num; i++) {
            dp[i][1] = Math.max(dp[i - 2][1], dp[i - 2][2]) + scores[i];
            dp[i][2] = dp[i - 1][1] + scores[i];
        }
        return Math.max(dp[num][1], dp[num][2]);
    }

}
