package DP.ZeroOneKnapsack;

/*
Given an integer array nums, return true if you can partition the array into
two subsets such that the sum of the elements in both subsets is equal or false otherwise.
Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
 */
public class PartitionEqualSubset2D {
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
        int target = totalSum / 2;
        int n = nums.length;

        // Create a 2D DP array
        boolean[][] dp = new boolean[n + 1][target + 1];

        // Initialize the first column as true (0 sum is always possible)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                if (nums[i - 1] <= j) {
                    // Direct result -Include the current number or exclude it
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                } else {
                    // Exclude the current number
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println("DP Table:");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        // The value in the bottom-right corner will be our answer, need exact half sum
        return dp[n][target];
    }

    public static void main(String[] args) {
        PartitionEqualSubset2D partition = new PartitionEqualSubset2D();
        int[] nums = {1, 5, 11, 5};
        boolean result = partition.canPartition(nums);
        System.out.println("Can partition: " + result); // Output: true
    }
}
/*Simplified to explain DP table creation logic
  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= target; j++) {
        boolean exclude = dp[i - 1][j];
        boolean include = false;
        if (nums[i - 1] <= j) { //current i value must be <= j
            include = dp[i - 1][j - nums[i - 1]];
            // current value included by default (T) + previous remaining value dp[i - 1][j - nums[i - 1]];
        }
        dp[i][j] = include || exclude;
    }
}
 */

/*
  i\j 0 1 2 3 4 5 6 7 8 9 10 11
0   0 T F F F F F F F F F F F
1   1 T T F F F F F F F F F F
5   2 T T F F F T T F F F F F
11  3 T T F F F T T F F F F T
5   4 T T F F F T T F F F T T

 */