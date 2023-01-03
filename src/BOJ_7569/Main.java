package BOJ_7569;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;

class Node {
	private int x; private int y; private int z;
	
	public Node(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getZ() {
		return z;
	}
}

public class Main {
	
	static Queue<Node> q = new LinkedList<Node>();
	static int[][][] visited;
	static int[][][] tomato;
	int[] dx = { 0, 1, 0, -1, 0, 0 };
	int[] dy = { 1, 0, -1, 0, 0, 0 };
	int[] dz = { 0, 0, 0, 0, 1, -1 };
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input1 = br.readLine().split(" ");
		int m = Integer.parseInt(input1[0]);
		int n = Integer.parseInt(input1[1]);
		int h = Integer.parseInt(input1[2]);
		visited = new int[h][n][m];
		tomato = new int[h][n][m];
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				String[] input2 = br.readLine().split(" ");
				for (int k = 0; k < m; k++) {
					tomato[i][j][k] = Integer.parseInt(input2[k]);
					if (tomato[i][j][k] == 1) {
						q.add(new Node(j, k, i));
						visited[i][j][k] = 1; // 익은 토마토 방문함
					}
				}
			}
		}
		
		System.out.println(T.solution(n, m, h));
		
		br.close();
	}
	
	public int solution(int n, int m, int h) {
		while (!q.isEmpty()) {
			Node v = q.poll();
			int x = v.getX();
			int y = v.getY();
			int z = v.getZ();
			
			for (int i = 0; i < 6; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nz = z + dz[i];
				
				if (nx < 0 || ny < 0 || nz < 0 || nx >= n || ny >= m || nz >= h) continue;
				if (tomato[nz][nx][ny] != 0 || visited[nz][nx][ny] > 0) continue; // 이미 방문했거나 익지 않은 토마토가 아니거나
				
				visited[nz][nx][ny] = visited[z][x][y] + 1;
				tomato[nz][nx][ny] = 1; // 익은 토마토로 만듦
				Node next = new Node(nx, ny, nz);
				q.add(next);
			}
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					if (tomato[i][j][k] == 0) return -1;
					if (visited[i][j][k] > max) max = visited[i][j][k];
				}
			}
		}
		return max - 1;
	}
}
