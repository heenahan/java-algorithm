package BOJ_1620;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
	
	static Map<String, Integer> name = new HashMap<>();
	static Map<Integer, String> number = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input1 = br.readLine().split(" ");
		int n = Integer.parseInt(input1[0]);
		int m = Integer.parseInt(input1[1]);
		for (int i = 1; i <= n; i++) {
			String input2 = br.readLine();
			name.put(input2, i);
			number.put(i, input2);
		}
		for (int i = 0; i < m; i++) {
			String input3 = br.readLine();
			if (Character.isDigit(input3.charAt(0))) {
				int num = Integer.parseInt(input3);
				bw.write(number.get(num) + "\n");
			} else bw.write(name.get(input3) + "\n");
		}
		
		br.close();
		bw.close();
	}
}
