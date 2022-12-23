package BOJ_16928;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;

public class Main {

    int[] visited = new int[101];

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input1 = br.readLine().split(" ");

        int n = Integer.parseInt(input1[0]);
        int[] ladder = new int[101];
        for (int i = 0; i < n; i++) {
            String[] input2 = br.readLine().split(" ");
            int x = Integer.parseInt(input2[0]);
            int y = Integer.parseInt(input2[1]);
            ladder[x] = y;
        }

        int m = Integer.parseInt(input1[1]);
        int[] sneak = new int[101];
        for (int i = 0; i < m; i++) {
            String[] input2 = br.readLine().split(" ");
            int u = Integer.parseInt(input2[0]);
            int v = Integer.parseInt(input2[1]);
            sneak[u] = v;
        }

        System.out.println(T.solution(ladder, sneak));

        br.close();
    }

    public int solution(int[] ladder, int[] sneak) {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);

        while(!q.isEmpty()) {
            int n = q.poll();

            if (n == 100) return visited[100];

            for (int i = 1; i <= 6; i++) {
                int next = n + i;
                if (next > 100) continue;
                if(ladder[next] > 0) next = ladder[next];
                else if (sneak[next] > 0) next = sneak[next];
                if (visited[next] > 0) continue;
                visited[next] = visited[n] + 1;
                q.add(next);
            }
        }
        return -1;
    }
}
