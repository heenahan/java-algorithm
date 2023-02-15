package BOJ_2295;

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
		for (int i = 0; i < num; i++) arr.add(Integer.parseInt(br.readLine()));
		System.out.println(T.solution(num, arr));
		
		br.close();
	}
	
	public int solution(int num, List<Integer> arr) {
		int max = Integer.MIN_VALUE;
		List<Integer> two = new ArrayList<>();
		for (int i = 0; i < num - 1; i++) {
			for (int j = i + 1; j < num; j++) two.add(arr.get(i) + arr.get(j));
		}
		two = two.stream().sorted().collect(Collectors.toList());
		for (int i = 0; i < num - 1; i++) {
			// two + arr[i] = arr[j]
			for (int j = i + 1; j < num; j++) {
				int minus = arr.get(j) - arr.get(i);
				// 콜렉션에 존재한다면
				if (Collections.binarySearch(two, minus) >= 0) {
					if (arr.get(j) > max) max = arr.get(j);
				}
			}
		}
		return max;
	}
	
	
}
