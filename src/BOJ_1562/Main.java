package BOJ_1562;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        // dp[i][j][k] j로 끝나는 i 길이 k 비트의 계단 수 개수
        long[][][] dp = new long[n + 1][10][1024];
        for (int i = 1; i < 10; i++) {
            // 한 자리 계단 수
            dp[1][i][1 << i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 1024; k++) {
                    // k라는 비트에 j를 추가
                    if (j > 0) {
                        dp[i][j][k | (1 << j)] += dp[i - 1][j - 1][k];
                    }
                    if (j < 9) {
                        dp[i][j][k | (1 << j)] += dp[i - 1][j + 1][k];
                    }
                    dp[i][j][k | (1 << j)] %= 1_000_000_000;
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < 10; i++) {
            // 모든 숫자를 방문한 경우만 더함
            answer += dp[n][i][1023];
            answer %= 1_000_000_000;
        }
        System.out.println(answer);

        sc.close();
    }
}