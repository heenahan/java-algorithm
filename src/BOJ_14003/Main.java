package BOJ_14003;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
	static int[] nums;
	static List<Integer> arr;
	static int[] index;
	static int[] lis;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int num = Integer.parseInt(br.readLine());
		nums = new int[num];
		index = new int[num];
		arr = new ArrayList<>();
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < num; i++) nums[i] = Integer.parseInt(input[i]);
		
		Integer size = T.solution(num);
		bw.write(size.toString());
		bw.newLine();
		for (int i = 0; i < size; i++) bw.write(lis[i] + " ");
		
		br.close();
		bw.close();
	}
	
	public int solution(int num) {
		arr.add(nums[0]);
		index[0] = 0;
		int end = 0;
		int size = 1;
		for (int i = 1; i < num; i++) {
			if (nums[i] > arr.get(size - 1)) {
				arr.add(nums[i]);
				size++;
				index[i] = size - 1;
				end = i;
			} else {
				int input = Collections.binarySearch(arr, nums[i]); // i번째가 어디에 들어가야 하나
				if (input < 0) {
					input = Math.abs(input + 1);
					arr.set(input, nums[i]);
				}
				index[i] = input;
			}
		}
		lis = new int[size];
		lis[size - 1] = nums[end];
		visited = new boolean[size];
		visited[size - 1] = true;
		for (int i = end - 1; i >= 0; i--) {
			if (lis[index[i] + 1] > nums[i] && visited[index[i] + 1]) {
				lis[index[i]] = nums[i];
				visited[index[i]] = true;
			}
		}
		return size;
	}
}
