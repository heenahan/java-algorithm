package PGS_미로_탈출;

import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

class Node {
	private int x; private int y;
	private boolean lever;
	
	public Node(int x, int y, boolean lever) {
		this.x = x;
		this.y = y;
		this.lever = lever;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isLever() {
		return lever;
	}
}

class Solution {
	
	int[][] visitedWhenNotLever; // 레버를 안당겼을 때 최솟값
	int[][] visitedWhenLever; // 레버를 당겼을 때 최소값
	int[] dx = { 1, 0, -1, 0 };
	int[] dy = { 0, 1, 0, -1 };
	
	public int solution(String[] maps) {
		int n = maps.length;
		int m = maps[0].length();
		visitedWhenNotLever = new int[n][m];
		visitedWhenLever = new int[n][m];
		char[][] map = Arrays.stream(maps).map(String::toCharArray).toArray(char[][]::new);
		
		Queue<Node> q = new LinkedList<>();
		// 시작 지점 찾으면 큐에 넣고 종료
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 'S') {
					q.add(new Node(i, j, false));
					visitedWhenNotLever[i][j] = 1;
					break;
				}
			}
			if (!q.isEmpty()) break;
		}
		
		while (!q.isEmpty()) {
			Node v = q.poll();
			int x = v.getX();
			int y = v.getY();
			boolean isLever = v.isLever();
			
			if (isLever && map[x][y] == 'E') return visitedWhenLever[x][y] - 1; // 처음은 0이다.
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				if (map[nx][ny] == 'X') continue; // 지나갈 수 없으면
				boolean nextLever = isLever;
				if (map[nx][ny] == 'L') nextLever = true; // 다음 방문이 레버면 true로 변환
				// 이미 방문 했으면 종료
				if ((nextLever && visitedWhenLever[nx][ny] > 0) || (!nextLever && visitedWhenNotLever[nx][ny] > 0)) continue;
				q.add(new Node(nx, ny, nextLever));
				if (isLever && nextLever) visitedWhenLever[nx][ny] = visitedWhenLever[x][y] + 1;
				else if (!isLever && nextLever) visitedWhenLever[nx][ny] = visitedWhenNotLever[x][y] + 1;
				else if (!isLever && !nextLever) visitedWhenNotLever[nx][ny] = visitedWhenNotLever[x][y] + 1;
			}
		}
		
		return -1;
	}
	
}