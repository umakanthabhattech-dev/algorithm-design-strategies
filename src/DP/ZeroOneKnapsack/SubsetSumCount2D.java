package DP.ZeroOneKnapsack;

/*
494. same as Target Sum but only positive numbers
Given a set of "positive numbers" nums and a value targetSum, count the total number
of subsets of the given set whose sum is equal to the targetSum.
Let’s say you are given a set = {1,2,3,4} a target sum = 4
The output will be 2 as the following subsets: {1,3} {4}

 */
public class SubsetSumCount2D {
    public static int countSubsets(int[] nums, int targetSum) {
        int n = nums.length;
        int[][] dp = new int[n + 1][targetSum + 1];

        // Base case: There's one way to achieve sum 0 - by choosing no elements.
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        // Fill the dp array
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= targetSum; j++) {
                // If the current number is greater than the sum, we can't include it
                //take ways only when excluding current number
                if (nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {//takes both include and exclude
                    // Otherwise, we can either include the current number or not
                    // dp[i][j] = excluding + including current number and checking for remaining sum in previous row
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
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
        return dp[n][targetSum]; // The bottom-right cell contains the answer
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int targetSum = 4;
        int result = countSubsets(nums, targetSum);
        System.out.println("Total subsets with sum " + targetSum + ": " + result); // Output: 2
    }
}

/*
Explained DP creation code in simple way

for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= targetSum; j++) {
        // ways when excluding current number
        int excludeWays = dp[i - 1][j];
        // ways when including current number (only if it does not exceed current sum)
        int includeWays = (nums[i - 1] <= j) ? dp[i - 1][j - nums[i - 1]] : 0;
        if i =1 and j=1
        (in include ways - we include current number and check for remaining sum in previous row.
        dp[i][j] = excludeWays + includeWays;
    }
}
 */
