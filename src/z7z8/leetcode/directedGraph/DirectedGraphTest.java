package z7z8.leetcode.directedGraph;

import java.util.*;

/**
 * author cash 有向无环图
 * create 2020-01-19-18:34
 * <p>
 * 输入：Task的依赖关系
 * 1.	Ti->Tj：表示Ti依赖Tj，Ti，Tj表示具体的Task，i,j=1,2,3,…
 * 2.	Tk:表示Tk不依赖其它Task，Tk表示具体的Task，k=1,2,3,…
 * 3.	多个关系之间用”,”分开,中间，前后无空格的等特殊字符
 * 输出：Task计算顺序
 * 1.	Ti,Tj,表示计算顺序为Ti先于Tj计算，如：图1结果为：T1,T2,T3
 * 注：其中T2和T3无先后顺序，为保证结果唯一性，T2、T3按升序排序
 * <p>
 * T1->T2,T2->T3,T2->T4,T3->T4,T4->T5,T6 输出：T5,T6,T4,T3,T2,T1
 * T1->T2,T2->T3,T3->T1 输出：Error, circular dependencies
 * K1，K2 输出：Error,invalid input
 **/
public class
DirectedGraphTest {
    public static void main(String[] args) {
        DirectedGraphTest t = new DirectedGraphTest();
        String s = t.caculateDependency("T1->T2,T2->T3,T3->T1");
        System.out.println(s);

    }

    public String caculateDependency(String input) {
        String[] split = input.split(",");
        //regex
        String regex = "^T[1-9]\\d*(->T[1-9]\\d*)?$";
        for (String s : split) {
            if (!s.matches(regex)) {
                return "Error,invalid input";
            }
        }
        List<Integer> result = new ArrayList();
        //treeMap
        // Number -> node
        TreeMap<Integer, Node> nodeMap = new TreeMap();
        //独立正则
        String independentRegex = "^T[1-9]\\d*$";
        //independent list
        for (String s : split) {
            if (s.matches(independentRegex)) {
                Integer n = Integer.valueOf(s.substring(1));
                Node n1 = nodeMap.get(n);
                if (null == n1) {
                    n1 = new Node(n);
                    nodeMap.put(n, n1);
                }
            } else {
                String[] ta = s.split("->");
                Integer t1 = Integer.valueOf(ta[0].substring(1));
                Integer t2 = Integer.valueOf(ta[1].substring(1));
                Node n1 = nodeMap.get(t1);
                if (null == n1) {
                    n1 = new Node(t1);
                    nodeMap.put(t1, n1);
                }
                //依赖出度++
                n1.outDegree++;

                Node n2 = nodeMap.get(t2);
                if (null == n2) {
                    n2 = new Node(t2);
                    nodeMap.put(t2, n2);
                }
                n2.subDependency.add(n1);
            }
        }
        Queue<Node> queue = new LinkedList();
        Collection<Node> nodes = nodeMap.values();
        //nodeMap's element count
        int size = nodes.size();
        for (Node n : nodes) {
            if (n.outDegree == 0) {
                queue.offer(n);
            }
        }
        List<Node> list = new ArrayList();
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            result.add(n.num);
            if (n.subDependency.size() != 0) {
                for (Node n1 : n.subDependency) {
                    n1.outDegree--;
                    //出度为0
                    if (n1.outDegree == 0) {
                        list.add(n1);
                    }
                }
            }
            if (queue.size() == 0) {//同一层最后一个
                //下一层次 sort
                Collections.sort(list);
                for (Node n2 : list) {
                    queue.offer(n2);
                }
                list = new ArrayList();
            }
        }

        if (result.size() != size) {//循环依赖,有环
            return "Error, circular dependencies";
        }

        StringBuffer sb = new StringBuffer();
        for (Integer i : result) {
            sb.append("T").append(i.intValue()).append(",");
        }
        String r = sb.toString();

        return r.length() > 0 ? r.substring(0, r.length() - 1) : r;
    }


    //node 任务节点类
    class Node implements Comparable<Node> {
        private Integer num;//编号
        //出度
        private int outDegree = 0;
        //sub dependency
        private List<Node> subDependency;

        public Node(Integer num) {
            this.num = num;
            //空list
            this.subDependency = new ArrayList();
        }

        @Override
        public int compareTo(Node o) {
            if (num < o.num) return -1;
            return 1;
        }
    }
}
