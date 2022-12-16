package BOJ_14888;

import java.io.*;

public class Main {

    private int[] operator;
    private int[] operand;
    private int num;
    private int min = Integer.MAX_VALUE;
    private int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        T.setNum(num);

        int[] operand = new int[num];
        String[] input1 = br.readLine().split(" ");
        for (int i = 0; i < num; i++) operand[i] = Integer.parseInt(input1[i]);
        T.setOperand(operand);

        int[] operator = new int[4];
        String[] input2 = br.readLine().split(" ");
        for (int i = 0; i < 4; i++) operator[i] = Integer.parseInt(input2[i]);
        T.setOperator(operator);

        T.solution(0, operand[0], 0, 0, 0, 0);

        System.out.println(T.getMax());
        System.out.println(T.getMin());

        br.close();
    }

    public void setOperator(int[] operator) {
        this.operator = new int[4];
        for (int i = 0; i < 4; i++) this.operator[i] = operator[i];
    }

    public void setOperand(int[] operand) {
        this.operand = new int[num];
        for (int i = 0; i < num; i++) this.operand[i] = operand[i];
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getMin() {
        return this.min;
    }

    public int getMax() {
        return this.max;
    }

    // 5 (0) 6 (1) 7
    // 1 : + 2 : - 3 : / 4 : *
    public void solution(int index, int result, int add, int sub, int mul, int div) {
        if (index == num - 1) {
            if (result > max) max = result;
            if (result < min) min = result;
            return;
        }
        for (int i = 0; i < 4; i++) {
            // 더하기
            if (i == 0 && add < operator[i]) solution(index + 1, result + operand[index + 1], add + 1, sub, mul, div);
            // 빼기
            if (i == 1 && sub < operator[i]) solution(index + 1, result - operand[index + 1], add, sub + 1, mul, div);
            // 곱하기
            if (i == 2 && mul < operator[i]) solution(index + 1, result * operand[index + 1], add, sub, mul + 1, div);
            // 나누기
            if (i == 3 && div < operator[i]) solution(index + 1, result / operand[index + 1], add, sub, mul, div + 1);
        }
    }
}
