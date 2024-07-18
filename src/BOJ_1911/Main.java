package BOJ_1911;

import java.io.*;
import java.util.*;

class Pool {
    private int st;
    private int ed;

    public Pool(int st, int ed) {
        this.st = st;
        this.ed = ed;
    }

    public int getSt() {
        return this.st;
    }

    public int getEd() {
        return this.ed;
    }
}

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nl = br.readLine().split(" ");
        int n = Integer.parseInt(nl[0]);
        int l = Integer.parseInt(nl[1]);

        List<Pool> pools = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] se = br.readLine().split(" ");
            int s = Integer.parseInt(se[0]);
            int e = Integer.parseInt(se[1]);

            pools.add(new Pool(s, e));
        }

        // 웅덩이 정렬
        Collections.sort(pools, Comparator.comparing(Pool::getEd));

        int wood = 0; int answer = 0;
        for (Pool pool : pools) {
            int st = pool.getSt(); int ed = pool.getEd();

            // 이미 덮어져 있다면 넘어감
            if (ed <= wood) {
                continue;
            }
            // 판자와 겹치는 부분이 있다면
            if (st <= wood) {
                int except = wood - st + 1;
                int remain = ed - st - except;
                int need = remain / l;
                if (remain % l > 0) need++;
                wood += (need * l);
                answer += need;
                continue;
            }
            wood = st - 1;
            int remain = ed - st;
            int need = remain / l;
            if (remain % l > 0) need++;
            wood += (need * l);
            answer += need;
        }

        System.out.print(answer);

        br.close();
    }
}