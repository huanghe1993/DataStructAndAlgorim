package com.huanghe.tree;

/**
 * @Author: River
 * @Date:Created in  18:51 2018/6/10
 * @Description: 二叉树存储结构
 */
public class Node<T> {

    T data;//结点数据
    Node<T> left;//左孩子
    Node<T> right;//右孩子结点
    boolean isFirst;//用于非递归的后序遍历

    /**
     * 构造函数：构造出一个新的结点出来
     * @param data
     */
    public Node(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data == null ? null : data.toString();
    }



}
