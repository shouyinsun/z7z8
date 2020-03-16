package z7z8.leetcode.interview;

/**
 * author cash
 * 二叉搜索树,找到第k小的值
 * create 2019-08-04-21:20
 **/
public class BST {

    public static void main(String[] args) {
        TreeNode t6=new TreeNode(6);
        TreeNode t3=new TreeNode(3);
        TreeNode t8=new TreeNode(8);
        t6.left=t3;
        t6.right=t8;
        TreeNode t2=new TreeNode(2);
        TreeNode t5=new TreeNode(5);
        t3.left=t2;
        t3.right=t5;


        BST bst=new BST();
        Result result=bst.doLookUp(t6,5);
        System.out.println(result.find);
        System.out.println(result.value);


    }

    public Result doLookUp(TreeNode t,int k){
        //检索节点的时候同时统计子树的节点个数
        Result result=new Result();
        if(null ==t ){
           return result;
        }
        if(t.left !=null){//左子树
            result=doLookUp(t.left,k);
            if(result.find) return result;
        }
        if(k-result.count==1) {//是否是该节点本身
            result.find=true;
            result.value=t.val;
            result.count+=1;
            return result;
        }
        int cnt=result.count+1;
        if(t.right !=null){//右子节点
            result=doLookUp(t.right,k-cnt);//查找k-cnt个小值
            if(result.find) return result;
        }
        result.count+=cnt;
        return  result;
    }
}

class Result{
    public boolean find;//是否找到
    public int count;//子树的个数
    public int value;//找的的value值
    public Result(){
        this.find=false;
        this.count=0;
    }
}