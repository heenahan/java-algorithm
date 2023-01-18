package BOJ_14503;

import java.io.*;
import java.util.Queue;
import java.util.LinkedList;

class Node {
	private int x; private int y;
	private int d; // 바라보는 방향
	
	public Node(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getD() {
		return d;
	}
}

public class Main {
	
	static int[][] room;
	static boolean[][] visited;
	static int[] dx = { -1, 0, 1, 0 }; // 북 동 남 서
	static int[] dy = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input1 = br.readLine().split(" ");
		int n = Integer.parseInt(input1[0]);
		int m = Integer.parseInt(input1[1]);
		
		String[] input2 = br.readLine().split(" ");
		int r = Integer.parseInt(input2[0]);
		int c = Integer.parseInt(input2[1]);
		int d = Integer.parseInt(input2[2]);
		
		room = new int[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			String[] input3 = br.readLine().split(" ");
			for (int j = 0; j < m; j++) room[i][j] = Integer.parseInt(input3[j]);
		}
		
		System.out.println(T.solution(n, m, r, c, d));
		
		br.close();
	}
	
	public int solution(int n, int m, int r, int c, int d) {
		Queue<Node> q = new LinkedList<>();
		visited[r][c] = true;
		q.add(new Node(r, c, d));
		
		int clean = 1;
		
		while (!q.isEmpty()) { // 큐에는 항상 하나의 노드만 들어있다
			Node v = q.poll();
			int x = v.getX();
			int y = v.getY();
			int dic = v.getD();
			
			for (int i = 1; i < 5; i++) { // 반시계로 회전한다.
				int nx = x + dx[(dic - i + 4) % 4];
				int ny = y + dy[(dic - i + 4) % 4];
				
				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				if (visited[nx][ny] || room[nx][ny] != 0) continue;
				Node next = new Node(nx, ny, (dic - i + 4) % 4);
				visited[nx][ny] = true;
				clean++;
				q.add(next);
				break;
			}
			
			// 4 방향 모두 청소할 공간이 없다면 큐는 빈 상태
			if (q.isEmpty()) {
				int nx = x + dx[(dic + 2)  % 4];
				int ny  = y + dy[(dic + 2) % 4];
				
				if (room[nx][ny] != 1) { // 벽이 아니라면 후진
					Node back = new Node(nx, ny, dic); // 바라보는 방향은 여전히 동일
					if (!visited[nx][ny]) clean++; // 청소하지 않았으면 청소함
					visited[nx][ny] = true;
					q.add(back);
				}
			}
		}
		
		return clean;
	}
}
