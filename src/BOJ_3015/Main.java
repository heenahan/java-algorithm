package BOJ_3015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Node {
	private int val;
	private long cnt;
	
	public Node(int val, long cnt) {
		this.val = val;
		this.cnt = cnt; // 자신의 뒤에 자신과 같은 수의 갯수
	}
	
	public int getVal() {
		return val;
	}
	
	public long getCnt() {
		return cnt;
	}
}

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		
		int num = Integer.parseInt(br.readLine());
		
		System.out.println(T.solution(num));
		
		br.close();
	}
	
	public long solution(int num) throws IOException {
		Stack<Node> stack = new Stack<>();
		long sum = 0;
		for (int i = 0; i < num; i++) {
			int h = Integer.parseInt(br.readLine());
			int same = 1;
			while (!stack.isEmpty() && stack.peek().getVal() <= h) { // top보다 놓은 h가 들어오면
				sum += stack.peek().getCnt();
				if (stack.peek().getVal() == h) same += stack.peek().getCnt(); // 들어온 값이 top과 같다면
				stack.pop();
			}
			if (!stack.isEmpty()) sum++; // 비지 않았다면 top에는 h보다 큰 수가 있으므로 top과 한번 마주보게 된다.
			stack.push(new Node(h, same)); // 들어온 h는 항상 stack에 들어온다.
		}
		return sum;
	}
}
