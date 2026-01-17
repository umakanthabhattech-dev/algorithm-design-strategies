package DP.ZeroOneKnapsack;

/*
416:partition equal subset sum
Given a set of positive numbers arr and a value total, determine if there
exists a subset in the given set whose sum is equal to total.
A subset can be an empty set, or it can either consist of some
elements of the set or all the elements of the set.

Let’s say you are given a set = {1, 2, 3, 7} and a total = 6.
The output will be TRUE as the subset = {1, 2, 3} adds up to make the desired total (1+2+3) = 6.
 */
public class SubsetSum1D {
    public static boolean isSubsetSum(int[] arr, int total) {
        int n = arr.length;
        boolean[] dp = new boolean[total + 1];

        // A sum of 0 can always be formed with an empty subset
        dp[0] = true;

        // Fill the dp array
        for (int i = 0; i < n; i++) {
            // Traverse backwards to avoid using the same element more than once
            for (int j = total; j >= arr[i]; j--) {
                //dp[j] = excluding || including(current+previous current by default true here check previous)
                dp[j] = dp[j] || dp[j - arr[i]];
            }
        }

        return dp[total];
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