package BOJ_2253;

import java.util.*;
import java.io.*;

public class Main {

    private static int[][] dp;
    private static int[] ds = { 0, -1, 1 };

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        Set<Integer> rocks = new HashSet<>();
        for (int i = 0; i < m; i++) {
            rocks.add(Integer.parseInt(br.readLine()));
        }

        // visited[i][j] j 크기의 점프로 i까지 갈 때 최소 횟수
        dp = new int[n + 1][n / 2];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[1][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < n / 2; j++) {
                if (dp[i][j] == Integer.MAX_VALUE) {
                    continue;
                }
                for (int d : ds) {
                    int ns = j + d;
                    if (ns <= 0 || ns >= n / 2 || i + ns > n) continue;
                    if (rocks.contains(i + ns)) continue;
                    dp[i + ns][ns] = Math.min(dp[i + ns][ns], dp[i][j] + 1);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n / 2; i++) {
            min = Math.min(min, dp[n][i]);
        }

        if (min == Integer.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(min);
        }

        br.close();
    }

}