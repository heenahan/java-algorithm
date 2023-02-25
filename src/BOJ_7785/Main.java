package BOJ_7785;

import java.io.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int num = Integer.parseInt(br.readLine());
		Set<String> set = new HashSet<>();
		for (int i = 0; i < num; i++) {
			String[] input = br.readLine().split(" ");
			switch (input[1]) {
				case "enter": set.add(input[0]); break;
				case "leave": if (set.contains(input[0])) set.remove(input[0]);
			}
		}
		set.stream().sorted(Comparator.reverseOrder()).forEach(p -> {
			try{
				bw.write(p);
				bw.newLine();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		
		br.close();
		bw.close();
	}
	
}
