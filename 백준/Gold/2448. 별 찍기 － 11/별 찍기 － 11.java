import java.io.*;

public class Main {
    
    static String[][] map;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        int height = n;
        int width = 5 * (n / 3) + (n / 3) - 1;
        map = new String[height][width];
        
        recursive(n, 0, width - 1, 0, height - 1);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] == null) bw.write(" ");
                else bw.write(map[i][j]);
            }
            bw.newLine();
        }
        
        br.close(); bw.close();
    }
    
    static void recursive(int n, int stCol, int edCol, int stRow, int edRow) {
        if (n == 3) {
            // 첫번째 row
            int mid = (stCol + edCol) / 2;
            map[stRow][mid] = "*";
            // 두번째 row
            map[stRow + 1][mid - 1] = "*";
            map[stRow + 1][mid + 1] = "*";
            // 세번째 row
            for (int i = stCol; i <= edCol; i++) {
                map[stRow + 2][i] = "*";
            }
            return;
        }
        
        int midRow = (stRow + edRow) / 2;
        int midCol = (stCol + edCol) / 2;
        // 위쪽
        int next = n / 2;
        recursive(next, stCol + next, edCol - next, stRow, midRow);
        // 아래 왼쪽
        recursive(next, stCol, midCol - 1, midRow + 1, edRow);
        // 아래 오른쪽
        recursive(next, midCol + 1, edCol, midRow + 1, edRow);
    }
}