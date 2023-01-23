package BOJ_15684;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	
	static boolean[][] ladder;
	static int n;
	static int h;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		h = Integer.parseInt(input[2]);
		
		ladder = new boolean[h][n - 1];
		for (int i = 0; i < m; i++) {
			String[] l = br.readLine().split(" ");
			int x = Integer.parseInt(l[0]); int y = Integer.parseInt(l[1]);
			ladder[x - 1][y - 1] = true;
		}
		
		int result = T.solution(0);
		if (result != Integer.MAX_VALUE) System.out.println(result);
		else System.out.println(-1);
		
		br.close();
	}
	
	public int solution(int level) {
		for (int i = 0; i < n; i++) { // 세로선 만큼
			int col = i;
			for (int j = 0; j < h; j++) { // 높이만큼
				if (col > 0 && ladder[j][col - 1]) col--;
				else if (col < n - 1 && ladder[j][col]) col++;
			}
			if (col != i) break; // i -> i로 내려오지 않았으면 종료
			if (i == n - 1) return level;
		}
		
		// 4에서 끊을경우 사다리 놓는 과정을 더 실시해서 시간초과 발생
		if (level == 3) return Integer.MAX_VALUE; // 3개의 사다리를 놓았음에도 조작이 안되는 경우
		
		int result = Integer.MAX_VALUE;
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n - 1; j++) {
				if (ladder[i][j]) continue; // 이미 사다리가 놓여져 있으면
				if (j > 0 && ladder[i][j - 1]) continue; // 연속된 사다리는 불가능
				if (j < n - 2 && ladder[i][j + 1]) continue;
				ladder[i][j] = true;
				result = Math.min(solution(level + 1), result);
				ladder[i][j] = false;
			}
		}
		return result;
	}
}
