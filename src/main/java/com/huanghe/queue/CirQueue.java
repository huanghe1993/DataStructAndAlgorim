package com.huanghe.queue;

/**
 * @Author: River
 * @Date:Created in  9:55 2018/6/8
 * @Description: 使用数组的形式实现循环队列
 */
public class CirQueue<E> {

    private Object[] queue;
    private int font;//头指针
    private int rear;//尾指针
    private int maxSize;

    public CirQueue(int maxSize) {
        this.maxSize = maxSize;
        queue = new Object[maxSize];
        font=0;
        rear=0;
    }

    /**
     * 判断循环队列是否为空
     */
    public boolean isEmpty(){
        if (font == rear) {
            return true;
        }
        return false;
    }

    /**
     * 判断循环队列是否满了
     */
    public boolean isFull() {
        if ((rear + 1) % maxSize == font) {
            return true;
        }
        return false;
    }

    /**
     * 循环队列的入队操作
     */
    public void put(E data) {
        if (!isFull()) {
            this.queue[rear] = data;//将元素赋值给队尾
            rear = (rear + 1) % maxSize; //rear指针向后移动一位，若是到了最后则转移到数组的头部
        } else {
            System.out.println("ERROR:Queue is Full");
            return;
        }
    }

    /**
     * 循环队列的出队列的操作
     */
    public E  pop() {
        if (!isEmpty()) {
            E temp = (E) queue[font];
            font = (font + 1) % maxSize;
            return temp;
        } else {
            return null;
        }
    }

}
