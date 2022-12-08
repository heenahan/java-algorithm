package BOJ_1759;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int l, c;
    String[] password;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input1 = br.readLine().split(" ");
        int l = Integer.parseInt(input1[0]);
        int c = Integer.parseInt(input1[1]);
        T.setLAndPassword(l);
        T.setC(c);

        List<String> arr = new ArrayList<>();
        String[] input2 = br.readLine().split(" ");
        for (String s : input2) arr.add(s);
        Collections.sort(arr);

        T.solution(0, 0, arr);

        br.close();
        bw.close();
    }

    public void setLAndPassword(int l) {
        this.l = l;
        password = new String[l];
    }

    public void setC(int c) {
        this.c = c;
    }

    public void solution(int num, int loop, List<String> arr) throws IOException {
        if (num == l) {
            int vowels = 0;
            for (int i = 0; i < l; i++) {
                char c = password[i].toCharArray()[0];
                int ascii = c - 97;
                // a : 97 e : 101 i : 105 o : 111  u : 117
                if (ascii == 0 || ascii == 4 || ascii == 8 || ascii == 14 || ascii == 20) vowels++;
            }
            if (vowels > 0 && num - vowels >= 2) {
                for (int i = 0; i < l; i++) bw.write(password[i]);
                bw.newLine();
            }
            return;
        } else if (loop >= c) return;
        password[num] = arr.get(loop);
        solution(num + 1, loop + 1, arr);
        solution(num, loop + 1, arr);
    }

}
