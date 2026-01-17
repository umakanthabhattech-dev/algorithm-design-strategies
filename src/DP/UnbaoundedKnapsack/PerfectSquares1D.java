package DP.UnbaoundedKnapsack;

/*
Leetcode Problem 279: Perfect Squares
Given an integer n, return the least number of perfect square numbers that sum to n.
A perfect square is an integer that is the square of an integer; in other words,
it is the product of some integer with itself. For example, 1, 4, 9,
and 16 are perfect squares while 3 and 11 are not
Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
 */
public class PerfectSquares1D {

    public static int minPerfectSquares(int n) {
        // Step 1: Initialize the DP array
        int[] dp = new int[n + 1];

        // Step 2: Fill the DP array
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE; // Initialize with a large value
            for (int j = 1; j * j <= i; j++) {
                int square = j * j;
                dp[i] = Math.min(dp[i], dp[i - square] + 1);
            }
        }

        // Step 3: Return the result
        return dp[n] == Integer.MAX_VALUE ? -1 : dp[n];
    }

    public static void main(String[] args) {
        int n = 12; // Example input
        int result = minPerfectSquares(n);
        System.out.println("Minimum number of perfect squares for " + n + " is: " + result);
    }
}
