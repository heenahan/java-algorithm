package BOJ_1931;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Meeting implements Comparable<Meeting> {
    private int start;
    private int end;

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Meeting o) {
        return getEnd() - o.getEnd(); // 오름차순
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Meeting> meetings = new ArrayList<>();

        int num = Integer.parseInt(br.readLine());
        for (int i = 0; i < num; i++) {
            String[] input = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            meetings.add(new Meeting(start, end));
        }

        Collections.sort(meetings);

        System.out.println(T.solution(meetings));

        br.close();
    }

    public int solution(List<Meeting> meetings) {
        Meeting before = new Meeting(0, 0);
        int num = 0;
        for (Meeting meeting : meetings) {
            int before_end = before.getEnd();
            int next_start = meeting.getStart();
            if (next_start >= before_end) {
                num++;
                before = meeting;
            }
        }
        return num;
    }
}
