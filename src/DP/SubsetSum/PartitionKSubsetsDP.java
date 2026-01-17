package DP.SubsetSum;

import java.util.Arrays;

/*
Leetcode 698: Partition to K Equal Sum Subsets
Given an integer array nums and an integer k,
return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.
nums = [4, 3, 2, 3, 5, 2, 1]
k = 4
Output: true
 */
public class PartitionKSubsetsDP {
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false;
        }

        int targetSum = sum / k;
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][targetSum + 1];

        // Base case: it's possible to partition an empty set into any number of subsets
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Iterate over the items and target sums
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= targetSum; j++) {
                // If the current item can be included without exceeding the target sum,
                // update the DP table based on the previous state
                if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                } else {
                    // If the current item cannot be included, the DP table remains unchanged
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println("DP Table:");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= targetSum; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        // Check if it's possible to partition the entire set into k subsets
        return dp[n][targetSum];
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};

        //int[] nums = {1,2,3,4};
        int k = 4;

        boolean canPartition = canPartitionKSubsets(nums, k);
        System.out.println("Can partition into " + k + " subsets: " + canPartition);
    }
}
