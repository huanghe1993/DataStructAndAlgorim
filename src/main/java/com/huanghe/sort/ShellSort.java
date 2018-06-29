package com.huanghe.sort;

import java.util.Arrays;

/**
 * @Author: River
 * @Date:Created in  15:31 2018/6/28
 * @Description: 希尔排序 时间复杂度是O（nlogn）
 *
 * 希尔排序是在直接选择排序上的改进的结果
 */
public class ShellSort {

    public static int[] shellSort(int[] array) {
        int n = array.length;
        int i, j;
        int gap = array.length;

        do {
            gap = gap / 3 + 1;
            for (i = gap; i < n; i++) {
                if (array[i] < array[i - gap]) {
                    int temp = array[i];
                    for (j = i - gap; j >= 0; j -= gap) {
                        if (array[j] > temp) {
                            array[j + gap] = array[j];
                        } else {
                            break;
                        }
                    }
                    array[j + gap] = temp;
                }
            }
        } while (gap > 1);

        return array;
    }

    public static void main(String[] args) {
        int[] array = {49,38,65,97,76,13,27,49,55,04};
        shellSort(array);
        System.out.println(Arrays.toString(array));
    }


}
