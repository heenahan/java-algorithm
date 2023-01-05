package BOJ_10844;

import java.util.Scanner;

public class Main {
	
	static int[][] dp;
	
	public static void main(String[] args) {
		Main T = new Main();
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		
		System.out.println(T.solution(num));
		
		in.close();
	}
	
	public int solution(int num) {
		dp = new int[num + 1][10]; // dp[num][i] 끝자리가 i인 num 자리의 수
		for (int i = 1; i < 10; i++) dp[1][i] = 1;
		for (int i = 2; i <= num; i++) {
			for (int j = 0; j < 10; j++) {
				if (j > 0) dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % 1000000000; // j가 0이면 1만 올 수 있다.
				if (j < 9) dp[i][j] = (dp[i][j] + dp[i - 1][j + 1])% 1000000000; // j가 9이면 8만 올 수 있다.
			}
		}
		int sum = 0;
		for (int i = 0; i < 10; i++) sum = (sum + dp[num][i]) % 1000000000;
		return sum;
	}

}
