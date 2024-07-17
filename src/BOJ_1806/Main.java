package BOJ_1806;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] nm = sc.nextLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        int[] nums = Arrays.stream(sc.nextLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        int ed = 0;
        int sum = 0; int len = Integer.MAX_VALUE;
        for (int st = 0; st < n; st++) {
            while (ed < n && sum < m) {
                sum += nums[ed];
                ed++;
            }
            if (sum >= m) {
                len = Math.min(len, ed - st);
            }
            sum -= nums[st];
        }

        if (len == Integer.MAX_VALUE) {
            System.out.print(0);
        } else {
            System.out.print(len);
        }

        sc.close();
    }
}
