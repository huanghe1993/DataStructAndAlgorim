package com.huanghe.recursion;

/**
 * @Author: River
 * @Date:Created in  22:10 2018/6/7
 * @Description: 斐波拉契数列的递归形式
 */
public class Fibonacci {
    public static void main(String[] args) {
        for (int i = 0; i <40 ; i++) {
            System.out.println(Fbi(i));
        }
    }

    private static int Fbi(int i) {
        if (i < 2) {
            return i == 0 ? 0 : 1;
        }
        return Fbi(i - 1) + Fbi(i - 2);
    }
}
