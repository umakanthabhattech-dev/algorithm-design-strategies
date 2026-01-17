package DP.Kadane;

/*
similar leetcode 1422:
Substring with Maximum Difference of Zeros and Ones
Substring means contiguous sequence of characters within a string.(same as subarray for arrays)
 */
public class MaxDifferenceBinaryString {
    public static int maxDifference(String binaryString) {
        // Transform the binary string into an integer array
        int n = binaryString.length();
        int[] transformedArray = new int[n];

        for (int i = 0; i < n; i++) {
            transformedArray[i] = (binaryString.charAt(i) == '0') ? 1 : -1;
        }

        // Use the refactored maxSubArray method to find the maximum subarray sum
        return maxSubArray(transformedArray);
    }

    public static int maxSubArray(int[] nums) {
        int currentMax = nums[0];
        int globalMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            globalMax = Math.max(globalMax, currentMax);
        }

        return globalMax;
    }

    public static void main(String[] args) {
        String binaryString = "001100";
        int result = maxDifference(binaryString);
        System.out.println("The maximum difference of zeros and ones is: " + result);
    }
}
