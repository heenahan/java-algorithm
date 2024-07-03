package BOJ_2294;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        List<Integer> coins = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            coins.add(Integer.parseInt(br.readLine()));
        }
        // dp[i] i원을 만들수 있는 최소 동전 개수
        // coin을 추가할 때 -> 1 + (i - coin원으로 만들 수 있는 최소 동전 개수)
        int[] dp = new int[k + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i = 1; i <= k; i++) {
                if (i < coin) {
                    continue;
                }
                if (dp[i - coin] == Integer.MAX_VALUE) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        if (dp[k] == Integer.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(dp[k]);
        }
        br.close();
    }
}