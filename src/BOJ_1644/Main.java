package BOJ_1644;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int answer = 0;

        List<Integer> primes = new ArrayList<>();
        List<Integer> sum = new ArrayList<>();
        sum.add(0);
        for (int i = 2; i <= n; i++) {
            boolean isPrime = true;
            for (int prime : primes) {
                if (i < prime * prime) {
                    break;
                }
                if (i % prime == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(i);
                int before = sum.get(sum.size() - 1);
                sum.add(before + i);
            }
        }
        int ed = 0;
        for (int st = 0; st < sum.size(); st++) {
            while (ed < sum.size() - 1 && sum.get(ed) - sum.get(st) < n) {
                ed++;
            }
            if (sum.get(ed) - sum.get(st) == n) {
                answer++;
            }
        }
        System.out.print(answer);
        sc.close();
    }
}