package BOJ_1629;

import java.io.*;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        BigInteger a = new BigInteger(input[0]);
        BigInteger b = new BigInteger(input[1]);
        BigInteger c = new BigInteger(input[2]);

        bw.write(T.solution(a, b, c).toString());

        br.close();
        bw.close();
    }

    public BigInteger solution(BigInteger a, BigInteger b, BigInteger c) {
        if (b.equals(BigInteger.ONE)) return a.mod(c);
        BigInteger mid = b.divide(BigInteger.TWO);
        BigInteger n = solution(a, mid, c);
        // 홀수라면
        if (b.mod(BigInteger.TWO).equals(BigInteger.ONE)) return n.pow(2).multiply(a).mod(c);
        else return n.pow(2).mod(c);
    }

}
