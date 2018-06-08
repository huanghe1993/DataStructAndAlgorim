package com.huanghe.stack;

import java.util.Arrays;
import java.util.concurrent.Executors;

/**
 * @Author: River
 * @Date:Created in  11:08 2018/6/7
 * @Description:
 */
public class SeqStack<E> {

    private Object[] stack;//支撑元素
    private int top;//栈顶指针
    private int maxSize; //栈的最大容量

    public SeqStack(){
        this(10);
    }

    // 可以指定容量的构造函数
    public SeqStack(int maxSize){
        this.stack = new Object[maxSize];
        this.top = -1;
        this.maxSize = maxSize;
    }

    // 是否为空
    public boolean isEmpty(){
        return top == -1;
    }

    //添加元素
    public void push(E e) throws Exception{
        if (top == maxSize - 1) {
            throw new Exception("栈已满");
        }
        stack[++top]=e;
    }

    // 弹出并删除栈顶元素
    @SuppressWarnings("unchecked")
    public E pop() throws Exception{
        if (top == -1) {
            throw new Exception("栈为空...");
        }
        E element = (E)stack[top];
        stack[top --] = null;    // 删除该元素
        return element;
    }

    //打印栈
    @Override
    public String toString() {
        return Arrays.toString(stack);
    }

    public static void main(String[] args) throws Exception {
        SeqStack<String> stack = new SeqStack<String>();
        stack.push("Rico");
        stack.push("TJU");
        stack.push("Livia");
        stack.push("NEU");

        System.out.println(stack);

        stack.pop();
        System.out.println(stack);

    }
}
