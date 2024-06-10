package PGS_징검다리_건너기;

import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        // 인덱스에 대한 deque 만듦 (deque는 내림차순으로 정렬되어 유지됨)
        Deque<Integer> dq = new ArrayDeque<>();
        // k번째까지 만듦
        for (int i = 0; i < k; i++) {
            // 뒤에 값 작으면 뺌
            while(!dq.isEmpty() && stones[dq.peekLast()] < stones[i]) {
                dq.pollLast();
            }
            dq.add(i);
        }
        int start = 0; int min = stones[dq.peek()];
        // k 부터 시작
        for (int end = k; end < stones.length; end++) {
            // start가 최대값과 일치 시 제거
            if (dq.peek() == start) {
                dq.poll();
            }
            while(!dq.isEmpty() && stones[dq.peekLast()] < stones[end]) {
                dq.pollLast();
            }
            dq.add(end);
            // 윈도우의 최대값과 비교
            min = Math.min(min, stones[dq.peek()]);
            start++;
        }
        return min;
    }
}