package BOJ_1707;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int k = Integer.parseInt(br.readLine());
		for (int i = 0; i < k; i++) {
			String[] ve = br.readLine().split(" ");
			int v = Integer.parseInt(ve[0]);
			int e = Integer.parseInt(ve[1]);

			Map<Integer, List<Integer>> graph = new HashMap<>();
			for (int j = 0; j < e; j++) {
				String[] ab = br.readLine().split(" ");
				int a = Integer.parseInt(ab[0]);
				int b = Integer.parseInt(ab[1]);

				graph.putIfAbsent(a, new ArrayList<>());
				graph.putIfAbsent(b, new ArrayList<>());

				graph.get(a).add(b);
				graph.get(b).add(a);
			}
			String answer = null;
			int[] visited = new int[v + 1];
			for (int j = 1; j <= v; j++) {
				if (visited[j] <= 0) { // 아직 방문 안했다면
					answer = bfs(graph, visited, j);
					if (Objects.equals(answer, "NO")) break;
				}
			}
			System.out.println(answer);
		}

		br.close();
	}

	private static String bfs(Map<Integer, List<Integer>> graph, int[] visited, int st) {
		Queue<Integer> q = new LinkedList<>();
		q.add(st); visited[st] = 1;

		while (!q.isEmpty()) {
			int v = q.poll();
			int vl = visited[v] % 2;

			for (int nv : graph.getOrDefault(v, new ArrayList<>())) {
				// 같은 레벨이면 NO
				if (visited[nv] > 0 && visited[nv] % 2 == vl) return "NO";
				if (visited[nv] > 0) continue;
				q.add(nv); visited[nv] = visited[v] + 1;
			}
		}
		return "YES";
	}
}