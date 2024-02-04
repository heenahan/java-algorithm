import java.util.*;
import java.util.stream.*;

class Solution {
    
    private Map<String, List<Integer>> scores = new HashMap<>();
    
    public int[] solution(String[] info, String[] query) {
        List<Integer> answer = new ArrayList<>();
        for (String i : info) {
            String[] applier = i.split(" ");
            backTracking(0, "", applier); // Map에 지원자 정보 저장
        }
        for (List<Integer> score : scores.values()) {
            Collections.sort(score);
        }
        for (String q : query) {
            String[] con = q.split(" ");
            String s = "";
            for (int i = 0; i < con.length - 1; i++) {
                if (!con[i].equals("and")) {
                    s += con[i];
                }
            }
            List<Integer> list = scores.getOrDefault(s, new ArrayList<>());
            Integer score = Integer.parseInt(con[con.length - 1]);
            int idx = lowerBound(list, score);
            answer.add(list.size() - idx);
        }
        return answer.stream()
                    .mapToInt(i -> i)
                    .toArray();
    }
    
    private int lowerBound(List<Integer> list, int key) {
        int start = 0; int end = list.size();
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid) < key) start = mid + 1;
            else end = mid;
        }
        return start;
    }
    
    private void backTracking(int n, String prefix, String[] info) {
        if (n == info.length - 1) { // 마지막 score 제외
            scores.putIfAbsent(prefix, new ArrayList<>());
            Integer score = Integer.parseInt(info[n]);
            scores.get(prefix).add(score);
            return;
        }
        
        backTracking(n + 1, prefix + info[n], info);
        backTracking(n + 1, prefix + "-", info);
    }
}