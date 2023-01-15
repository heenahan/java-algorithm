package BOJ_21608;

import java.io.*;
import java.util.stream.IntStream;

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
	
	// int[i][0] i번째 학생, int[i][1] ~ int[i][4] i번째 학생이 좋아하는 학생
	static int[][] students;
	static int[][] seat; // 결정난 자리
	static Node[] node;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		students = new int[num * num][5];
		seat = new int[num][num];
		node = new Node[num * num + 1] ;
		for (int i = 0; i < num * num; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < 5; j++) students[i][j] = Integer.parseInt(input[j]);
		}
		
		System.out.println(T.solution(num));
		
		br.close();
	}
	
	public int solution(int num) {
		for (int[] student : students) {
			// 우선 순위가 가장 높은 칸
			int priority_x = Integer.MAX_VALUE;
			int priority_y = Integer.MAX_VALUE;
			int favorite = Integer.MIN_VALUE; // 인접한 칸에 좋아하는 친구 수
			int blank = Integer.MIN_VALUE; // 인접한 칸에 비어있는 칸의 수
			
			for (int i = 0; i < num; i++) {
				for (int j = 0; j < num; j++) {
					if (seat[i][j] > 0) continue; // 이미 정해진 자리면 패스
					int fav = 0; int blk = 0;
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						
						if (nx < 0 || ny < 0 || nx >= num || ny >= num) continue;
						for (int fav_st : IntStream.range(1, 5).map(r -> student[r]).toArray()) {
							if (seat[nx][ny] == fav_st) fav++;
						}
						if (seat[nx][ny] == 0) blk++;
					}
					// 우선순위 친구 수 > 빈칸 수 > 위쪽 > 왼쪽
					if ((fav > favorite)
							    || (fav == favorite && blk > blank)
							    || (fav == favorite && blk == blank && priority_x > i)
								|| (fav == favorite && blk == blank && priority_x == i && priority_y > j)) {
						priority_x = i;
						priority_y = j;
						favorite = fav;
						blank = blk;
					}
				}
			}
			seat[priority_x][priority_y] = student[0];
			node[student[0]] = new Node(priority_x, priority_y);
		}
		int sum = 0;
		for (int[] student : students) {
			Node v = node[student[0]];
			int x = v.getX();
			int y = v.getY();
			int fav = 0;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= num || ny >= num) continue;
				for (int fav_std : IntStream.range(1, 5).map(j -> student[j]).toArray()) {
					if (fav_std == seat[nx][ny]) fav++;
				}
			}
			
			if (fav > 0) sum += Math.pow(10, fav - 1);
		}
		
		return sum;
	}
}
