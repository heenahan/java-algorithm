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
		List<Integer> two = new ArrayList<>();
		for (int i = 0; i < num; i++) { // x, y, z, k가 서로 같아도 됨!!
			for (int j = i; j < num; j++) two.add(arr.get(i) + arr.get(j));
		}
		arr = arr.stream().sorted().collect(Collectors.toList()); // 가장 큰 수가 뒤에 위치하도록
		two = two.stream().sorted().collect(Collectors.toList()); // 이분탐색을 위한 정렬
		for (int i = num - 1; i >= 0; i--) { // 가장 먼저 찾은 k가 문제에서 원하는 가장 큰 k
			// two + arr[j] = arr[i]
			for (int j = 0; j < num; j++) {
				int minus = arr.get(i) - arr.get(j);
				// 콜렉션에 존재한다면
				if (Collections.binarySearch(two, minus) >= 0) {
					return arr.get(i);
				}
			}
		}
		return Integer.MAX_VALUE;
	}
	
	
}
