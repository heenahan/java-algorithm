package BOJ_15686;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

class Node {
	private int x; private int y;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}

public class Main {
	
	static int min = Integer.MAX_VALUE;
	static int n; // 도시 크기
	static int m; // 선택할 수 있는 최대 치킨집
	static List<Node> home = new ArrayList<>(); // 집의 위치
	static List<Node> chicken = new ArrayList<>(); // 치킨집의 위치
	static int[] selected; // 선택한 치킨집
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		
		selected = new int[m]; // 초기화
		
		for (int i = 0; i < n; i++) {
			String[] city = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				int num = Integer.parseInt(city[j]);
				if (num == 1) home.add(new Node(i, j));
				else if (num == 2) chicken.add(new Node(i, j));
			}
		}
		
		arr = IntStream.range(0, chicken.size()).map(i -> { if(i >= m) return 1; else return 0; }).toArray();
		
		T.solution();
		System.out.println(min);
		
		br.close();
	}
	
	public boolean nextPermutation(int[] arr) {
		int i = arr.length - 1;
		while (i > 0 && arr[i - 1] >= arr[i]) i--;
		if (i <= 0) return false;
		
		int j = arr.length - 1;
		while (arr[j] <= arr[i - 1]) j--;
		
		int temp = arr[i - 1];
		arr[i - 1] = arr[j];
		arr[j] = temp;
		
		j = arr.length - 1;
		while (i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++; j--;
		}
		return true;
	}
	
	public void solution() {
		int chickenSize = chicken.size();
		
		do {
			int index = 0;
			for (int i = 0; i < chickenSize; i++) {
				if (arr[i] == 0) selected[index++] = i;
			} // 조합 구하기
			
			int homeSize = home.size();
			
			int sum = 0;
			for (int j = 0; j < homeSize; j++) { // 선택된 치킨집과 치킨 거리 구하기
				Node h = home.get(j);
				int hx = h.getX(); int hy = h.getY();
				int shortD = Integer.MAX_VALUE;
				for (int k = 0; k < m; k++) {
					Node c = chicken.get(selected[k]);
					int cx = c.getX(); int cy = c.getY();
					int calc = Math.abs(hx - cx) + Math.abs(hy - cy);
					if (shortD > calc) shortD = calc;
				}
				sum += shortD;
			}
			
			if (min > sum) min = sum; // 합한 치킨 거리가 가장 작다면
			
		} while(nextPermutation(arr));
	}
	
}
