package BOJ_9095;

import java.io.*;

public class Main {

    int[] memorization = new int[12];

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int test = Integer.parseInt(br.readLine());
        for (int i = 0; i < test; i++) {
            int num = Integer.parseInt(br.readLine());
            bw.write(T.solution(num).toString());
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    public Integer solution(int num) {
        if (num < 0) return 0;
        else if (num == 0) return 1;
        if (memorization[num] > 0) return memorization[num]; // 이미 계산되었으면 return
        // 계산이 안됐으면 계산
        memorization[num] = solution(num - 1) + solution(num - 2) + solution(num - 3);
        return memorization[num];
    }

}
