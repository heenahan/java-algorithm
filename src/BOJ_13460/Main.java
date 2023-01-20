package BOJ_13460;

import java.io.*;

public class Main {
	
	static char[][] board;
	static int hole_x; // 구멍 위치
	static int hole_y;
	static int[] dx = { 1, 0, -1, 0 }; // 하 오 상 왼
	static int[] dy = { 0, 1, 0, -1 };
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input1 = br.readLine().split(" ");
		int n = Integer.parseInt(input1[0]);
		int m = Integer.parseInt(input1[1]);
		
		board = new char[n][m];
		int[][] d = new int[2][2];
		for (int i = 0; i < n; i++) {
			char[] input2 = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				board[i][j] = input2[j];
				if (board[i][j] == 'R') {
					d[0][0] = i;
					d[0][1] = j;
				} else if (board[i][j] == 'B') {
					d[1][0] = i;
					d[1][1] = j;
				} else if (board[i][j] == 'O') {
					hole_x = i;
					hole_y = j;
				}
			}
		}
		
		T.solution(0, d);
		if (result != Integer.MAX_VALUE) System.out.println(result);
		else System.out.println(-1);
		
		br.close();
	}
	
	// dic[0] red x y, dic[1] blue x y
	public void solution(int num, int[][] d) {
		if (num == 10) return;
		
		int[][] priority = new int [4][2];
		
		if (d[0][0] == d[1][0]) { // 행이 같다
			// 빨간색 구슬의 열이 크다
			if (d[0][1] > d[1][1]) {
				priority[1][1] = 1; // 오른쪽일 때 파란색 구슬이 나중
				priority[3][0] = 1; // 왼쪽일 때 파란색 구슬이 먼저
			} else {
				priority[1][0] = 1;
				priority[3][1] = 1;
			}
		} else if (d[0][1] == d[1][1]) {// 열이 같다.
			// 빨간색 구슬의 행이 크다
			if (d[0][0] > d[1][0]) {
				priority[0][1] = 1; // 아래쪽일 때 파란색 구슬이 나중
				priority[2][0] = 1;
			} else {
				priority[0][0] = 1;
				priority[2][1] = 1;
			}
		}
		
		for (int i = 0; i < 4; i++) {
			// 우선순위 정해지지 않았다면
			if (priority[i][0] == priority[i][1]) priority[i][1] = 1;
			boolean[] isHole = new boolean[2]; // 매번 다른 움직임으로 초기화
			int[][] next_d = new int[2][2];
			for (int j = 0; j < 2; j++) {
				int color = priority[i][j];
				int x = d[color][0];
				int y = d[color][1];
				while (true) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					if (nx == hole_x && ny == hole_y) { // 구멍에 들어갔으면 종료
						isHole[color] = true;
						x = nx; y = ny;
						break;
					}
					if (board[nx][ny] == '#') break;
					// 먼저 움직인 구슬과 부딪히면
					if (next_d[(color + 1) % 2][0] == nx && next_d[(color + 1) % 2][1] == ny) break;
					
					x = nx; y = ny;
				}
				next_d[color][0] = x;
				next_d[color][1] = y;
			}
			if (isHole[0] && !isHole[1]) { // 빨간색 구슬만 빠졌다면 종료
				result = Math.min(num + 1, result);
			} else if (!isHole[0] && !isHole[1]) solution(num + 1, next_d); // 둘 다 안빠졌으면 다음 움직임
		}
	}
	
}
