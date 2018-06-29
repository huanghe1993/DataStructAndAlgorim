package com.huanghe.RBtree;

/**
 * @Author: River
 * @Date:Created in  21:12 2018/6/17
 * @Description:
 */
public class RBNode<T extends Comparable<T>> {

    boolean color;//颜色
    T key; //关键字(键值)
    RBNode<T> left;//左孩子
    RBNode<T> right;//右孩子
    RBNode<T> parent;//父结点

    public RBNode(T key, boolean color, RBNode<T> parent, RBNode<T> left, RBNode<T> right) {
        this.key = key;
        this.color = color;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }


}
