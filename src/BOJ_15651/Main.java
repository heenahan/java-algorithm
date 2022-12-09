package BOJ_15651;

import java.io.*;

public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n, m;
    Integer[] answer;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        T.setN(n);
        T.setMAndAnswer(m);

        T.solution(0);

        br.close();
        bw.close();
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setMAndAnswer(int m) {
        this.m = m;
        this.answer = new Integer[m];
    }

    public void solution(int num) throws IOException {
        if (num == m) {
            for (int i = 0; i < m; i++) bw.write(answer[i].toString() + " ");
            bw.newLine();
            return;
        }
        for (int i = 1; i <= n; i++) {
            answer[num] = i;
            solution(num + 1);
        }
    }
}
