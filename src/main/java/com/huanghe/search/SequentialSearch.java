package com.huanghe.search;

import java.security.Key;
import java.util.Scanner;



/**
 * @Author: River
 * @Date:Created in  11:32 2018/6/14
 * @Description: 顺序查找 算法的时间复杂度是O(n)
 */
public class SequentialSearch {

    public static void main(String[] args) {
        int[] a = {4, 6, 2, 8, 1, 9, 0, 3};
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你需要查找的数：");
        //获取控制台的数据
        int num = scanner.nextInt();
       //int result=search(a,num);
        int result = search1(a, num);
        if (result == -1) {
            System.out.println("您输入的数据不存在数组中");
        } else {
            System.out.println("你输入的数字存在，在数组中的位置是第：" + (result + 1) + "个");
        }
    }

    /**
     * 顺序查找最简单的方式：算法的时间复杂度为0(n)
     * @param a
     * @param num
     * @return
     */
    public static int search(int[] a, int num) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == num) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 改进的顺序查找：由于上面查找在循环中需要进行两次的判断时间的效率比较低
     * @param a
     * @param num
     * @return
     */
    public static int search1(int[] a, int num) {
        int i;
        if (a[0] == num) {
            return 0;
        }
        a[0] = num; // array[0]不是key,那么将key赋值给array[0],将array[0]作为哨兵这里"哨兵"也可以放在数组尾部
        i=a.length-1;
        while (a[i] != num) {
            i--;
        }
        if (i == 0) {
            return -1;
        }
        return i;
        
    }

    
}
