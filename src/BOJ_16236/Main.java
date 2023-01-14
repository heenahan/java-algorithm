package BOJ_16236;

import java.util.Queue;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Node {
	private int x; private int y;
	
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
	
	static int shark_x;
	static int shark_y;
	static int shark_size = 2;
	
	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, -1, 1, 0 };
	
	static int[][] visited;
	static int[][] space;
	
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		visited = new int[num][num];
		space = new int[num][num];
		for (int i = 0; i < num; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < num; j++) {
				space[i][j] = Integer.parseInt(input[j]);
				if (space[i][j] == 9) {
					shark_x = i;
					shark_y = j;
				}
			}
		}
		
		System.out.println(T.solution(num));
		
		br.close();
	}
	
	public int solution(int num) {
		Queue<Node> q = new LinkedList<>();
		visited[shark_x][shark_y] = 1;
		space[shark_x][shark_y] = 0;
		q.add(new Node(shark_x, shark_y));
		
		int fish = 0; // 상어가 레벨업하고 먹은 물고기
		int move = 0; // 상어의 움직임
		int next_x = Integer.MAX_VALUE; // 상어 다음 방문
		int next_y = Integer.MAX_VALUE;
		int level = Integer.MAX_VALUE;
		
		while (!q.isEmpty()) {
			Node v = q.poll();
			int x = v.getX();
			int y = v.getY();
			
			if (shark_size > space[x][y] && space[x][y] != 0){ // 상어 사이즈보다 작은 물고기를 방문했다면
				if (level > visited[x][y]) {
					next_x = x;
					next_y = y;
					level = visited[x][y];
				} else if (level == visited[x][y]) {
					if (next_x > x || (next_x == x && next_y > y)) {
						next_x = x;
						next_y = y;
						level = visited[x][y];
					}
				}
				
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= num || ny >= num) continue;
				// 이미 방문했거나 물고기가 상어 사이즈보다 크다면 방문 못함
				if (visited[nx][ny] > 0 || space[nx][ny] > shark_size) continue;
				
				visited[nx][ny] = visited[x][y] + 1;
				Node next = new Node(nx, ny);
				q.add(next);
			}
			
			if (q.isEmpty() && next_x < num && next_y < num) { // 모두 탐색했고 방문할 수 있는 물고기를 찾았을 때
				move += level - 1;
				space[next_x][next_y] = 0;
				fish++;
				
				if (shark_size == fish) {
					shark_size++;
					fish = 0;
				}
				
				visited = new int[num][num];
				
				shark_x = next_x;
				shark_y = next_y;
				
				visited[shark_x][shark_y] = 1;
				space[shark_x][shark_y] = 0;
				q.add(new Node(shark_x, shark_y));
				
				next_x = Integer.MAX_VALUE;
				next_y = Integer.MAX_VALUE;
				level = Integer.MAX_VALUE;
			}
		}
		
		return move;
	}
}
