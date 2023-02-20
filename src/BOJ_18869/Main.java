package BOJ_18869;

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
		String[] input1 = br.readLine().split(" ");
		int n = Integer.parseInt(input1[0]);
		int m = Integer.parseInt(input1[1]);
		
		List<List<Integer>> universe = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			List<Integer> u = new ArrayList<>();
			String[] input2 = br.readLine().split(" ");
			for (int j = 0; j < m; j++) u.add(Integer.parseInt(input2[j]));
			universe.add(u);
		}
		
		System.out.println(T.solution(universe, n, m));
		
		br.close();
	}
	
	public int solution(List<List<Integer>> universe, int n, int m) {
		int count = 0;
		List<StringBuilder> compression = new ArrayList<>(); // 좌표 압축
		for (int i = 0; i < n; i++) { // 모든 리스트 정렬
			List<Integer> sortList = new ArrayList<>(universe.get(i));
			Collections.sort(sortList);
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < m; j++) { // 정렬된 리스트에서 위치를 찾음
				int v = Collections.binarySearch(sortList, universe.get(i).get(j));
				sb.append(v);
			}
			compression.add(sb);
		}
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				// 시간초과를 줄이기 위해 m 만큼 돌면서 구하는게 아닌 stringbuilder로 만들어서 비교
				if (compression.get(i).compareTo(compression.get(j)) == 0) count++;
			}
		}
		return count;
	}
	
}
