package com.huanghe.sort;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

/**
 * @Author: River
 * @Date:Created in  22:45 2018/6/27
 * @Description: 直接插入排序
 *
 * 直接插入排序算法：基本操作就是将一个记录插入到已经排好序的有序表中，从而得到一个新的、记录数加一的有序表
 */
public class StraightInsertSort {

    public static int[] straightInsertSort(int[] array) {
        int n = array.length;
        int j;
        for (int i = 1; i < n; i++) {
            //后一个元素比前一个元素小的时候，需要进行排序
            if (array[i] < array[i-1]) {
                //待插入的数据
                int temp = array[i];
                for (j = i - 1; j >= 0; j--) {
                    if (array[j] > temp) {
                        array[j + 1] = array[j];
                    } else {
                        break;
                    }
                }
                array[j + 1] = temp;
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {5,2,6,0,3,9,1,7,4,8};
        straightInsertSort(array);
        System.out.println(Arrays.toString(array));
    }

}
