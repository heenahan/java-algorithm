package BOJ_1932;

import java.io.*;

public class Main {

    int[][] dp;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());
        int[][] triangle = new int[size][size];
        for (int i = 0; i < size; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j <= i; j++) triangle[i][j] = Integer.parseInt(input[j]);
        }
        System.out.println(T.solution(size, triangle));

        br.close();
    }

    public int solution(int size, int[][] triangle) {
        dp = new int[size][size];

        for (int i = 0; i < size; i++) dp[size - 1][i] = triangle[size - 1][i]; // 초기화

        for (int i = size - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j];
            }
        }

        return dp[0][0];
    }
}
