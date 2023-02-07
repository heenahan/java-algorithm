package BOJ_11052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int[] price;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		price = new int[num];
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < num; i++) price[i] = Integer.parseInt(input[i]);
		System.out.println(T.solution(num));
		
		br.close();
	}
	
	public int solution(int num) {
		int[] dp = new int[num + 1]; // dp[i] i개의 카드를 구매하는 최댓값
		dp[1] = price[0];
		for (int i = 2; i <= num; i++) {
			dp[i] = price[i - 1];
			for (int j = 1; j < i; j++) dp[i] = Math.max(dp[i], dp[i - j] + dp[j]);
		}
		return dp[num];
	}
}
