package 사다리타기;

import java.util.*;
public class Solution {
    public char[] solution(int n, int[][] ladder){
        char[] answer = new char[n];
        int height = ladder.length; // 사다리 높이
        boolean[][] mark = new boolean[height][n - 1];
        for (int i = 0; i < height; i++) { // 사다리 마킹 작업
            int[] ld = ladder[i];
            for (int l : ld) {
                mark[i][l - 1] = true;
            }
        }
        for (int i = 0; i < n; i++) { // n명이 사다리 타기 시작
            int loc = i; // 위치
            for (int j = 0; j < height; j++) {
                if (loc > 0) { // 왼쪽에 사다리 있는지 확인
                    if (mark[j][loc - 1]) { // 있으면 왼쪽 이동
                        loc--;
                        continue;
                    }
                }
                if (loc < n - 1 && mark[j][loc]) { // 자신의 위치에 있으면 오른쪽 이동
                    loc++;
                }
            }
            answer[loc] = (char) ('A' + i);
        }
        return answer;
    }

    public char[] solution2(int n, int[][] ladder) { // 모두 함께 내려온다고 생각
        char[] answer = new char[n];
        for (int i = 0; i < n; i++) {
            answer[i] = (char) (i + 'A');
        }
        int height = ladder.length;
        for (int i = 0; i < height; i++) {
            int[] ld = ladder[i];
            for (int l : ld) {
                char temp = answer[l];
                answer[l] = answer[l - 1];
                answer[l - 1] = temp;
            }
        }
        return answer;
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(Arrays.toString(T.solution2(5, new int[][]{{1, 3}, {2, 4}, {1, 4}})));
        System.out.println(Arrays.toString(T.solution2(7, new int[][]{{1, 3, 5}, {1, 3, 6}, {2, 4}})));
        System.out.println(Arrays.toString(T.solution2(8, new int[][]{{1, 5}, {2, 4, 7}, {1, 5, 7}, {2, 5, 7}})));
        System.out.println(Arrays.toString(T.solution2(12, new int[][]{{1, 5, 8, 10}, {2, 4, 7}, {1, 5, 7, 9, 11}, {2, 5, 7, 10}, {3, 6, 8, 11}})));
    }
}