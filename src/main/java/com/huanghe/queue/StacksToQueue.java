package com.huanghe.queue;

import com.huanghe.stack.LinkedStack;

import java.util.Stack;

/**
 * @Author: River
 * @Date:Created in  11:29 2018/6/8
 * @Description: 两个栈实现一个队列
 * 思路:
 * 1:队列的push()操作，就直接在stack1上进行栈的push()操作即可；
 * 2:队列的pop()操作，其实就是得到stack1中最底下的那个元素，怎么得到呢？先把上面逐个退出的元素一个个放在另一个栈stack2中；
 * 3:当stack1为空的时候，stack2的栈顶元素，就是要取得的元素，用栈的pop()操作取出，在将该元素进行返回前，再将stack2中的元素，倒回到stack1中，然后将该元素返回，over.
 */
public class StacksToQueue<E> {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        while (!stack1.empty()) {
            Integer tempNode = stack1.pop();
            stack2.push(tempNode);
        }

        int result = stack2.pop();

        while (!stack2.empty()) {
            stack1.push(stack2.pop());
        }

        return result;
    }

    public static void main(String []args) {
        StacksToQueue solution = new StacksToQueue();

          solution.push(2);
          solution.push(3);
         System.out.println(solution.pop());
         solution.push(5);

          System.out.println(solution.pop());
          System.out.println(solution.pop());

    }

}
