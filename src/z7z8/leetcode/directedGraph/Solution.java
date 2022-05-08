package z7z8.leetcode.directedGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author cash
 * @description 课程表
 * 其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * @date 2022/4/3 4:42 PM
 */
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Node> nodeMap = new HashMap();
        int count = 0;
        for (int i = 0; i < numCourses; i++) {
            nodeMap.put(i, new Node(i));
        }
        for (int[] a : prerequisites) {
            for (int i = 1; i < a.length; i++) {
                int pre = a[i - 1];
                int cur = a[i];
                Node preNode = nodeMap.get(pre);
                preNode.outDegree += 1;
                Node curNode = nodeMap.get(cur);
                curNode.dependOnMe.add(preNode);
            }
        }
        Queue<Node> queue = new LinkedList();
        for (Map.Entry<Integer, Node> entry : nodeMap.entrySet()) {
            //出度是0,没有依赖
            Node node = entry.getValue();
            if (0 == node.outDegree) {
                queue.add(node);
            }
        }
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            count++;
            List<Node> dependOnMeList = node.dependOnMe;
            for (Node n : dependOnMeList) {
                if (0 == --n.outDegree) {
                    queue.add(n);
                }
            }
        }

        return count == numCourses;
    }

    class Node {
        Integer name;
        //出度,我依赖的节点个数
        int outDegree = 0;
        //依赖于我的node
        List<Node> dependOnMe = new ArrayList();

        public Node(Integer name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        //[[1,4],[2,4],[3,1],[3,2]]
        int[][] prerequisites = new int[][] {{1, 4}, {2, 4}, {3, 1}, {3, 2}};
        System.out.println(solution.canFinish(5, prerequisites));
    }

}
