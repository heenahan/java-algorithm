package BOJ_1202;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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
        List<Integer> bags = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int b = Integer.parseInt(br.readLine());
            bags.add(b);
        }
        bags = bags.stream()
            .sorted()
            .collect(Collectors.toList());

        long sum = 0;
        while(!q.isEmpty()) {
            Jewerly j = q.poll();
            int m = j.getM();
            int idx = lowerBound(bags, m);
            // 보석을 넣을 수 있는 가방이 존재하지 않음
            if (idx >= bags.size()) {
                continue;
            }
            sum += j.getV();
            bags.remove(idx);
        }
        System.out.println(sum);
    }
    // 첫번째로 등장하거나 같은 값이 없다면 처음으로 초과하는 값
    private static int lowerBound(List<Integer> bags, int m) {
        int st = 0; int ed = bags.size();
        while (st < ed) {
            int mid = (ed + st) / 2;
            if (bags.get(mid) < m) {
                st = mid + 1;
            } else {
                ed = mid;
            }
        }
        return st;
    }
}