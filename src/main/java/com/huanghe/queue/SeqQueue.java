package com.huanghe.queue;

import java.util.Arrays;

/**
 * @Author: River
 * @Date:Created in  9:37 2018/6/8
 * @Description: 基于数组的队列实现，顺序存储结构
 */
public class SeqQueue<E> {

    /**
     * 队列的存储结构
     */
    private Object[] queue;
    private int size;
    private int maxSize;

    public SeqQueue(int maxSize) {
        this.maxSize = maxSize;
        queue = new Object[maxSize];
    }

    /**
     * 判断队列是否已经满了
     * @return
     */
    public boolean isFull(){
        return size == maxSize;
    }

    /**
     * 判断是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }


    /**
     * 向队尾添加元素
     */
    public void put(E data) {
        if (!isFull()) {
            queue[size] = data;
            size++;
        }
    }

    /**
     * 删除队头并返回对头元素的值
     */
    public E pop(){
        if (!isEmpty()) {
            E temp = (E) queue[0];
            for (int i = 0; i < size - 1; i++) {
                queue[i] = queue[i + 1];
            }
            queue[size - 1] = null;
            size--;
            return temp;
        }
        return null;
    }

    /**
     * 返回队头元素
     */
    public E peek(){
        if (!isEmpty()) {
            return (E) queue[0];
        }
        return null;
    }

    /**
     * 返回队列的大小
     * @return
     */
    public int size(){
        return size;
    }

    @Override
    public String toString() {
        return Arrays.toString(queue);
    }
}
