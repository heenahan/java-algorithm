package PGS_보석_쇼핑;

import java.util.*;

public class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        Set<String> names = new HashSet<>();
        for (String gem : gems) {
            names.add(gem);
        }

        Map<String, Integer> shopping = new HashMap<>();
        int end = 0; int min = Integer.MAX_VALUE;
        for (int start = 0; start < gems.length; start++) {
            // 두 사이즈가 같을 때까지
            while (end < gems.length && shopping.size() < names.size()) {
                shopping.put(gems[end], shopping.getOrDefault(gems[end], 0) + 1);
                end++;
            }
            int length = end - start;
            // 길이가 같고 length가 작을때
            if (shopping.size() == names.size() && length < min) {
                answer[0] = start + 1; answer[1] = end;
                min = length;
            }
            // start 삭제
            shopping.put(gems[start], shopping.get(gems[start]) - 1);
            if (shopping.get(gems[start]) == 0) {
                shopping.remove(gems[start]);
            }
        }
        return answer;
    }
}