package DP.UnbaoundedKnapsack;

/*
Leetcode Problem 322: Coin Change
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
public class CoinChange1D {
    public static int coinChange(int[] coins, int amount) {
        // Initialize the dp array with a value larger than any possible number of coins
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE; // Set to a large value
        }
        dp[0] = 0; // Base case: 0 coins are needed to make amount 0

        // Fill the dp array
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // If dp[amount] is still Integer.MAX_VALUE, return -1 (not possible to form that amount)
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        int result = coinChange(coins, amount);
        System.out.println(result); // Output: 3 (11 = 5 + 5 + 1)
    }
}

