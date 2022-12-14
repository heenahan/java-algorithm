package BOJ_12852;

import java.io.*;

public class Main {

    int[] dp;
    int[] visited; // 자식

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        T.solution(num);

        br.close();
    }

    public void solution(int num) {
        dp = new int[num + 1];
        visited = new int[num + 1];
        dp[1] = 0;

        for (int i = 2; i <= num; i++) {
            dp[i] = dp[i - 1] + 1;
            visited[i] = i - 1;
            if (i % 2 == 0) {
                if (dp[i] > dp[i / 2]) {
                    dp[i] = dp[i / 2] + 1;
                    visited[i] = i / 2;
                }
            }
            if (i % 3 == 0) {
                if (dp[i] > dp[i / 3]) {
                    dp[i] = dp[i / 3] + 1;
                    visited[i] = i / 3;
                }
            }
        }
        System.out.println(dp[num]);
        int route = num;
        while (route > 0) {
            System.out.print(route + " ");
            route = visited[route];
        }
    }
}
