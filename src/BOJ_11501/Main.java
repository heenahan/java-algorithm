package BOJ_11501;

import java.io.*;

public class Main {

    static int[] day;
    static int[] after; // 자신을 포함해 뒤에서 가장 큰 수

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            day = new int[n];
            after = new int[n];
            int max = Integer.MIN_VALUE;

            String[] input = br.readLine().split(" ");
            for (int j = n - 1; j >= 0; j--) { // 뒤에서 부터
                day[j] = Integer.parseInt(input[j]);
                if (day[j] > max) max = day[j];
                after[j] = max;
            }

            bw.write(T.solution(n).toString());
            bw.newLine();
        }

        br.close();
        bw.close();

    }
	
    public Long solution(int num) {
        long benefit = 0;
        int have = 0;
        for (int i = 0; i < num; i++) {
			if (day[i] == after[i]) { // 미래의 주가 중 현재 주가보다 큰 값이 없을 때 전부 팜
				benefit += day[i] * have;
				have = 0;
			} else { // 아니면 삼
				benefit -= day[i];
				have++;
			}
        }
        return benefit;
    }
}
