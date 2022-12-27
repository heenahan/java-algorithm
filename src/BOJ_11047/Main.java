package BOJ_11047;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) coins[i] = Integer.parseInt(br.readLine());

        System.out.println(T.solution(k, coins, n));

        br.close();
    }

    public int solution(int price, int[] coins, int num) {
        int sum = 0;
        while (price > 0) {
            for (int i = num - 1; i >= 0; i--) {
                if (coins[i] <= price) {
                    sum += price / coins[i];
                    price %= coins[i];
                }
            }
        }
        return sum;
    }
}
