package BOJ_1509;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split("");
        int n = s.length;
        boolean[][] isPalindrome = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            isPalindrome[i][i] = true;
            for (int j = i + 1; j < n; j++) {
                if(Objects.equals(s[i], s[j])) {
                    // 길이가 2이고 가운데가 팰린드롬일때
                    if (j - i + 1 == 2 || isPalindrome[i + 1][j - 1]) {
                        isPalindrome[i][j] = true;
                    }
                }
            }
        }
        // bfs
        int[] visited = new int[n + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(0); visited[0] = 1;
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int i = v; i < n; i++) {
                // 팰린드롬이고 방문 안했으면
                if (isPalindrome[v][i] && visited[i + 1] <= 0) {
                    q.add(i + 1);
                    visited[i + 1] = visited[v] + 1;
                }
            }
        }
        System.out.println(visited[n] - 1);

        br.close();
    }
}