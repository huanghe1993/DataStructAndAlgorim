package com.huanghe.sort;

import java.util.Arrays;

/**
 * @Author: River
 * @Date:Created in  21:29 2018/6/20
 * @Description: 冒泡排序
 * <p>
 * 时间复杂度：最好情形O(n)，平均情形O(n^2)，最差情形O(n^2)
 * 空间复杂度：O(1)
 * 稳 定 性：稳定
 * 内部排序(在排序过程中数据元素完全在内存)
 */
public class _01BubbleSort {

    /**
     * 从小到大的排序：这个算法不能算的上是冒泡排序，它更应该是最简单的交换排序
     *
     * @param target
     * @return
     */
    public static int[] bubbleSort(int[] target) {
        int temp;
        int n = target.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (target[i] > target[j]) {
                    temp = target[i];
                    target[i] = target[j];
                    target[j] = temp;
                }
            }
            System.out.println(Arrays.toString(target));
        }
        return target;
    }

    /**
     * 正宗的冒泡排序
     *
     * @param target
     * @return
     */
    public static int[] bubbleSort1(int[] target) {
        int temp;
        int n = target.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = n - 1; j > i; j--) {
                if (target[j - 1] > target[j]) {
                    temp = target[j - 1];
                    target[j - 1] = target[j];
                    target[j] = temp;
                }
            }
            System.out.println(Arrays.toString(target));
        }
        return target;
    }

    /**
     * 冒泡排序的优化
     */
    public static int[] bubbleSort2(int[] target) {
        int n = target.length;
        boolean exchange = true;
        //最多需要进行n-1趟比较
        for (int i = 0; i < n - 1 && exchange; i++) {
            exchange = false;
            for (int j = n - 1; j > i; j--) {
                if (target[j - 1] > target[j]) {
                    int temp = target[j - 1];
                    target[j - 1] = target[j];
                    target[j] = temp;
                    exchange = true;
                }
            }
            System.out.println(Arrays.toString(target));
        }
        return target;
    }

}
