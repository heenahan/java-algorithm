package BOJ_2193;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main T = new Main();
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        System.out.println(T.solution(num));
        in.close();
    }

    public long solution(int num) { // 값이 매우 커짐
        long[][] dp = new long[num + 1][2];
        dp[1][0] = 0; dp[1][1] = 1;
        for (int i = 2; i <= num; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1]; // 끝자리가 0으로 끝남 00, 10
            dp[i][1] = dp[i - 1][0]; // 끝자리가 1로 끝남 01만 가능
        }
        return dp[num][0] + dp[num][1];
    }
}
