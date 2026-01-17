package DP.UnbaoundedKnapsack;

import java.util.Arrays;

/*
You are given an integer array coins representing coins of different denominations
and an integer amount representing a total amount of money.
Return the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.
You may assume that you have an infinite number of each kind of coin.
Example 1:
Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
 */

public class CoinChange2D {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5}; // Coin denominations
        int amount = 11; // Amount to make
        int[][] dp = new int[coins.length + 1][amount + 1];

        // Initialize the DP array
        for (int i = 0; i <= coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j == 0) {
                    dp[i][j] = 0; // 0 coins needed to make amount 0
                } else {
                    dp[i][j] = Integer.MAX_VALUE; // Set to infinity for other amounts
                }
            }
        }

        // Fill the DP table
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                // If we do not take the coin
                dp[i][j] = dp[i - 1][j];

                // If we take the coin (only if the coin value is less than or equal to j)
                if (coins[i - 1] <= j) {
                    if (dp[i][j - coins[i - 1]] != Integer.MAX_VALUE) {
                        //Min of (not include , include current ith coin)
                        //dp[i][j - coins[i - 1]]+1 first remove last occurrence of current coin and add it again
                        dp[i][j] = Math.min(dp[i][j], dp[i][j - coins[i - 1]] + 1);
                    }
                }
            }
        }

        // Result
        if (dp[coins.length][amount] == Integer.MAX_VALUE) {
            System.out.println("It's not possible to make the amount " + amount + " with the given coins.");
        } else {
            System.out.println("Minimum coins needed to make amount " + amount + ": " + dp[coins.length][amount]);
        }

        // Optional: Print the DP table
        System.out.println("DP Table:");
        for (int i = 0; i <= coins.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
    }
}
/*
Minimum coins needed to make amount 11: 3
DP Table:
        0	1	2	3	4	5	6	7	8	9	10	11
0       0	∞	∞	∞	∞	∞	∞	∞	∞	∞	∞	∞
1       0	1	2	3	4	5	6	7	8	9	10	11
2       0	1	1	2	2	3	3	4	4	5	5	6
5       0	1	1	2	2	1	2	2	3	3	2	3

Time complexity O(n * m), since we have n iterations for the coins and m iterations for the amounts.
Space complexity O(n * m)  due to 2D array
 */