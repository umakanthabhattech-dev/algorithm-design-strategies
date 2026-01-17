package DP.Kadane;

/*
leetcode 1825. Largest Sum of Non-Negative Subarray
Problem Description: (sam as previous class LargestNonNegativeSum)
Given an array of integers, find the largest sum of contiguous subarray that contains only non-negative
numbers.
If there are no non-negative numbers, return 0.
Example:
Input: [-2, 1, 2, 3, -5, 4, 5]
Output: 6
Explanation: The contiguous subarray [1, 2, 3] has the largest sum of 6
among all non-negative contiguous subarrays.
 */
public class LargestSumNonNegativeSubarray {
    public static int largestSumSubarray(int[] nums) {
        int maxSum = 0;
        int currentSum = 0;

        for (int num : nums) {
            if (num >= 0) {
                currentSum += num;  // Add non-negative numbers to the current sum.
                maxSum = Math.max(maxSum, currentSum);  // Update the maximum sum if the current sum is larger.
            } else {
                currentSum = 0;  // Reset the current sum if a negative number is encountered.
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, -3, 4, 5, -2, 6, 7};
        int result = largestSumSubarray(nums);
        System.out.println("The largest sum of contiguous subarray with only non-negative elements is: " + result);
    }
}
