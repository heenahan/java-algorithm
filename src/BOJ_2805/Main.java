package BOJ_2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input1 = br.readLine().split(" ");
		int n = Integer.parseInt(input1[0]);
		int m = Integer.parseInt(input1[1]);
		
		int[] tree = new int[n];
		String[] input2 = br.readLine().split(" ");
		for (int i = 0; i < n; i++) tree[i] = Integer.parseInt(input2[i]);
		
		System.out.println(T.solution(tree, m));
		
		br.close();
	}
	
	public int solution(int[] tree, int m) {
		// 높이는 0도 가능
		int start = 0; int end = 1000000000;
		int mid = (start + end + 1) / 2;
		while (start < end) {
			long sum = 0; // sum이 충분히 21억을 넘길 수 있다
			for (int t : tree) {
				int minus = t - mid;
				if (minus >= 0) sum += minus;
			}
			
			if (sum >= m) start = mid;
			else end = mid - 1;
			
			mid = (start + end + 1) / 2;
		}
		return start;
	}
	
}
