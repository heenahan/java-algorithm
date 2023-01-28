package BOJ_14891;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	
	static int[][] gear = new int[4][8];
	static int[][] magnet = new int[4][2]; // 왼쪽 오른쪽
	static int[] dx = { -1, 1 };
	static int[] dm = { 1, 0, -1 }; // 반시계, 시계
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 4; i++) {
			String[] input = br.readLine().split("");
			for (int j = 0; j < 8; j++) gear[i][j] = Integer.parseInt(input[j]);
			magnet[i][0] = 6; magnet[i][1] = 2;
		}
		
		int n = Integer.parseInt(br.readLine()); // 회전 수만큼
		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			int num = Integer.parseInt(input[0]);
			int r = Integer.parseInt(input[1]);
			T.solution(num - 1, r);
		}
		
		System.out.println(T.score());
		
		br.close();
	}
	
	public void solution(int num, int r) {
		int[][] nextM = new int[4][2];
		rotate(nextM, num, r + 1); // 먼저 회전
		for (int i = 0; i < 2; i++) {
			int v = num; int vr = r + 1;
			int next = num + dx[i];
			while (next >= 0 && next < 4) {
				if (v > next) { // next → ← v
					// 마주하는 극이 다르면 반대 방향으로 돌음
					if (gear[v][magnet[v][0]] != gear[next][magnet[next][1]] && vr != 1) {
						vr = (vr + 2) % 4;
						rotate(nextM, next, vr);
					} else { // 같으면 회전 안함
						vr = 1;
						nextM[next][0] = magnet[next][0];
						nextM[next][1] = magnet[next][1];
					}
				} else { // v → ← next
					if (gear[v][magnet[v][1]] != gear[next][magnet[next][0]] && vr != 1) {
						vr = (vr + 2) % 4;
						rotate(nextM, next, vr);
					} else {
						vr = 1;
						nextM[next][0] = magnet[next][0];
						nextM[next][1] = magnet[next][1];
					}
				}
				v = next; next += dx[i];
			}
		}
		magnet = nextM;
	}
	
	public int score() {
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			if (gear[i][(magnet[i][0] + 2) % 8] == 1) sum += Math.pow(2, i);
		}
		return sum;
	}
	
	private void rotate(int[][] nextM, int next, int vr) {
		nextM[next][0] = (magnet[next][0] + dm[vr] + 8) % 8;
		nextM[next][1] = (magnet[next][1] + dm[vr] + 8) % 8;
	}
	
}
