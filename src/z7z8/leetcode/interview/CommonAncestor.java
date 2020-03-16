package z7z8.leetcode.interview;

/**
 * author cash
 * create 2019-08-04-22:20
 * 两个节点的最近的公共祖先
 **/
public class CommonAncestor {

    public static void main(String[] args) {
        // [3,5,1,6,2,0,8,null,null,7,4]
        TreeNode n3=new TreeNode(3);
        TreeNode n5=new TreeNode(5);
        TreeNode n1=new TreeNode(1);
        n3.left=n5;
        n3.right=n1;
        TreeNode n6=new TreeNode(6);
        TreeNode n2=new TreeNode(2);
        n5.left=n6;
        n5.right=n2;
        TreeNode n0=new TreeNode(0);
        TreeNode n8=new TreeNode(8);
        n1.left=n0;
        n1.right=n8;
        TreeNode n7=new TreeNode(7);
        TreeNode n4=new TreeNode(4);
        n2.left=n7;
        n2.right=n4;

        CommonAncestor s=new CommonAncestor();
        System.out.println(s.lowestCommonAncestor(n3,n5,n8).val);

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //root.val==p.val || root.val==q.val 直接返回root,如果其他的节点都没有另一个值
        // 那另一个值一定在root的子树下面
        if(null == root || root.val==p.val || root.val==q.val){
            return root;
        }

        TreeNode l=lowestCommonAncestor(root.left,p,q);
        TreeNode r=lowestCommonAncestor(root.right,p,q);

        //左、右子树都不为空,root节点满足条件
        if(l!=null && r!=null) return root;

        if(l!=null) return l;
        if(r!=null) return r;
        return null;
    }
}
