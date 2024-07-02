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
            .thenComparing(App::getM));
        // dp[i][j] j 비용 이하이면서  0부터 i번째 원소들의 부분집합 중 최대 확보할 수 있는 메모리양
        int[][] dp = new int[N][10001];
        for (int i = 0; i < 10001; i++) {
            App app = apps.get(0);
            int am = app.getM(); int ac = app.getC();
            if (i >= ac) {
                dp[0][i] = am;
            }
        }
        for (int i = 1; i < N; i++) {
            App app = apps.get(i);
            int am = app.getM(); int ac = app.getC();
            for (int j = 0; j < 10001; j++) {
                if (j >= ac) {
                    // app[i]를 넣을지 안넣을지 결정
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - ac] + am);
                    continue;
                }
                // app[i]를 넣으면 j를 초과하므로 넣지 않음
                dp[i][j] = dp[i - 1][j];
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 10001; j++) {
                // M을 초과하면서 j가 최소일 경우
                if (dp[i][j] >= M && min > j) {
                    min = j;
                }
            }
        }
        System.out.println(min);

        sc.close();
    }
}