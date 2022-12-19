package BOJ_2839;

import java.util.Scanner;

public class Main {

    int[] dp;

    public static void main(String[] args) {
        Main T = new Main();
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        System.out.println(T.solution(num));
        sc.close();
    }

    public int solution(int num) {
        dp = new int[num + 1];
        dp[0] = 0; dp[1] = Integer.MAX_VALUE; dp[2] = Integer.MAX_VALUE;

        for (int i = 3; i <= num; i++) {
            if (i >= 5) {
                if (dp[i - 3] == Integer.MAX_VALUE && dp[i - 5] == Integer.MAX_VALUE) dp[i] = Integer.MAX_VALUE;
                else dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;
            } else {
                if (dp[i - 3] == Integer.MAX_VALUE) dp[i] = Integer.MAX_VALUE;
                else dp[i] = dp[i - 3] + 1;
            }

        }

        if (dp[num] == Integer.MAX_VALUE) return -1;
        else return dp[num];
    }
}
