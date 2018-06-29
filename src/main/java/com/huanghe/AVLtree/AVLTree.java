package com.huanghe.AVLtree;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayDeque;

/**
 * @Author: River
 * @Date:Created in  17:10 2018/6/15
 * @Description:
 */
public class AVLTree<T extends Comparable<T>> {

    private TreeNode<T> root;

    /**
     * 将给定数据插入节点
     *
     * @param data 需要插入树中的节点数据
     */
    public void insert(T data) {
        root = insertNode(root, data);
    }

    /**
     * 插入二叉树的内部实现
     *
     * @param root 指定的根节点
     * @param data 需要插入的数据
     * @return 返回插入数据后的树
     */
    private TreeNode<T> insertNode(TreeNode<T> root, T data) {
        if (root == null) {
            root = new TreeNode<T>(data);
        } else {
            //比较插入数据和当前结点的大小
            int cmp = data.compareTo(root.data);

            if (cmp < 0) {//当前的数据小于根结点的数据
                root.left = insertNode(root.left, data);
                //判断平衡因子是否是超过了1
                if (isLargerThanOne(root)) {//如果超过了1就需要进行旋转
                    //如果此时的结点比被破坏结点的左孩子还小
                    if (data.compareTo(root.left.data) < 0) {
                        //右单旋转
                        root = singleLeftRotation(root);
                    } else {
                        //左-右双旋（新加的结点比根结点的左子树要大）
                        root = doubleLeftRightRotation(root);
                    }
                } else if (cmp > 0) {
                    root.right = insertNode(root.right, data);
                    if (isLargerThanOne(root)) {
                        if (data.compareTo(root.right.data) > 0) {
                            //左单旋
                            root = singleRightRotation(root);
                        } else {
                            //右-左双旋
                            root = doubleRightLeftRotation(root);
                        }
                    }
                } else {
                    System.out.println("已存在相同节点");
                }
            }
        }
        //更新节点高度
        root.height = Math.max(getHeightRecord(root.left), getHeightRecord(root.right)) + 1;
        return root;
    }

    /**
     * 右旋转(LL插入)LL，说的是【被破坏节点】的【左子节点】的【左子节点】
     *
     * @param tree 被破坏的结点
     * @return 返回旋转完毕后的子树
     */
    public TreeNode<T> singleLeftRotation(TreeNode<T> tree) {
        //将被破坏结点的左子结点提升为根结点
        TreeNode<T> root = tree.left;

        //如果新根节点的右边不为空，把它放进被破坏节点的左边
        tree.left = root.right == null ? null : root.right;

        //被破坏节点放进新根节点的右边
        root.right = tree;

        return root;
    }

    /**
     * 左旋转（RR插入） 围绕【被破坏节点】的【右子节点】向左旋转
     */
    public TreeNode singleRightRotation(TreeNode tree) {
        //将被破坏结点的右子结点提升为根结点
        TreeNode root = tree.right;

        //如果新根结点的左边不为空，则转移到被破坏结点的右边
        tree.right = root.left == null ? null : root.left;

        //被破坏结点放进新根结点的左边
        root.left = tree;

        return root;
    }

    /**
     * 先左转后右转（LR插入(左-右双旋):）
     */
    public TreeNode doubleLeftRightRotation(TreeNode tree) {
        //根结点的左子树进行左转
        tree.left = singleRightRotation(tree.left);
        //整个子树进行右转
        return singleLeftRotation(tree);
    }

    /**
     * 先进行右旋转，然后再进行左旋转（RL插入(右-左双旋):）
     */
    public TreeNode doubleRightLeftRotation(TreeNode tree) {
        tree.right = singleLeftRotation(tree.right);
        return singleRightRotation(tree);
    }

    /**
     * 比较平衡因子是否大于1
     */
    public boolean isLargerThanOne(TreeNode tree) {
        return Math.abs(getHeightRecord(tree.left) - getHeightRecord(tree.right)) > 1;
    }

    /**
     * 获取树的高度
     */
    private int getHeightRecord(TreeNode tree) {
        if (tree != null) {
            return tree.height;
        }
        return 0;
    }

    /**
     * 属性不带高度时，根据结点判断树的高度
     */
    public int getHeightRecursion(TreeNode tree) {
        if (tree == null) {
            return 0;
        }
        return Math.max(getHeightRecursion(tree.right), getHeightRecursion(tree.left)) + 1;
    }

    /**
     * 按层级显示一棵树，不带高度的二叉树
     * @param node
     */
    public void show(TreeNode<Integer> node){
        //当作为栈使用时，性能比Stack好；当作为队列使用时，性能比LinkedList好
        ArrayDeque<Object> nodes = new ArrayDeque<>();
        int i = 0, n = 0;
        boolean isFirst = true;
        TreeNode<Integer> t;
        while (nodes.size() != 0) {
            //  remove() 删除队列中第一个元素，并返回该元素的值，如果元素为null，将抛出异常
            t = (TreeNode<Integer>) nodes.remove();
            System.out.println(t.data + "\t");
            if (++i == 2 << n || isFirst) {
                i = 0;
                n = isFirst ? n : n + 1;
                isFirst = false;
                System.out.println();
            }
            if (t.left != null) {
                nodes.add(t.left);
            }
            if (t.right != null) {
                nodes.add(t.right);
            }
        }
        System.out.println();
    }


}
