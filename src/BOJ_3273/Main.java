package BOJ_3273;

import java.io.*;

public class Main {

    boolean[] visited = new boolean[1000001];

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int size = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int num = Integer.parseInt(br.readLine());
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = Integer.parseInt(input[i]);

        bw.write(T.solution(num, arr).toString());

        br.close();
        bw.close();
    }

    public Integer solution(int num, int[] arr) {
        int count = 0;
        for (int element : arr) {
            if (visited[Math.abs(num - element)]) count++;
            visited[element] = true;
        }
        return count;
    }
}
