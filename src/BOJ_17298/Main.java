package BOJ_17298;

import java.io.*;
import java.util.Stack;

class Node {
	private int index; private int num;
	
	public Node(int index, int num) {
		this.index = index;
		this.num = num;
	}
	
	public int getIndex() {
		return index;
	}
	
	public int getNum() {
		return num;
	}
}

public class Main {
	
	static int[] nums;
	static int[] max;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		nums = new int[n];
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(input[i]);
		
		T.solution(n);
		for (int i = 0; i < n; i++) {
			if (max[i] > 0) bw.write(max[i] + " ");
			else bw.write( -1 + " ");
		}
		
		br.close();
		bw.close();
	}
	
	public void solution(int n) {
		max = new int[n];
		Stack<Node> stack = new Stack<>();
		
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty()) {
				Node v = stack.peek();
				int index = v.getIndex();
				int num = v.getNum();
				
				if (nums[i] > num) {
					stack.pop();
					max[index] = nums[i];
				} else break;
			}
			stack.push(new Node(i, nums[i]));
		}
	}
}
