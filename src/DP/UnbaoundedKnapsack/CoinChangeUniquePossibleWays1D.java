package DP.UnbaoundedKnapsack;

/*
leetcode problem link: https://leetcode.com/problems/coin-change-2/

518. Coin Change II - unique ways (1D DP)
You are given an integer array coins representing coins of different denominations
and an integer amount representing a total amount of money.
Return the number of combinations that make up that amount.
If that amount of money cannot be made up by any combination of the coins, return 0.
You may assume that you have an infinite number of each kind of coin.
example:
Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
 */
public class CoinChangeUniquePossibleWays1D {
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];

        // Base case: There is one way to make change for 0
        dp[0] = 1;

        // Fill the dp array
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                //As need to consider unique ways dont add 1 to dp[j - coin]+1 as coin exchange1 problem
                dp[j] += dp[j - coin];
            }
        }

        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 5;
        int result = coinChange(coins, amount);
        System.out.println("Total unique ways to make change: " + result);
    }
}
