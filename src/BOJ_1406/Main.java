package BOJ_1406;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Node {

    Character c;
    Node left;
    Node right;

    public Node(Character c, Node left, Node right) {
        this.c = c;
        this.left = left;
        this.right = right;
    }

    public Character getC() {
        return c;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setC(Character c) {
        this.c = c;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

}

public class Main {

    Node head = null;

    public Node getHead() {
        return this.head;
    }

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().concat("0").toCharArray();
        Node cursor = T.toLinkedList(str);
        int num = Integer.parseInt(br.readLine());
        for (int i = 0; i < num; i++) {
            char[] command = br.readLine().toCharArray();
            switch (command[0]) {
                case 'L' : cursor = T.moveLeft(cursor); break;
                case 'D' : cursor = T.moveRight(cursor); break;
                case 'B' : T.remove(cursor); break;
                case 'P' : T.insert(command[2], cursor); break;
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (cursor.getLeft() != null) {
            cursor = cursor.getLeft();
        }
        while (cursor.getC() != '0') { // 마지막을 알리는 문자 '0'을 빼고 모두 출력
            bw.write(cursor.getC());
            cursor = T.moveRight(cursor);
        }

        br.close();
        bw.close();
    }

    // 마지막 노드 반환
    public Node toLinkedList(char[] str) {
        Node before = null;
        for (int i = 0; i < str.length; i++) {
            Node n = new Node(str[i], before, null);
            if (i == 0) head = n;
            if (before != null) before.setRight(n);
            before = n;
        }
        return before;
    }

    public Node moveLeft(Node cursor) {
        Node left = cursor.getLeft();
        if (left == null) return cursor;
        else return left;
    }

    public Node moveRight(Node cursor) {
        Node right = cursor.getRight();
        if (right == null) return cursor;
        else return right;
    }

    public void insert(char v, Node cursor) { // 맨 처음 문자에 위치해 있어도 상관 없음!
        Node before = cursor.getLeft();
        Node n = new Node(v, before, cursor);
        if (before != null) before.setRight(n);
        cursor.setLeft(n);
    }

    public void remove(Node cursor) {
        Node left = cursor.getLeft();
        if (left == null) return;
        Node before = left.getLeft(); // 새로운 before이 null 일 수 있음!!
        if (before != null) before.setRight(cursor);
        cursor.setLeft(before);
    }

}
