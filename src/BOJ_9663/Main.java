package BOJ_9663;

import java.util.Scanner;

public class Main {
	
	static int count = 0;
	static int queen;
	static int[] row; // row[i] i번째 행의 퀸이 놓아진 위치
	
	public static void main(String[] args) {
		Main T = new Main();
		Scanner in = new Scanner(System.in);
		
		queen = in.nextInt();
		row = new int[queen];
		T.solution(0);
		
		System.out.println(count);
		
		in.close();
	}
	// num행에 놓을
	public void solution(int num) {
		if (num == queen) {
			count++;
			return;
		}
		// 놓을 수 있는 열 찾음
		for (int i = 0; i < queen; i++) {
			boolean flag = true;
			for (int j = 0; j < num; j++) { // 이미 놓아진 행들의 퀸과 비교
				if (row[j] == i) flag = false; // 같은 열에 놓아지면 안됨
				if (num - j + row[j] == i) flag = false; // ↘
				if (j - num + row[j] == i) flag = false; // ↙
			}
			if (flag) {
				row[num] = i;
				solution(num + 1);
			}
		}
	}
}
