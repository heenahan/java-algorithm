package BOJ_4949;

import java.io.*;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (true) {
            char[] str = br.readLine().toCharArray();
            if (str[0] == '.') break;
            bw.write(T.solution(str));
            bw.newLine();
        }

        br.close();
        bw.close();

    }

    public String solution(char[] str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str) {
            if (c == '(' || c == '[') stack.push(c);
            if (c == ')' || c == ']') {
                if (stack.isEmpty()) return "no";
                else {
                    char open = stack.pop();
                    if ((open == '(' && c == ')') || (open == '[' && c == ']')) continue;
                    else return "no";
                }
            }
        }
        if (stack.isEmpty()) return "yes";
        else return "no";
    }

}
