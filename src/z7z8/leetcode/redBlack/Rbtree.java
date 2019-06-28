package z7z8.leetcode.redBlack;

/**
 * author cash
 * create 2019-06-18-23:07
 *
 * 
 1.每个节点是红色的,或者是黑色的
 2.根节点是黑色的
 3.每个叶节点(nil)是黑色的
 4.如果一个节点是红色的,则它的两个子节点都是黑色的
 5.每个节点,从该节点到其后代叶节点的每条路径上,均包含相同数目的黑色节点
 **/
/**
 * 红黑树节点类
 */
class Node {
    // 红黑节点颜色
    public final static int RED = 0;
    public final static int BLACK = 1;

    // ------------------------------ Instance Variables

    public Node left;    // 左节点
    public Node right;    // 右节点
    public Node parent; // 父节点
    public int color;    // 节点颜色
    public int data;    // 数据域,int型数据
}

/**
 * 红黑树类
 *
 */
public class Rbtree {

    // ------------------------------ Instance Variables

    // 红黑树空节点
    public Node nil;

    // 红黑树根节点
    public Node root;

    // ------------------------------ Constructors

    /**
     * Init nil node and root node.
     */
    public Rbtree() {
        nil = new Node();
        nil.left = nil.right = nil.parent = nil;
        nil.color = Node.BLACK;
        root = nil;
    }

    // ------------------------------ Public methods

    /**
     * 往红黑树中插入一个元素
     * @param data
     */
    public void insert(int data) {
        Node y = nil;
        Node x = root;

        if (root == nil) {
            root = newNode(data);
            root.color = Node.BLACK;
        } else {
            while (x != nil) {
                if (data < x.data) {
                    y = x;
                    x = x.left;
                } else if (data > x.data) {
                    y = x;
                    x = x.right;
                } else {
                    return;
                }
            }

            Node z = newNode(data);
            z.parent = y;
            if (data < y.data) {
                y.left = z;
            } else {
                y.right = z;
            }
            if (y.color == Node.RED) {
                insertFixUp(z);
            }
        }
    }

