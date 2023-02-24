package BOJ_9084;

import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int testCase = Integer.parseInt(br.readLine());
		for (int i = 0; i < testCase; i++) {
			int num = Integer.parseInt(br.readLine());
			int[] coin = new int[num];
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < num; j++) coin[j] = Integer.parseInt(input[j]);
			int money = Integer.parseInt(br.readLine());
			bw.write(T.solution(money, num, coin) + "\n");
		}
		
		br.close();
		bw.close();
	}
	
	public int solution(int money, int num, int[] coin) {
		int[][] dp = new int[10001][20];
		for (int i = 0; i < num; i++) dp[coin[i]][i]++;
		for (int i = 1; i <= money; i++) {
			for (int j = 0; j < num; j++) {
				if (dp[i][j] > 0) {
					for (int k = j; k < num; k++) {
						if (money >= i + coin[k]) dp[i + coin[k]][k] += dp[i][j];
					}
				}
			}
		}
		int sum = 0;
		for (int i = 0; i < num; i++) sum += dp[money][i];
		return sum;
	}
	
}
