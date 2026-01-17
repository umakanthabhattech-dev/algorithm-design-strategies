package DP.LIS;

/*
Leetcode 673.
Number of Longest Increasing Subsequence
Given an integer array nums, return the number of longest increasing subsequences.
Notice that the sequence has to be strictly increasing.
Input: nums = [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
 */
public class NumberOfLIS {
    public static int findNumberOfLIS(int[] nums) {
        if (nums.length == 0) return 0;

        int n = nums.length;
        int[] lengths = new int[n]; // lengths[i] will hold the length of LIS ending at index i
        int[] counts = new int[n];   // counts[i] will hold the number of LIS ending at index i
        int maxLength = 0;           // To track the maximum length of LIS found

        for (int i = 0; i < n; i++) {
            lengths[i] = 1; // Each element is an increasing subsequence of length 1
            counts[i] = 1;  // Each element has one way to form an increasing subsequence
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (lengths[i] < lengths[j] + 1) {
                        lengths[i] = lengths[j] + 1; // Update the length
                        counts[i] = counts[j];       // Reset count to counts[j]
                    } else if (lengths[i] == lengths[j] + 1) {
                        //when we get another same length value that means another combination are there
                        counts[i] += counts[j];      // Add the counts
                    }
                }
            }
            maxLength = Math.max(maxLength, lengths[i]); // Update maxLength
        }

        // Sum up the counts of all LIS of maximum length
        int totalCount = 0;
        for (int i = 0; i < n; i++) {
            if (lengths[i] == maxLength) {
                totalCount += counts[i];
            }
        }

        return totalCount;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 4, 7};
        System.out.println("Number of longest increasing subsequences = " + findNumberOfLIS(nums));
    }
}
//https://www.youtube.com/watch?v=cKVl1TFdNXg

/*
Same as previous Longest Increasing Subsequence problem, we maintain two arrays:
1. dp[i]: Length of the longest increasing subsequence ending at index i.
2. count[i]: Number of longest increasing subsequences ending at index i.
/

public class CountLongestIncreasingSubsequence {

    public static int findNumberOfLIS(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;

        int[] dp = new int[n];     // dp[i] = length of LIS ending at i
        int[] count = new int[n];  // count[i] = number of LIS ending at i

        // Step 1: Initialization
        for (int i = 0; i < n; i++) {
            dp[i] = 1;        // every element is LIS of length 1
            count[i] = 1;     // exactly one way initially
        }

        int maxLen = 1;

        // Step 2: Build dp[] and count[]
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {

                if (nums[j] < nums[i]) {

                    // Case 1: Found longer LIS
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    }

                    // Case 2: Found another LIS of same length
                    else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        // Step 3: Sum counts of all LIS with max length
        int totalLIS = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLen) {
                totalLIS += count[i];
            }
        }

        return totalLIS;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 4, 7};
        System.out.println(findNumberOfLIS(nums)); // Output: 2
    }
}

 */