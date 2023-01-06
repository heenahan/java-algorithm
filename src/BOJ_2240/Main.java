package BOJ_2240;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int[][] dp; // dp[t][w] t초에 w번 움직였을 때 최댓값
	static int[] tree; // tree[t] t초에 위치한 나무
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		int t = Integer.parseInt(input[0]);
		int w = Integer.parseInt(input[1]);
		dp = new int[t + 1][w + 1];
		tree = new int[t + 1];
		
		for (int i = 1; i <= t; i++) tree[i] = Integer.parseInt(br.readLine());
		
		System.out.println(T.solution(t, w));
		
		br.close();
	}
	
	public int solution(int t, int w) {
		for (int i = 1; i <= t; i++) {
			int step = 0;
			while (step <= i && step <= w) {
				if (step == 0) {
					// 나무는 두 그루 뿐임. 홀수번째 움직일 때 2번 나무에 짝수번째 움직일 때 1번 나무에 위치
					// 자두가 떨어지는 나무에 서있음
					if ((tree[i] == 1 && step % 2 == 0) || (tree[i] == 2 && step % 2 == 1)) dp[i][step] = dp[i - 1][step] + 1;
					else dp[i][step] = dp[i - 1][step];
				} else {
					if ((tree[i] == 1 && step % 2 == 0) || (tree[i] == 2 && step % 2 == 1)) dp[i][step] = Math.max(dp[i - 1][step], dp[i - 1][step - 1]) + 1;
					else dp[i][step] = Math.max(dp[i - 1][step - 1], dp[i - 1][step]); // 움직임, 안움직임
				}
				step++;
			}
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i <= w; i++) {
			if (dp[t][i] > max) max = dp[t][i];
		}
		return max;
	}
}
