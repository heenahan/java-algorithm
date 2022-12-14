package BOJ_11727;

import java.io.*;

public class Main {

    int[] dp = new int[1001];

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        System.out.println(T.solution(num));

        br.close();
    }

    public int solution(int num) {
        dp[1] = 1; dp[2] = 3;
        for (int i = 3; i <= num; i++) dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 10007;
        return dp[num];
    }
}
