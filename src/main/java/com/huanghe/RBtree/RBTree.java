package com.huanghe.RBtree;

/**
 * @Author: River
 * @Date:Created in  21:16 2018/6/17
 * @Description: 红黑树的描述
 */
public class RBTree<T extends Comparable<T>> {
    private RBNode<T> mRoot; //根结点

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    /*
 * 对红黑树的节点(x)进行左旋转
 *
 * 左旋示意图(对节点x进行左旋)：
 *      px                              px
 *     /                               /
 *    x                               y
 *   /  \      --(左旋)-.           / \                #
 *  lx   y                          x  ry
 *     /   \                       /  \
 *    ly   ry                     lx  ly
 *
 *
 */
    private void leftRotate(RBNode<T> x) {
        //设置x的右孩子为y
        RBNode<T> y = x.right;

        //将‘y的左孩子’设为‘x的右孩子’
        //如果y的左孩子非空，则将x设置为y的左孩子的父亲
        x.right=y;
        if (y.left != null) {
            y.left.parent = x;
        }

        //将x的父亲设置为y的父亲
        if (x.parent == null) { //如果x的父亲为空结点，则设置y为根结点
            this.mRoot = y;
        } else {
            if (x.parent.left == x) {//如果 x是它父节点的左孩子，则将y设为“x的父节点的左孩子”
                x.parent.left = y; // 如果 x是它父节点的左孩子，则将y设为“x的父节点的左孩子”
            } else {
                x.parent.right = y; // 如果 x是它父节点的左孩子，则将y设为“x的父节点的左孩子”
            }
        }

        // 将 “x” 设为 “y的左孩子”
        y.left = x;
        // 将 “x的父节点” 设为 “y”
        x.parent = y;
    }

}
