package BOJ_1325;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static List<List<Integer>> gp = new ArrayList<>();
	static int[] trust;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input1 = br.readLine().split(" ");
		int n = Integer.parseInt(input1[0]);
		int m = Integer.parseInt(input1[1]);
		
		for (int i = 0; i <= n; i++) gp.add(new ArrayList<>());
		trust = new int[n + 1];
		for (int i = 0; i < m; i++) {
			String[] input2 = br.readLine().split(" ");
			int st = Integer.parseInt(input2[0]);
			int ed = Integer.parseInt(input2[1]);
			gp.get(st).add(ed);
		}
		
		T.solution(n);
		
		br.close();
		bw.close();
	}
	
	public void solution(int n) throws IOException {
		for (int i = 1; i <= n; i++) dfs(n, i);
		int max = Integer.MIN_VALUE;
		for (int t : trust) {
			if (t > max) max = t;
		}
		for (int i = 1; i <= n; i++) {
			if (trust[i] == max) bw.write(i + " ");
		}
	}
	
	private void dfs(int n, int st) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[n + 1];
		
		q.add(st);
		visited[st]= true;
		while (!q.isEmpty()) {
			int v = q.poll();
			
			for (int next : gp.get(v)) {
				if (visited[next]) continue;
				q.add(next);
				visited[next] = true;
				trust[next]++;
			}
		}
	}
	
}
