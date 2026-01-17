package DP.ZeroOneKnapsack;

import java.util.Arrays;

/*
Suppose you are given an array, nums, containing positive numbers.
You need to partition the array into two arrays such that the absolute
difference between their sums is minimized.
Note: Each element of the nums array should be present in one of the partitioned arrays.
[2, 3, 1]
[2,1],sum=3
[3],sum=3
So, the minimum difference becomes ∣3−3∣=0
 */
public class MinimizeSumDifference2D {
    public static int minimizeDifference(int[] arr) {
        int n = arr.length;
        int totalSum = Arrays.stream(arr).sum();

        // We only need to check up to half of the total sum
        int target = totalSum / 2;

        // DP array where dp[i][j] will be true if sum j is possible with first i elements
        boolean[][] dp = new boolean[n + 1][target + 1];

        // Initialize dp[0][0] = true (0 sum is possible with 0 elements)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                dp[i][j] = dp[i - 1][j]; // Exclude the current element
                if (j >= arr[i - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - arr[i - 1]]; // Include the current element
                }
            }
        }
        // Find the largest j such that dp[n][j] is true
        int closestSum = 0;
        for (int j = target; j >= 0; j--) {
            if (dp[n][j]) {
                closestSum = j;
                break;
            }
        }

        // Calculate the minimum difference
        int sum1 = closestSum;
        int sum2 = totalSum - closestSum;

        return Math.abs(sum1 - sum2);
    }

    public static void main(String[] args) {
        int[] arr = {1, 6, 11, 5};
        System.out.println("The minimum difference is: " + minimizeDifference(arr));
    }
}
// Fill the DP table logic
//boolean dont include current = dp[i-1][j]
//boolean include current = dp[i-1][j-arr[i-1]] both 7-6=1 and 6 both must me true
//dp[i][j] = dont include || include


//Below explaination is to get closestSum above line 41
// This will give us the closest sum to totalSum/2 to minimize the
// difference.we look for the largest sum we can achieve
// that is less than or equal to totalSum/2
//This sum will be one subset, and the other subset
//will be totalSum - this sum
//If we can achieve sum 11, then the other subset
//will also be 11, difference = 0
//If we can achieve sum 10, then the other subset
//will be 12, difference = 2
//So, we want to find the largest achievable sum
//that is less than or equal to target
//time complexity O(N∗S)
//space complexity O(N∗S)
