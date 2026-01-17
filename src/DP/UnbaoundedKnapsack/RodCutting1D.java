package DP.UnbaoundedKnapsack;

/*
leetcode Problem: Rod Cutting Problem max revenue (1D DP)
You are given a rod of length n meters. You can cut the rod into smaller pieces,
and each piece has a price based on its length. Your task is to earn the maximum
revenue that can be obtained by cutting up the rod into smaller pieces.
example:
Input: prices = [1, 5, 8, 9, 10, 17, 17, 20], n = 8
Output: 22
Explanation: The maximum revenue can be obtained by cutting the rod into two pieces of lengths 2 and 6,
which gives a total revenue of 5 + 17 = 22.
 */
public class RodCutting1D {
    // Function to find the maximum revenue obtainable by cutting up the rod
    public static int cutRod(int[] prices, int n) {
        // Create a 1D DP array
        int[] dp = new int[n + 1];

        // Build the DP array
        for (int i = 1; i <= prices.length; i++) {
            for (int j = i; j <= n; j++) {
                // Max of not cutting the rod or cutting it
                dp[j] = Math.max(dp[j], prices[i - 1] + dp[j - i]);
            }
        }

        // The maximum revenue will be in the last cell
        return dp[n];
    }

    public static void main(String[] args) {
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20}; // Prices for lengths 1 to 8
        int n = 8; // Length of the rod
        int maxRevenue = cutRod(prices, n);
        System.out.println("Maximum Revenue: " + maxRevenue);
    }
}
