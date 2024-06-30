package BOJ_2342;

import java.util.*;

public class Main {

    private static int[][] cost = {
        // cost[i][j] i -> j로 가는데 비용
        { 1, 2, 2, 2, 2 },
        { 3, 1, 3, 4, 3 },
        { 3, 3, 1, 3, 4 },
        { 3, 4, 3, 1, 3 },
        { 3, 3, 4, 3, 1 }
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = Arrays.stream(sc.nextLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        // dp[i][j][k] i번째 지시에서 (j, k) 스탭일때 최소값
        int[][][] dp = new int[arr.length][5][5];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 5; j++) {
                // overflow 방지
                Arrays.fill(dp[i][j], Integer.MAX_VALUE - 4);
            }
        }
        dp[0][0][0] = 0;
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i - 1];
            // 이전 지시의 모든 발 위치에서 지금 지시를 행했을 때 최소값 구함
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    dp[i][j][current] = Math.min(dp[i][j][current], dp[i - 1][j][k] + cost[k][current]);
                    dp[i][current][j] = Math.min(dp[i][current][j], dp[i - 1][j][k] + cost[k][current]);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (min > dp[arr.length - 1][i][j]) {
                    min = dp[arr.length - 1][i][j];
                }
            }
        }
        System.out.println(min);

        sc.close();
    }
}