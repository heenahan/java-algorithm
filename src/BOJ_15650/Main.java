package BOJ_15650;

import java.io.*;

public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    Integer[] answer;
    boolean[] visited;
    int n, m;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        T.setNAndVisited(n);
        T.setMAndAnswer(m);

        T.solution(0);

        br.close();
        bw.close();
    }

    public void setNAndVisited(int n) {
        this.n = n;
        this.visited = new boolean[n + 1];
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
        int start = 0;
        if (num > 0) start = answer[num - 1];
        for (int i = start + 1; i <= n; i++) {
            if (!visited[i]) {
                answer[num] = i;
                visited[i] = true;
                solution(num + 1);
                visited[i] = false;
            }
        }
    }

}
