package DP.ZeroOneKnapsack;

/*
Minimize the sum difference between two subsets - can subsets have different lengths
Suppose you are given an array, nums, containing positive numbers.
You need to partition the array into two arrays such that the absolute
difference between their sums is minimized.
Note: Each element of the nums array should be present in one of the partitioned arrays.
[2, 3, 1]
[2,1],sum=3
[3],sum=3
So, the minimum difference becomes ∣3−3∣=0
 */
public class MinimizeSumDifference1D {
    public static int findMinDifference(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int subsetSum = totalSum / 2;
        boolean[] dp = new boolean[subsetSum + 1];
        dp[0] = true; // There's always a subset with sum 0

        // Fill the DP array
        for (int num : nums) {
            for (int j = subsetSum; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }

        // Find the largest j such that dp[j] is true
        for (int j = subsetSum; j >= 0; j--) {
            if (dp[j]) {
                return totalSum - 2 * j; // Minimum difference
            }
        }

        return totalSum; // Fall back in case of empty array
    }

    public static void main(String[] args) {
        int[] nums = {1, 6, 11, 5};
        int minDifference = findMinDifference(nums);
        System.out.println("The minimum difference is: " + minDifference);
    }
}
//time complexity O(N∗S)
//space complexity O(S)
//where “N” represents the total items and “S” is the total sum of all
