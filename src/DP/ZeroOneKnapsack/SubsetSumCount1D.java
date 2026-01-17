package DP.ZeroOneKnapsack;

/*
Given a set of positive numbers nums and a value targetSum, count the total number
of subsets of the given set whose sum is equal to the targetSum.
Let’s say you are given a set = {1,2,3,4} a target sum = 4
The output will be 2 as the following subsets: {1,3} {4}

 */
public class SubsetSumCount1D {
    public static int countSubsets(int[] nums, int targetSum) {
        int n = nums.length;
        int[] dp = new int[targetSum + 1];

        // Base case: There's one way to achieve sum 0 - by choosing no elements.
        dp[0] = 1;

        // Fill the dp array
        for (int i = 0; i < n; i++) {
            for (int j = targetSum; j >= nums[i]; j--) {
                //Exclude current number :
                //For excluding current number we already have the ways in dp[j] handled in for loop
                //Don't need to explicitly write it again like in 2D problem

                //Include current number :
                // dp[j] = excluding + including current number and checking for remaining sum
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[targetSum]; // The last element contains the answer
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int targetSum = 4;
        int result = countSubsets(nums, targetSum);
        System.out.println("Total subsets with sum " + targetSum + ": " + result); // Output: 2
    }
}
