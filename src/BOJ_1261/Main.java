package BOJ_1261;

import java.util.*;
import java.io.*;

class XY {
	private int x;
	private int y;
	private int wall;

	public XY(int x, int y, int wall) {
		this.x = x;
		this.y = y;
		this.wall = wall;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getWall() {
		return this.wall;
	}
}

public class Main {

	private static int[] dx = { 0, -1, 0, 1 };
	private static int[] dy = { -1, 0, 1, 0 };
	private static int[][] wall;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] mn = br.readLine().split(" ");
		int m = Integer.parseInt(mn[0]);
		int n = Integer.parseInt(mn[1]);

		int[][] map = new int[n][m];
		wall = new int[n][m]; // 벽을 최소 몇 개 부서야하는지

		for (int i = 0; i < n; i++) {
			Arrays.fill(wall[i], Integer.MAX_VALUE);
			map[i] = Arrays.stream(br.readLine().split(""))
				.mapToInt(Integer::parseInt)
				.toArray();
		}

		dijkstra(n, m, map);

		System.out.print(wall[n - 1][m - 1]);

		br.close();
	}

	public static void dijkstra(int n, int m, int[][] map) {
		PriorityQueue<XY> q = new PriorityQueue<>(Comparator.comparing(XY::getWall));
		q.add(new XY(0, 0, 0));
		wall[0][0] = 0;

		while (!q.isEmpty()) {
			XY v = q.poll();
			int x = v.getX(); int y = v.getY();
			int w = v.getWall();

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
				int nw = w;
				// 벽이라면
				if (map[nx][ny] == 1) {
					nw++;
				}
				// 더 작은 횟수로 벽을 부술 수 있다면
				if (wall[nx][ny] > nw) {
					q.add(new XY(nx, ny, nw));
					wall[nx][ny] = nw;
				}
			}
		}
	}
}