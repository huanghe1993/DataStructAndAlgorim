package com.huanghe.BinarySearchTree;

/**
 * @Author: River
 * @Date:Created in  16:40 2018/6/14
 * @Description: 二叉查找树
 */
public class TreeNode {

    public int data;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int data){
        this.data = data;
    }

    @Override
    public String toString() {
        return "TreeNode [data="+data+"]";
    }
}
