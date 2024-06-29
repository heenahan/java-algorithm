package BOJ_20922;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        int[] arr = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        Map<Integer, Integer> map = new HashMap<>();
        int ed = 0; int len = Integer.MIN_VALUE;
        for (int st = 0; st < n; st++) {
            // ed가 n보다 작고 ed 값을 넣었을 때 k를 넘지 않는다면
            while (ed < n && map.getOrDefault(arr[ed], 0) < k) {
                map.put(arr[ed], map.getOrDefault(arr[ed], 0) + 1);
                ed++;
            }
            len = Math.max(len, ed - st);
            map.put(arr[st], map.getOrDefault(arr[st], 0) - 1);
            if (map.get(arr[st]) == 0) {
                map.remove(arr[st]);
            }
        }

        bw.write(String.valueOf(len));

        br.close(); bw.close();
    }
}