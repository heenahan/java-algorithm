package BOJ_2457;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Flower implements Comparable<Flower> {
    int start_month; int start_day;
    int end_month; int end_day;

    public Flower(int start_month, int start_day, int end_month, int end_day) {
        this.start_month = start_month;
        this.start_day = start_day;
        this.end_month = end_month;
        this.end_day = end_day;
    }

    @Override
    public int compareTo(Flower o) { // this > o return 1 오름차 정렬
        if (getEnd_month() > o.getEnd_month()) return 1; // 월이 더 큼
        else if (getEnd_month() == o.getEnd_month()) { // 월이 같음
            if (getEnd_day() > o.getEnd_day()) return 1; // 일이 큼
            else if (getEnd_day() == o.getEnd_day()) return 0; // 월 일 모두 같음
            else return -1;
        } else return -1;
    }

    public int getStart_month() {
        return start_month;
    }

    public int getStart_day() {
        return start_day;
    }

    public int getEnd_month() {
        return end_month;
    }

    public int getEnd_day() {
        return end_day;
    }
}

public class Main {

    int end_month = 3;
    int end_day = 1;

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        List<Flower> flowers = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            String[] input = br.readLine().split(" ");
            int sm = Integer.parseInt(input[0]);
            int sd = Integer.parseInt(input[1]);
            int em = Integer.parseInt(input[2]);
            int ed = Integer.parseInt(input[3]);
            flowers.add(new Flower(sm, sd, em, ed));
        }
        Collections.sort(flowers, Collections.reverseOrder());

        System.out.println(T.solution(flowers, num));

        br.close();
    }

    public int solution(List<Flower> flowers, int num) {
        int count = 0;
        int index = num;
        while (index >= 0) {
            boolean flag = false;
            for (int i = 0; i < index; i++) {
                Flower v = flowers.get(i);
                // 피는 날짜가 이전 꽃이 지는 날짜보다 과거일 때
                if (isBefore(v.getStart_month(), v.getStart_day())) {
                    count++;
                    flag = true;
                    index = i;
                    end_month = v.getEnd_month();
                    end_day = v.getEnd_day();
                    break;
                }
            }
            if (end_month == 12) return count; // 11월 마지막날까지 펴야 함 따라서 지는 날은 12월이어야 함
            // 이전 꽃보다 늦게 지는 것 중 일찍 피는게 없거나 제일 늦게 피는걸 골랐는데 지는 날이 12월이 아닐 때
            if (!flag || index == 0) return 0;
        }
        return -1;
    }

    public boolean isBefore(int month, int day) {
        if (month < end_month) return true; // 월이 작으면 과거
        else if (month == end_month) {
            if (day <= end_day) return true; // 월 일 같아도 과거
            else return false;
        } else return false; // 미래
    }
}
