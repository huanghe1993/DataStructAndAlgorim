package com.huanghe.search;

/**
 * @Author: River
 * @Date:Created in  15:45 2018/6/14
 * @Description:
 */
public interface Searcher {
    /**
     * 从数组array中查找关键字key,如果存在则返回该关键字在数组中任意出现的位置(不局限于首次或者末次之类的),否则返回-1
     */
    int search(int[] array, int key);
}
