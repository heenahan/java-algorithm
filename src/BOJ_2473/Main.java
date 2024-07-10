package BOJ_2473;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        // 용액 정렬
        long[] solutions = Arrays.stream(br.readLine().split(" "))
            .mapToLong(Long::parseLong)
            .sorted()
            .toArray();

        long min = Long.MAX_VALUE;
        long[] answer = new long[3];
        // 두 용액 혼합
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long sum = solutions[i] + solutions[j];
                int idx = lowerBound(solutions, -sum);
                if (idx >= n - 1) {
                    idx = n - 2;
                }
                if (idx <= 0) {
                    idx = 1;
                }
                int left = idx - 1;
                int right = idx + 1;

                if (idx != i && idx != j) {
                    if (Math.abs(sum + solutions[idx]) < min) {
                        min = Math.abs(sum + solutions[idx]);
                        answer = new long[]{ solutions[i], solutions[j], solutions[idx] };
                    }
                }
                if (left != i && left != j) {
                    if (Math.abs(sum + solutions[left]) < min) {
                        min = Math.abs(sum + solutions[left]);
                        answer = new long[]{ solutions[i], solutions[j], solutions[left] };
                    }
                }
                if (right != i && right != j) {
                    if (Math.abs(sum + solutions[right]) < min) {
                        min = Math.abs(sum + solutions[right]);
                        answer = new long[]{ solutions[i], solutions[j], solutions[right] };
                    }
                }
            }
        }

        Arrays.sort(answer);
        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);

        br.close();
    }

    private static int lowerBound(long[] solutions, long key) {
        int st = 0; int ed = solutions.length;
        while (st < ed) {
            int mid = (st + ed) / 2;
            if (solutions[mid] < key) {
                st = mid + 1;
            } else {
                ed = mid;
            }
        }
        return st;
    }
}
