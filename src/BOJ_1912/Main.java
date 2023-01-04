package BOJ_1912;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	
	static int[] nums;
	static int[] dp; // 자신을 포함한 연속합 중 가장 큰 것
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		nums = new int[num];
		dp = new int[num];
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < num; i++) nums[i] = Integer.parseInt(input[i]);
		
		System.out.println(T.solution(num));
		
		br.close();
	}
	
	public int solution(int num) {
		dp[0] = nums[0];
		for (int i = 1; i < num; i++) {
			if (nums[i] > dp[i - 1] + nums[i]) dp[i] = nums[i]; // 자신이 시작하는 수열이 가장 클 때
			else dp[i] = dp[i - 1] + nums[i];
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < num; i++) {
			if (dp[i] > max) max = dp[i];
		}
		return max;
	}
}
