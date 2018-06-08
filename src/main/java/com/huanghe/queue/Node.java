package com.huanghe.queue;


/**
 * @Author: River
 * @Date:Created in  15:39 2018/6/5
 * @Description: 链表的基本元素
 */
public class Node<T> {

    T data;
    Node<T> next;

    /**
     * 构造函数
     * @param data 数据元素
     */
    public Node(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}

