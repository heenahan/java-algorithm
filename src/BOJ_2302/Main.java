package BOJ_2302;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        Set<Integer> vip = new HashSet<>();
        for (int i = 0; i < m; i++) {
            vip.add(Integer.parseInt(br.readLine()));
        }

        int[] dp = new int[n + 1];
        dp[0] = 1; dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            // vip가 아니거나 vip가 왼쪽에 위치하지 않을 경우 왼쪽과 자리를 옮길 수 있음
            if (!vip.contains(i) && !vip.contains(i - 1)) {
                dp[i] += dp[i - 2];
            }
            // 자기 자리에 앉는 경우
            dp[i] += dp[i - 1];
        }

        System.out.print(dp[n]);

        br.close();
    }
}