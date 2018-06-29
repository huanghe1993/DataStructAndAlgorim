package com.huanghe.search;

import java.util.Scanner;

/**
 * @Author: River
 * @Date:Created in  14:57 2018/6/14
 * @Description: 折半查找 算法的时间复杂度是O(logn)
 *
 * 折半查找的先决条件是查找表中的数据元素排列必须是有序的。折半查找先以有序数列的中点位置为比较对象，
 * 如果要找的元素值小于中点位置元素，则将待查序列缩小为左半部分，否则为右半部分。通过一次比较，可以将查找的区间缩小一半，
 * 每次比较，都可以将当前查找范围缩小至一般，可以明显的减少比较的次数，提高查找效率。
 *
 */



public class BinarySearch implements Searcher{

    @Override
    public int search(int[] array, int key) {
        int low, high, mid;
        low=0;
        high=array.length-1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (array[mid] > key) {
                high = mid-1;
            } else if (array[mid] < key) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
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

