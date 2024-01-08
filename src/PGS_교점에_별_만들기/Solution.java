package PGS_교점에_별_만들기;

import java.util.*;
import java.util.stream.*;

class XY {
    private long x;
    private long y;

    public XY(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return this.x;
    }

    public long getY() {
        return this.y;
    }
}

class Solution {
    public String[] solution(int[][] line) {
        List<XY> list = new ArrayList<>();
        long maxX = Long.MIN_VALUE; long minX = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE; long minY = Long.MAX_VALUE;
        for (int i = 0; i < line.length; i++) {
            for (int j = i + 1; j < line.length; j++) {
                int[] first = line[i];
                int[] second = line[j];
                long ad = (long) first[0] * second[1];
                long bc = (long) first[1] * second[0];

                if (ad - bc == 0) continue;

                long mother = ad - bc;
                long childX = (long) first[1] * second[2] - (long) first[2] * second[1];
                long childY = (long) first[2] * second[0] - (long) first[0] * second[2];

                if (childX % mother != 0 || childY % mother != 0) continue;

                long x = childX / mother;
                long y = childY / mother;
                list.add(new XY(x, y));

                if (x > maxX) maxX = x;
                if (x < minX) minX = x;
                if (y > maxY) maxY = y;
                if (y < minY) minY = y;
            }
        }
        int width = (int) (maxX - minX + 1);
        int height = (int) (maxY - minY + 1);
        String[][] result = new String[height][width];
        for (String[] s : result) {
            Arrays.fill(s, ".");
        }
        for (XY xy : list) {
            int y = (int) (maxY - xy.getY());
            int x = (int) (xy.getX() - minX);
            result[y][x] = "*";
        }
        return Arrays.stream(result)
            .map(s -> Arrays.stream(s).collect(Collectors.joining()))
            .toArray(String[]::new);
    }
}