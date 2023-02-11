package PGS_더_맵게;
import java.util.PriorityQueue;

class Solution {
	
	public int solution(int[] scoville, int K) {
		PriorityQueue<Integer> q = new PriorityQueue<>(); // 작은 수부터 꺼낸다.
		int answer = 0;
		
		for (int i : scoville) q.add(i);
		while (true) {
			int v1 = q.poll();
			if (v1 >= K) return answer; // 가장 낮은 스코빌을 꺼냈을 때 K이상이면 종료
			if (q.isEmpty()) break; // 더 이상 없으면 종료
			int v2 = q.poll();
			q.add(v1 + (v2 * 2));
			answer++;
		}
		return -1;
	}
	
}