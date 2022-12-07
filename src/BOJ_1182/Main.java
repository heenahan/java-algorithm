package BOJ_1182;

import java.io.*;

public class Main {

    int n, s;
    int count = 0;
    boolean[] visited;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input1 = br.readLine().split(" ");
        int n = Integer.parseInt(input1[0]);
        int s = Integer.parseInt(input1[1]);
        T.setNAndVisited(n);
        T.setS(s);

        String[] input2 = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(input2[i]);

        T.solution(0, arr, 0);
        Integer answer = T.getCount();
        if (s == 0) answer--;

        bw.write(answer.toString());

        br.close();
        bw.close();
    }

    public void setNAndVisited(int n) {
        this.n = n;
        this.visited = new boolean[n];
    }

    public void setS(int s) {
        this.s = s;
    }

    public Integer getCount() {
        return this.count;
    }

    public void solution(int num, int[] arr, int sum) {
        if (num == n) { // 예외 -5, 5, -5, 5
            if (sum == s) count++;
            return;
        }
        solution(num + 1, arr, sum + arr[num]);
        solution(num + 1, arr, sum);
    }

}