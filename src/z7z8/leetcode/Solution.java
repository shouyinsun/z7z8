package z7z8.leetcode;

/**
 * @author cash
 * @description
 * @date 2022/4/6 10:29 PM
 */
public class Solution {

    public int lengthOfLIS(int[] nums) {
        int size = nums.length;
        //len[i],i满足的长度
        int[] len = new int[size];
        len[0] = 1;
        int r = 1;
        for (int i = 1; i < size; i++) {
            len[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    len[i] = Math.max(len[i], len[j] + 1);
                }
            }
            r = Math.max(r, len[i]);
        }
        return r;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {7,7,7,7,7,7,7};
        int r = solution.lengthOfLIS(nums);
        System.out.println(r);

        StringBuilder sb =new StringBuilder("abc");
        System.out.println(sb.reverse());
    }
}
