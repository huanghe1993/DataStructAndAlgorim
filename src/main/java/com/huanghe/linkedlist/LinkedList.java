package com.huanghe.linkedlist;

import jdk.nashorn.internal.ir.Flags;

import javax.swing.plaf.ViewportUI;

/**
 * @Author: River
 * @Date:Created in  15:46 2018/6/5
 * @Description :实现单链表的一系列的操作
 */
public class LinkedList<E> {

    private Node<E> head;//链表表头
    private  int size;//链表的长度

    public LinkedList() {
        //头指针存的数据为空
        head = new Node<E>(null);
    }

    /**
     * 获取链表的头结点
     * @return
     */
    public Node<E> getHead(){
        return head;
    }

    /**
     * 判断链表是否为空
     * @return
     */
    public boolean isEmpty(){
        return head.next == null;
    }


    /**
     * 获取链表的长度
     * @return 链表的长度
     */
    public int length(){
        int len = 0;
        Node<E> node = head.next;
        while (node != null) {
            len++;
            node = node.next;
        }
        return len;
    }

    /**
     * 清空链表
     */
    public void clear(){
        head = null;
    }

    /**
     * 打印输出单链表，从头到尾
     */
    public void print(){
        Node<E> current=head.next;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
    }

    /**
     * show 从尾到头输出单链表，使用递归的方式
     */
    public void reversePrint(Node<E> head) {
        if (head.next != null) {
            reversePrint(head.next);
            System.out.println(head.next.data+" ");
        }
    }

    /**
     * show 在指定的位置插入结点
     * @param data:待加入的数据
     * @param index：待加入的位置 ,index的起点位置是从0开始算起的
     * @return true插入成功
     *          false插入失败
     */
    public boolean add(E data, int index) {
        if (index > length()+1 || index < 0) {
            throw new IllegalArgumentException("index is" + index + " IllegalArgumentException");
        }

        Node<E> node = new Node<>(data);//新结点

        if (head.next == null) {
            head = node;
            size++;
        } else {
            if (index == 1) {//在链表的头部插入结点
                node.next = head.next;//新结点的next指向head
                head.next = node; //头指针指向新结点
                size++;
            } else {//在链表的中间插入结点
                Node<E> currnt = head.next;
                //需要找到需要插入位置的前一个结点
                int count = 1;
                while (currnt != null && count < index - 1) {//找到的是需要插入位置的前一个结点
                    currnt = currnt.next;
                    count++;
                }
                node.next = currnt.next;//新结点的下一个结点就是需要插入位置的结点
                currnt.next = node;//要插入结点的前一个结点的下一个结点是新结点
                size++;
            }
        }
        return true;
    }
    /**
     *在头部添加第一个结点，已经有头结点
     */
    public void add(E data){
        Node<E> node=new Node<>(data);
        node.next=head.next;
        head.next=node;
        size++;
    }

    /**
     * show 指定的位置的元素
     * @param index 指定的位置
     * @return true:删除成功
     *          false:删除失败
     */
    public boolean delete(int index){
        if (index < 1 || index > length()) {
            return false;
        }
        if (index == 1) {//删除单项链表的第一个结点
            Node<E> current = head.next;
            head.next = current.next;
            size--;
        } else {//删除中间结点和尾部结点
            Node<E> current=head.next;
            int count = 1;
            while (count < index-1) {//找到的是index-1的结点
                current = current.next;
                count++;
            }
            current.next=current.next.next;
            size--;
        }
        return true;
    }

    /**
     * 反转链表
     */
    public void reverseLinkedList() {
        Node<E> current = head.next;
        Node<E> pre=null;

        while (current != null) {
            Node<E> next = current.next; //记录当前结点的下一个结点
            current.next=pre;//当前结点指向反转后的链表
            pre=current; //更新反正后的链表
            current=next; //更新当前的结点
        }
        head.next=pre;//最后把头指针指向原链表的最后一个元素
    }

