package BOJ_10942;

import java.io.*;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		
		int n = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) sb.append(input[i]);
		int m = Integer.parseInt(br.readLine());
		
		T.solution(n, m, sb);
		
		br.close();
		bw.close();
	}
	
	public void solution(int n, int m, StringBuilder num) throws IOException {
		boolean[][] palindrome = new boolean[n][n];
		StringBuilder reverse = num.reverse();
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				// 양쪽이 동일할 때
				int len = j - i + 1;
				String subNum = num.substring(i, i + len / 2);
				String subRev = reverse.substring(n - (i + len), n - (i + len / 2 + len % 2));
				if (subNum.equals(subRev)) palindrome[i][j] = true;
			}
		}
		for (int i = 0; i < m; i++) {
			String[] input = br.readLine().split(" ");
			int st = Integer.parseInt(input[0]);
			int ed = Integer.parseInt(input[1]);
			if (palindrome[st - 1][ed - 1]) bw.write(1 + "\n");
			else bw.write(0 + "\n");
		}
	}
}
