package com.itseu.common.deque.application;

import com.itseu.common.base.TreeNode;
import com.itseu.common.deque.Deque;
import com.itseu.common.deque.impl.LinkedListDeque;
import com.itseu.common.queue.impl.LinkedListQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DequeApplication {

    /*z序遍历二叉树：第一层左 右 操作Last
     第二层 右 左 操作fisrt*/
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedListDeque<TreeNode> deque = new LinkedListDeque<>(Integer.MAX_VALUE);
        deque.offerFirst(root);
        boolean orderFlag = true;
        while (!deque.isEmpty()) {
            int size = deque.getSize();
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                if(orderFlag) {
                    TreeNode treeNode = deque.pollLast();
                    list.add(treeNode.val);
                    if(treeNode.left != null) {
                        deque.offerFirst(treeNode.left);
                    }
                    if(treeNode.right != null) {
                        deque.offerFirst(treeNode.right);
                    }
                } else {
                    TreeNode treeNode = deque.pollFirst();
                    list.add(treeNode.val);
                    if(treeNode.right != null) {
                        deque.offerLast(treeNode.right);
                    }
                    if(treeNode.left != null) {
                        deque.offerLast(treeNode.left);
                    }
                }
            }
            orderFlag = !orderFlag;
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1_1 = new TreeNode(2);
        TreeNode node1_2 = new TreeNode(3);
        TreeNode node2_1 = new TreeNode(4);
        TreeNode node2_2 = new TreeNode(5);
        TreeNode node2_3 = new TreeNode(6);
        TreeNode node2_4 = new TreeNode(7);
        TreeNode node3_1 = new TreeNode(8);
        TreeNode node3_2 = new TreeNode(9);
        TreeNode node3_3 = new TreeNode(10);
        TreeNode node3_4 = new TreeNode(11);

        root.left = node1_1;
        root.right = node1_2;
        node1_1.left = node2_1;
        node1_1.right = node2_2;
        node1_2.left = node2_3;
        node1_2.right = node2_4;
        node2_1.left = node3_1;
        node2_2.left = node3_2;
        node2_3.right = node3_3;
        node2_4.left = node3_4;
        System.out.println(zigzagLevelOrder(root));
    }
}
