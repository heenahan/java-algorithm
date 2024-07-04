package BOJ_12865;

import java.util.*;
import java.io.*;

class Bag {
    private int w;
    private int v;

    public Bag(int w, int v) {
        this.w = w;
        this.v = v;
    }

    public int getW() {
        return this.w;
    }

    public int getV() {
        return this.v;
    }
}

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        List<Bag> bags = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] wv = br.readLine().split(" ");
            int w = Integer.parseInt(wv[0]);
            int v = Integer.parseInt(wv[1]);
            bags.add(new Bag(w, v));
        }

        int[][] dp = new int[n][k + 1];
        for (int i = 0; i <= k; i++) {
            Bag b = bags.get(0);
            int bw = b.getW(); int bv = b.getV();
            if (i >= bw) {
                dp[0][i] = bv;
            }
        }
        for (int i = 1; i < n; i++) {
            Bag b = bags.get(i);
            int bw = b.getW(); int bv = b.getV();
            for (int j = 0; j <= k; j++) {
                if (j >= bw) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - bw] + bv);
                    continue;
                }
                dp[i][j] = dp[i - 1][j];
            }
        }
        System.out.println(dp[n - 1][k]);

        br.close();
    }
}