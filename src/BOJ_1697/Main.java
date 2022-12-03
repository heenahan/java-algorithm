package BOJ_1697;

import java.io.*;
import java.util.Queue;
import java.util.LinkedList;

class Node {
    int x;
    int level;

    public Node(int x, int level) {
        this.x = x;
        this.level = level;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}

public class Main {

    boolean[] visited = new boolean[100001];

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        bw.write(T.solution(n, k).toString());

        br.close();
        bw.close();

    }

    public Integer solution(int n, int k) {
        Queue<Node> q = new LinkedList<>();
        Node node = new Node(n, 0);
        q.add(node);

        while (!q.isEmpty()) {
            Node v = q.poll();
            int x = v.getX();
            int level = v.getLevel();
            if (x == k) return level;

            // 1칸 뒤로
            if (x - 1 >= 0 && !visited[x - 1]) {
                Node next = new Node(x - 1, level + 1);
                visited[x - 1] = true;
                q.add(next);
            }
            // 1칸 앞으로
            if (x + 1 <= 100000 && !visited[x + 1]) {
                Node next = new Node(x + 1, level + 1);
                visited[x + 1] = true;
                q.add(next);
            }
            // 곱하기 2 앞으로
            if (x * 2 <= 100000 && !visited[x * 2]) {
                Node next = new Node(x * 2, level + 1);
                visited[x * 2] = true;
                q.add(next);
            }
        }

        return 0;
    }

}