    /**
     * 寻找单链表中的中间节点
     * 思路：（1）也就是遍历从前到尾进行遍历，先得到链表的长度，然后得到中间结点的位置，最得到中间结点
     * 这样需要进行两次的遍历
     * @return
     */
    public void  findMiddleNodes1(){
        Node<E> current=head.next;
        int middleIndex=0;
        if (length() % 2 == 1) {//奇数,打印的是一个数字
            middleIndex=length()/2+1;
            for (int i = 0; i < middleIndex-1; i++) {
                current = current.next;
            }
            System.out.println(current.data);
        }else {//偶数，中间的数字是有两个的
            middleIndex=length()/2;
            for (int i = 0; i < middleIndex-1; i++) {
                current = current.next;
            }
            System.out.println(current.data);
            System.out.println(current.next.data);
        }


    }
    /**
     * 寻找单链表中的中间节点
     * 思路：（1）快慢指针法  一个快指针一次走2步，一个慢指针一次走一步，快指针先到链表尾部，而慢指针则恰好到达链表中部；
     * 这样需要进行两次的遍历
     * @return
     */
    public void findMiddleNodes2(){
        Node<E> index1=head.next;//快指针
        Node<E> index2=head.next;//慢指针
        if (head.next == null) {
            System.out.println(index1.data);
        }
        while (index2 != null && index2.next != null
                && index2.next.next != null) {
            index1 = index1.next;
            index2 = index2.next.next;
        }
        System.out.print(index1.data); // 第一个中间节点
        if (index2.next != null) { // 当链表长度为偶数时，打印第二个中间节点
            System.out.println(index1.next.data);
        }
    }

    /**
     * show:找出单链表中倒数第k个元素：
     * 思路：（1）最开始想到的是就是进行遍历的操作既然是倒数的第K个元素就是正数的第size-(K-1)个元素，想到的就是进行遍历
     * @param k:倒数倒数第K个位置
     * @return 倒数第K个位置的结点
     * 算法的时间复杂度为 O(n),但是需要进行两次遍历，第一次获取的是链表的长度，第二次获取的才是数据
     */
    public Node<E> getEndK1(int k) {
        Node<E> current = head.next;
        int count = 1;
        while (count < (length() - (k - 1))) {
            current = current.next;
            count++;
        }
        return current;
    }

    /**
     * show:找出单链表中倒数第k个元素：
     * 思路：（2）由于单链表只能从头到尾依次访问链表的各个节点，所以如果要找链表的倒数第k个元素，也只能从头到尾进行遍历查找。
     *            在查找过程中，设置两个指针，让其中一个指针比另一个指针先前移k-1步,然后两个指针同时往前移动。循环直到先行的指针指为NULL时，
     *            另一个指针所指的位置就是所要找的位置
     * @param k:倒数倒数第K个位置
     * @return 倒数第K个位置的结点
     * 算法的时间复杂度为 O(n),但是需要进行两次遍历，第一次获取的是链表的长度，第二次获取的才是数据
     */
    public Node<E> getEndK2(int k) {
        Node<E> pre=head.next;
        Node<E> post=head.next;

        for (int i = 0; i <k-1 ; i++) {
            if (pre != null) {
                pre = pre.next;//快指针比慢指针快k-1步
            }
        }

        if (pre != null) {
            while (pre != null && pre.next != null) {
                pre = pre.next;
                post = post.next;
            }

            return post;
        }
        return null;
    }

    /**
     * 判断链表是否有环
     * 思路分析：
     * 单链表有环，是指单链表中某个节点的next指针域指向的是链表中在它之前的某一个节点，这样在链表的尾部形成一个环形结构。判断链表是否有环，有以下几种方法。
     * （1）最常用方法：定义两个指针，同时从链表的头节点出发，一个指针一次走一步，另一个指针一次走两步。如果走得快的指针追上了走得慢的指针，那么链表就是环形链表；
     * 如果走得快的指针走到了链表的末尾（next指向 NULL）都没有追上第一个指针，那么链表就不是环形链表。
     *
     * 例如链表A->B->C->D->B->C->D，两个指针最初都指向节点A，
     * 进入第一轮循环，指针1移动到了节点B，指针2移动到了C。
     * 第二轮循环，指针1移动到了节点C，指针2移动到了节点B。
     * 第三轮循环，指针1移动到了节点D，指针2移动到了节点D，
     * 此时两指针指向同一节点，判断出链表有环
     */
    public boolean hasLoop() {
        Node<E> pre=head.next;
        Node<E> post=head.next;

        while (pre != null && pre.next != null && pre.next.next != null) {
            post=post.next;
            pre = pre.next.next;
            if (pre == post) {
                return true;
            }
        }
        return false;
    }

