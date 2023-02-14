package BOJ_18870;

import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Set<Integer> set = new HashSet<>();
		int num = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");
		int[] nums = new int[num];
		for (int i = 0; i < num; i++) {
			nums[i] = Integer.parseInt(input[i]);
			set.add(nums[i]); // 중복 없이 저장
		}
		// 정렬 후 리스트로
		List<Integer> arr = set.stream().sorted().collect(Collectors.toList());
		
		for (int i = 0; i < num; i++) {
			int result = T.solution(arr, nums[i]);
			bw.write(result + " ");
		}
		
		br.close();
		bw.close();
	}
	
	public int solution(List<Integer> arr, int num) {
		return Collections.binarySearch(arr, num);
	}
	
}
