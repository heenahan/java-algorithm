package BOJ_1914;

import java.io.*;
import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;

class Node {
    private int start;
    private int end;

    public Node(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

}

public class Main {

    List<Node> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int num = Integer.parseInt(br.readLine());

        if (num <= 20) {
            T.hanoi_top(1, 2, 3, num);
            T.printList(bw);
        } else T.printMove(bw, num);

        br.close();
        bw.close();
    }

    public void printList(BufferedWriter bw) throws IOException {
        Integer size = list.size();

        bw.write(size.toString());
        bw.newLine();

        for (Node n : list) {
            bw.write(n.getStart() + " " + n.getEnd());
            bw.newLine();
        }
    }

    public void printMove(BufferedWriter bw, int num) throws IOException{
        BigInteger move = new BigInteger("2");
        move = move.pow(num).subtract(new BigInteger("1"));
        bw.write(move.toString());
    }

    public void hanoi_top(int start, int temp, int end, int num) {
        if (num == 0) return;
        hanoi_top(start, end, temp, num - 1);
        Node node = new Node(start, end);
        list.add(node);
        hanoi_top(temp, start, end, num - 1);
    }

}