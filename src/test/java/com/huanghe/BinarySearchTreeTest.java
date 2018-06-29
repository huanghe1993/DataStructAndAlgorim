package com.huanghe;

import com.huanghe.BinarySearchTree.BinarySearchTree;
import org.junit.Test;

/**
 * @Author: River
 * @Date:Created in  10:07 2018/6/15
 * @Description:
 */
public class BinarySearchTreeTest {

    @Test
    public void testBinarySearchTree(){

        int[] input = {53,78,65,17,87,9,81,45,23};
        BinarySearchTree tree = new BinarySearchTree(input);
        tree.insert(1, tree.getRoot());
    }
}