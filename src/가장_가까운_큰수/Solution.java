package 가장_가까운_큰수;

import java.util.*;

class Solution {

    int[] digit;
    boolean[] visited;
    int min;

    public int solution(int n){
        int temp = n;
        min = Integer.MAX_VALUE;
        List<Integer> nums = new ArrayList<>();
        while (temp > 0) {
            nums.add(temp % 10);
            temp /= 10;
        }
        digit = new int[nums.size()];
        visited = new boolean[nums.size()];
        dfs(nums, 0, n);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public void dfs(List<Integer> nums, int depth, int n) {
        if (depth == nums.size()) {
            int combinedNum = digit[0];
            for (int i = 1; i < digit.length; i++) {
                combinedNum *= 10;
                combinedNum += digit[i];
            }
            if (combinedNum > n && combinedNum < min) {
                min = combinedNum;
            }
            return;
        }
        for (int i = 0; i < nums.size(); i++) {
            if (!visited[i]) {
                digit[depth] = nums.get(i);
                visited[i] = true;
                dfs(nums, depth + 1, n);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(123));
        System.out.println(T.solution(321));
        System.out.println(T.solution(20573));
        System.out.println(T.solution(27711));
        System.out.println(T.solution(54312));
    }
}