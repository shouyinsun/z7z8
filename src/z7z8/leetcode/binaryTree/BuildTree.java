package z7z8.leetcode.binaryTree;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * author cash 通过中序跟前序构建二叉树(没有重复值)
 * preOrder 前序
 * inOrder 中序
 * create 2019-06-17-22:19
 **/
public class BuildTree {
    public Map<Integer,Integer> inOrderIndexMap= Maps.newHashMap();
    public Node build(int[] preOrder, int pStart, int pEnd, int[] inOrder, int iStart, int iEnd) {
        if (pStart >= pEnd) return new Node(preOrder[pStart]);
        if (iStart >= iEnd) return new Node(inOrder[iStart]);
        //data 值
        Integer root=preOrder[pStart];
        //中序的map,data值与index的映射
        Integer index=inOrderIndexMap.get(root);
        Node n=new Node(root);
        //递归获得左、右子节点
        //中序的 index-iStart就是左子树的节点个数
        Node left=build(preOrder,pStart+1,pStart+(index-iStart),inOrder,iStart,index-1);
        //中序的 iEnd-index 是右子节点的个数
        Node right=build(preOrder,pEnd-(iEnd-index-1),pEnd,inOrder,index+1,iEnd);
        n.left=left;
        n.right=right;
        return n;
    }

    public void setInOrderIndexMap(Map<Integer, Integer> inOrderIndexMap) {
        this.inOrderIndexMap = inOrderIndexMap;
    }

    public static void main(String[] args) {
        int[] preOder=new int[]{3,9,20,15,7};
        int[] inOder=new int[]{9,3,15,20,7};

        BuildTree bt=new BuildTree();
        Map map=Maps.newHashMap();
        for(int i=0;i<inOder.length;i++){
            map.put(inOder[i],i);
        }

        //无重复值,data 跟 index 映射
        bt.setInOrderIndexMap(map);
        Node n=bt.build(preOder,0,preOder.length-1,inOder,0,inOder.length-1);
        System.out.println(n.data);

    }
}

