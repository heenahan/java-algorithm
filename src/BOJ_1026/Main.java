package BOJ_1026;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        List<Integer> a = new ArrayList<>();
        String[] input1 = br.readLine().split(" ");
        for (int i = 0; i < num; i++) a.add(Integer.parseInt(input1[i]));

        List<Integer> b = new ArrayList<>();
        String[] input2 = br.readLine().split(" ");
        for (int i = 0; i < num; i++) b.add(Integer.parseInt(input2[i]));

        System.out.println(T.solution(a, b, num));
        br.close();
    }

    public int solution(List<Integer> a, List<Integer> b, int num) {
        Collections.sort(a); // 오름차순
        Collections.sort(b, Collections.reverseOrder()); // 내림차순

        int sum = 0;

        for(int i = 0; i < num; i++) sum += a.get(i) * b.get(i);

        return sum;
    }
}
