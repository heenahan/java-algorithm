package BOJ_15654;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    boolean[] visited;
    int[] arr;
    int n;
    int m;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input1 = br.readLine().split(" ");
        int n = Integer.parseInt(input1[0]);
        int m = Integer.parseInt(input1[1]);
        T.setVisitedAndN(n); T.setArrAndM(m);

        String[] input2 = br.readLine().split(" ");
        List<Integer> num = new ArrayList<>();
        for (int i = 0; i < n; i++) num.add(Integer.parseInt(input2[i]));
        Collections.sort(num);

        T.solution(0, num);

        br.close();
        bw.close();
    }

    public void setVisitedAndN(int n) {
        visited = new boolean[n];
        this.n = n;
    }

    public void setArrAndM(int m) {
        arr = new int[m];
        this.m = m;
    }

    public void solution(int index, List<Integer> num) throws IOException {
        if (index == m) {
            for (int i = 0; i < m; i++) bw.write(arr[i] + " ");
            bw.newLine();
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[index] = num.get(i);
                solution(index + 1, num);
                visited[i] = false;
            }
        }
    }
}
