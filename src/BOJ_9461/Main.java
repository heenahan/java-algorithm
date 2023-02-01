package BOJ_9461;

import java.io.*;

public class Main {
	
	static long[] seq = new long[101];
	static int index = 6;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int num = Integer.parseInt(br.readLine());
		seq[1] = 1; seq[2] = 1; seq[3] = 1; seq[4] = 2; seq[5] = 2;
		for (int i = 0; i < num; i++) {
			int next = Integer.parseInt(br.readLine());
			if (next >= index) bw.write(T.solution(next) + "\n");
			else bw.write(seq[next] + "\n");
		}
		
		br.close();
		bw.close();
	}
	
	public long solution(int num) {
		for (int i = index; i <= num; i++) {
			seq[i] = seq[i - 5] + seq[i - 1];
		}
		index = num;
		return seq[num];
	}
}
