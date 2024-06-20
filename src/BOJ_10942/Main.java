package BOJ_10942;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		String[] arr = br.readLine().split(" ");
		int[] p = new int[n];
		int[][] dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			p[i] = Integer.parseInt(arr[i]);
		}
		// dp[st][ed]는 st부터 ed까지 팰린드롬인지 아닌지
		// 길이가 1일 때 true
		// 길이가 2일 때 양측이 동일하면 true
		// 길이가 3이상일 때 양측이 동일하고 st + 1, ed - 1이 true이면 true
		for (int st = n - 1; st >= 0; st--) {
			dp[st][st] = 1;
			for (int ed = st + 1; ed < n; ed++) {
				if (p[st] == p[ed]) {
					if (ed - st + 1 == 2 || dp[st + 1][ed - 1] == 1) {
						dp[st][ed] = 1;
						continue;
					}
				}
				dp[st][ed] = 0;
			}
		}
		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			String[] se = br.readLine().split(" ");
			int s = Integer.parseInt(se[0]);
			int e = Integer.parseInt(se[1]);
			bw.write(String.valueOf(dp[s - 1][e - 1]));
			bw.newLine();
		}

		br.close(); bw.close();
	}
}