package DP.DPonTrees;

import util.TreeNode;

/*
124. Binary Tree Maximum Path Sum
A path in a binary tree is a sequence of nodes where each pair of adjacent
nodes in the sequence has an edge connecting them. A node can only appear in
the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.
       1
      / \
     2   3
    Input: root = [1,2,3]
    Output: 6
    Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
    Example 2:

       -10
       /  \
      9   20
          / \
         15  7
    Input: root = [-10,9,20,null,null,15,7]
    Output: 42
    Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.

*/
class BinaryTreeMaximumPathSum {
    public int maxPathSum(TreeNode root) {
        int[] maxValue = new int[1];
        maxValue[0] = Integer.MIN_VALUE;
        maxPathDown(root, maxValue);
        return maxValue[0];
    }

    private int maxPathDown(TreeNode node, int maxValue[]) {
        if (node == null) return 0;

        //To avoid negative value
        int left = Math.max(0, maxPathDown(node.left, maxValue));
        int right = Math.max(0, maxPathDown(node.right, maxValue));
        //store the max value at each node
        maxValue[0] = Math.max(maxValue[0], left + right + node.val);

        //return max sum path to parent
        return Math.max(left, right) + node.val;

    }
    public static void main(String[] args) {
        // Create a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);

        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);


        // Find the maximum path sum  

        BinaryTreeMaximumPathSum solution = new BinaryTreeMaximumPathSum();
        int maxSum = solution.maxPathSum(root);

        // Print the result
        System.out.println("Maximum path sum: " + maxSum);
    }
}
/*
The time complexity of the maxPathSum method is O(n),
where n is the number of nodes in the binary tree.
This is because the method visits each node exactly once.

The space complexity of the maxPathSum method is O(h),
where h is the height of the binary tree. This is because the method uses recursion,
and the maximum depth of the recursion stack is equal to the height of the tree.
In the worst case, the height of a binary tree can be n,
so the space complexity can be O(n) in the worst case. However,
in the average case, the height of a binary tree is O(log n), so the space complexity is typically O(log n).
 */