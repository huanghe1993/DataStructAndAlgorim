package com.huanghe.search;

import com.huanghe.recursion.Fibonacci;

/**
 * @Author: River
 * @Date:Created in  15:36 2018/6/14
 * @Description:
 */
public class FibonacciSearcher implements Searcher {
    @Override
    public int search(int[] array, int key) {
        int low,high,mid,len,k;
        k=0;
        low=0;
        len=array.length;
        high=array.length-1;
        while (len > Fibonacci.Fbi(k) - 1) {
            k++; //计算high在Fibonacci的位置
        }
        // 创建临时数组(数组长度为fib[k] - 1)
        int[] tmp = new int[Fibonacci.Fbi(k) - 1];
        // 拷贝原数组到tmp数组中
        System.arraycopy(array, 0, tmp, 0, len);
        // 填充tmp数组中剩余的位置,补充的元素值为最后一个元素值
        for (int i = len; i <Fibonacci.Fbi(k)-1 ; i++) {
            tmp[i] = array[high];
        }

        // 开始进行类似于二分查找的查找
        while (low <= high) {
            // 对于tmp数组,整个数组的长度为fib[k]-1
            // 而 fib[k]-1 = (fib[k-1]-1) + 1 + (fib[k-2]-1);
            // 所以可以这样理解： mid下标对应元素可以将整个数组拆分为两部分,第1部分有fib[k-1]-1个元素,第2部分有fib[k-2]-1个元素
            // mid=low+fib[k-1]-1; 正是将 数组的[low, max(high,tmp.length-1)]
            // 部分按照斐波那契规则分为两部分
            mid = low + Fibonacci.Fbi(k-1) - 1;
            if (tmp[mid] > key) {
                // 需要查找第1部分
                high = mid - 1;
                // fib[k] = fib[k-1] + fib[k-2]
                // 第一部分有fib[k-1]个元素,所以将k-1赋值为k
                k = k - 1;
            } else if (tmp[mid] < key) {
                // 需要查找第2部分
                low = mid + 1;
                // fib[k] = fib[k-1] + fib[k-2]
                // 第二部分有fib[k-2]个元素,所以将k-2赋值给k
                k = k - 2;
            } else {
                // 查找成功
                // 以下代码其实就是返回 min(mid, high);
                // return Math.min(mid, high);
                if (mid <= high)
                    return mid;
                else
                    return high; // 因为mid可能大于high,即查找到了补充的元素,那么还是应该返回high
            }
        }
        return -1;
    }

}
