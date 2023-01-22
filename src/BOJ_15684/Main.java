package BOJ_15684;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Node {
	private int level; private boolean[][] ladder;
	
	public Node(int level, boolean[][] ladder) {
		this.level = level;
		this.ladder = ladder;
	}
	
	public int getLevel() {
		return level;
	}
	
	public boolean[][] getLadder() {
		return ladder;
	}
}

public class Main {
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		int h = Integer.parseInt(input[2]);
		
		boolean[][] ladder = new boolean[h][n - 1];
		for (int i = 0; i < m; i++) {
			String[] l = br.readLine().split(" ");
			int x = Integer.parseInt(l[0]); int y = Integer.parseInt(l[1]);
			ladder[x - 1][y - 1] = true;
		}
		
		System.out.println(T.solution(h, n - 1, ladder));
		
		br.close();
	}
	
	public int solution(int n, int m, boolean[][] ladder) {
		Queue<Node> q = new LinkedList<>();
		Node node = new Node(0, ladder);
		q.add(node);
		
		while (!q.isEmpty()) {
			Node v = q.poll();
			int level = v.getLevel();
			boolean[][] ladders = v.getLadder();
			
			for (int i = 0; i < m + 1; i++) { // 세로선 만큼
				int col = i;
				for (int j = 0; j < n; j++) { // 높이만큼
					if (col > 0 && ladders[j][col - 1]) col--;
					if (col < m && ladders[j][col]) col++;
				}
				if (col != i) break; // i -> i로 내려오지 않았으면 종료
				if (i == m) return level;
			}
			
			if (level == 3) continue; // 3개의 사다리를 놓았음에도 조작이 안될경우
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (ladders[i][j]) continue; // 이미 사다리가 놓여져 있으면
					if (j > 0 && ladders[i][j - 1]) continue; // 연속된 사다리는 불가능
					if (j < m - 1 && ladders[i][j + 1]) continue;
					boolean[][] next = Arrays.stream(ladders).toArray(boolean[][]::new);
					next[i][j] = true;
					q.add(new Node(level + 1, next));
				}
			}
		}
		return -1;
	}
}
