package BOJ_1261;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Node {
	private int x; private int y;
	private int broken; // 부순 벽의 갯수
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}

public class Main {
	
	static int[][] maze;
	static boolean[][] visited;
	static int[][] broken; // 부순 벽의 개수
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input1 = br.readLine().split(" ");
		int m = Integer.parseInt(input1[0]); // 행 열이 아닌 열 행으로 주어짐ㅠㅠ
		int n = Integer.parseInt(input1[1]);
		
		maze = new int[n][m];
		visited = new boolean[n][m];
		broken = new int[n][m];
		for (int i = 0; i < n; i++) {
			String[] input2 = br.readLine().split("");
			for (int j = 0; j < m; j++) maze[i][j] = Integer.parseInt(input2[j]);
		}
		System.out.println(T.solution(n, m));
		
		br.close();
	}
	
	public int solution(int n, int m) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0));
		visited[0][0] = true;
		
		while (!q.isEmpty()) {
			Node v = q.poll();
			int x = v.getX();
			int y = v.getY();
			int wall = broken[x][y];
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				// 이미 방문했고 발견한 부순 벽의 수보다 크다면
				if (visited[nx][ny] && wall >= broken[nx][ny]) continue;
				// 방문 안했다면 초기화
				if (!visited[nx][ny]) broken[nx][ny] = wall;
				else broken[nx][ny] = Math.min(wall, broken[nx][ny]);
				if (maze[nx][ny] == 1) broken[nx][ny]++;
				visited[nx][ny] = true;
				q.add(new Node(nx, ny));
			}
		}
		return broken[n - 1][m - 1];
	}
}
