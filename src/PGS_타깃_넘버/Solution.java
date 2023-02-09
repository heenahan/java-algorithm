package PGS_타깃_넘버;

import java.util.Queue;
import java.util.LinkedList;

class Node {
	private int index;
	private int sum;
	
	public Node(int index, int sum) {
		this.index = index;
		this.sum = sum;
	}
	
	public int getIndex() { return index; }
	
	public int getSum() { return sum; }
}

class Solution {
	
	public int solution(int[] numbers, int target) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, numbers[0]));
		q.add(new Node(0, -numbers[0]));
		int answer = 0;
		
		while (!q.isEmpty()) {
			Node v = q.poll();
			int index = v.getIndex();
			int sum = v.getSum();
			
			if (index == numbers.length - 1 && sum == target) answer++;
			if (index + 1 < numbers.length) {
				q.add(new Node(index + 1, sum + numbers[index + 1]));
				q.add(new Node(index + 1, sum - numbers[index + 1]));
			}
		}
		return answer;
	}
	
}