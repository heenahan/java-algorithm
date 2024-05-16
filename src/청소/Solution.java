package 청소;
import java.util.*;

class Solution {

    int[][] direction = {
        { 0, 1 },
        { 1, 0 },
        { 0, -1 },
        { -1, 0 }
    };

    public int[] solution(int n, int[][] board, int k){
        int x = 0; int y = 0; int d = 0;
        while (k > 0) {
            int nx = x + direction[d][0];
            int ny = y + direction[d][1];
            // 범위 넘어가면
            if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                d = (d + 1) % 4;
                k--;
                continue;
            }
            // 벽이라면
            if (board[nx][ny] == 1) {
                d = (d + 1) % 4;
                k--;
                continue;
            }
            x = nx; y = ny;
            k--;
        }
        return new int[]{ x, y };
    }

    public static void main(String[] args){
        Solution T = new Solution();
        int[][] arr1 = {{0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {1, 0, 1, 0, 1},
            {0, 0, 0, 0, 0}};
        System.out.println(Arrays.toString(T.solution(5, arr1, 10)));
        int[][] arr2 = {{0, 0, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1},
            {1, 1, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}};
        System.out.println(Arrays.toString(T.solution(6, arr2, 20)));
        int[][] arr3 = {{0, 0, 1, 0, 0},
            {0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0}};
        System.out.println(Arrays.toString(T.solution(5, arr3, 25)));

    }
}