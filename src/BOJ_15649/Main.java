package BOJ_15649;

import java.io.*;

public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int[] arr;
    boolean[] visited;
    int m;
    int n;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        T.setArr(m);
        T.setVisited(n);
        T.setN(n);
        T.setM(m);

        T.solution(0);

        br.close();
        bw.close();
    }

    public void setArr(int m) {
        arr = new int[m];
    }

    public void setVisited(int n) {
        visited = new boolean[n + 1];
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setM(int m) {
        this.m = m;
    }

    public void solution(int num) throws IOException {
        if (num == m) {
            for (int i = 0; i < m; i++) bw.write(String.valueOf(arr[i]) + " ");
            bw.newLine();
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                arr[num] = i;
                visited[i] = true;
                solution(num + 1);
                visited[i] = false;
            }
        }
    }
}
