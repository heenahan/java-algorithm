package BOJ_1541;

import java.util.Scanner;
import java.util.Deque;
import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) {
        Main T = new Main();
        Scanner in = new Scanner(System.in);
        String expression = in.next();
        String[] operator = expression.split("[\\d]+"); // 숫자가 하나 이상인 것을 기준 -> +, -만 남음
        String[] operand = expression.split("[\\D]"); // 숫자가 아닌 것을 기준 -> 숫자만 남음

        System.out.println(T.solution(operator, operand));

        in.close();
    }

    public int solution(String[] operator, String[] operand) {
        Deque<Integer> d = new ArrayDeque<>();
        int size = operator.length;
        d.addLast(Integer.parseInt(operand[0]));
        for (int i = 1; i < size; i++) {
            int next = Integer.parseInt(operand[i]);
            if (operator[i].equals("+")) { // 플러스면 먼저 계산
                int before = d.pollLast();
                d.addLast(before + next);
            } else d.addLast(next);
        }
        int sum = d.pollFirst();
        while (!d.isEmpty()) sum -= d.pollFirst();
        return sum;
    }
}
