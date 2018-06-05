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
        list.delete(1);
        list.print();
        System.out.println();
        list.delete(2);
        list.print();
        System.out.println();
        list.delete(5);
        list.print();
    }

}