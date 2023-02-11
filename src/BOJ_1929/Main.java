package BOJ_1929;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		T.solution(n, m);
		
		br.close();
		bw.close();
	}
	
	public void solution(int n, int m) throws IOException {
		boolean[] num = new boolean[m + 1];
		for (int i = 2; i <= m; i++) {
			if (!num[i]) { // 지워지지 않았다면
				if (i >= n && i <= m) bw.write(i + "\n");
				for (int j = i * 2; j <= m; j += i) num[j] = true;
			}
		}
	}
}
