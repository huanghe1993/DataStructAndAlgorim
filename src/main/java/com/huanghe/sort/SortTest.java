package com.huanghe.sort;

import java.util.Arrays;

/**
 * @Author: River
 * @Date:Created in  21:36 2018/6/20
 * @Description: 排序测试
 */
public class SortTest {

    public static void main(String[] args) {
        System.out.println("\n----------------------\n");
        System.out.println("简单排序 ： ");
        int[] target3 = { 1, 2, 3, 4, 5, 8, 7, 6 };
        System.out.println("原数组 ： " + Arrays.toString(target3));
        _01BubbleSort.bubbleSort(target3);

        System.out.println("\n----------------------\n");
        System.out.println("冒泡排序 ： ");
        int[] target2 = { 1, 2, 3, 4, 5, 8, 7, 6 };
        System.out.println("original Array ： " + Arrays.toString(target2));
        _01BubbleSort.bubbleSort1(target2);
    }
}
