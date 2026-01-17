package DP.Kadane;

/*
leetcode 53. Maximum Subarray with Indices
Given an integer array nums, find the
subarray with the largest sum, and return its sum along with start and end indices.
Example 1:
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: [6, 3, 6]
Explanation: The subarray [4,-1,2,1] has the largest sum 6, starting at index 3 and ending at index 6.

 */
public class MaxSubArrayKadaneWithIndices {
    public static int[] maxSubArray(int[] nums) {
        int currentSum = nums[0];
        int maxSum = nums[0];

        int start = 0, end = 0, tempStart = 0;

        for (int i = 1; i < nums.length; i++) {

            // Decide whether to start fresh at nums[i]
            if (nums[i] > currentSum + nums[i]) {
                currentSum = nums[i];
                tempStart = i;
            } else {
                currentSum += nums[i];
            }

            // Update maxSum and final indices
            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
            }
        }

        return new int[]{maxSum, start, end};
    }

//    public static Result kadaneWithIndices(int[] a) {
//        int best = Integer.MIN_VALUE;
//        int maxEndingHere = 0;
//        int start = 0, candidateStart = 0, end = 0;
//        for (int i = 0; i < a.length; i++) {
//            int v = a[i];
//            if (maxEndingHere + v < v) {
//                // start new here
//                maxEndingHere = v;
//                candidateStart = i;
//            } else {
//                maxEndingHere += v;
//            }
//            if (maxEndingHere > best) {
//                best = maxEndingHere;
//                start = candidateStart;
//                end = i;
//            }
//        }
//        return new Result(best, start, end);
//    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] res = maxSubArray(arr);

        System.out.println("Max Sum = " + res[0]);
        System.out.println("Start Index = " + res[1]);
        System.out.println("End Index = " + res[2]);
    }
}

