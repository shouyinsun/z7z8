package z7z8.leetcode.TopN;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * @author cash
 * @description 频率topN
 * @date 2022/3/14 8:55 PM
 */
public class FrequencyTopN {

    public List<Integer> topKFrequent(int[] nums, int k) {

        List<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> valueCntMap = new HashMap();
        for (int n : nums) {
            valueCntMap.put(n, valueCntMap.getOrDefault(n, 0) + 1);
        }
        //自定义的comparator
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(new Comparator<Entry<Integer, Integer>>() {
            @Override
            public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
                if (o1.getValue().equals(o2.getValue())) {
                    return o2.getKey() - o1.getKey();
                } else {
                    return o2.getValue() - o1.getValue();
                }
            }
        });

        for (Map.Entry<Integer, Integer> entry : valueCntMap.entrySet()) {
            priorityQueue.add(entry);
        }
        for (int i = 0; i < k; i++) {
            result.add(priorityQueue.poll().getKey());
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3, 1, 2, 1, 4, 5, 6, 3, 2, 1, 2};
        FrequencyTopN frequencyTopN = new FrequencyTopN();
        System.out.println(frequencyTopN.topKFrequent(nums, 3));
    }
}
