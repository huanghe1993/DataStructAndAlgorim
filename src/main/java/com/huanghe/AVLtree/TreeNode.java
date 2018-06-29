package com.huanghe.AVLtree;

/**
 * @Author: River
 * @Date:Created in  16:48 2018/6/15
 * @Description:
 */
public class TreeNode<T extends Comparable> {

    public TreeNode<T> left;//左结点

    public TreeNode<T> right;//右结点

    public T data;

    public int height;//当前结点的高度(高度是指当前结点到叶子结点的最长路径)  而深度则是指从根结点到当前结点的最大路径

    public TreeNode(T data) {
        this(null,null,data);
    }

    public TreeNode(TreeNode<T> left, TreeNode<T> right, T data) {
        this(left,right,data,0);
    }

    public TreeNode(TreeNode<T> left, TreeNode<T> right, T data, int height) {
        this.left=left;
        this.right=right;
        this.data=data;
        this.height = height;
    }

}
