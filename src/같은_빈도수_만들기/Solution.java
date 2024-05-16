package 같은_빈도수_만들기;

import java.util.*;

class Solution {
    public int[] solution(String s){
        int[] answer = new int[5];
        Map<Character, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            int count = map.get(c);
            if (count > max) max = count;
        }
        for (int i = 0; i < 5; i++) {
            char c = (char) ('a' + i);
            int count = map.getOrDefault(c, 0);
            answer[i] = max - count;
        }
        return answer;
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(Arrays.toString(T.solution("aaabc")));
        System.out.println(Arrays.toString(T.solution("aabb")));
        System.out.println(Arrays.toString(T.solution("abcde")));
        System.out.println(Arrays.toString(T.solution("abcdeabc")));
        System.out.println(Arrays.toString(T.solution("abbccddee")));
    }
}

