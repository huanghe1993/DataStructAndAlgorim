package com.huanghe.stack;


import java.util.LinkedList;

/**
 *
 *
 * 借助于LinkedList实现栈
 * @Author: River
 * @Date:Created in  21:42 2018/6/7
 * @Description: 利用链表实现栈的存储结构
 */
public class LinkedListStack<E> {

    //
    private LinkedList<E> stack;

    public LinkedListStack() {
        stack = new LinkedList<E>();
    }

    //判断是否为空
    public boolean isEmpty(){
        return stack.isEmpty();
    }

    // 压栈
    public void push(E data){
        stack.addFirst(data);
    }

    //弹栈
    public E pop() throws Exception {
        if (stack.isEmpty()) {
            throw new Exception("栈已满...");
        }

        return stack.pop();
    }

    @Override
    public String toString() {
        return stack.toString();
    }

    public static void main(String[] args) throws Exception {
        LinkedListStack<String> stack = new LinkedListStack<String>();
        stack.push("Rico");
        stack.push("TJU");
        stack.push("Livia");
        stack.push("NEU");

        System.out.println(stack);

        stack.pop();
        System.out.println(stack);
    }
}
