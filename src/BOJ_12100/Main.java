package BOJ_12100;

import java.io.*;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
	
	static int num;
	static int max = Integer.MIN_VALUE;
	// → ← ↑ ↓
	int[] start_x = { 0, 0, 0, num - 1 };
	int[] start_y = { num - 1, 0, 0, 0 };
	int[] ix = { 1, 1, -num, num };
	int[] iy = { num, -num, 1, 1 };
	int[] jx = { 0, 0, 1, -1 };
	int[] jy = { -1, 1, 0, 0 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(br.readLine());
		
		Main T = new Main();
		int[][] board = new int[num][num];
		for (int i = 0; i < num; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < num; j++) {
				board[i][j] = Integer.parseInt(input[j]);
				if (board[i][j] > max) max = board[i][j];
			}
		}
		
		T.solution(0, board);
		System.out.print(max);
		
		br.close();
	}
	
	public void solution(int n, int[][] board) {
		if (n == 5) return; // 최대 5번 움직임
		
		int[][] move = new int[num][num];
		Queue<Integer> q = new LinkedList<>();
		
		for (int i = 0; i < 4; i++) {
			int x = start_x[i];
			int y = start_y[i];
			int mx  = start_x[i];
			int my = start_y[i];
			while (x >= 0 && y >= 0 && x < num && y < num) {
				while (x >= 0 && y >= 0 && x < num && y < num) {
					if (board[x][y] != 0) q.add(board[x][y]);
					x += jx[i]; y += jy[i];
				}
				Integer before = q.poll(); // 2 4 4 2
				while (mx >= 0 && my >= 0 && mx < num && my < num) {
					Integer v = q.poll();
					
					if (before == null) move[mx][my] = 0;
					else if (before.equals(v)) {
						move[mx][my] = before * 2;
						before = q.poll();
					} else {
						move[mx][my] = before;
						before = v;
					}
					if (move[mx][my] > max) max = move[mx][my];
					mx += jx[i]; my += jy[i];
				}
				x += ix[i]; y += iy[i]; mx += ix[i]; my += iy[i];
			}
			
			solution(n + 1, move);
		}
	}
}
