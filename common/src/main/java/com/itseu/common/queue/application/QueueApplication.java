package com.itseu.common.queue.application;

import com.itseu.common.base.TreeNode;

import java.util.*;

/*队列的应用*/
public class QueueApplication {
    public static List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> result = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode left_3_1 = new TreeNode(3);
        TreeNode right_3_2 = new TreeNode(4);
        TreeNode left_3_3 = new TreeNode(5);
        TreeNode right_3_4 = new TreeNode(6);

        TreeNode left_2_1 = new TreeNode(7, left_3_1, right_3_2);
        TreeNode right_2_2 = new TreeNode(8, left_3_3, right_3_4);

        TreeNode root = new TreeNode(1, left_2_1, right_2_2);

        List<List<Integer>> res = levelOrder(root);
        for (List<Integer> list : res) {
            System.out.println(list);
        }


    }
}

