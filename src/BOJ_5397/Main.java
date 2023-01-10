package BOJ_5397;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.List;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int num = Integer.parseInt(br.readLine());
		for (int i = 0; i < num; i++) {
			String[] password = br.readLine().split("");
			bw.write(T.solutin(password));
			bw.newLine();
		}
		
		br.close();
		bw.close();
	}
	
	public String solutin(String[] password) {
		List<String> list = new LinkedList<>();
		ListIterator<String> iter = list.listIterator();
		for (String s : password) {
			switch (s) {
				case "<" : // 왼쪽
					if (iter.hasPrevious()) iter.previous();
					break;
				case ">" : // 오른쪽
					if (iter.hasNext()) iter.next();
					break;
				case "-" :
					if (iter.hasPrevious()) {
						iter.previous();
						iter.remove();
					}
					break;
				default :
					iter.add(s);
					break;
			}
		}
		while (iter.hasPrevious()) iter.previous();
		StringBuilder sb = new StringBuilder();
		while (iter.hasNext()) sb.append(iter.next());
		return sb.toString();
	}
}
