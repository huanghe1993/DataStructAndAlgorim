package com.huanghe.stack;

/**
 * @Author: River
 * @Date:Created in  21:49 2018/6/7
 * @Description: 不用LinkedList自己来实现栈的操作
 */
public class LinkedStack<E> {

    private Node<E> top;//栈顶元素
    private int size;//链式栈的大小

    // 构造函数
    public LinkedStack(){
    }

    /**
     * 判断栈是否为空
     * @return
     */
    public boolean isEmpty(){
        return size==0;
    }

    /**
     * 压栈
     */
    public void push(E data) {
        Node<E> node = new Node<>(data);
        //新加入的元素指向栈顶
        node.next=top;
        top=node; //新结点直接赋值给栈顶指针
        size++;
    }

    /**
     * 出栈
     * @return
     */
    public Node<E> pop(){
        if (isEmpty()) {
            return null;
        }

        Node<E> node=top; //得到栈顶元素
        top = top.next; //栈顶指针指向下一个位置
        node.next = null; //释放结点
        size--;
        return node;
    }

    /**
     * 弹出栈顶元素但是不删除
     * @return
     */
    public Node<E> peek(){
        if (isEmpty()) {
            return null;
        }
        return top;
    }

    /**
     * @description 返回栈的大小
     * @author rico
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * @description 打印栈
     * @author rico
     */
    public void print() {
        Node<E> index = top;
        while (index != null) {
            System.out.print(index.data + " ");
            index = index.next;
        }
        System.out.println();
    }
}
