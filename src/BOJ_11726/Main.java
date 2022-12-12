package BOJ_11726;

import java.io.*;

public class Main {

    int dp[];

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        System.out.println(T.solution(num));

        br.close();
    }

    public int solution(int num) {
        dp = new int[num + 1];
        dp[0] = 1; dp[1] = 1; // dp[2] = 2 를 넣을경우 num이 1이면 index 범위가 넘어간다.
        for (int i = 2; i <= num; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
        }
        return dp[num];
    }
}
