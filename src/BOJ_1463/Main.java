package BOJ_1463;

import java.io.*;

public class Main {

    int[] dp = new int[1000001];

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        System.out.println(T.solution(num));

        br.close();
    }

    public int solution(int num) {
        if (num == 1) return 0;
        if (dp[num] > 0) return dp[num]; // 결정됬으면 저장된 값 return
        int min = solution(num - 1);
        if (num % 2 == 0) {
            int two = solution(num / 2);
            if (min > two) min = two;
        }
        if (num % 3 == 0) {
            int three = solution(num / 3);
            if (min > three) min = three;
        }
        dp[num] = min + 1;
        return dp[num];
    }

}
