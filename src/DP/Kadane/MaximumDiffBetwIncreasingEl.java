package DP.Kadane;

/*
2016. Maximum Difference Between Increasing Elements
Given a 0-indexed integer array nums of size n, find the maximum difference between nums[i] and nums[j]
(i.e., nums[j] - nums[i]), such that 0 <= i < j < n and nums[i] < nums[j].
Return the maximum difference. If no such i and j exists, return -1.

Example 1:

Input: nums = [7,1,5,4]
Output: 4
Explanation:
The maximum difference occurs with i = 1 and j = 2, nums[j] - nums[i] = 5 - 1 = 4.
Note that with i = 1 and j = 0, the difference nums[j] - nums[i] = 7 - 1 = 6, but i > j, so it is not valid.
 */
class MaximumDiffBetwIncreasingEl {
    public int maximumDifference(int[] nums) {
        // Initialize the minimum value to a very large value
        int minVal = Integer.MAX_VALUE;
        // Initialize the answer to -1, assuming there is no positive difference found
        int maxDiff = -1;

        // Loop through each number in the input array
        for (int num : nums) {
            // If the current number is greater than the minimum value found so far
            if (num > minVal) {
                // Update the maximum difference with the greater value between the current maximum difference
                // and the difference between the current number and the minimum value found so far
                maxDiff = Math.max(maxDiff, num - minVal);
            } else {
                // If the current number is not greater than the minimum value found so far,
                // then update the minimum value to the current number
                minVal = num;
            }
        }

        // Return the maximum difference found, or -1 if no positive difference exists
        return maxDiff;
    }

    public static void main(String[] args) {
        //int[] nums = {7, 1, 5, 4};
        int[] nums = {9, 4, 3, 2};
        MaximumDiffBetwIncreasingEl el = new MaximumDiffBetwIncreasingEl();
        System.out.println("Maximum difference: " + el.maximumDifference(nums)); // Output: 5
    }
}

