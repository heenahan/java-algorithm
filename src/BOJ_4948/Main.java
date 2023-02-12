package BOJ_4948;

import java.io.*;

public class Main {
	
	static boolean[] remove = new boolean[300000];
	static int start = 2;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int num = Integer.parseInt(br.readLine());
		while (num != 0) {
			bw.write(T.solution(num) + "\n");
			num = Integer.parseInt(br.readLine());
		}
		
		br.close();
		bw.close();
	}
	
	public int solution(int num) {
		if (start < num * 2) {
			for (int i = 2; i <= num * 2; i++) {
				if (!remove[i]) {
					for (int j = i * 2; j <= num * 2; j += i) remove[j] = true;
				}
			}
			start = num * 2;
		}
		int count = 0;
		for (int i = num + 1; i <= num * 2; i++) {
			if (!remove[i]) count++;
		}
		return count;
	}
	
}
