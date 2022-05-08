package z7z8.z7z8Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
public class Solution {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> resultList = new ArrayList();
        int size = nums.length;
        boolean[] checked = new boolean[size];
        List<Integer> result = new ArrayList();
        resultList.add(result);
        // 2^n,不包含重复的子集,顺序不同也是相同子集
        // index依次增大
        lookup(resultList, result, 0, nums, checked);
        return resultList;
    }

    private void lookup(List<List<Integer>> resultList, List<Integer> result, int startIndex,
        int[] nums, boolean[] checked) {
        for (int i = startIndex; i < nums.length; i++) {
            if (!checked[i]) {
                result.add(nums[i]);
                resultList.add(new ArrayList(result));
                checked[i] = true;
                lookup(resultList, result, i + 1, nums, checked);
                result.remove(result.size() - 1);
                checked[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.subsets(new int[] {1, 2, 3}));
        Queue<Integer> queue  =new LinkedList();
        queue.add(1);
        if(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                queue.poll();
            }
        }
        TreeMap<Integer, Integer> map = new TreeMap();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            entry.getKey();
            entry.setValue(0);
        }

    }

}
