import java.util.*;

class Solution {
    
    int[] oneX = { 1, 0, -1, 0 };
    int[] oneY = { 0, 1, 0, -1 };
    
    int[] twoX = { 2, 0, -2, 0 };
    int[] twoY = { 0, 2, 0, -2 };
    
    int[] diagonalX = { -1, 1, -1, 1 };
    int[] diagonalY = { -1, -1, 1, 1 };
    int[][] blockX = { { -1, 0 }, { 0, 1 }, { -1, 0 }, { 0, 1 } };
    int[][] blockY = { { 0, -1 }, { -1, 0}, { 0, 1 }, { 1, 0 } };
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for (int i = 0; i < places.length; i++) {
            answer[i] = calculate(places[i]);
        }
        return answer;
    }
    
    private int calculate(String[] place) {
        String[][] p = Arrays.stream(place)
                .map(s -> s.split(""))
                .toArray(String[][]::new);
            
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                // 응시자가 아닌 경우
                if (!p[i][j].equals("P")) continue;
                // 거리가 1인 경우 
                for (int k = 0; k < 4; k++) {
                    int nx = i + oneX[k];
                    int ny = j + oneY[k];
                    if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
                    if (p[nx][ny].equals("P")) return 0;
                }
                // 거리가 2일 때
                for (int k = 0; k < 4; k++) {
                    int nx = i + twoX[k];
                    int ny = j + twoY[k];
                    if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
                    if (!p[nx][ny].equals("P")) continue;
                    int blockX = i + oneX[k];
                    int blockY = j + oneY[k];
                    // 칸막이 없으면
                    if (!p[blockX][blockY].equals("X")) return 0;
                }
                // 대각선일때
                for (int k = 0; k < 4; k++) {
                    int nx = i + diagonalX[k];
                    int ny = j + diagonalY[k];
                    if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
                    if (!p[nx][ny].equals("P")) continue;
                    
                    int blockX1 = i + blockX[k][0];
                    int blockY1 = j + blockY[k][0];
                    int blockX2 = i + blockX[k][1];
                    int blockY2 = j + blockY[k][1];
                    if (!p[blockX1][blockY1].equals("X") || !p[blockX2][blockY2].equals("X")) return 0;
                }
            }
        }
        return 1;
    }
}