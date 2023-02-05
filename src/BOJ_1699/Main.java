package BOJ_1699;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Main T = new Main();
		Scanner in = new Scanner(System.in);
		
		int num = in.nextInt();
		
		System.out.println(T.solution(num));
		
		in.close();
	}
	
	public int solution(int num) {
		int[] dp = new int[num + 1];
		int root = 1;
		dp[1] = 1;
		for (int i = 2; i <= num; i++) {
			if (Math.pow(root + 1, 2) == i) { // 다음 제곱근 이면
				root++;
				dp[i] = 1;
			} else { // 제곱근이 아니면
				dp[i] = dp[root * root] + dp[i - root * root];
				for (int j = root - 1; j > 0; j--) {
					dp[i] = Math.min(dp[i], dp[j * j] + dp[i - j * j]);
				}
			}
		}
		return dp[num];
	}
	
}
