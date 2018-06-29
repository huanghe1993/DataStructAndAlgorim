package com.huanghe.sort;

/**
 * @Author: River
 * @Date:Created in  16:25 2018/6/28
 * @Description: 堆排序
 * <p>
 * 堆排序是在直接选择排序上的改进的结果
 * <p>
 * 对排序的步骤是：将我们待排序的序列构成一个大顶堆或者是小顶堆，如果我们需要从小到大排序就需要构成一个大顶堆
 * <p>
 * 此时整个序列的最大值就是堆的根结点。将它移走（就是将其与对数组的末尾元素互换，此时末尾元素就是最大的元素）
 * 然后将剩余的n-1个序列重新构成一个堆，这样就会得到n个元素中的最大值
 * 如此反复的进行，就会得到一个有序的序列
 */
public class HeapSort {

    public static int[] heapSort(int[] array) {
        int n = array.length;
        int i;
        //1、将待排序的序列构成一个大顶堆
        for (i = n / 2; i > 0; i--) {
            heapAdjust(array, i, n);
        }
        //2、逐步的将每个最大值的根结点与末尾元素进行互换，并调整其成为大顶堆
        for (i =n ;i >1 ;i--) {
            //第一个元素（最大的元素）和最后的一个元素进行互换
            swap(array, 1, i);
            heapAdjust(array, 1, i-1);
        }

        return array;
    }

    /**
     * 大顶堆
     * @param array
     * @param s:双亲结点
     * @param n
     */
    private static void heapAdjust(int[] array, int s, int n) {
        int i,temp;
        //s是双亲
        temp=array[s];
        //i=2*s表示的是s的左孩子，i*2表示的是下一个双亲结点
        for (i = 2 * s; i < n; i *= 2) {
            if (i < n && array[i] < array[i + 1]) {
                //如果右孩子大于左孩子,i++使得i指向最大的元素
                //如果左孩子大于右孩子，i就不需要加1
                i++;
            }
            //如果双亲大于（左右孩子的话）就退出循环
            if (temp >= array[i]) {
                break;
            }
            //否则双亲就等于大的那个孩子
            array[s]=array[i];
            //双亲待存放的位置放在s里面
            s = i;
        }
        //原来的双亲存放到属于它的位置
        array[s] = temp;
    }

    private static void swap(int[] array, int i, int j) {
        int temp=array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}
