package BOJ_1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		int k = Integer.parseInt(input[0]);
		int n = Integer.parseInt(input[1]);
		
		int[] have = new int[k];
		for (int i = 0; i < k; i++) {
			have[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(T.solution(have, n));
		
		br.close();
	}
	
	// 랜선의 길이가 길수록 만들어지는 랜선의 수가 적어진다.
	// 가지고 있는 랜선들의 길이, 가장 큰 랜선의 길이, 필요한 랜선의 수
	public long solution(int[] have, int n) {
		long start = 1; long end = Integer.MAX_VALUE;
		long mid = (end + start + 1) / 2;
		while (start < end) { // start와 end가 같으면 탈출
			int sum = 0;
			for (int len : have) sum += (len / mid);
			
			if (sum >= n) start = mid; // 원하는 갯수보다 크다면 정답은 오른편에 있다.
			else end = mid - 1; // 원하는 갯수보다 작다면 정답은 왼편에 있다.
			
			mid = (end + start + 1) / 2;
		}
		
		return start;
	}
	
}
