package BOJ_1253;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");
		List<Integer> arr = new ArrayList<>();
		for (int i = 0; i < num; i++) arr.add(Integer.parseInt(input[i]));
		
		System.out.println(T.solution(num, arr));
		
		br.close();
	}
	
	public int solution(int n, List<Integer> arr) {
		// 정렬함
		arr = arr.stream().sorted().collect(Collectors.toList());
		boolean[] visited = new boolean[n]; // 두 번 이상 세면 안된다.
		// 둘 다 0이 아니거나 둘 중 하나가 0이거나 둘 다 0인 경우
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				int sum = arr.get(i) + arr.get(j);
				int lb = lowerBound(n, arr, sum);
				int ub = upperBound(n, arr, sum);
				// 둘 다 0일 때 0이 세 개 이상일 경우 모두 visited
				if (arr.get(i) == 0 && arr.get(j) == 0) {
					if (ub - lb > 2) {
						for (int k = lb; k < ub; k++) visited[k] = true;
					}
				} else if (arr.get(i) == 0 || arr.get(j) == 0) { // 둘 중 하나가 0일 경우
					if (ub - lb > 1) {
						for (int k = lb; k < ub; k++) visited[k] = true;
					}
				} else { // 둘 다 0이 아닐 경우
					// 값이 없다면 lb ub가 같을 것이다.
					for (int k = lb; k < ub; k++) visited[k] = true;
				}
			}
		}
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (visited[i]) count++;
		}
		return count;
	}
	
	private int upperBound(int n, List<Integer> sort, int key) {
		int start = 0; int end = n;
		while (start < end) {
			int mid = start + (end - start) / 2;
			if (sort.get(mid) <= key) start = mid + 1;
			else end = mid;
		}
		return start;
	}
	
	private int lowerBound(int n, List<Integer> sort, int key) {
		int start = 0; int end = n;
		while (start < end) {
			int mid = start + (end - start) / 2;
			if (sort.get(mid) < key) start = mid + 1;
			else end = mid;
		}
		return start;
	}
}
