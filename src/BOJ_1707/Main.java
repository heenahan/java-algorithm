package BOJ_1707;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int test = Integer.parseInt(br.readLine());
		for (int i = 0; i < test; i++) {
			String[] input1 = br.readLine().split(" ");
			int n = Integer.parseInt(input1[0]);
			int m = Integer.parseInt(input1[1]);
			
			List<List<Integer>> arr = new ArrayList<>();
			for (int j = 0; j <= n; j++) arr.add(new ArrayList<>());
			for (int j = 0; j < m; j++) {
				String[] input2 = br.readLine().split(" ");
				int st = Integer.parseInt(input2[0]);
				int ed = Integer.parseInt(input2[1]);
				arr.get(st).add(ed);
			}
			if (T.solution(n, arr)) System.out.println("YES");
			else System.out.println("NO");
		}
		
		br.close();
	}
	
	public boolean solution(int num, List<List<Integer>> arr) {
		// 정점을 모두 돈다.
		for (int i = 1; i <= num; i++) {
			Queue<Integer> q = new LinkedList<>();
			int[] parent = new int[num + 1]; // 사이클을 판별하기 위함, 자신의 부모
			q.add(i);
			parent[i] = Integer.MAX_VALUE; // 루트의 부모는 최댓값
			
			while(!q.isEmpty()) {
				int v = q.poll();
				
				for (int next : arr.get(v)) {
					// 이번 bfs에서 자신의 조상으로 돌아갔다면 사이클이 생긴 것
					if (parent[next] > 0) {
						int p = parent[v];
						while (p < Integer.MAX_VALUE) {
							if (p == next) return false;
							p = parent[p];
						}
					}
					parent[next] = v; // 자신의 부모
					q.add(next);
				}
			}
		}
		return true;
	}
	
}
