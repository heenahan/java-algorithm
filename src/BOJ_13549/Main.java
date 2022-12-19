package BOJ_13549;

import java.io.*;
import java.util.Queue;
import java.util.LinkedList;

public class Main {

    boolean[] visited = new boolean[200001];
    int[] dfs = new int[200001];

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int start = Integer.parseInt(input[0]);
        int end = Integer.parseInt(input[1]);

        System.out.println(T.solution(start, end));

        br.close();
    }

    public int solution(int start, int end) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while(!q.isEmpty()) {
            int num = q.poll();

            if (num == end) return dfs[end];

            if (num - 1 >= 0 && !visited[num - 1]) {
                dfs[num - 1] = dfs[num] + 1;
                visited[num - 1] = true;
                q.add(num - 1);
            }
            if (num + 1 <= 200000 && !visited[num + 1]) {
                dfs[num + 1] = dfs[num] + 1;
                visited[num + 1] = true;
                q.add(num + 1);
            }
            if (num * 2 <= 200000 && !visited[num * 2]) {
                dfs[num * 2] = dfs[num];
                visited[num * 2] = true;
                q.add(num * 2);
            }
        }
        return -1;
    }
}
