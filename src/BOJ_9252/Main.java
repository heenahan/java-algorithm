package BOJ_9252;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] a = sc.nextLine().split("");
        String[] b = sc.nextLine().split("");

        int[][] dp = new int[a.length + 1][b.length + 1];
        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                if (Objects.equals(a[i - 1], b[j - 1])) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                }
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
            }
        }
        StringBuilder sb = new StringBuilder();
        int x = a.length; int y = b.length;
        // 역추적
        while (x != 0 && y != 0) {
            if (Objects.equals(a[x - 1], b[y - 1])) {
                sb.append(a[x - 1]);
                x--; y--;
                continue;
            }
            if (dp[x][y] == dp[x - 1][y]) {
                x--;
                continue;
            }
            y--;
        }

        System.out.println(dp[a.length][b.length]);
        System.out.print(sb.reverse());

        sc.close();
    }
}