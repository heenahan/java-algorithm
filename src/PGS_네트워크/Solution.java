package PGS_네트워크;

import java.util.Queue;
import java.util.LinkedList;

class Solution {
	
	boolean[] visited;
	
	public int solution(int n, int[][] computers) {
		int answer = 0;
		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				bfs(i, n, computers);
				answer++;
			}
		}
		return answer;
	}
	
	public void bfs(int start, int num, int[][] computers) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visited[start] = true;
		
		while (!q.isEmpty()) {
			int v = q.poll();
			
			for (int i = 0; i < num; i++) {
				if (visited[i] || computers[v][i] != 1) continue;
				visited[i] = true;
				q.add(i);
			}
		}
	}
}