package com.huanghe.search;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * @Author: River
 * @Date:Created in  15:05 2018/6/14
 * @Description:
 */
public class BinarySearchTest {
    @Test
    public void test() throws Exception {
        int[] a={1, 2, 3, 4, 5, 6, 7, 8, 9};
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你需要查找的数：");
        //获取控制台的数据
        int key = scanner.nextInt();
        int result = new BinarySearch().search(a, key);
        if (result == -1) {
            System.out.println("您输入的数据不存在数组中");
        } else {
            System.out.println("你输入的数字存在，在数组中的位置是第：" + (result + 1) + "个");
        }
    }

}