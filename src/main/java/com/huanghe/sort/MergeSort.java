package com.huanghe.sort;

import java.util.Arrays;

/**
 * @Author: River
 * @Date:Created in  20:43 2018/6/28
 * @Description: 归并排序:使用递归 的方式
 */
public class MergeSort {

    public static void sort(int []arr){
        int []temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        sort(arr,0,arr.length-1,temp);
    }

    private static void sort(int[] arr,int left,int right,int []temp){
        if(left<right){
            int mid = (left+right)/2;
            sort(arr,left,mid,temp);//左边归并排序，使得左子序列有序
            sort(arr,mid+1,right,temp);//右边归并排序，使得右子序列有序
            merge(arr,left,mid,right,temp);//将两个有序子数组合并操作
        }
    }

    private static void merge(int[] array, int left, int mid, int right, int[] temp) {
        int i = left;//左序列指针
        int j = mid+1;//右序列指针
        int t = 0;//临时数组指针
        while (i<=mid && j<=right){
            if(array[i]<=array[j]){
                temp[t++] = array[i++];
            }else {
                temp[t++] = array[j++];
            }
        }
        while(i<=mid){//将左边剩余元素填充进temp中
            temp[t++] = array[i++];
        }
        while(j<=right){//将右序列剩余元素填充进temp中
            temp[t++] = array[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while(left <= right){
            array[left++] = temp[t++];
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 9, 1, 4, 7, 8, 3};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
