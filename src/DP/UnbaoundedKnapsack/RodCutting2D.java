package DP.UnbaoundedKnapsack;

/*
You are given a rod of length n meters. You can cut the rod into smaller pieces,
and each piece has a price based on its length. Your task is to earn the maximum
revenue that can be obtained by cutting up the rod into smaller pieces.
 */
public class RodCutting2D {
    // Function to find the maximum revenue obtainable by cutting up the rod
    public static int cutRod(int[] prices, int n) {
        // Create a 2D DP array
        int[][] dp = new int[prices.length + 1][n + 1];

        // Build the DP table
        for (int i = 1; i <= prices.length; i++) {
            for (int j = 1; j <= n; j++) {
                if (i <= j) {
                    // Max of not cutting the rod or cutting it
                    dp[i][j] = Math.max(dp[i - 1][j], prices[i - 1] + dp[i][j - i]);
                } else {
                    // If the piece is too long, we can't cut it
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // The maximum revenue will be in the last cell
        return dp[prices.length][n];
    }

    public static void main(String[] args) {
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20}; // Prices for lengths 1 to 8
        int n = 8; // Length of the rod
        int maxRevenue = cutRod(prices, n);
        System.out.println("Maximum Revenue: " + maxRevenue);
    }
}
