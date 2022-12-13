package BOJ_11659;

import java.io.*;

public class Main {

    int[] dp;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input1 = br.readLine().split(" ");
        int n = Integer.parseInt(input1[0]);
        int m = Integer.parseInt(input1[1]);

        String[] input2 = br.readLine().split(" ");
        int[] arr= new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(input2[i]);
        T.setDp(n, arr);

        for (int i = 0; i < m; i++) {
            String[] input3 = br.readLine().split(" ");
            int start = Integer.parseInt(input3[0]);
            int end = Integer.parseInt(input3[1]);
            bw.write(T.solution(start, end).toString());
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    public void setDp(int num, int[] arr) {
        dp = new int[num + 2];
        int sum = 0;
        for (int i = num; i > 0; i--) {
            sum += arr[i - 1];
            dp[i] = sum;
        }
    }

    public Integer solution(int start, int end) {
        return dp[start] - dp[end + 1];
    }
}
