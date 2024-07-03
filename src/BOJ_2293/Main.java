package BOJ_2293;

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
        int[] dp = new int[k + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = 1; i <= k; i++) {
                if (i < coin) {
                    continue;
                }
                dp[i] += dp[i - coin];
            }
        }
        System.out.print(dp[k]);
        br.close();
    }
}