package BOJ_15652;

import java.io.*;

public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int[] arr;
    int n;
    int m;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        T.setN(n); T.setArrAndM(m);

        T.solution(0);

        br.close();
        bw.close();
    }

    public void setArrAndM(int m) {
        arr = new int[m + 1];
        this.m = m;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void solution(int num) throws IOException {
        if (num == m) {
            for (int i = 1; i <= m; i++) bw.write(arr[i] + " ");
            bw.newLine();
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (i >= arr[num]) {
                arr[num + 1] = i;
                solution(num + 1);
            }
        }
    }
}
