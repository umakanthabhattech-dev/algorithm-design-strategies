package DP.LIS;

/*
Given an integer array nums, return the length of the longest strictly increasing
subsequence.
 */
public class LongestIncreasingSubsequence1D {
    public static int lengthOfLIS(int[] nums) {

        // Step 1: If array is empty, LIS length is 0
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;

        // Step 2: dp[i] = length of LIS ending at index i
        int[] dp = new int[n];

        // Step 3: Initialize dp array with 1
        // Every element itself is an LIS of length 1
        //Because every element alone is an LIS of length 1(min length value)
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        // Step 4: Build dp[] using two loops
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {

                // Step 5: If nums[j] < nums[i], we can extend LIS
                //If nums[i] can extend the increasing subsequence ending at j
                if (nums[j] < nums[i]) {

                    // Step 6: Update dp[i] with the best possible value
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // Step 7: Find the maximum value in dp[]
        int maxLIS = 0;
        for (int len : dp) {
            maxLIS = Math.max(maxLIS, len);
        }

        // Step 8: Return the answer
        return maxLIS;
    }

    // Main method for testing
    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums)); // Output: 4
    }
}
/*
Time complexity = O(n2)
Space complexity = O(n)
 */

/*
“What is the LIS ending exactly at index i?”
Instead of thinking globally, think locally.

dp[i] = length of the Longest Increasing Subsequence
        that ENDS at index i
Initialization :
    dp[i] = 1 for all i
    Why?
    Any single element is a valid LIS of length 1

For every i from left to right
Compare with every j < i

    If nums[j] < nums[i]
        dp[i] = max(dp[i], dp[j] + 1)

0, 1, 2, 3
if i=3 then j can be 0,1,2
We are checking all previous indices j
to see if we can extend the increasing subsequence ending at j using nums[i].
Why dp[i] = max(dp[i], dp[j] + 1) ?
    1. dp[j] gives us the length of the longest increasing subsequence
         that ends at index j.





 If I can extend the subsequence ending at j using nums[i],
then LIS at i becomes one longer than LIS at j.

Why this works (DP intuition) ?
    At each index i, we are considering all possible previous indices j
    ou reuse previously computed LIS values

You avoid recomputing the same subsequences

This is classic overlapping subproblems + optimal substructure
*/

/*

 */