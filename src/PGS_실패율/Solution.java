package PGS_실패율;

import java.util.*;

class Node {
	private int lev;
	private double rate;
	
	public Node(int lev, double rate) {
		this.lev = lev;
		this.rate = rate;
	}
	
	public int getLev() { return lev; }
	public double getRate() { return rate; }
}

class Solution {
	public int[] solution(int N, int[] stages) {
		List<Node> list = new ArrayList<>();
		int[] arr = Arrays.stream(stages).sorted().toArray();
		/**
		 * 도전자 : 전체 사용자 - lowerBound
		 * 통과 못한 도전자 : upperBound - lowerBound
		 */
		for (int i = 1; i <= N; i++) {
			int lo = lowerBound(0, arr.length, i, arr);
			int up = upperBound(0, arr.length, i, arr);
			if (lo == up) list.add(new Node(i, 0)); // 모두 통과
			else list.add(new Node(i,((double) up - lo) / (arr.length - lo)));
		}
		return list.stream().sorted(Comparator.comparingDouble(Node::getRate).reversed()).mapToInt(n -> n.getLev()).toArray();
	}
	
	// key를 초과하는 첫번째 값
	private int upperBound(int st, int ed, int key, int[] arr) {
		int mid;
		while (st < ed) {
			mid = st + (ed - st) / 2;
			if (arr[mid] <= key) st = mid + 1;
			else ed = mid;
		}
		return st;
	}
	
	// key와 같거나 key보다 큰 수 중 가장 작은 수
	private int lowerBound(int st, int ed, int key, int[] arr) {
		int mid;
		while (st < ed) {
			mid = st + (ed - st) / 2;
			if (arr[mid] < key) st = mid + 1;
			else ed = mid;
		}
		return st;
	}
	
}