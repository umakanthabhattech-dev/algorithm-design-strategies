package DP.UnbaoundedKnapsack;

/*
You are given an integer array coins representing coins of different denominations
and an integer amount representing a total amount of money.
Return the number of combinations that make up that amount.
If that amount of money cannot be made up by any combination of the coins, return 0.
You may assume that you have an infinite number of each kind of coin.
Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
 */
public class CoinChangeUniquePossibleWays2D {
    public static int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        // Base case: There is one way to make change for 0
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        // Fill the dp table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i - 1] <= j) {
                    //Add do not choose i + choose i
                    //As u need to find unique ways so dont add +1 in  dp[i][j - coins[i - 1]]+1
                    ////As need to consider unique ways dont add 1 to dp[j - coin]+1 as coin exchange1 problem
                    //we effectively account for all unique combinations that can be formed by
                    // including the current coin. so no need to add 1
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        int result = coinChange(coins, amount);
        System.out.println("Total unique ways to make change: " + result);
    }
}
/*
     0  1  2  3  4  5  6  7  8  9 10 11
0 [  1  0  0  0  0  0  0  0  0  0  0  0 ]
1 [  1  1  1  1  1  1  1  1  1  1  1  1 ]
2 [  1  1  2  2  3  3  4  4  5  5  6  6 ]
3 [  1  1  2  2  3  4  5  6  7  8 10 11 ]
A=amount, C=no of coins
Time complexity O(A*C)
Space O(A*C)
 */
