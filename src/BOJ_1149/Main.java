package BOJ_1149;

import java.io.*;

public class Main {

    int[][] dp;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        T.setDp(num);
        int[][] cost = new int[num + 1][3];
        for (int i = 1; i <= num; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) cost[i][j] = Integer.parseInt(input[j]);
        }

        System.out.println(T.solution(num, cost));

        br.close();
    }

    public void setDp(int num) {
        dp = new int[num + 1][3];
    }

    // 0 : 빨강 1 : 초록 2 : 파랑
    public int solution(int num, int[][] cost) {
        dp[1][0] = cost[1][0]; dp[1][1] = cost[1][1]; dp[1][2] = cost[1][2];
        for (int i = 2; i <= num; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if (min > dp[num][i]) min = dp[num][i];
        }
        return min;
    }
}
