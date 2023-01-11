package BOJ_2493;

import java.io.*;
import java.util.Stack;

class Node {
	private int index; private int height;
	
	public Node(int index, int height) {
		this.index = index;
		this.height = height;
	}
	
	public int getIndex() { return index; }
	
	public int getHeight() { return height; }
}

public class Main {
	
	static int[] top;
	static int[] arrive;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		top = new int[n];
		arrive = new int[n];
		
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < n; i++) top[i] = Integer.parseInt(input[i]);
		
		T.solution(n);
		
		for (int i = 0; i < n; i++) bw.write(arrive[i] + " ");
		
		br.close();
		bw.close();
	}
	
	public void solution(int num) {
		Stack<Node> stack = new Stack<>();
		
		for (int i = num - 1; i >= 0; i--) {
			while (!stack.isEmpty()) {
				Node v = stack.peek();
				int index = v.getIndex();
				int height = v.getHeight();
				
				if (top[i] > height) {
					arrive[index] = i + 1; // 도착하는 탑의 위치
					stack.pop();
				} else break;
			}
			stack.push(new Node(i, top[i]));
		}
	}

}
