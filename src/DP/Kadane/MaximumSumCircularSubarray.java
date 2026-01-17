package DP.Kadane;
/*
918. Maximum Sum Circular Subarray
Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.

A circular array means the end of the array connects to the beginning of the array.
Formally, the next element of nums[i] is nums[(i + 1) % n] and
the previous element of nums[i] is nums[(i - 1 + n) % n].

A subarray may only include each element of the fixed buffer nums at most once.
Formally, for a subarray nums[i], nums[i + 1], ..., nums[j],
there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.

Example 1:
Input: nums = [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3.
Example 2:

Input: nums = [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.

Solution Approach:
To solve the problem of finding the maximum sum circular subarray, we can use a modified version
of Kadane's algorithm. The idea is to find the maximum subarray sum in two scenarios:
1. The maximum subarray sum that does not wrap around the array (standard Kadane's algorithm).
2. The maximum subarray sum that wraps around the array. This can be found by calculating the total sum of the array
   and subtracting the minimum subarray sum (also found using Kadane's algorithm).
   X = totalSum - minSubarraySum where X is the maximum sum of the circular subarray that wraps around.
   so final result will be max(maxSubarraySum, totalSum - minSubarraySum)
3.If all numbers are negative, the maximum subarray sum will be the maximum single element,
   which is handled by checking if totalSum equals minSubarraySum.
   if all negative totalSum becomes equal to minSubarraySum.
   so X = totalSum - minSubarraySum = 0 which is invalid as we need non-empty subarray.
   so in that case we return maxSubarraySum directly.

Case 1 — Normal (No wrap)
The maximum subarray lies completely inside the array, like usual Kadane’s algorithm.
Case 2 — Wrap-around case
The maximum subarray wraps around the end of the array to the beginning.
Here the maximum subarray includes:
-some elements from the end of the array S1
[1, -2, 3, 4] → max = 3 + 4 = 7

-some elements from the beginning of the array
[5, -3, 5]
Wrap case → 5 + 5 = 10

S1 = Kadane maximum subarray (normal)
S2 = total_sum – Kadane minimum subarray (wrap)

 */
public class MaximumSumCircularSubarray {
    public static int maxSubArraySum(int[] nums) {
        int currentMax = nums[0];
        int globalMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            globalMax = Math.max(globalMax, currentMax);
        }

        return globalMax;
    }

    public static int minSubArraySum(int[] nums) {
        int currentMin = nums[0];
        int globalMin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentMin = Math.min(nums[i], currentMin + nums[i]);
            globalMin = Math.min(globalMin, currentMin);
        }

        return globalMin;
    }

    public static int maxCircularSubarraySum(int[] nums) {
        int maxKadane = maxSubArraySum(nums);
        int minKadane = minSubArraySum(nums);
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        // If all numbers are negative, return the maximum subarray sum
        if (totalSum == minKadane) {
            return maxKadane;
        }

        return Math.max(maxKadane, totalSum - minKadane);
    }

    public static void main(String[] args) {
        int[] nums = {8, -8, 9, -9, 10, -11, 12};
        int result = maxCircularSubarraySum(nums);
        System.out.println("The maximum sum circular subarray is: " + result); // Output: 22
    }
}

