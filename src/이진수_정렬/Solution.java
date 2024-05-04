package 이진수_정렬;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Bit {
    private int count;
    private int num;

    public Bit(int count, int num) {
        this.count = count;
        this.num = num;
    }

    public int getCount() {
        return count;
    }

    public int getNum() {
        return num;
    }
}

public class Solution {

    public int[] solution(int[] nums){
        int[] answer = new int[nums.length];
        PriorityQueue<Bit> queue = new PriorityQueue<>(Comparator.comparing(Bit::getCount)
            .thenComparing(Bit::getNum));
        for (int num : nums) {
            var count = Integer.bitCount(num);
            queue.add(new Bit(count, num));
        }
        for (int i = 0; i < nums.length; i++) {
            answer[i] = queue.poll().getNum();
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution T = new Solution();
        System.out.println(Arrays.toString(T.solution(new int[]{5, 6, 7, 8, 9})));
        System.out.println(Arrays.toString(T.solution(new int[]{5, 4, 3, 2, 1})));
        System.out.println(Arrays.toString(T.solution(new int[]{12, 5, 7, 23, 45, 21, 17})));
    }
}
