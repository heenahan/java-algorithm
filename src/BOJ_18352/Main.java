package BOJ_18352;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input1 = br.readLine().split(" ");
		int n = Integer.parseInt(input1[0]); // 도시
		int m = Integer.parseInt(input1[1]); // 도로
		int k = Integer.parseInt(input1[2]); // 최단 거리
		int x = Integer.parseInt(input1[3]); // 출발 도시
		
		List<List<Integer>> gp = new ArrayList<>();
		for (int i = 0; i <= n; i++) gp.add(new ArrayList<>());
		for (int i = 0; i < m; i++) {
			String[] input2 = br.readLine().split(" ");
			int st = Integer.parseInt(input2[0]);
			int ed = Integer.parseInt(input2[1]);
			gp.get(st).add(ed);
		}
		
		T.solution(n, k, x, gp);
		
		br.close();
		bw.close();
	}
	
	public void solution(int n, int k, int x, List<List<Integer>> gp) throws IOException {
		int[] visited = new int[n + 1]; // 방문
		List<Integer> arr = new ArrayList<>();
		Queue<Integer> q = new LinkedList<>();
		q.add(x); // 시작 도시
		visited[x] = 1;
		
		while (!q.isEmpty()) {
			int v = q.poll();
			
			if (visited[v] - 1 == k) {
				arr.add(v);
				continue; // 다음 방문 안함
			}
			
			for (int num : gp.get(v)) {
				if (visited[num] > 0) continue; // 이미 방문했으면 종료
				visited[num] = visited[v] + 1;
				q.add(num);
			}
		}
		if (arr.isEmpty()) bw.write(-1 + "\n");
		else {
			arr.stream().sorted().forEach(num -> { // 정렬 후 출력
				try {
					bw.write(num + "\n");
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			});
		}
 	}
}
