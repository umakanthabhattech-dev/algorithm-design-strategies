package DP.FibonacciSequence;

/*
leetcode 70: Climbing Stairs
You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
Example 1:
Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:
Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 */
public class ClimbingStairs {
    public int climbStairs(int n) {
        if (n <= 1) return 1; // Base case: 1 way to climb 0 or 1 step

        int[] dp = new int[n + 1]; // Array to store the number of ways to reach each step
        dp[0] = 1; // 1 way to stay at the ground (0 steps)
        dp[1] = 1; // 1 way to climb 1 step

        // Fill the dp array
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2]; // The number of ways to reach step i
        }
        return dp[n]; // Return the number of ways to reach the top
    }
    public static void main(String[] args) {
        ClimbingStairs stairs = new ClimbingStairs();
        int result = stairs.climbStairs(5);
        System.out.println("No of ways to climb = " + result);
    }
}
/*
O(n) time and uses O(n)
 */
/*
Logic is similar to Fibonacci sequence
we can reach nth step from (n-1)th step or (n-2)th step
because from (n-1)th step we can take 1 step and from (n-2)th step we can take 2 steps
So total ways to reach nth step = ways to reach (n-1)th step + ways to reach (n-2)th step
as we can take either 1 step or 2 steps at a time
 */

/*
Top down
private int climb(int n) {
        // Base cases
        if (n <= 1) return 1; // 1 way to climb 0 or 1 step

        // Check if the result is already computed
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        // Recursive calls
        int ways = climb(n - 1) + climb(n - 2);

        // Store the result in the memoization map
        memo.put(n, ways);

        return ways;
    }
 */