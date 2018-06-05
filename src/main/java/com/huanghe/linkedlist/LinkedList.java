package com.huanghe.linkedlist;

/**
 * @Author: River
 * @Date:Created in  15:46 2018/6/5
 * @Description :实现单链表的一系列的操作
 */
public class LinkedList<E> {

    private Node<E> head;//链表表头
    private  int size;//链表的长度

    public LinkedList() {
        //头指针存的数据为空
        head = new Node<E>(null);
    }

    /**
     * 获取链表的头结点
     * @return
     */
    public Node<E> getHead(){
        return head;
    }

    /**
     * 判断链表是否为空
     * @return
     */
    public boolean isEmpty(){
        return head.next == null;
    }


    /**
     * 获取链表的长度
     * @return 链表的长度
     */
    public int length(){
        int len = 0;
        Node<E> node = head.next;
        while (node != null) {
            len++;
            node = node.next;
        }
        return len;
    }

    /**
     * 清空链表
     */
    public void clear(){
        head = null;
    }

    /**
     * 打印输出单链表，从头到尾
     */
    public void print(){
        Node<E> current=head.next;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
    }

    /**
     * show 从尾到头输出单链表，使用递归的方式
     */
    public void reversePrint(Node<E> head) {
        if (head.next != null) {
            reversePrint(head.next);
            System.out.println(head.next.data+" ");
        }
    }

    /**
     * show 在指定的位置插入结点
     * @param data:待加入的数据
     * @param index：待加入的位置 ,index的起点位置是从0开始算起的
     * @return true插入成功
     *          false插入失败
     */
    public boolean add(E data, int index) {
        if (index > length()+1 || index < 0) {
            throw new IllegalArgumentException("index is" + index + " IllegalArgumentException");
        }

        Node<E> node = new Node<>(data);//新结点

        if (head.next == null) {
            head = node;
            size++;
        } else {
            if (index == 1) {//在链表的头部插入结点
                node.next = head.next;//新结点的next指向head
                head.next = node; //头指针指向新结点
                size++;
            } else {//在链表的中间插入结点
                Node<E> currnt = head.next;
                //需要找到需要插入位置的前一个结点
                int count = 1;
                while (currnt != null && count < index - 1) {//找到的是需要插入位置的前一个结点
                    currnt = currnt.next;
                    count++;
                }
                node.next = currnt.next;//新结点的下一个结点就是需要插入位置的结点
                currnt.next = node;//要插入结点的前一个结点的下一个结点是新结点
                size++;
            }
        }
        return true;
    }
    /**
     *在头部添加第一个结点，已经有头结点
     */
    public void add(E data){
        Node<E> node=new Node<>(data);
        node.next=head.next;
        head.next=node;
        size++;
    }

    /**
     *
     * @param index 指定的位置
     * @return true:删除成功
     *          false:删除失败
     */
    public boolean delete(int index){
        if (index < 1 || index > length()) {
            return false;
        }
        if (index == 1) {//删除单项链表的第一个结点
            Node<E> current = head.next;
            head.next = current.next;
            size--;
        } else {//删除中间结点和尾部结点
            Node<E> current=head.next;
            int count = 1;
            while (count < index-1) {//找到的是index-1的结点
                current = current.next;
                count++;
            }
            current.next=current.next.next;
            size--;
        }
        return true;
    }




}
