package BOJ_11501;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static int[] day;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            day = new int[n];

            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) day[j] = Integer.parseInt(input[j]);

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
            int max = day[i];
            for (int j = i; j < num; j++) {
                if (day[j] > max) max = day[j];
            }
            if (max > day[i]) { // 미래 주가가 오늘 주가보다 클 때
                benefit -= day[i]; // 주식 매수
                have++;
            } else { // 아니면 전부 팜
                benefit += day[i] * have;
                have = 0;
            }
        }
        return benefit;
    }
}
