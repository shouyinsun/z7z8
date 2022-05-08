package z7z8.z7z8Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author cash
 */
public class Solution2 {

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o2[1] - o1[1];
                } else {
                    return o1[0] - o2[0];
                }
            }
        });
        int size = people.length;
        int[][] res = new int[size][2];
        for (int[] p : people) {
            int spaceCnt = p[1] + 1;
            for (int i = 0; i < size; i++) {
                if (null == res[i]) {
                    spaceCnt--;
                    if (0 == spaceCnt) {
                        res[i] = p;
                    }
                }
            }
        }
        return res;
    }
}