package com.huanghe.stack;

/**
 * @Author: River
 * @Date:Created in  11:01 2018/6/7
 * @Description:
 */
public class Node<T> {
    Node<T> next;
    T data;

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
    @Override
    public String toString() {
        return data.toString();
    }

    public Node<T> getNext() {
        return next;
    }

}
