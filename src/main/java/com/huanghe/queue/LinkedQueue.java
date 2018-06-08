package com.huanghe.queue;


/**
 * @Author: River
 * @Date:Created in  10:19 2018/6/8
 * @Description: 队列的链式存储结构：使用链表的方式
 */
public class LinkedQueue<E> {

    private Node<E> font;//头指针
    private Node<E> rear;//尾指针
    private int size;//队列的大小
    private boolean empt;

    //初始化
    public LinkedQueue() {
        font = rear = new Node<E>(null);
    }

    /**
     * 向队尾添加元素
     *
     * @param data
     */
    public void put(E data) {
        Node<E> node = new Node<>(data);
        node.next = null;//队尾的指针的next为null
        rear.next = node;//队尾的指针的next指向的是新的结点
        rear = node;//把当前的队尾的指针设置为新的元素的值
        size++;
    }

    /**
     * 删除队头元素并且返回
     */
    public E pop() {
        if (!isEmpt()) {//如果队列不为空
            Node<E> temp = font.next;//使用临时指针保留font的下一个指针
            E data = temp.data; //得到数据域
            font.next = temp.next;//将原队中的头结点的后继赋值给头结点的后继的后继

            size--;
            if (rear == temp) {//若队头是队尾，则删除后将rear指向头结点
                rear = font;
            }
            return data;
        } else {
            System.out.println("ERROR:队列的长度为空");
            return null;
        }
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpt() {

        return size == 0;
    }

    /**
     * 返回对头的元素
     * @return
     */
    public E peek() {
        if (isEmpt()) {
            return font.next.data;
        }
        return null;
    }

    @Override
    public String toString() {
        Node<E> current = font.next;
        StringBuilder sb = new StringBuilder();
        while (current != null) {
            sb.append(current.data).append(" ");
            current = current.next;
        }
        return sb.toString();

    }
}
