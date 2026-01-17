package DP.Kadane;

/*

Problem Description:
Given an array of integers, find the largest sum of contiguous subarray that contains only non-negative numbers.
If there are no non-negative numbers, return 0.

Example:
Input: [-2, 1, 2, 3, -5, 4, 5]
Output: 6
Explanation: The contiguous subarray [1, 2, 3] has the largest sum of 6
among all non-negative contiguous subarrays.
 */
public class LargestNonNegativeSum {
    public static int getLargestNonNegativeSum(int[] nums) {
        int maxSum = 0; // To store the maximum sum found
        int currentSum = 0; // To store the current sum of non-negative numbers

        for (int num : nums) {
            if (num >= 0) {
                currentSum += num; // Add to current sum if non-negative
            } else {
                // If we hit a negative number, compare and reset current sum
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                }
                currentSum = 0; // Reset current sum
            }
        }

        // Final check in case the array ends with a non-negative number
        if (currentSum > maxSum) {
            maxSum = currentSum;
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, -1, 5, 6, -2, 4};
        int result = getLargestNonNegativeSum(nums);
        System.out.println("The largest sum of contiguous non-negative subarray is: " + result);
    }
}
/*
time complexity: O(n)
space complexity: O(1) because we are using only a constant amount of extra space.
 */
