package DP.ZeroOneKnapsack;

/*
leetcode 416. Partition Equal Subset Sum
Given an integer array nums, return true if you can partition the array into
two subsets such that the sum of the elements in both subsets is equal or false otherwise.
Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
 */
public class PartitionEqualSubset1D {
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // If the total sum is odd, we cannot partition it into two equal subsets
        if (totalSum % 2 != 0) {
            return false;
        }

        /*
         The idea is to find a subset of the array that sums up to half of the total
         sum of the array. If such a subset exists, then the other subset will also sum to the same value.
         */
        int targetSum = totalSum / 2;
        boolean[] dp = new boolean[targetSum + 1];
        dp[0] = true; // There's always a way to get a sum of 0 (by choosing no elements)

        // Fill the dp array
        for (int num : nums) {
            for (int j = targetSum; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }

        return dp[targetSum];
    }

    public static void main(String[] args) {
        PartitionEqualSubset1D solution = new PartitionEqualSubset1D();
        //int[] nums = {1, 5, 11, 5};
        int nums[] = {2, 3, 3, 3, 4, 5};
        System.out.println(solution.canPartition(nums)); // Output: true
    }
}