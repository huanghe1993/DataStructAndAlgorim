package com.huanghe.BinarySearchTree;

/**
 * @Author: River
 * @Date:Created in  16:40 2018/6/14
 * @Description:
 */
public class BinarySearchTree {

    private TreeNode root;

    /**
     * @description 根据已知序列构建二叉搜索树
     * @author rico
     * @created 2017年6月3日 下午6:15:54
     * @param input
     */
    public BinarySearchTree(int[] input) {
        createBinarySearchTree(input);
    }

    /**
     * @description 根据已知序列构建二叉搜索树
     * @author rico
     * @created 2017年6月3日 下午6:15:06
     * @param input
     */
    public void createBinarySearchTree(int[] input) {
        if (input != null) {
            for (int i = 0; i < input.length; i++) {
                root = insert(input[i], root);
            }
        }
    }

    /**
     * @description 二叉搜索树的搜索算法，递归算法
     * @author rico
     * @created 2017年6月3日 下午3:27:43
     * @param target
     *            目标值
     * @param root
     *            二叉搜索树的根结点
     * @return
     */
    public TreeNode search(int target, TreeNode root) {
        TreeNode result = null;
        if (root != null) { // 递归终止条件
            if (target == root.data) { // 递归终止条件
                result = root;
                return result;
            } else if (target < root.data) { // 目标值小于根结点值，从左子树查找
                result = search(target, root.left);
            } else { // 目标值大于根结点值，从右子树查找
                result = search(target, root.right);
            }
        }
        return result;
    }

    /**
     * @description 二叉搜索树的插入操作
     * @author rico
     * @created 2017年6月3日 下午5:55:05
     * @param target
     * @param node
     * @return
     */
    public TreeNode insert(int target, TreeNode node) {
        if (search(target, node) == null) {
            if (node == null) {
                return new TreeNode(target);
            } else {
                if (target < node.data) {
                    node.left = insert(target, node.left);
                } else {
                    node.right = insert(target, node.right);
                }
            }
        }
        return node;
    }

    /**
     * @description 删除搜索二叉树的制定结点
     * @author rico
     * @created 2017年6月3日 下午8:43:29
     * @param target
     * @param node
     * @return
     */
    public TreeNode remove(int target, TreeNode node) {
        TreeNode tmp = null;
        if (node != null) {
            if (target < node.data) { // 从左子树删除
                node.left = remove(target, node.left);
            } else if (target > node.data) { // 从右子树删除
                node.right = remove(target, node.right);
            } else if (node.left != null && node.right != null) { // 找到待删除结点，且其左右子树不为空
                // 找到以待删除结点右子树的中序遍历第一个结点(最小结点)
                tmp = node.right;
                while (tmp.left != null) {
                    tmp = tmp.left;
                }

                // 用最小结点补位待删除结点
                node.data = tmp.data;

                // 删除待删除结点右子树上补位结点
                node.right = remove(node.data, node.right);
            } else {
                if (node.left == null) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            }
        }
        return node;
    }

    /**
     * 删除节点的操作
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (key < root.data) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        if (key > root.data) {
            root.right = deleteNode(root.right, key);
            return root;
        }
        //开始执行删除操作
        //(1)删除根节点
        if(root.left == null && root.right == null){
            root = null;
            return root;
        }
        //(2)只有一个child只有右子树
        if (root.left == null && root.right != null) {
            root = root.right;
            return root;
        }
        //(2)只有一个child,只有左子树
        if(root.right == null && root.left != null){
            root = root.left;
            return root;
        }
        //(3)有两个child
        if(root.left != null && root.right != null){
            //挑选左子树中最大的或者右子树中最小的，替换当前节点，再将替换的节点置空
            int data = findMaxInLeftTree(root.left);
            root.data = data;
            root.left = deleteNode(root.left, data);
            return root;
        }
        return root;

    }
    //找到左子树中最大的值
    private int findMaxInLeftTree(TreeNode left) {
        if(left == null){
            return 0;
        }
        if(left.right == null){
            return left.data;
        }
        if(left.right == null && left.left == null){
            return left.data;
        }
        return findMaxInLeftTree(left.right);
    }


    /**
     * @description 中序遍历二叉搜索树，递归算法，升序排序
     * @author rico
     * @created 2017年6月3日 下午3:52:54
     * @param node
     */
    public void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    /**
     * @description 打印二叉搜索树
     * @author rico
     * @created 2017年6月3日 下午6:08:42
     * @param node
     */
    public void printTree(TreeNode node) {
        if (node != null) {
            System.out.print(node.data);
            if (node.left != null || node.right != null) {
                System.out.print("(");
                printTree(node.left);
                System.out.print(",");
                printTree(node.right);
                System.out.print(")");
            }
        }
    }

    /**
     * @description 访问二叉搜索树的根结点
     * @author rico
     * @created 2017年6月3日 下午3:54:49
     * @return
     */
    public TreeNode getRoot() {
        return root;
    }
}

