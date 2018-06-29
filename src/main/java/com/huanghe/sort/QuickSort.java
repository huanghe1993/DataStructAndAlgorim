package com.huanghe.sort;

import java.util.Arrays;

/**
 * @Author: River
 * @Date:Created in  21:37 2018/6/28
 * @Description: 快速排序算法
 *
 * 快速排序的思想：通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另外一部分记录的关键字小，
 * 则可分别对这两部分记录继续排序，以达到整个有序的目的
 */
public class QuickSort {

    public static void quickSort(int[] array) {
        int n = array.length;
        Qsort(array, 0, n - 1);
    }

    /**
     * @param array:待排序的数组
     * @param low：数组的起始位置
     * @param high：数组的结束位置
     */
    private static void Qsort(int[] array, int low, int high) {
        //基准点
        int point;

        //因为是不断和基准点进行比较，左边的指针和右边的指针都会不断的进行移动，当指针重叠的时候说明他们已经进行了一轮的比较
        if (low < high) {
            point =paration(array,low,high);
            Qsort(array,low,point-1); //基准点的左边
            Qsort(array,point+1,high); //基准点的右边
        }
    }

    /**
     * 计算基准点的函数，并将小于基准点的所有元素放在基准点的左边，将大于该基准点的元素放在基准点的右边
     *
     * @return
     */
    private static int paration(int[] array, int low, int high) {
        int point;
        //基准点定位为数组的第一个元素
        point = array[low];

        //如果high大于基准点，则high指向的元素需要放在基准点的右边
        while (low < high) {
            //这个while循环是过滤掉比基准点大的元素
            while (low < high && array[high] >= point) {
                high--;
            }
            //出了while循环说明需要进行交换两个元素
            swap(array, low, high);
            //左边的
            while (low < high && array[low] <= point) {
                low++;
            }
            //出了while循环说明左边的出现了大于基准点的，需要进行交换
            swap(array, low, high);
        }
        return low;
    }

    private static void swap(int[] array, int i, int j) {
        int temp=array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {49,38,65,97,76,13,27,49,55,04};
        quickSort(array);
        System.out.println(Arrays.toString(array));
    }
}
