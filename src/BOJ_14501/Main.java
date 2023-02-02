package BOJ_14501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int[][] consult;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		consult = new int[num + 2][2];
		for (int i = 1; i <= num; i++) {
			String[] input = br.readLine().split(" ");
			int day = Integer.parseInt(input[0]);
			int cost = Integer.parseInt(input[1]);
			consult[i][0] = day; consult[i][1] = cost;
		}
		
		System.out.println(T.solution(num + 1));
		
		br.close();
	}
	
	public int solution(int num) {
		int[] dp = new int[num + 1];
		for (int i = num - 1; i > 0; i--) {
			if (i + consult[i][0] > num) dp[i] = dp[i + 1];
			else dp[i] = Math.max(dp[i + 1], dp[i + consult[i][0]] + consult[i][1]);
		}
		return dp[1];
	}
}
