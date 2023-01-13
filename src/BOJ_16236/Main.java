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
		int move = 0;
		
		while (!q.isEmpty()) {
			Node v = q.poll();
			int x = v.getX();
			int y = v.getY();
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= num || ny >= num) continue;
				// 이미 방문했거나 물고기가 상어 사이즈보다 크다면 방문 못함
				if (visited[nx][ny] > 0 || space[nx][ny] > shark_size) continue;
				if (space[nx][ny] == shark_size || space[nx][ny] == 0) { // 상어 사이즈와 같다면 이동만 가능
					Node next = new Node(nx, ny);
					visited[nx][ny] = visited[x][y] + 1;
					q.add(next);
				} else { // 상어 사이즈보다 작다면 먹을 수 있는 물고기 중 가장 가까운 물고기이다.
					move += visited[x][y]; // 여기까지 오는데 걸린 움직임 더함
					fish++;
					
					if (shark_size == fish) {
						shark_size++;
						fish = 0;
					}
					
					// 모두 초기화
					q = new LinkedList<>();
					visited = new int[num][num];
					shark_x = nx;
					shark_y = ny;
					
					visited[shark_x][shark_y] = 1;
					space[shark_x][shark_y] = 0;
					q.add(new Node(shark_x, shark_y));
					
					break; // 먹을 수 있는 물고기를 발견했으면 다음 방문 안함
				}
			}
		}
		
		return move;
	}
}
