package BOJ_18869;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
		
		System.out.println(T.solution(universe));
		
		br.close();
	}
	
	public int solution(List<List<Integer>> universe) {
		int count = 0;
		List<List<Integer>> sortUniverse = new ArrayList<>();
		for (int i = 0; i < universe.size(); i++) { // 모든 리스트 정렬
			List<Integer> sortList = universe.get(i).stream().sorted().collect(Collectors.toList());
			sortUniverse.add(sortList);
		}
		for (int i = 0; i < universe.size(); i++) {
			List<Integer> u = universe.get(i);
			for (int j = i + 1; j < universe.size(); j++) {
				List<Integer> compare = universe.get(j);
				boolean fail = false;
				for (int k = 0; k < u.size(); k++) {
					int v = Collections.binarySearch(sortUniverse.get(i), u.get(k));
					int cv = Collections.binarySearch(sortUniverse.get(j), compare.get(k));
					if (v != cv) {
						fail = true;
						break;
					}
				}
				if (!fail) count++;
			}
		}
		return count;
	}
	
}
