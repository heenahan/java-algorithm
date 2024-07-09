package BOJ_17143;
import java.util.*;
import java.io.*;

class Shark {
    private int r;
    private int c;
    private int s;
    private int d;
    private int z;

    public Shark(int r, int c, int s, int d, int z) {
        this.r = r;
        this.c = c;
        this.s = s;
        this.d = d;
        this.z = z;
    }

    public int getR() {
        return this.r;
    }

    public int getC() {
        return this.c;
    }

    public int getS() {
        return this.s;
    }

    public int getD() {
        return this.d;
    }

    public int getZ() {
        return this.z;
    }
}

public class Main {

    // skip 위 아래 오른 왼
    private static int[] move;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] RCM = br.readLine().split(" ");
        int R = Integer.parseInt(RCM[0]);
        int C = Integer.parseInt(RCM[1]);
        int M = Integer.parseInt(RCM[2]);

        // 위치별 상어의 크기
        Shark[][] before = new Shark[R][C];
        move = new int[]{ 0, R - 1, 0, 0, C - 1 };
        for (int i = 0; i < M; i++) {
            String[] rcsdz = br.readLine().split(" ");
            int r = Integer.parseInt(rcsdz[0]);
            int c = Integer.parseInt(rcsdz[1]);
            int s = Integer.parseInt(rcsdz[2]);
            int d = Integer.parseInt(rcsdz[3]);
            int z = Integer.parseInt(rcsdz[4]);

            Shark shark = new Shark(r - 1, c - 1, s, d, z);
            before[r - 1][c - 1] = shark;
        }

        int sum = 0;
        // 크기 순으로 상어 정렬
        PriorityQueue<Shark> sharks = new PriorityQueue<>(Comparator.comparing(Shark::getZ));
        // 낚시왕이 열을 한 칸씩 움직임
        for (int i = 0; i < C; i++) {
            // 땅과 가장 가까운 상어 먹음
            for (int j = 0; j < R; j++) {
                if (before[j][i] != null) {
                    sum += before[j][i].getZ();
                    before[j][i] = null;
                    break;
                }
            }
            // 상어 크기 순으로 정렬
            for (int j = 0; j < R; j++) {
                for (int k = 0; k < C; k++) {
                    if (before[j][k] != null) {
                        sharks.add(before[j][k]);
                    }
                }
            }
            Shark[][] after = new Shark[R][C];
            // 상어 이동
            while (!sharks.isEmpty()) {
                Shark v = sharks.poll();

                int sr = v.getR(); int sc = v.getC();
                int ss = v.getS(); int sd = v.getD(); int sz = v.getZ();

                int nr = sr; int nc = sc; int nd = sd;
                // 위 아래일 때 r에서 움직임
                if (sd == 1 || sd == 2) {
                    if (sd == 1) sr = -sr;
                    int totalMove = move[sd] + sr + ss;
                    int c = totalMove / (R - 1); // 방향을 바꾼 횟수
                    int m = totalMove % (R - 1); // 이동한 횟수
                    // c가 짝수일 경우 방향을 안바꿈, 홀수일 경우 방향을 바꿈
                    // 위로 이동 중
                    if ((c % 2 == 0 && sd == 1) || (c % 2 == 1 && sd == 2)) {
                        nr = (R - 1) - m; nd = 1;
                    } else {
                        nr = m; nd = 2;
                    }
                }
                if (sd == 3 || sd == 4) {
                    if (sd == 4) sc = -sc;
                    int totalMove = move[sd] + sc + ss;
                    int c = totalMove / (C - 1); // 방향을 바꾼 횟수
                    int m = totalMove % (C - 1); // 이동한 횟수
                    if ((c % 2 == 0 && sd == 4) || (c % 2 == 1 && sd == 3)) {
                        nc = (C - 1) - m; nd = 4;
                    } else {
                        nc = m; nd = 3;
                    }
                }
                // 작은 수부터 움직이므로 상어 있으면 먹음
                Shark nshark = new Shark(nr, nc, ss, nd, sz);
                after[nr][nc] = nshark;
            }
            for (int j = 0; j < R; j++) {
                for (int k = 0; k < C; k++) {
                    before[j][k] = after[j][k];
                }
            }
        }
        System.out.print(sum);

        br.close();
    }
}
