package z7z8.leetcode.TopN;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author cash
 * @description 值topN
 * @date 2022/3/14 8:54 PM
 */
public class ValueTopN {

    public ArrayList<Integer> GetLeastNumbers(int[] input, int k) {
        int length = input.length;
        ArrayList<Integer> result = new ArrayList();
        if (0 == length) {
            return result;
        }
        if (k <= 0) {
            return result;
        }
        if (k > length) {
            return result;
        }
        result = priorityQueue(input, k);
        return result;
    }

    /***
     优先队列,内部堆排序
     **/
    private ArrayList<Integer> priorityQueue(int[] input, int k) {
        ArrayList<Integer> result = new ArrayList();
        PriorityQueue<Integer> queue = new PriorityQueue(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return (Integer)o1 -(Integer)o2;
            }
        });
        for (int i : input) {
            queue.add(i);
        }
        for (int i = 0; i < k; i++) {
            result.add(queue.poll());
        }
        return result;
    }

    public static void main(String[] args) {
        ValueTopN valueTopN = new ValueTopN();
        int[] arr = new int[] {1, 23, 4, 54, 2, 4,0, 7, 3, 57, 1,3, 2, 73, 7445, 90};
        int cnt = 5;
        System.out.println(valueTopN.GetLeastNumbers(arr, cnt));
    }

}
