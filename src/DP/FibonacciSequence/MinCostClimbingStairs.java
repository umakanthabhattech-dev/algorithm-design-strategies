package DP.FibonacciSequence;

/*
Leetcode 746. Min Cost Climbing Stairs
You are given an integer array cost where cost[i] is the cost of ith step on a staircase.
Once you pay the cost, you can either climb one or two steps.
You can either start from the step with index 0, or the step with index 1.
Return the minimum cost to reach the top of the floor.
You can either start from the step with index 0, or the step with index 1.

Return the minimum cost to reach the top of the floor.

Example 1:

Input: cost = [10,15,20]
Output: 15
Explanation: You will start at index 1.
- Pay 15 and climb two steps to reach the top.
The total cost is 15.
Example 2:
Input: cost = [1,100,1,1,1,100,1,1,100,1]
Output: 6
Explanation: You will start at index 0.
- Pay 1 and climb two steps to reach index 2.
- Pay 1 and climb two steps to reach index 4.
- Pay 1 and climb two steps to reach index 6.
- Pay 1 and climb one step to reach index 7.
- Pay 1 and climb two steps to reach index 9.
- Pay 1 and climb one step to reach the top.
The total cost is 6.
 */
public class MinCostClimbingStairs {
    public int minCost(int[] cost) {
        int n = cost.length;
        // Create an array to store the minimum cost to reach each step
        int[] dp = new int[n + 1];

        // Base cases
        dp[0] = 0; // Cost to reach the ground (before the first step)
        dp[1] = 0; // Cost to reach the first step (can start here)

        // Fill the dp array
        for (int i = 2; i <= n; i++) {
            //cost of reaching i-1 th step + cost of i-1 th step to go to ith step
            //or cost of reaching i-2 th step + cost of i-2 th step to go to ith step
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }

        // The minimum cost to reach the top is stored in dp[n]
        return dp[n];
    }
    public static void main (String args[]) {
        MinCostClimbingStairs stairs = new MinCostClimbingStairs();
        int[] cost = {1,100,1,1,1,100,1,1,100,1};
        System.out.println("result = " + stairs.minCost(cost));
    }
}
//time O(n) space O(n)
/*
bottom up
public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        // Create a memoization array to store the minimum cost for each step
        Integer[] memo = new Integer[n + 1];
        return Math.min(minCost(n - 1, cost, memo), minCost(n - 2, cost, memo));
    }

    private int minCost(int i, int[] cost, Integer[] memo) {
        // Base case: if we reach the ground (step 0), cost is 0
        if (i < 0) return 0;
        // If the cost for this step has already been computed, return it
        if (memo[i] != null) return memo[i];

        // Calculate the minimum cost to reach the current step
        memo[i] = cost[i] + Math.min(minCost(i - 1, cost, memo), minCost(i - 2, cost, memo));
        return memo[i];
    }
 */
