package BOJ_11728;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input1 = br.readLine().split(" ");

        int n = Integer.parseInt(input1[0]);
        int[] a = new int[n];
        String[] input2 = br.readLine().split(" ");
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(input2[i]);

        int m = Integer.parseInt(input1[1]);
        int[] b = new int[m];
        String[] input3 = br.readLine().split(" ");
        for (int i = 0; i < m; i++) b[i] = Integer.parseInt(input3[i]);

        int[] result = T.solution(a, n, b, m);
        for (int i = 0; i < n + m; i++) bw.write(result[i] + " ");

        br.close();
        bw.close();
    }

    public int[] solution(int[] a, int n, int[] b, int m) {
        int[] result = new int[n + m];
        int i = 0; int j = 0; int index = 0;
        while (i < n && j < m) {
            if (a[i] > b[j]) { // 작은 값부터 넣음
                result[index] = b[j];
                j++;
            } else {
                result[index] = a[i];
                i++;
            }
            index++;
        }
        while (i < n) {
            result[index] = a[i];
            index++; i++;
        }
        while (j < m) {
            result[index] = b[j];
            index++; j++;
        }
        return result;
    }
}
