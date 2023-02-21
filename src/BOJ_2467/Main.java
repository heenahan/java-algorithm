package BOJ_2467;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int num = Integer.parseInt(br.readLine());
		
		List<Integer> arr = new ArrayList<>();
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < num; i++) arr.add(Integer.parseInt(input[i]));
		
		int[] two = T.solution(arr);
		System.out.println(two[0] + " " + two[1]);
		
		br.close();
	}
	
	public int[] solution(List<Integer> arr) {
		// 정렬 upper bound, lower bound는 중복이 있어선 안되고 값이 unique해야함
		List<Integer> sorted = arr.stream().sorted().distinct().collect(Collectors.toList());
		int size = sorted.size();
		
		int[] two = new int[2]; // 합성할 두 용액
		int nearToZero = Integer.MAX_VALUE;
		for (Integer i : arr) {
			int find = -i; int mix = Integer.MAX_VALUE;
			// i와 합쳤을 때 0에 가장 가깝도록 만들 수 있는 수의 위치
			int lower = Collections.binarySearch(sorted, find);
			if (lower < 0) lower = Math.abs(lower + 1); // lower_bound a[lower]은 -i와 같거나 -i보다 큰 수 중 가장 작은 수 이다.
			// i와 섞었을 때 제일 작은 값은 a[lower - 1] or a[lower] or a[lower + 1]
			int idx = 0;
			// 같은 용액이 섞여선 안됨
			if (lower > 0 && i != sorted.get(lower - 1) && mix > Math.abs(i + sorted.get(lower - 1))) {
				mix = Math.abs(i + sorted.get(lower - 1));
				idx = lower - 1;
			}
			if (lower < size && i != sorted.get(lower) && mix > Math.abs(i + sorted.get(lower))) {
				mix = Math.abs(i + sorted.get(lower));
				idx = lower;
			}
			if (lower < size - 1 && i != sorted.get(lower + 1) && mix > Math.abs(i + sorted.get(lower + 1))) {
				mix = Math.abs(i + sorted.get(lower + 1));
				idx = lower + 1;
			}
			if (nearToZero > mix) {
				nearToZero = mix;
				if (i > sorted.get(idx)) {
					two[0] = sorted.get(idx);
					two[1] = i;
				} else {
					two[0] = i;
					two[1] = sorted.get(idx);
				}
			}
		}
		return two;
	}

}
