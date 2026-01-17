package DP.Kadane;
import java.util.HashSet;
import java.util.Set;

/***CODE is having logic issue so use  Sliding Window + HashSet***/
/*
Maximum Sum of a Contiguous Subarray With All Unique Elements
Given an integer array nums,
find the contiguous subarray (containing at least one number)
which has the largest sum and all its elements are unique,
and return its sum.
Example 1
Input:
nums = [1, 2, 3, 3, 4, 5, 2, 1]

Output:
15
Explanation:
One optimal unique-element subarray is [3, 4, 5, 2, 1] with sum 3 + 4 + 5 + 2 + 1 = 15.
No other contiguous subarray with unique elements has a higher sum.
 */
public class LargestSumContSubarrayuniqueel {
    public static int maxSubArray(int[] nums) {
        int currentMax = nums[0];
        int globalMax = nums[0];
        Set<Integer> uniqueEl = new HashSet<>();
        uniqueEl.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= currentMax + nums[i]) {
                uniqueEl.clear();
                currentMax = nums[i];
            } else {
                if (!uniqueEl.contains(nums[i])) {
                    currentMax = currentMax + nums[i];
                }
            }

            uniqueEl.add(nums[i]);
            System.out.println("For i = " + i + ", Current element: " + nums[i]);
            System.out.println("unique set = " + uniqueEl);
            //print currentMax and globalMax in a single line
            System.out.println("currentMax = " + currentMax + ", current globalMax = " + globalMax);
            globalMax = Math.max(globalMax, currentMax);
            System.out.println("globalMax after comparison = " + globalMax);
            System.out.println("--------------------------------------------------");
        }

        return globalMax;
    }

    public static void main(String[] args) {
        //int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        //int[] nums = {1, 2, 3, 3, 4, 5, 2, 1};
//{-2, 1, -2, 4, -1, 2, 1, -5, 4};
        //+1, -2, +1
        int[] nums = {1, 2, 3, 1, 5};
        int result = maxSubArray(nums);
        System.out.println("The maximum subarray sum is: " + result); // Output: 15
    }
}
/*time complexity: O(n)
space complexity: O(n)
 */
