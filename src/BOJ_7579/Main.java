package BOJ_7579;

import java.util.*;

class App {
    private int m;
    private int c;

    public App(int m, int c) {
        this.m = m;
        this.c = c;
    }

    public int getM() {
        return this.m;
    }

    public int getC() {
        return this.c;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] nm = sc.nextLine().split(" ");
        int N = Integer.parseInt(nm[0]);
        int M = Integer.parseInt(nm[1]);

        int[] m = Arrays.stream(sc.nextLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        int[] c = Arrays.stream(sc.nextLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        List<App> apps = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            apps.add(new App(m[i], c[i]));
        }
        // c를 기준으로 내림차순 정렬
        Collections.sort(apps, Comparator.comparing(App::getC)
            .thenComparing(App::getM)
            .reversed());
        // dp[i][j] j 용량을 넘어서는 0부터 i번째 원소들의 부분집합 중 최솟값
        int[][] dp = new int[N][M + 1];
        for (int i = 1; i <= M; i++) {
            App a = apps.get(0);
            int ac = a.getC();
            dp[0][i] = ac;
        }
        for (int i = 1; i < N; i++) {
            App a = apps.get(i);
            int am = a.getM(); int ac = a.getC();
            for (int j = 1; j <= M; j++) {
                // j를 초과했을 때
                if (j > am) {
                    // i를 뺀 것과 i를 추가한 것 중 더 작은 것
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - am] + ac);
                    continue;
                }
                // j를 초과하기 전 i만 더하는게 최솟값임
                dp[i][j] = ac;
            }
        }
        System.out.println(dp[N - 1][M]);

        sc.close();
    }
}