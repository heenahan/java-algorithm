package BOJ_2230;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input1 = br.readLine().split(" ");
		int n = Integer.parseInt(input1[0]);
		int m = Integer.parseInt(input1[1]);
		
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(br.readLine());
		
		System.out.println(T.solution(n, m, arr));
		
		br.close();
	}
	
	public int solution(int n, int m, int[] arr) {
		arr = Arrays.stream(arr).sorted().toArray();
		int min = Integer.MAX_VALUE;
		int ed = 0; // 같은 수 일수도 있다.
		for (int st = 0; st < n; st++) {
			while (ed < n && arr[ed] - arr[st] < m) ed++;
			if (ed == n) break; // st가 커져도 m 이상을 만족하는 ed는 존재 안함.
			if (min > arr[ed] - arr[st]) min = arr[ed] - arr[st];
		}
		return min;
	}
	
}
