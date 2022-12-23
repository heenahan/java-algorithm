package BOJ_9019;

import java.util.Queue;
import java.util.LinkedList;
import java.io.*;

class Register {
    int n; char[] calc;

    public Register(int n, char[] calc) {
        this.n = n;
        this.calc = new char[calc.length];
        for (int i = 0; i < calc.length; i++) this.calc[i] = calc[i];
    }

    public int getN() {
        return n;
    }

    public char[] getCalc() {
        return calc;
    }
}

public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    boolean[] visited;
    int[] shift = { 1, 10, 100, 1000 };

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        for (int i = 0; i < num; i++) {
            String[] input = br.readLine().split(" ");
            int st = Integer.parseInt(input[0]);
            int ed = Integer.parseInt(input[1]);
            T.setVisited();
            T.solution(st, ed);
        }

        br.close();
        bw.close();
    }

    public void setVisited() {
        visited = new boolean[10000];
    }

    public void solution(int st, int ed) throws IOException {
        Queue<Register> q = new LinkedList<>();
        char[] empty = { };
        Register r = new Register(st, empty);
        visited[st] = true;
        q.add(r);

        while (!q.isEmpty()) {
            Register vr = q.poll();
            int num = vr.getN();
            char[] calc = vr.getCalc();
            int size = calc.length;

            if (num == ed) {
                for (int i = 0; i < size; i++) bw.write(calc[i]);
                bw.newLine();
                return;
            }

            // 복사
            char[] new_calc = new char[size + 1];
            for (int i = 0; i < size; i++) new_calc[i] = calc[i];

            int dnum = num * 2 % 10000;
            new_calc[size] = 'D';
            if (!visited[dnum]) {
                q.add(new Register(dnum, new_calc));
                visited[dnum] = true;
            }

            int snum = num - 1;
            if (snum < 0) snum = 9999;
            new_calc[size] = 'S';
            if (!visited[snum]) {
                q.add(new Register(snum, new_calc));
                visited[snum] = true;
            }

            int temp = num;
            int lnum = 0;
            for (int i = 1; i < 5; i++) {
                lnum += (temp % 10 * shift[i % 4]);
                temp /= 10;
            }
            new_calc[size] = 'L';
            if (!visited[lnum]) {
                q.add(new Register(lnum, new_calc));
                visited[lnum] = true;
            }

            temp = num;
            int rnum = 0;
            for (int i = 3; i < 7; i++) {
                rnum += (temp % 10 * shift[i % 4]);
                temp /= 10;
            }
            new_calc[size] = 'R';
            if (!visited[rnum]) {
                q.add(new Register(rnum, new_calc));
                visited[rnum] = true;
            }

        }
    }

}
