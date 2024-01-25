import java.util.*;

class Solution {
    // 아래 오른 대각
    private static final int[] dx = { 1, 0, -1 };
    private static final int[] dy = { 0, 1, -1 };
    
    public int[] solution(int n) {
        int mul = n % 2 == 0 ? n / 2 : n / 2 + 1;
        int sum = n % 2 == 0 ? n / 2 : 0;
        int size = n * mul + sum;
        
        int[][] triangle = new int[n][n];
        triangle[0][0] = 1;
        int x = 0; int y = 0; int d = 0; int num = 2;
        while (num <= size) {
            int nx = x + dx[d]; 
            int ny = y + dy[d];
            // 범위를 넘어서거나 이미 채워졌다면
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || triangle[nx][ny] > 0) {
                d = (d + 1) % 3;
                continue;
            }
            triangle[nx][ny] = num;
            num++; x = nx; y = ny;
        }
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (triangle[i][j] == 0) break;
                answer.add(triangle[i][j]);
            }
        }
        return answer.stream()
            .mapToInt(i -> i)
            .toArray();
    }
}