package BOJ_1715;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> q = new PriorityQueue<>();
		for (int i = 0; i < num; i++) {
			int card = Integer.parseInt(br.readLine());
			q.add(card);
		}
		
		System.out.println(T.solution(q));
		
		br.close();
	}
	
	public int solution(PriorityQueue<Integer> q) {
		int sum = 0;
		while (q.size() > 1) {
			int v1 = q.poll(); // 가장 작은 것 두 개를 꺼낸다.
			int v2 = q.poll();
			
			sum += (v1 + v2);
			q.add(v1 + v2);
		}
		return sum;
	}
}
