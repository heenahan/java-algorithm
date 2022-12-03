package BOJ_10828;

import java.io.*;

class Node {
    private int v;
    private Node before;

    public Node(int v, Node before) {
        this.v = v;
        this.before = before;
    }

    public int getV() {
        return this.v;
    }

    public Node getBefore() {
        return this.before;
    }

    public void setBefore(Node before) {
        this.before = before;
    }

}

public class Main {

    private int size = 0;
    private Node top = null;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int num = Integer.parseInt(br.readLine());
        for (int i = 0; i < num; i++) {
            String[] command = br.readLine().split(" ");
            switch (command[0]) {
                case "push" : T.push(Integer.parseInt(command[1])); break;
                case "pop" : bw.write(T.pop().toString()); bw.newLine(); break;
                case "size" : bw.write(T.getSize().toString()); bw.newLine(); break;
                case "empty" : bw.write(T.isEmpty().toString()); bw.newLine(); break;
                case "top" : bw.write(T.getTop().toString()); bw.newLine(); break;
            }
        }
        br.close();
        bw.close();
    }

    public Integer getSize() {
        return this.size;
    }

    // 새로운 top
    public void push(int v) {
        Node n = new Node(v, top);
        top = n;
        size++;
    }

    public Integer pop() {
        if (top == null) return -1; // 사이즈가 0
        int v = top.getV();
        top = top.getBefore(); // 이전 노드가 top으로 변환
        size--;
        return v;
    }

    public Integer isEmpty() {
        if (top == null) return 1;
        else return 0;
    }

    public Integer getTop() {
        if (top == null) return -1;
        else return this.top.getV();
    }

}
