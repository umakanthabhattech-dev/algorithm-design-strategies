package DP.DPonTrees;

import util.TreeNode;

/*
Leetcode 337. House Robber III
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
Besides the root, each house has one and only one parent house. After a tour,
the smart thief realized that all houses in this place form a binary tree.
It will automatically contact the police if two directly-linked houses were broken into on the same night.

Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.

Example 1:
Input: root = [3,2,3,null,3,null,1]
Output: 7
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 */
public class HouseRobberIII {

    int rob(TreeNode root) {

        int[] options = travel(root);
        return Math.max(options[0], options[1]);
    }

    private int[] travel(TreeNode root) {
        // Base case. just return {0,0} as you cannot rob anything
        if (root == null)
            return new int[2];

        int[] left_node_choices = travel(root.left);
        int[] right_node_choices = travel(root.right);
        int[] options = new int[2];

        // Store value if looted in [0]
        options[0] = root.val + left_node_choices[1] + right_node_choices[1];

        // Store value if skipped in [1]
        options[1] = Math.max(left_node_choices[0], left_node_choices[1]) +
                Math.max(right_node_choices[0], right_node_choices[1]);

        return options;
    }
    public static void main(String[] args) {
        // Create a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(9);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(7);
        root.right.left = new TreeNode(8);

        HouseRobberIII robber = new HouseRobberIII();
        int maxLoot = robber.rob(root);
        System.out.println("Maximum amount of money the robber can rob: " + maxLoot);
    }

}
