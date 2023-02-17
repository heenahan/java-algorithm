package BOJ_16401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String[] input1 = br.readLine().split(" ");
		int m = Integer.parseInt(input1[0]);
		int n = Integer.parseInt(input1[1]);
		
		int[] snack = new int[n];
		String[] input2 = br.readLine().split(" ");
		for (int i = 0; i < n; i++) snack[i] = Integer.parseInt(input2[i]);
		
		System.out.println(T.solution(m, snack));
		
		br.close();
	}
	// 예외 모든 조카에게 같은 길이의 막대과자를 나눠줄 수 없으면 0
	// 조카 네 명이고 과자 3개일 때 { 1, 1, 1 }이면 나눠주지 못함
	// 반대로 조카 세 명이고 과자 4개일 때 { 7, 7, 7, 7 }이면 하나가 남지만 모두에게 줄 순 있음
	public int solution(int m, int[] snack) {
		int start = 1; int end = 1000000000;
		int mid = (start + end + 1) / 2;
		while (start < end) {
			int sum = 0;
			for (int s : snack) sum += (s / mid);
			
			if (sum >= m) start = mid;
			else end = mid - 1;
			
			mid = (start + end + 1) / 2;
		}
		int sum = 0;
		for (int s : snack) sum += (s / start);
		if (sum < m) return 0;
		else return start;
	}
}
