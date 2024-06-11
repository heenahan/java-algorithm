package PGS_셔틀버스;

import java.util.*;

public class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        // 승객을 먼저 온 순서대로 줄 세움
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (String time : timetable) {
            String[] ts = time.split(":");
            int hour = Integer.parseInt(ts[0]);
            int min = Integer.parseInt(ts[1]);
            int totalTime = hour * 60 + min;
            queue.add(totalTime);
        }

        int start = 9 * 60; // 시작시간
        int con = 0;
        for (int i = 0; i < n; i++) {
            List<Integer> passengers = new ArrayList<>();
            // 태울 수 있을 때까지 태움 (탑승 시간보다 작을 때)
            while (!queue.isEmpty() && start >= queue.peek()) {
                passengers.add(queue.poll());
                // m 크기를 넘어서 태우면 종료
                if (passengers.size() >= m) {
                    break;
                }
            }
            // 마지막 버스일 때
            if (i == n - 1) {
                // 이미 꽉 찼다면 마지막 사람보다 1분만 빨리 오면 됨
                if (passengers.size() >= m) {
                    int last = passengers.get(m - 1);
                    con = last - 1;
                    continue;
                }
                // 자리가 남아있다면 버스 시간에 딱 맞춰 오면 됨
                con = start;
            }
            start += t;
        }
        String hour = Integer.toString(con / 60);
        String min = Integer.toString(con % 60);
        hour = hour.length() > 1 ? hour : "0" + hour;
        min = min.length() > 1 ? min : "0" + min;
        return hour + ":" + min;
    }
}