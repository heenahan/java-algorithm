import static Direction;

enum Direction {
    RIGHT, LEFT;
}

class Solution {
    public int[] solution(int n) {
        int mul = n % 2 == 0 ? n / 2 : n / 2 + 1;
        int sum = n % 2 == 0 ? n / 2 : 0;
        int size = n * mul + sum;

        int[] answer = new int[size];
        answer[0] = 1; int idx = 0; Direction d = Direction.RI
        for (int i = )
        return answer;
    }
}