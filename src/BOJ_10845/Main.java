package BOJ_10845;

import java.io.*;

class Node {

    private int v;
    private Node next;

    public Node(int v, Node next) {
        this.v = v;
        this.next = next;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}

public class Main {

    private int size = 0;
    private Node front = null;
    private Node rear = null;
    // push pop size empty front back 정수 없으면 -1
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int num = Integer.parseInt(br.readLine());
        for (int i = 0; i < num; i++) {
            String[] command = br.readLine().split(" ");
            switch(command[0]) {
                case "push": T.push(Integer.parseInt(command[1])); break;
                case "pop": bw.write(T.pop().toString()); bw.newLine(); break;
                case "size": bw.write(T.getSize().toString()); bw.newLine(); break;
                case "empty": bw.write(T.isEmpty().toString()); bw.newLine(); break;
                case "front": bw.write(T.getFront().toString()); bw.newLine(); break;
                case "back": bw.write(T.getRear().toString()); bw.newLine(); break;
            }
        }

        br.close();
        bw.close();

    }

    public Integer getSize() {
        return this.size;
    }

    public void push(int v) {
        Node n = new Node(v, null);
        if (rear != null) rear.setNext(n);
        rear = n;
        if (front == null) front = n;
        size++;
    }

    public Integer pop() {
        if (front == null) return -1;
        int v = front.getV();
        Node next = front.getNext();
        if (next == null) rear = next;
        front = next;
        size--;
        return v;
    }

    public Integer isEmpty() {
        if (front == null) return 1;
        else return 0;
    }

    public Integer getFront() {
        if (front == null) return -1;
        else return front.getV();
    }

    public Integer getRear() {
        if (rear == null) return -1;
        else return rear.getV();
    }

}
