package BOJ_10799;

import java.io.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class Main {
	
	static List<Integer> raser;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] bracket = br.readLine().split("");
		
		bw.write(T.solution(bracket).toString());
		
		br.close();
		bw.close();
	}
	
	public Integer solution(String[] bracket) {
		int size = bracket.length;
		raser = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();
		int slice = 0;
		
		for (int i = 0; i < size; i++) {
			if (bracket[i].equals("(")) { // 여는 괄호
				stack.push(i);
			} else { // 닫는 괄호
				int v = stack.pop();
				
				if (i - v > 1) { // 쇠막대기라면
					for (int j = 0; j < raser.size(); j++) {
						if (raser.get(j) > v) slice++; // 쇠막대기 사이에 레이저 개수
					}
					slice++;
				} else raser.add(v); // 레이저라면
			}
		}
		
		return slice;
	}
}
