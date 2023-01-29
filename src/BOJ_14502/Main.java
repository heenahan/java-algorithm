package BOJ_14502;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
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
	
	static List<Node> blank = new ArrayList<>(); // 벽을 세울 수 있는 빈칸들의 위치
	static List<Node> virus = new ArrayList<>(); // 바이러스 위치
	static int[][] labaratoy; // 실험실
	static boolean[][] visited;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input1 = br.readLine().split(" ");
		int n = Integer.parseInt(input1[0]);
		int m = Integer.parseInt(input1[1]);
		
		labaratoy = new int[n][m];
		for (int i = 0; i < n; i++) {
			String[] input2 = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				labaratoy[i][j] = Integer.parseInt(input2[j]);
				if (labaratoy[i][j] == 0) blank.add(new Node(i, j));
				else if (labaratoy[i][j] == 2) virus.add(new Node(i, j));
			}
		}
		// 조합을 구하기 위한 배열, 3개를 선택하므로 앞의 3개는 0
		arr = IntStream.range(0, blank.size()).map(i -> { if (i >= 3) return 1; else return 0; }).toArray();
		
		System.out.println(T.solution(n, m));
		
		br.close();
	}
	
	public int solution(int n, int m) {
		int max = Integer.MIN_VALUE;
		do {
			Queue<Node> q = new LinkedList();
			for (Node v : virus) q.add(v); // 바이러스 위치 넣음
			visited = new boolean[n][m];
			for (int i = 0; i < arr.length; i++) { // 조합을 구함
				if (arr[i] == 0) {
					Node v = blank.get(i);
					int x = v.getX(); int y = v.getY();
					visited[x][y] = true; // 벽을 세워 방문 못함
				}
			}
			max = Math.max(max, bfs(q, n, m)); // 조합을 구할때마다 bfs 구함
		} while(nextPermutation());
		return max;
	}
	
	private int bfs(Queue<Node> q, int n, int m) {
		while (!q.isEmpty()) {
			Node v = q.poll();
			int x = v.getX();
			int y = v.getY();
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				if (visited[nx][ny] || labaratoy[nx][ny] != 0) continue;
				Node next = new Node(nx, ny);
				visited[nx][ny] = true;
				q.add(next);
			}
		}
		int sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// 방문하지 않은 빈칸
				if (!visited[i][j] && labaratoy[i][j] == 0) sum++;
			}
		}
		return sum;
	}
	
	private boolean nextPermutation() { // 순열을 구함
		int i = arr.length - 1;
		while (i > 0 && arr[i - 1] >= arr[i]) i--;
		if (i <= 0) return false;
		
		int j = arr.length - 1;
		while (arr[j] <= arr[i - 1]) j--;
		
		int temp = arr[i - 1];
		arr[i - 1] = arr[j];
		arr[j] = temp;
		
		j = arr.length - 1;
		while (i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++; j--;
		}
		return true;
	}
	
}
