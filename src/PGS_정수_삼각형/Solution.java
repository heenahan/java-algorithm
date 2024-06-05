package PGS_정수_삼각형;

public class Solution {
    public int solution(int[][] triangle) {
        int height = triangle.length;
        // bottom up
        for (int i = height - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                // 왼쪽 오른쪽 중 큰 거 선택
                triangle[i][j] += Math.max(triangle[i + 1][j], triangle[i + 1][j + 1]);
            }
        }
        return triangle[0][0];
    }
}
