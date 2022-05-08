package z7z8.leetcode.binaryTree;

import java.util.List;
import java.util.Stack;

import com.google.common.collect.Lists;

/**
 * @author cash
 * @description
 * @date 2022/4/15 9:18 PM
 */
public class NoRecursiveOrder {

    /**
     * 前序
     * @param root
     * @return
     */
    public List<Integer> preOrder(Node root) {
        List<Integer> result = Lists.newArrayList();
        Stack<Node> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            result.add(node.val);
            if (null != node.right) {
                stack.push(node.right);
            }
            if (null != node.left) {
                stack.push(node.left);
            }
        }
        return result;
    }

    /**
     * 中序
     * @param root
     * @return
     */
    public List<Integer> inOrder(Node root) {
        List<Integer> result = Lists.newArrayList();
        Stack<Node> stack = new Stack();
        while (null != root || !stack.isEmpty()) {
            while (null != root) {
                stack.push(root);
                //一直向左
                root = root.left;
            }
            root = stack.pop();
            result.add(root.val);
            //右节点
            root = root.right;
        }
        return result;
    }

    /**
     * 后序
     * @param root
     * @return
     */
    public List<Integer> postOrder(Node root){
        List<Integer> result = Lists.newArrayList();
        Stack<Node> stack = new Stack();
        Node pre =null;
        while(null !=root || !stack.isEmpty()){
            while(null !=root){
                stack.push(root);
                //一直向左
                root = root.left;
            }
            //peek,栈顶
            Node peek = stack.peek();
            //三种情况
            if(null == peek.right){
                //没有right,加入
                pre =stack.pop();
                result.add(pre.val);
            }else if(pre == peek.right){
                //右节点是pre,可加入
                pre =stack.pop();
                result.add(pre.val);
            }else{
                //有右节点不是pre,遍历右节点
                root = peek.right;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Node root =new Node(1);
        Node left1 =new Node(2);
        Node right1 =new Node(3);
        root.left =left1;
        root.right =right1;
        Node left11 =new Node(4);
        Node right12 =new Node(5);
        left1.left=left11;
        left1.right =right12;
        Node left21 =new Node(6);
        Node right22 =new Node(7);
        right1.left =left21;
        right1.right =right22;

        NoRecursiveOrder noRecursiveOrder =new NoRecursiveOrder();
        System.out.println(noRecursiveOrder.preOrder(root));
        System.out.println(noRecursiveOrder.inOrder(root));
        System.out.println(noRecursiveOrder.postOrder(root));

    }
}
