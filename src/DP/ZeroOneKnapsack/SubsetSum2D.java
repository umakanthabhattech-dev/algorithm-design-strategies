package DP.ZeroOneKnapsack;

/*
leetcode Q416: 0/1 Knapsack - Subset Sum Problem
Given a set of positive numbers arr and a value total, determine if there
exists a subset in the given set whose sum is equal to total.
A subset can be an empty set, or it can either consist of some
elements of the set or all the elements of the set.

Let’s say you are given a set = {1, 2, 3, 7} and a total = 6.
The output will be TRUE as the subset = {1, 2, 3} adds up to make the desired total (1+2+3) = 6.
 */
public class SubsetSum2D {
    public static boolean isSubsetSum(int[] arr, int total) {
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][total + 1];

        // A sum of 0 can always be formed with an empty subset
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Fill the dp array
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= total; j++) {
                if (arr[i - 1] <= j) {
                    // Include the current element or exclude it
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                } else {
                    // Exclude the current element
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println("DP Table:");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= total; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[n][total];
    }

    public static void main(String[] args) {
        int[] arr = {3, 34, 4, 12, 5, 2};
        int total = 9;
        if (isSubsetSum(arr, total)) {
            System.out.println("Found a subset with the given sum.");
        } else {
            System.out.println("No subset with the given sum exists.");
        }
    }
}
