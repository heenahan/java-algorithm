package 타일_점프;

import java.util.*;

class Solution {

    int[] visited;

    public int solution(int[] nums){
        visited = new int[nums.length];
        Arrays.fill(visited, -1);
        return bfs(nums);
    }

    public int bfs(int[] nums) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = 0;

        while (!queue.isEmpty()) {
            int v = queue.poll();

            if (v == nums.length - 1) {
                return visited[v];
            }
            for (int i = 1; i <= nums[v]; i++) {
                int next = v + i;
                // 인덱스를 넘어갈 경우
                if (next >= nums.length) continue;
                // 아직 방문하지 않았을 때 - 최초 방문이 최소 점프 수
                if (visited[next] < 0) {
                    visited[next] = visited[v] + 1;
                    queue.add(next);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(new int[]{2, 2, 1, 2, 1, 1}));
        System.out.println(T.solution(new int[]{1, 0, 1, 1, 3, 1, 2, 1}));
        System.out.println(T.solution(new int[]{2, 3, 1, 0, 1, 1, 2, 3, 1, 5, 1, 3, 1}));
        System.out.println(T.solution(new int[]{1, 2, 1, 2, 1, 2, 1, 1, 3, 1, 2, 1}));
        System.out.println(T.solution(new int[]{1, 3, 2, 1, 1, 2, 3, 1, 3, 1, 2, 3, 5, 1, 5, 1, 2, 1, 1}));
    }
}