    /**
     * 如果链表有环：寻找环连接点（入口点）
     * @return 如果没有环返回的是null
     */
    public Node<E> findLoopPort(){
        Node<E> fast=head.next; //快指针
        Node<E> slow=head.next;//慢指针

        while (fast != null && fast.next != null) {
            fast = fast.next.next;//fast走两步
            slow = slow.next;//slow走一步
            if (slow == fast) {//此时走到了碰撞点
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return null;
        }
        slow = head;

        //由于第一次碰撞点的距离到连接点的距离=头指针到连接点的距离
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 如何知道环的长度？
     * 对于这个，记录下的碰撞点p，slow、fast从该点开始，再次碰撞所走过的操作数就是环的长度s。
     */
    public  int  loopLength(){
        if (hasLoop() == false) {
            return 0;
        }
        Node<E> fast=head.next; //快指针
        Node<E> slow=head.next;//慢指针
        int length = 0;
        boolean begin = false;
        boolean agian = false;
        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            //超两圈后停止计数，挑出循环
            if (fast == slow && agian == true)
                break;
            //超一圈后开始计数
            if (fast == slow && agian == false) {
                begin = true;
                agian = true;
            }
            //计数
            if (begin == true)
                ++length;
        }
        return length;

    }


    /**
     * 判断两单链表是否相交（假设两个链表都没有环）
     *
     * 1、判断第一个链表的每个节点是否在第二个链表中
     * 2、把第二个链表连接到第一个后面，判断得到的链表是否有环，有环则相交
     * 3、先遍历第一个链表，记住最后一个节点，再遍历第二个链表，得到最后一个节点时和第一个链表的最后一个节点做比较，如果相同，则相交
     * (两个链表无环时若相交，则至少尾节点必然是同一个)
     *
     * 判断两单链表是否相交（假设一个有环一个没有环）
     * 1：一个有环，一个没环：不用判断了，肯定两链表不相交
     *
     * 判断两单链表是否相交（假设两个都有环）
     * 1：两个都有环：判断链表A的碰撞点是否出现在链表B的环中，如果在，则相交。（相交时，环必定是两链表共有的）
     */
    public boolean isIntersect(LinkedList<E> list2) {
        Node<E> p = head.next;//当前链表
        Node<E> q = list2.getHead().next;//目标链表的头结点

        // 两链表有一个为空，则返回 false
        if(p == null || q == null){
            return false;
        }

        while (p != null) {
            p = p.next;
        }
        while (q != null) {
            q = q.next;
        }
        return p == q;
    }

    /**
     * 无环情况下找出第一个相交点
     *
     * 此时已判断出是否相交，相交后，来寻找第一个相交点
     * 两种思路：方法一：较易懂，算出两个链表的长度差δ，两个指针从表头出发，之后较长的链表先移动δ步，
     *                  之后两链表同时移动，直到遇到相同的节点，该节点即为第一个相交点
     *
     */
    public Node getFirstJoint(LinkedList<E> list2){
        Node<E> current1 = head.next;   // 当前链表
        Node<E> current2 = list2.getHead().next;  // 目标链表

        //获取当前链表的长度
        int len1=length();
        int len2=list2.length();

        if (this.isIntersect(list2)) {//首先判断是否相交
            int step = Math.abs(len1 - len2);
            if (len2 > len1) {
                while (step > 0) {
                    current2 = current2.next;
                    step--;
                }
            } else {
                while (step > 0) {
                    current1 = current1.next;
                    step--;
                }
            }
            //两个指针同时移动，一旦指向同一个节点，即为交点
            while(current1 != current2){
                current1 = current1.next;
                current2 = current2.next;
            }
            return current1;
        }
        return null;
    }

    /**
     * 去除链表中的重复的元素
     * 算法的时间复杂度为O(n^2)
     */
    public  void removeDuplicateNodes(){
        Node<E> current = head.next;
        while (current != null) {
            Node<E> temp = current;
            while (temp != null && temp.next != null) {
                if (current.data.equals(temp.next.data)) {
                    Node<E> duplicateNode=temp.next;
                    temp.next = duplicateNode.next;
                    duplicateNode.next = null;
                    size--;
                }
                temp = temp.next;
            }
            current = current.next;
        }
    }
}
