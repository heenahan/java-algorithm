package 침몰하는_타이타닉;

import java.util.*;

class Solution {

    public int solution(int[] nums, int m){
        int answer = 0;
        int[] sortedNums = Arrays.stream(nums)
            .sorted()
            .toArray();
        int st = 0;
        int ed = sortedNums.length - 1;
        while (st < ed) {
            answer++;
            if (sortedNums[st] + sortedNums[ed] > m) {
                ed--;
                continue;
            }
            st++; ed--;
        }
        return answer;
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(new int[]{90, 50, 70, 100, 60}, 140));
        System.out.println(T.solution(new int[]{86, 95, 107, 67, 38, 49, 116, 22, 78, 53}, 150));
        System.out.println(T.solution(new int[]{68, 72, 30, 105, 55, 115, 36, 67, 119, 111, 95, 24, 25, 80, 55, 85, 75, 83, 21, 81}, 120));
    }
}