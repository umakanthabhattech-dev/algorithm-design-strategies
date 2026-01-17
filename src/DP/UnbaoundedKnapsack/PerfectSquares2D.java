package DP.UnbaoundedKnapsack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given an integer n, return the least number of perfect square numbers that sum to n.
A perfect square is an integer that is the square of an integer; in other words,
it is the product of some integer with itself. For example, 1, 4, 9,
and 16 are perfect squares while 3 and 11 are not
Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
 */
public class PerfectSquares2D {

    public static int minPerfectSquares(int n) {
        // Step 1: Generate all perfect squares less than or equal to n
        List<Integer> squares = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            squares.add(i * i);
        }
        //print squares
        System.out.println("Perfect Squares <= " + n + ": " + squares);

        // Step 2: Initialize the DP array
        int[][] dp = new int[squares.size() + 1][n + 1];

        // Step 3: Fill the DP array
        for (int i = 0; i <= squares.size(); i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = Integer.MAX_VALUE; // No squares available
                } else if (j == 0) {
                    dp[i][j] = 0; // 0 can be formed with 0 squares
                } else {
                    // Exclude the perfect square
                    dp[i][j] = dp[i - 1][j];
                    // Include the perfect square if possible
                    if (j >= squares.get(i - 1)) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j - squares.get(i - 1)] + 1);
                    }
                }
            }
        }
        // Optional: Print the DP table test
        System.out.println("DP Table:");
        for (int i = 0; i <= squares.size(); i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        // Step 4: Return the result
        return dp[squares.size()][n] == Integer.MAX_VALUE ? -1 : dp[squares.size()][n];
    }

    public static void main(String[] args) {
        int n = 13; // Example input
        int result = minPerfectSquares(n);
        System.out.println("Minimum number of perfect squares for " + n + " is: " + result);
    }
}
//in dp[i][j] = Math.min(dp[i][j], dp[i][j - squares.get(i - 1)] + 1);
//we consider two scenarios: excluding the current perfect square (dp[i - 1][j])
// and including it (dp[i][j - squares.get(i - 1)] + 1).
// The +1 accounts for the inclusion of the current perfect square in the sum.
//why dp[i][j - squares.get(i - 1)] + 1 and not dp[i-1][j - squares.get(i - 1)] + 1
// Because we are allowed to use the same perfect square multiple times,
// we refer to dp[i][j - squares.get(i - 1)] to consider the possibility of including
// the current perfect square again in the sum.
/*Because this is an unbounded knapsack: when you include the current perfect square you may include it again.
Using dp[i][j - squares.get(i - 1)] keeps the same row (i) so the item can be reused;
using dp[i - 1][j - squares.get(i - 1)] would force you to move to the previous row and
thus disallow taking the same square more than once (that is the 0/1 knapsack behavior).

Also note a bug risk: adding 1 to Integer.MAX_VALUE will overflow.
Guard the add or use a safe sentinel (for example n + 1). Example fix:
if (j >= squares.get(i - 1) && dp[i][j - squares.get(i - 1)] != Integer.MAX_VALUE) {
    dp[i][j] = Math.min(dp[i][j], dp[i][j - squares.get(i - 1)] + 1);
}*/