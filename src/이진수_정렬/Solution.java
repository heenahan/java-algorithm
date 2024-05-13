package 이진수_정렬;

import java.util.*;

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
        List<Bit> bits = new ArrayList<>();
        for (int num : nums) {
            int temp = num;
            int count = 0;
            while (temp > 0) {
                if (temp % 2 == 1) count++;
                temp /= 2;
            }
            bits.add(new Bit(count, num));
        }
        return bits.stream()
            .sorted(Comparator.comparing(Bit::getCount).thenComparing(Bit::getNum))
            .mapToInt(Bit::getNum)
            .toArray();
    }

    public static void main(String[] args) {
        Solution T = new Solution();
        System.out.println(Arrays.toString(T.solution(new int[]{5, 6, 7, 8, 9})));
        System.out.println(Arrays.toString(T.solution(new int[]{5, 4, 3, 2, 1})));
        System.out.println(Arrays.toString(T.solution(new int[]{12, 5, 7, 23, 45, 21, 17})));
    }
}
