package BOJ_6593;

import java.io.*;
import java.util.Queue;
import java.util.LinkedList;

class Node {
    int x; int y; int z;

    public Node(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}

public class Main {

    int[][][] visited;
    int[] dx = { 1, 0, -1, 0, 0, 0 };
    int[] dy = { 0, 1, 0, -1, 0, 0 };
    int[] dz = { 0, 0, 0, 0, 1, -1 }; // 위 아래

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {

            String[] input1 = br.readLine().split(" ");
            int l = Integer.parseInt(input1[0]);
            int r = Integer.parseInt(input1[1]);
            int c = Integer.parseInt(input1[2]);

            if (l == 0 && r == 0 && c == 0) break;
            T.setVisited(l, r, c);
            char[][][] building = new char[l][r][c];

            int start_x = 0; int start_y = 0; int start_z = 0;
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < r; j++) {
                    char[] input2 = br.readLine().toCharArray();
                    for (int k = 0; k < c; k++) {
                        building[i][j][k] = input2[k];
                        if (building[i][j][k] == 'S') {
                            start_x = j;
                            start_y = k;
                            start_z = i;
                        }
                    }
                }
                br.readLine();
            }

            int result = T.solution(l, r, c, start_x, start_y, start_z, building);
            if (result == -1) bw.write("Trapped!");
            else bw.write("Escaped in " + (result - 1) + " minute(s).");

            bw.newLine();
        }

        br.close();
        bw.close();
    }

    public void setVisited(int l, int r, int c) {
        this.visited = new int[l][r][c];
    }

    public int solution(int l, int r, int c, int start_x, int start_y, int start_z, char[][][] building) {
        Queue<Node> q = new LinkedList<>();
        Node start = new Node(start_x, start_y, start_z);
        q.add(start);
        visited[start_z][start_x][start_y] = 1;

        while (!q.isEmpty()) {
            Node v = q.poll();
            int x = v.getX();
            int y = v.getY();
            int z = v.getZ();

            if (building[z][x][y] == 'E') return visited[z][x][y];

            for (int i = 0; i < 6; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nz = z + dz[i];

                if (nx < 0 || ny < 0 || nz < 0 || nx >= r || ny >= c || nz >= l) continue;
                if (building[nz][nx][ny] == '#' || building[nz][nx][ny] == 'S') continue;
                if (visited[nz][nx][ny] > 0) continue;

                Node next = new Node(nx, ny, nz);
                visited[nz][nx][ny] = visited[z][x][y] + 1;
                q.add(next);
            }
        }
        return -1;
    }

}
