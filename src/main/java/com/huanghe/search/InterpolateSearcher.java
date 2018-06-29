package com.huanghe.search;

/**
 * @Author: River
 * @Date:Created in  15:12 2018/6/14
 * @Description:  插值的数值在mid=key-a[low]/(a[high]-a[low])*(high-low)
 */
public class InterpolateSearcher implements Searcher {
    @Override
    public int search(int[] array, int key) {
        int low,mid,high;
        low=0;
        high=array.length-1;
        while (low <= high) {
            mid = low + (key - array[low]) / (array[high] - array[low]) * (high - low);
            if (array[mid] < key) {
                low=mid+1;
            } else if (array[mid] > key) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
