package BOJ_3151;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		arr = new int[num];
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < num; i++) arr[i] = Integer.parseInt(input[i]);
		
		System.out.println(T.solution(num, arr));
		
		br.close();
	}
	
	public long solution(int num, int[] arr) {
		// 정렬
		Arrays.sort(arr);
		long count = 0;
		// 두 개를 선택함
		for (int i = 0; i < num; i++) {
			for (int j = i + 1; j < num; j++) {
				int upper = upperBound(j + 1, num, -(arr[i] + arr[j]));
				int lower = lowerBound(j + 1, num, -(arr[i] + arr[j]));
				count += (upper - lower);
			}
		}
		return count;
	}
	// key를 초과하는 첫번째 값
	private int upperBound(int st, int ed, int key) {
		int mid;
		while (st < ed) {
			mid = st + (ed - st) / 2;
			if (arr[mid] <= key) st = mid + 1;
			else ed = mid;
		}
		return st;
	}
	// key와 같거나 key보다 큰 수 중 가장 작은 수
	private int lowerBound(int st, int ed, int key) {
		int mid;
		while (st < ed) {
			mid = st + (ed - st) / 2;
			if (arr[mid] < key) st = mid + 1;
			else ed = mid;
		}
		return st;
	}
}