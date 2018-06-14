package com.huanghe.tree;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author: River
 * @Date:Created in  9:50 2018/6/13
 * @Description:
 */
public class BinaryTreeTest {

    @Test
    public void testBinaryTree(){
        BinaryTree tree = new BinaryTree();

        tree.createBinararyTree("A(B(D(,G)),C(E,F))");

        tree.levelOrder();

        System.out.println();
        System.out.println("tree的层次(广序)遍历 ： " + tree.levelOrder());

        System.out.println("\n----------------------------------------------\n");
        System.out.println("tree的层次(前序)遍历 (递归) ： " + tree.preOrder(tree.getRoot()));
        System.out.println("tree的前序遍历 (迭代)： " + tree.preOrder());
        System.out.println("tree的前序遍历 (迭代)： " + tree.preOrderTraversal(tree.getRoot()));

        System.out.println("\n----------------------------------------------\n");
        System.out.println("tree的层次(中序)遍历 (递归) ： " + tree.inOrder(tree.getRoot()));
//        System.out.println("tree的层次(中序)遍历 (迭代) ： " + tree.inOrder(tree.getRoot()));

        System.out.println("\n----------------------------------------------\n");
        System.out.println("tree的后序遍历 (递归)： " + tree.postOrder(tree.getRoot()));
        System.out.println("tree的后序遍历 (迭代)： " + tree.postOrder());
        System.out.println("tree的后序遍历 (迭代)： " + tree.postOrderTraversal(tree.getRoot()));
        System.out.println("\n----------------------------------------------\n");



        // 根据tree的先序遍历和中序遍历结果构建树
        String pre = tree.preOrder().replace(" ", "");
        String in = tree.inOrder().replace(" ", "");
        BinaryTree<Character> tree2 = new BinaryTree<>(pre, in, true);
        System.out.println("根据tree的前序和中序的遍历的结果重构二叉树为：" + tree2.printBinaryTree(tree2.getRoot()));
        System.out.println("tree和tree2是否相同："+tree.equals(tree2));
        System.out.println("\n--------------------------------\n");


        System.out.println("tree的根结点 ： " + tree.getRoot());
        System.out.println("\n----------------------------------------------\n");

        System.out.println("tree的高度 ： " + tree.height(tree.getRoot()));
        System.out.println("\n----------------------------------------------\n");
    }

}