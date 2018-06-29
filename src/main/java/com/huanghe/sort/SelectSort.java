package com.huanghe.sort;

/**
 * @Author: River
 * @Date:Created in  22:35 2018/6/27
 * @Description:
 * 时间复杂度是0(n^2)
 */
public class SelectSort {

    public static int[] selectSort(int[] target) {
        int n = target.length;
        int min;
        for (int i = 0; i <n; i++) {
            min = i;
            for (int j = i + 1; j < n; j++) {
                if (target[j] < target[min]) {
                    min = j;
                }
            }
            //判断min和待交换的i是否是一样的，如果是不是一样的就需要进行交换
            if (min != i) {
                int temp = target[min];
                target[min] = target[i];
                target[i] = temp;
            }
        }
        return target;
    }
}
