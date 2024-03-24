package 한_번만_사용한_최초_문자;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(String s){
        Set<Character> set = new HashSet<>();
        char c = ' ';
        int idx = -1;
        for (int i = s.length() - 1; i >= 0; i--) {
            char sc = s.charAt(i);
            if (set.contains(sc)) {
                if (sc == c) {
                    c = ' ';
                    idx = -1;
                }
                continue;
            }
            set.add(sc);
            c = sc;
            idx = i + 1;
        }
        return idx;
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution("statitsics"));
        System.out.println(T.solution("aabb"));
        System.out.println(T.solution("stringshowtime"));
        System.out.println(T.solution("abcdeabcdfg"));
    }
}