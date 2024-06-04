package PGS_기지국_설치;

public class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int start = 1;
        int aw = w * 2 + 1;
        for (int station : stations) {
            int ns = Math.max(1, station - w);
            int ne = Math.min(n, station + w);
            int length = ns - start;
            // 기지국 설치
            answer += (length / aw);
            if (length % aw > 0) {
                answer++;
            }
            start = ne + 1;
        }
        int length = n - start + 1;
        answer += (length / aw);
        if (length % aw > 0) {
            answer++;
        }
        return answer;
    }
}