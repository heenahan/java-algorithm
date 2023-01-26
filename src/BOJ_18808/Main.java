package BOJ_18808;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	
	static boolean[][] notebook;
	static int n; static int m;
	
	static int[] startX;
	static int[] startY;
	static int[] ix;
	static int[] iy;
	static int[] jx;
	static int[] jy;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input1 = br.readLine().split(" ");
		n = Integer.parseInt(input1[0]);
		m = Integer.parseInt(input1[1]);
		int size = Integer.parseInt(input1[2]);
		notebook = new boolean[n][m];
		
		for (int i = 0; i < size; i++) {
			String[] input2 = br.readLine().split(" ");
			int x = Integer.parseInt(input2[0]);
			int y = Integer.parseInt(input2[1]);
			boolean[][] sticker = new boolean[x][y];
			for (int j = 0; j < x; j++) {
				String[] input3 = br.readLine().split(" ");
				for (int k = 0; k < y; k++) {
					if (Integer.parseInt(input3[k]) == 1) sticker[j][k] = true;
				}
			}
			T.solution(x, y, sticker);
		}
		
		int sum = 0;
		for (boolean[] row : notebook) {
			for (boolean b : row) if (b) sum++;
		}
		System.out.println(sum);
		
		br.close();
	}
	
	public void setXY(int x, int y) {
		// 0 90 180 270
		startX = new int[]{ 0, x - 1, x - 1, 0 }; // 2사분면, 3사분면, 4사분면, 1사분면
		startY = new int[]{ 0, 0, y - 1, y - 1 };
		ix = new int[]{ 1, x, -1, -x };
		iy = new int[]{ -y, 1, y, -1 };
		jx = new int[]{ 0, -1, 0, 1 };
		jy = new int[]{ 1, 0, -1, 0 };
	}
	
	public void solution(int x, int y, boolean[][] sticker) {
		setXY(x, y);
		
		boolean flag = false;
		for (int i = 0; i < 4; i++) { // 4번 회전
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					// 올바르다 했으니 남은 행과 열이 스티커 사이즈 넘어가면 패스
					if (i % 2 == 0 && (x > n - j || y > m - k)) continue;
					if (i % 2 == 1 && (y > n - j || x > m - k)) continue;
					if (flag = attach(j, k, i, x, y, sticker)) break;
				}
				if (flag) break;
			}
			if (flag) break; // 붙였으면 회전하지 않고 종료
		}
	}
	
	// 시작 회전, 스티커 크기, 스티커 모양
	public boolean attach(int row, int col, int rotation, int x, int y, boolean[][] sticker) {
		boolean[][] next = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) next[i][j] = notebook[i][j];
		}
		
		int i = row; int j = col; // 노트북의 좌표
		int nx = startX[rotation]; int ny = startY[rotation]; // 스티커의 좌표
		while (nx >= 0 && ny >= 0 && nx < x && ny < y) {
			while (nx >= 0 && ny >= 0 && nx < x && ny < y) {
				if (sticker[nx][ny] && next[i][j]) return false; // 붙여져 있다면
				if (sticker[nx][ny] && !next[i][j]) next[i][j] = true;
				nx += jx[rotation]; ny += jy[rotation];
				j++;
			}
			nx += ix[rotation]; ny += iy[rotation];
			i++; j = col;
		}
		notebook = next;
		return true;
	}
	
}
