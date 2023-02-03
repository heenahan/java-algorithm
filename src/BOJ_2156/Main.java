package BOJ_2156;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int[] amount;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		amount = new int[num + 1];
		for (int i = 1; i <= num; i++) {
			amount[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(T.solution(num));
		
		br.close();
	}
	
	public int solution(int num) {
		int[][] dp = new int[num + 1][2]; // dp[i][0] 연속 안됨 dp[i][1] 연속됨
		if (num == 1) return amount[1];
		dp[1][0] = amount[1]; dp[1][1] = amount[1];
		dp[2][0] = amount[2]; dp[2][1] = dp[1][0] + amount[2];
		int max = dp[2][1];
		for (int i = 3; i <= num; i++) {
			dp[i][0] = Math.max(dp[i - 2][0], dp[i - 2][1]) + amount[i];
			dp[i][1] = dp[i - 1][0] + amount[i];
			if (Math.max(dp[i][0], dp[i][1]) > max) max = Math.max(dp[i][0], dp[i][1]);
		}
		return max;
	}
	
}
