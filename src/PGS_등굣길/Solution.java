package PGS_등굣길;

import java.util.*;

public class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n][m];
        // 물 웅덩이
        for (int[] puddle : puddles) {
            int x = puddle[0];
            int y = puddle[1];
            map[y - 1][x - 1] = -1;
        }
        // 테두리 위
        for (int i = 0; i < 1; i++) {
            // 물 웅덩이 앞으로 지나갈 수 없으니 0
            boolean isPuddle = false;
            for (int j = 0; j < m; j++) {
                if (map[i][j] < 0) {
                    isPuddle = true;
                    continue;
                }
                if (!isPuddle) {
                    map[i][j] = 1;
                    continue;
                }
                map[i][j] = 0;
            }
        }
        // 테두리 왼쪽
        boolean isPuddle = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 1; j++) {
                if (map[i][j] < 0) {
                    isPuddle = true;
                    continue;
                }
                if (!isPuddle) {
                    map[i][j] = 1;
                    continue;
                }
                map[i][j] = 0;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (map[i][j] < 0) { // 웅덩이면 skip
                    continue;
                }
                int up = Math.max(0, map[i - 1][j]);
                int left = Math.max(0, map[i][j - 1]);
                map[i][j] = (up + left) % 1_000_000_007;
            }
        }
        return map[n - 1][m - 1];
    }
}
