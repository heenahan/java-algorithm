package BOJ_9251;

import java.io.*;

public class Main {
	
	static char[] str1;
	static char[] str2;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		str1 = br.readLine().toCharArray();
		str2 = br.readLine().toCharArray();
		
		bw.write(T.solution(str1.length, str2.length).toString());
		
		br.close();
		bw.close();
	}
	
	public Integer solution(int n, int m) {
		int[][] dp = new int[n + 1][m + 1];
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (str1[i - 1] != str2[j - 1]) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				} else {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					if (Math.max(dp[i - 1][j], dp[i][j - 1]) > dp[i][j]) dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		
		return dp[n][m];
	}
}
