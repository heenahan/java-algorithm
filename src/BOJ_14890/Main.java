package BOJ_14890;

import java.io.*;

public class Main {
	
	static int num;
	static int[][] road;
	int[] ix = { 1, -num + 1 };
	int[] iy = { -num + 1, 1 };
	int[] jx = { 0, 1 };
	int[] jy = { 1, 0 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input1 = br.readLine().split(" ");
		int n = Integer.parseInt(input1[0]);
		int l = Integer.parseInt(input1[1]);
		num = n;
		
		Main T = new Main();
		road = new int[n][n];
		for (int i = 0; i < n; i++) {
			String[] input2 = br.readLine().split(" ");
			for (int j = 0; j < n; j++) road[i][j] = Integer.parseInt(input2[j]);
		}
		
		System.out.println(T.solution(l));
		
		br.close();
	}
	
	public int solution(int l) {
		int count = 0;
		
		for (int i = 0; i < 2; i++) {
			int x = 0; int y = 0;
			while (x < num && y < num) {
				int canBePut = 1; // 놓을 수 있는 길의 수
				int haveToPut = 0; // 놓아야 하는  길의 수
				int before = road[x][y];
				while ((i == 1 && x < num - 1) || (i == 0 && y < num - 1)) {
					x += jx[i]; y += jy[i]; // 행과 열은 크기가 2 이상
					if (before == road[x][y]) { // 이전 높이와 같다면
						if (haveToPut > 0) haveToPut--;
						else canBePut++;
					} else if (before + 1 == road[x][y]) { // 이전 높이보다 1 크면
						if (canBePut >= l) canBePut = 1;
						// 놓을 수 있는 길의 수가 적거나 놓아야 하는 길이 있다면
						else if (canBePut < l || haveToPut > 0) break;
					} else if (before - 1 == road[x][y]) { // 이전 높이보다 1 작으면
						if (haveToPut > 0) break; // 놓아야 하는 길이 있다면
						else {
							haveToPut = l - 1; // 자기 자신에 경사로 놓고
							canBePut = 0; // 경사로를 놓아야 하므로 놓을 수 있는 길 존재 안함
						}
					} else break;
					before = road[x][y];
					if (((i == 0 && y == num - 1) || (i == 1 && x == num - 1)) && haveToPut == 0) count++; // 끝까지 갔고 놓아야 하는 계단이 없다면
				}
				// 음수가 되지 않도록 함
				x = Math.max(0, x + ix[i]); y = Math.max(0, y + iy[i]);
			}
		}
		
		return count;
	}
	
}
