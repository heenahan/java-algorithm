package BOJ_15683;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

class Node {
	
	private int num; // CCTV 번호
	private int x; private int y;
	
	public Node(int num, int x, int y) {
		this.num = num;
		this.x = x;
		this.y = y;
	}
	
	public int getNum() {
		return num;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}

public class Main {
	
	static List<Node> arr = new ArrayList<>();
	static int n; static int m;
	static int[] dx = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dy = { 1, 0, -1, 0 };
	static int[] l = { 1, 2, 2, 3, 4 }; // 감시하는 방향 수
	static int[] rotation = { 4, 2, 4, 4, 1 }; // 회전 수
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input1 = br.readLine().split(" ");
		n = Integer.parseInt(input1[0]);
		m = Integer.parseInt(input1[1]);
		
		char[][] office = new char[n][m];
		for (int i = 0; i < n; i++) {
			String[] input2 = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				office[i][j] = input2[j].charAt(0);
				// 1 ~ 5일경우
				if (office[i][j] > '0' && office[i][j] < '6') arr.add(new Node(office[i][j] - 48, i, j));
			}
		}
		
		System.out.println(T.solution(0, office));
		
		br.close();
	}
	
	public int solution(int index, char[][] office) {
		if (index == arr.size()) {
			int sum = 0;
			for (char[] row : office) {
				for (char c : row) if (c == '0') sum++;
			}
			return sum;
		}
		int min = Integer.MAX_VALUE;
		Node v = arr.get(index);
		int num = v.getNum();
		int x = v.getX(); int y = v.getY();
		
		for (int i = 0; i < rotation[num - 1]; i++) { // 회전
			char[][] watching = new char[n][m];
			for (int k = 0; k < n; k++) {
				for (int r = 0; r < m; r++) watching[k][r] = office[k][r];
			}
			for (int j = 0; j < l[num - 1]; j++) { // 감시하는 방향 수
				if (num == 4) num = 7;
				int nx = x + dx[((num * j) + i) % 4];
				int ny = y + dy[((num * j) + i) % 4];
				while (nx >= 0 && ny >= 0 && nx < n && ny < m) {
					if (watching[nx][ny] == '6') break; // 오로지 벽일 때만 통과 못함
					else watching[nx][ny] = '#';
					nx += dx[((num * j) + i) % 4]; ny += dy[((num * j) + i) % 4];
				}
				if (num == 7) num = 4; // l의 index out of bounds error
			}
			min = Math.min(min, solution(index + 1, watching));
		}
		return min;
	}
	
}
