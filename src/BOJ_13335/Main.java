package BOJ_13335;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;

class Node {
	private int weight; // 트럭 무게
	private int time; // 다리를 빠져나오는 시간
	
	public Node(int weight, int time) {
		this.weight = weight;
		this.time = time;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public int getTime() {
		return time;
	}
}

public class Main {
	
	static Queue<Node> q = new LinkedList<>();
	static int[] truck;
	static int n; // 트럭의 개수
	static int w; // 다리 길이
	static int l; // 다리 최대 하중
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input1 = br.readLine().split(" ");
		n = Integer.parseInt(input1[0]);
		w = Integer.parseInt(input1[1]);
		l = Integer.parseInt(input1[2]);
		truck = new int[n];
		
		String[] input2 = br.readLine().split(" ");
		for (int i = 0; i < n; i++) truck[i] = Integer.parseInt(input2[i]);
		
		System.out.println(T.solution());
		
		br.close();
	}
	
	public int solution() {
		int count = 1; int index = 1;
		int bridge = truck[0]; // 다리 위에 올라간 트럭의 총 무게
		q.add(new Node(truck[0], count + w));
		
		while (!q.isEmpty()) {
			count++;
			Node v = q.peek();
			int weight = v.getWeight();
			int time = v.getTime();
			// 다리 위에 올라가있는 트럭 중 가장 앞의 트럭 검사
			if (count >= time) { // 트럭이 다리를 빠져나올 시간이면
				q.poll();
				bridge -= weight;
			}
			// 다음으로 다리에 올라갈 트럭 검사
			if (index < n && l - bridge >= truck[index]) { // 트럭을 올렸을 때 최대 하중을 넘지 않으면
				q.add(new Node(truck[index], count + w));
				bridge += truck[index];
				index++;
			}
		}
		
		return count;
	}
	
}
