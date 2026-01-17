package DP.FibonacciSequence;

// Importing required functions

import java.util.Arrays;
import java.util.stream.Stream;

/*
leetcode 377. Combination Sum IV
Given a fixed list of numbers, [1, 3, 4], and a target number,
n, count all the possible ways in which n can be expressed as the sum of the given numbers.
If there is no possible way to represent n using the provided numbers, return 0.
Note: You may assume that you can use a specific number as many times as you want.
Additionally, the order in which we select numbers from the list is significant.
If n=4 using the numbers 1, 3, and 4, the target number can be expressed in the following ways:
1+1+1+1=4
1+3=4
3+1=4
4=4

 */
class NumberFactors {
    // Available numbers are 1, 3, and 4
    public static long countWays(int n) {
        // Initializing our solution vector
        long[] dp = new long[n + 1];
        Arrays.fill(dp, -1);

        // Each vector index holds the number of ways to
        // reach a number equal to the index
        dp[0] = 1;

        // Variables to store sub-target numbers
        long n4, n3, n1 = 0;

        // Iteratively calculate the number of ways to reach a
        // target number and store it in the solutions' array
        for (int r = 1; r <= n; r++) {
            // Return 0 if index is less than 0, otherwise
            // set to array value
            n1 = r - 1 < 0 ? 0 : dp[r - 1];
            n3 = r - 3 < 0 ? 0 : dp[r - 3];
            n4 = r - 4 < 0 ? 0 : dp[r - 4];

            // Using our recurrence relation to calculate new answers
            dp[r] = n1 + n3 + n4;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int[] targetNumbers = {3, 5, 10, 25, 0};

        // You can uncomment the lines below and check how this bottom-up solution executes without a time-out

        // targetNumbers = Arrays.copyOf(targetNumbers, targetNumbers.length + 1);
        // targetNumbers[targetNumbers.length - 1] = 50;

        for (int i = 0; i<targetNumbers.length; i++) {
            System.out.println(i + 1 + ".\tn: " + targetNumbers[i] +
                    "\n\n\tNumber of ways to reach the target number: " + countWays(targetNumbers[i]));
            Stream.generate(() -> "-").limit(100).forEach(System.out::print);
            System.out.println(" ");
        }
    }
}
/*
top down
private static long countWaysRec(int n, long[] memo) {
      if(n < 0) return 0;

    if (memo[n] != -1)
      return memo[n];

    return memo[n] =
        countWaysRec(n - 1, memo) + countWaysRec(n - 3, memo) + countWaysRec(n - 4, memo);
  }

  public static long countWays(int n) {

    long[] memo = new long[n + 1];
    Arrays.fill(memo, -1);

    //1 way to get to the number 0
    memo[0] = 1;

    return countWaysRec(n, memo);
  }
 */
/*
Another bottum up 1D
 public static int countWays(int[] numbers, int target) {
        // Create a DP array to store the number of ways to form sums
        int[] dp = new int[target + 1];
        // There is one way to make the sum 0: by choosing no elements
        dp[0] = 1;

        // Fill the DP array
        for (int num : numbers) {
            for (int j = 0; j <= target; j++) {
                if (j >= num) {
                    dp[j] += dp[j - num];
                }
            }
        }

        // The number of ways to form the target n
        return dp[target];
    }

2D
public static int countWays(int[] numbers, int target) {
        int[][] dp = new int[numbers.length + 1][target + 1];
        dp[0][0] = 1; // One way to make sum 0 with 0 elements

        for (int i = 1; i <= numbers.length; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j]; // Exclude the current number
                if (j >= numbers[i - 1]) {
                    dp[i][j] += dp[i][j - numbers[i - 1]]; // Include the current number
                }
            }
        }

        return dp[numbers.length][target];
    }

 */
