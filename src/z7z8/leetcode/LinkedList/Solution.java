package z7z8.leetcode.LinkedList;

/**
 * author cash
 * create 2019-07-02-0:02
 **/
public class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode sub = head.next;
        //fake节点,head前一节点,方便断链
        ListNode fake = new ListNode(-1);
        fake.next = head;
        //最后一个节点next置为null,内部循环结束条件
        fake.next.next = null;
        while (null != sub) {
            ListNode pre = fake;
            while (pre != null) {
                ListNode next = pre.next;
                if (null != next) {
                    if (next.val > sub.val) {
                        pre.next = sub;
                        //temp节点保存
                        ListNode temp = sub.next;
                        sub.next = next;
                        sub = temp;
                        break;
                    } else {
                        pre = pre.next;
                    }
                } else {
                    pre.next = sub;
                    //temp节点保存
                    ListNode temp = sub.next;
                    sub.next = next;
                    sub = temp;
                    break;
                }
            }
        }
        return fake.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        Solution solution = new Solution();
        ListNode r = solution.insertionSortList(node1);
        while (r != null) {
            System.out.println(r.val);
            r = r.next;
        }
    }
}
