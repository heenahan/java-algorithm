package BOJ_1202;

import java.io.*;
import java.util.*;

class Jewerly {

    private int m;
    private int v;

    public Jewerly(int m, int v) {
        this.m = m;
        this.v = v;
    }

    public int getM() {
        return this.m;
    }

    public int getV() {
        return this.v;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        // 가격 순으로 정렬
        PriorityQueue<Jewerly> q = new PriorityQueue<>(Comparator
            .comparing(Jewerly::getV)
            .reversed());
        for (int i = 0; i < n; i++) {
            String[] mv = br.readLine().split(" ");
            int m = Integer.parseInt(mv[0]);
            int v = Integer.parseInt(mv[1]);
            q.add(new Jewerly(m, v));
        }
        // 가방 정렬
        TreeMap<Integer, Integer> bags = new TreeMap<>();
        for (int i = 0; i < k; i++) {
            int b = Integer.parseInt(br.readLine());
            bags.put(b, bags.getOrDefault(b, 0) + 1);
        }

        long sum = 0;
        while(!q.isEmpty()) {
            Jewerly j = q.poll();
            int m = j.getM();
            if (bags.containsKey(m)) {
                sum += j.getV();
                bags.put(m, bags.get(m) - 1);
                if (bags.get(m) == 0) {
                    bags.remove(m);
                }
                continue;
            }
            Integer h = bags.higherKey(m);
            if (Objects.nonNull(h)) {
                sum += j.getV();
                bags.put(h, bags.get(h) - 1);
                if (bags.get(h) == 0) {
                    bags.remove(h);
                }
            }
        }
        System.out.println(sum);
    }
}