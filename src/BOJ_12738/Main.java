package BOJ_12738;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Main {
	
	static int[] nums;
	static List<Integer> arr;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int num = Integer.parseInt(br.readLine());
		nums = new int[num];
		arr = new ArrayList<>();
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < num; i++) nums[i] = Integer.parseInt(input[i]);
		
		bw.write(T.solution(num).toString());
		
		br.close();
		bw.close();
	}
	
	public Integer solution(int num) {
		arr.add(nums[0]);
		int size = 1;
		for (int i = 1; i < num; i++) {
			if (nums[i] > arr.get(size - 1)) { // 끝 값보다 크면 넣음
				arr.add(nums[i]);
				size++;
			} else {
				int push = Collections.binarySearch(arr, nums[i]); // 넣을 위치 찾음
				if (push < 0) arr.set(Math.abs(push + 1), nums[i]); // upper bound
			}
		}
		return size;
	}
}