    /**
     * 从红黑树中移除一个元素
     * @param data 待移除的元素
     */
    public void remove(int data) {
        Node z = contains(data);
        if (z == null) {
            return;
        }

        Node x;
        Node y = z;
        int yOldColor = y.color;
        if (z.left == nil) {
            x = z.right;
            transplant(z, x);
        } else if (z.right == nil) {
            x = z.left;
            transplant(z,  x);
        } else {
            y = z.right;
            while (y.left != nil) {
                y = y.left;
            }
            yOldColor = y.color;
            x = y.right;

            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if (yOldColor == Node.BLACK) {
            removeFixUp(x);
        }
    }

    /**
     * 红黑树中是否包含data
     * @param data
     * @return
     */
    public Node contains(int data) {
        Node node = root;

        while (node != null) {
            if (data < node.data) {
                node = node.left;
            } else if (data > node.data) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();

        if (root != nil) {
            toStringInternal(root, buf);
        }
        return buf.toString();
    }

    // ------------------------------ Private methods

    /**
     * New a initialize node, default node's color is RED
     */
    private Node newNode(int data) {
        Node node = new Node();

        node.data = data;
        node.color = Node.RED;
        node.left = node.right = node.parent = nil;
        return node;
    }

    private void toStringInternal(Node node, StringBuilder buf) {
        if (node != nil) {
            toStringInternal(node.left, buf);
            buf.append(node.data + (node.color == Node.RED ? "-red " : "-black "));
            toStringInternal(node.right, buf);
        }
    }

    /**
     * 两个节点的替换,newNode替换oldNode
     * @param oldNode
     * @param newNode
     */
    private void transplant(Node oldNode, Node newNode) {
        if (oldNode.parent == nil) {
            root = newNode;
        } else if (oldNode == oldNode.parent.left) {
            oldNode.parent.left = newNode;
        } else {
            oldNode.parent.right = newNode;
        }

        newNode.parent = oldNode.parent;
    }

    /**
     * 左旋操作
     * 左旋就是当前节点成为左节点,原右节点成为其父节点
     */
    private void leftRotate(Node x) {
        Node y = x.right;//右节点

        x.right = y.left;//y的左节点成为x的右节点
        if (y.left != nil) {//y的左节点的父节点指向x
            y.left.parent = x;
        }
        y.parent = x.parent;//y的父节点
        if (x.parent == nil) {//x原来是root节点
            root = y;
        } else if (x == x.parent.left) {//x原来是父节点的左节点
            x.parent.left = y;
        } else {//x原来是父节点的右节点
            x.parent.right = y;
        }

        x.parent = y;//x的父节点置为y
        y.left = x;//y的左节点置为x
    }

    /**
     * 右旋操作
     * 右旋就是当前节点成为右子节点,原左节点成为其父节点
     */
    private void rightRotate(Node x) {
        Node y = x.left;//左节点

        x.left = y.right;//y的右节点成为x的左节点
        if (y.right != nil) {
            y.right.parent = x;//parent指向x
        }
        y.parent = x.parent;
        if (x.parent == nil) {//x原来是root节点
            root = y;
        } else if (x == x.parent.left) {//x原来是父节点的左节点
            x.parent.left = y;
        } else {//x原来是父节点的右节点
            x.parent.right = y;
        }

        x.parent = y.parent;//x的父节点置为y
        y.right = x;//y的左节点置为x
    }

    /**
     * 插入节点后不满足红黑树条件时来修复
     * @param z 待插入的节点
     */
    private void insertFixUp(Node z) {
        // y为z节点的叔叔节点
        Node y ;

        while (z.parent.color == Node.RED) {
            if (z.parent == z.parent.parent.left) {
                y = z.parent.parent.right;
                if (y.color == Node.RED) { // Case1: x uncle node y is red
                    z.parent.color = Node.BLACK;
                    y.color = Node.BLACK;
                    z.parent.parent.color = Node.RED;
                } else {
                    if (z == z.parent.right) { // Case2: x uncle node y is black, but x is right node
                        z = z.parent;
                        leftRotate(z);
                    }

                    z.parent.color = Node.BLACK; // Case3: x uncle node y is black, but x is left node
                    z.parent.parent.color = Node.RED;
                    rightRotate(z.parent.parent);
                }
            } else {
                y = z.parent.parent.left;
                if (y.color == Node.RED) {
                    z.parent.color = Node.BLACK;
                    y.color = Node.BLACK;
                    z.parent.parent.color = Node.RED;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }

                    z.parent.color = Node.BLACK;
                    z.parent.parent.color = Node.RED;
                    leftRotate(z.parent.parent);
                }
            }
        }

        root.color = Node.BLACK;
    }

    /**
     * 移除一个元素后红黑树不符合要求时的修复方法
     * @param x
     */
    private void removeFixUp(Node x) {
        Node y;

        while (x != root && x.color == Node.BLACK) {
            if (x == x.parent.left) {
                y = x.parent.right;
                if (y.color == Node.RED) { //情况1：x的兄弟节点w是红色
                    y.color = Node.BLACK;
                    x.parent.color = Node.RED;
                    leftRotate(x.parent);
                    y = x.parent.right;
                }

                if (y.left.color == Node.BLACK && y.right.color == Node.BLACK) { //情况2:x的兄弟节点w是黑色,而且w的两个子节点都是黑色的
                    y.color = Node.RED;
                    x = x.parent;
                } else {
                    if (y.right.color == Node.BLACK) { //情况3：x的兄弟节点w是黑色的,w的左孩子是红色的,w的右孩子是黑色的
                        y.left.color = Node.BLACK;
                        y.color = Node.RED;
                        rightRotate(y);
                        y = x.parent.right;
                    }

                    y.color = x.parent.color; //情况4：x的兄弟节点w是黑色的,且w的右孩子是红色的
                    x.parent.color = Node.BLACK;
                    y.right.color = Node.BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                y = x.parent.left;
                if (y.color == Node.RED) {
                    y.color = Node.BLACK;
                    x.parent.color = Node.RED;
                    rightRotate(x.parent);
                    y = x.parent.left;
                }

                if (y.left.color == Node.BLACK && y.right.color == Node.BLACK) {
                    y.color = Node.RED;
                    x = x.parent;
                } else {
                    if (y.left.color == Node.BLACK) {
                        y.right.color = Node.BLACK;
                        y.color = Node.RED;
                        leftRotate(y);
                        y = x.parent.left;
                    }

                    y.color = x.parent.color;
                    x.parent.color = Node.BLACK;
                    y.left.color = Node.BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = Node.BLACK;
    }
}