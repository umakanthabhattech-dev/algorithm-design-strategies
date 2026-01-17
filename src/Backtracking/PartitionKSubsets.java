package Backtracking;

import java.util.Arrays;

/*
Leetcode 698: Partition to K Equal Sum Subsets
Given an integer array nums and an integer k,
return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.
 */
public class PartitionKSubsets {

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false;
        }

        int targetSum = sum / k;
        boolean[] used = new boolean[nums.length];
        return canPartition(0, nums, used, k, 0, targetSum);
    }

    private static boolean canPartition(int startIndex, int[] nums, boolean[] used, int k, int currentSum, int targetSum) {
        if (k == 1) {
            return true;
        }

        if (currentSum == targetSum) {
            return canPartition(0, nums, used, k - 1, 0, targetSum);
        }

        for (int i = startIndex; i < nums.length; i++) {
            if (!used[i] && currentSum + nums[i] <= targetSum) {
                used[i] = true;
                if (canPartition(i + 1, nums, used, k, currentSum + nums[i], targetSum)) {
                    return true;
                }
                used[i] = false;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        int k = 4;

        boolean canPartition = canPartitionKSubsets(nums, k);
        System.out.println("Can partition into " + k + " subsets: " + canPartition);
    }
}
/*
time complexity: O(k * 2^n)
space complexity: O(n)
698. Partition to K Equal Sum Subsets
Given an integer array nums and an integer k,
return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.
Input: nums = [4,3,2,3,5,2,1], k = 4
Output: true
Explanation: It is possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.

 */