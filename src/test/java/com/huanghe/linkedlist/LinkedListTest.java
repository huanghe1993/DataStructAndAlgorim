package com.huanghe.linkedlist;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author: River
 * @Date:Created in  22:02 2018/6/5
 * @Description:
 */
public class LinkedListTest {

    @Test
    public void test(){
        LinkedList<Integer> list = new LinkedList<>();
        //测试在链表的表头添加元素中添加元素
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        //测试在链表的指定位置添加元素
        list.add(10, 2);
        list.add(11, 1);
        list.add(12,list.length()+1);
        list.print();
        System.out.println();
        System.out.println(list.length());
        //测试删除第一个元素
        list.delete(1);
        list.print();
        System.out.println();
        //测试删除中间元素
        list.delete(2);
        list.print();
        System.out.println();
        //测试删除最后一个元素
        list.delete(5);
        list.print();
        System.out.println();
        //测试反转链表
        list.reverseLinkedList();
        list.print();
        System.out.println();
        System.out.println("================测试找出单链表中倒数第K个元素=================");
        Node<Integer> node = list.getEndK1(2);
        System.out.println("倒数第K个元素是："+node.data);
        System.out.println("================测试找出单链表中倒数第K个元素:快慢指针法=================");
        Node<Integer> node1 = list.getEndK2(2);
        System.out.println("倒数第K个元素是："+node1.data);
        System.out.println("================测试寻找单链表中的中间节点=================");
        list.findMiddleNodes1();
        System.out.println("================测试寻找单链表中的中间节点：快慢指针法=================");
        list.add(5);
        list.print();
        System.out.println();
        list.findMiddleNodes2();
    }

}