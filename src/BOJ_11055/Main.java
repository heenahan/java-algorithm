package BOJ_11055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int[] nums;
	static int[] dp; // dp[i] i번째 원소가 처음 시작할 때 최댓값
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		nums = new int[num + 1];
		dp = new int[num + 2];
		String[] input = br.readLine().split(" ");
		for (int i = 1; i <= num; i++) nums[i] = Integer.parseInt(input[i - 1]);
		
		System.out.println(T.solution(num));
		
		br.close();
	}
	
	public int solution(int num) {
		for (int i = 1; i <= num; i++) dp[i] = nums[i]; // 초기화
		for (int i = num; i >= 0; i--) { // (i, j) i에서 j까지 순열
			for (int j = i + 1; j <= num; j++) { // (i, i + 1) 부터 (i, num)까지 계산
				if (nums[j] > nums[i]) { // j번째 원소가 i번째 원소보다 크면
					dp[i] = Math.max(dp[i], nums[i] + dp[j]); // 지금까지 구했던 i번째를 포함했을 때 값보다 크면
				}
			}
		}
		return dp[0];
	}
}