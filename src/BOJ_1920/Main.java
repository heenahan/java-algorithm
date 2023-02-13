package BOJ_1920;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int num = Integer.parseInt(br.readLine());
		String[] input1 = br.readLine().split(" ");
		List<Integer> arr = new ArrayList<>();
		for (int i = 0; i < num; i++) arr.add(Integer.parseInt(input1[i]));
		arr.sort(Comparator.naturalOrder()); // 오름차순
		
		int find = Integer.parseInt(br.readLine());
		String[] input2 = br.readLine().split(" ");
		for (int i = 0; i < find; i++) {
			int target = Integer.parseInt(input2[i]);
			if (T.solution(num, arr, target)) bw.write(1 + "\n");
			else bw.write(0 + "\n");
		}
		
		br.close();
		bw.close();
	}
	
	public boolean solution(int num, List<Integer> arr, int target) {
		int start = 0; int end = num - 1;
		while (end >= start) {
			int mid = (end + start) / 2;
			if (target > arr.get(mid)) start = mid + 1;
			else if (target < arr.get(mid)) end = mid - 1;
			else return true;
		}
		return false;
	}
}
