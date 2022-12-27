package BOJ_2217;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        List<Integer> ropes = new ArrayList<>();
        for (int i = 0; i < num; i++) ropes.add(Integer.parseInt(br.readLine()));
        Collections.sort(ropes);

        System.out.println(T.solution(ropes, num));
        br.close();
    }

    public int solution(List<Integer> ropes, int num) {
        int max = Integer.MIN_VALUE;
        for (int i = num - 1; i >= 0; i--) {
            int sum = ropes.get(i) * (num - i);
            if (sum > max) max = sum;
        }
        return max;
    }
}
