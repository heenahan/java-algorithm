package BOJ_15787;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	
	static int[] train;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		
		train = new int[n + 1];
		System.out.println(T.solution(m));
		
		br.close();
	}
	
	public int solution(int m) throws IOException {
		for (int i = 0; i < m; i++) {
			String[] input = br.readLine().split(" ");
			int num = Integer.parseInt(input[0]);
			int trainNumber = Integer.parseInt(input[1]);
			switch (num) {
				case 1 :
					train[trainNumber] |= (1 << Integer.parseInt(input[2]));
					break;
				case 2 :
					train[trainNumber] &= ~(1 << Integer.parseInt(input[2]));
					break;
				case 3 :
					train[trainNumber] &= ~(1 << 20);
					train[trainNumber] <<=  1;
					break;
				case 4 :
					train[trainNumber] &= ~(1 << 1);
					train[trainNumber] >>= 1;
					break;
			}
		}
		Set<Integer> set = new HashSet<>();
		for (int i = 1; i < train.length; i++) set.add(train[i]);
		return set.size();
	}
	
}
