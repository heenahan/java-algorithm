package BOJ_1874;

import java.io.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class Main {
	
	static int[] arr; // 순열
	static List<String> list;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int num = Integer.parseInt(br.readLine());
		arr = new int[num];
		for (int i = 0; i < num; i++) arr[i] = Integer.parseInt(br.readLine());
		
		if (T.solution(num)) {
			for (int i = 0; i < list.size(); i++) {
				bw.write(list.get(i));
				bw.newLine();
			}
		} else bw.write("NO");
		
		br.close();
		bw.close();
	}
	
	public boolean solution(int num) {
		list = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();
		
		int index = 0;
		
		for (int i = 1; i <= num; i++) {
			if (arr[index] >= i) {
				stack.push(i);
				list.add("+");
			} else { // 가리키는 값보다 작으면 스택에 값이 저장
				while (!stack.isEmpty()) {
					int v = stack.peek();
					
					if (arr[index] == v) {
						stack.pop();
						list.add("-");
						index++;
					} else if (arr[index] >= i) break; // i보다 크다면 stack에 안 넣은 값
					else return false;
				}
				stack.push(i);
				list.add("+");
			}
		}
		while (!stack.isEmpty()) {
			int v = stack.pop();
			
			if (arr[index] == v) {
				list.add("-");
				index++;
			} else return false;
		}
		return true;
	}
}
