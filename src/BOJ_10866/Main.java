package BOJ_10866;

import java.io.*;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		
		int num = Integer.parseInt(br.readLine());
		T.solution(num);
		
		br.close();
		bw.close();
	}
	
	public void solution(int num) throws IOException{
		Deque<Integer> deq = new ArrayDeque<>();
		for (int i = 0; i < num; i++) {
			String[] command = br.readLine().split(" ");
			switch (command[0]) {
				case "push_front" :
					deq.addFirst(Integer.parseInt(command[1]));
					break;
				case "push_back" :
					deq.addLast(Integer.parseInt(command[1]));
					break;
				case "pop_front" :
					Integer popf = deq.pollFirst();
					if (popf == null) bw.write(-1 + "\n");
					else bw.write(popf + "\n");
					break;
				case "pop_back" :
					Integer popb = deq.pollLast();
					if (popb == null) bw.write(-1 + "\n");
					else bw.write(popb + "\n");
					break;
				case "size" :
					bw.write(deq.size() + "\n");
					break;
				case "empty" :
					if (deq.isEmpty()) bw.write(1 + "\n");
					else bw.write(0 + "\n");
					break;
				case "front" :
					Integer peekf = deq.peekFirst();
					if (peekf == null) bw.write(-1 + "\n");
					else bw.write(peekf + "\n");
					break;
				case "back" :
					Integer peekb = deq.peekLast();
					if (peekb == null) bw.write(-1 + "\n");
					else bw.write(peekb + "\n");
					break;
			}
		}
	}
}
