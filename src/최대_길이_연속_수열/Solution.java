package 최대_길이_연속_수열;

import java.util.*;

public class Solution {
    public int solution(int[] nums){
        List<Integer> n = new ArrayList<>();
        for (int num : nums) {
            n.add(num);
        }
        Collections.sort(n); // 정렬
        int max = Integer.MIN_VALUE; int len = 0;
        for (int st = 0; st < nums.length - 1; st++) {
            if (n.get(st + 1) <= n.get(st) + 1) {
                if (n.get(st + 1) == n.get(st) + 1) len++;
                max = Math.max(max, len);
                continue;
            }
            len = 0;
        }
        return max + 1;
    }

    public int solution2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int len = 0; int max = Integer.MIN_VALUE;
        for (int s : set) {
            if (set.contains(s - 1)) continue;
            int st = s;
            while (set.contains(st + 1)) {
                len++; st++;
            }
            max = Math.max(max, len); len = 0;
        }
        return max + 1;
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution2(new int[]{8, 1, 9, 3, 10, 2, 4, 0, 2, 3}));
        System.out.println(T.solution2(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0, 0, 0}));
        System.out.println(T.solution2(new int[]{3, 3, 3, 3, 3, 3, 3, 3}));
        System.out.println(T.solution2(new int[]{-3, -1, -2, 0, 3, 3, 5, 6, 2, 2, 1, 1}));
        System.out.println(T.solution2(new int[]{-5, -3, -1, -4, 3, 3, 5, 6, 2, 2, 1, 1, 7}));
    }
}