package com.huanghe.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: River
 * @Date:Created in  10:59 2018/6/8
 * @Description: 用两个队列模拟一个栈
 *
 * 思路：
 * 1.假设有两个队列Q1和Q2，当二者都为空时，入栈操作可以用入队操作来模拟
 * 2.可以随便选一个空队列，假设选Q1进行入栈操作，现在假设a,b,c依次入栈了（即依次进入队列Q1），
 * 3.这时如果想模拟出栈操作，则需要将c出栈，因为在栈顶，这时候可以考虑用空队列Q2，将a，b依次从Q1中出队，
 * 4.而后进入队列Q2，将Q1的最后一个元素c出队即可，此时Q1变为了空队列，Q2中有两个元素，队头元素为a，队尾元素为b，
 * 5.接下来如果再执行入栈操作，则需要将元素进入到Q1和Q2中的非空队列，即进入Q2队列，出栈的话，就跟前面的一样，
 * 6.将Q2除最后一个元素外全部出队，并依次进入队列Q1，再将Q2的最后一个元素出队即可。
 */
public class QueueToStack<T> {
    Queue<T> queueA = new LinkedList<>();
    Queue<T> queueB = new LinkedList<>();


    public void push(T value) {
        if (queueA.size() == 0 && queueB.size() == 0) {//如果两个队列都是空的话,则随便选择一个队列执行入栈操作
            queueA.add(value);
        }else if (queueA.size()==0&&queueB.size()!=0){///如果不是两个队列都是为空的话,则选择非空的队列入栈
            queueB.add(value);
        }else if (queueA.size()!=0&&queueB.size()==0){
            queueA.add(value);
        }
    }

    public T pop() {
        if (queueA.size()==0&&queueB.size()==0){
            return null;
        }
        T result = null;
        if (queueA.size()==0&&queueB.size()!=0){
            while (queueB.size()>0){
                result = queueB.poll();
                if (queueB.size()!=0){
                    queueA.add(result);
                }
            }
        }else if (queueA.size()!=0&&queueB.size()==0){
            while (queueA.size()>0){
                result = queueA.poll();
                if (queueA.size()!=0){
                    queueB.add(result);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        QueueToStack<Integer> stack=new QueueToStack<>();
        int tmp=0;
        stack.push(1);
        stack.push(2);
        stack.push(3);
        tmp=stack.pop();
        System.out.println(tmp);//3
        stack.push(4);
        tmp=stack.pop();
        System.out.println(tmp);//4
        tmp=stack.pop();
        System.out.println(tmp);//2
        stack.push(5);
        stack.push(6);
        tmp=stack.pop();
        System.out.println(tmp);//6
        tmp=stack.pop();
        System.out.println(tmp);//5
        tmp=stack.pop();
        System.out.println(tmp);//1
    }
}
