package DP.FibonacciSequence;


import java.util.Arrays;

/*
Suppose there is a game where a player can score either 1, 2, 4 points in each turn.
Given a total score, n, find all the possible ways in which you can score these n points.

Note: You may assume that you can use a specific score as many times as you want.
Additionally, the order in which we select scores from the list is significant.
Let's say the total points to be earned are 3. A player can score this total in the following three ways:

1, 1, and 1, in three turns: 1 + 1 + 1 = 3.
1 and then a 2, in two turns: 1 + 2 = 3.
2 and then a 1, in two turns: 2 + 1 = 3.
similar to Combination Sum IV leetcode number is 377
 */
class GameScoring {
    // Scoring options are 1, 2, and 4
    public static long scoringOptions(int n) {
        // Initializing our solution array
        long[] dp = new long[n + 1];
        Arrays.fill(dp, -1);

        // Each array index holds the number of ways to
        // reach a score equal to the index
        dp[0] = 1;

        // Varaibles to store scores
        long s4, s2, s1 = 0;

        // Iteratively calculate the number of ways to reach a
        // score and store it into the solutions array
        for (int r = 1; r <= n; r++) {
            // Return 0 if index is less than 0, otherwise
            // set to array value
            s1 = r - 1 < 0 ? 0 : dp[r - 1];
            s2 = r - 2 < 0 ? 0 : dp[r - 2];
            s4 = r - 4 < 0 ? 0 : dp[r - 4];

            // Using our recurrence relation to calculate new answers
            dp[r] = s1 + s2 + s4;
        }
        return dp[n];
    }
    //we use dp[r - 1] + dp[r - 2] + dp[r - 4] as recurrence relation
    // dp[r] = number of ways to reach score r
    // dp[r - 1] = number of ways to reach score r-1,
    // dp[r - 2] = number of ways to reach score r-2,
    // dp[r - 4] = number of ways to reach score r-4
    // By adding 1, 2, or 4 to these previous scores respectively,
    // we can reach the current score r.
    // we deduct index by 1,2,4 because those are the scoring options available
    //scoring options are 1,2,4

    // Driver code
    public static void main(String[] args) {
        int[] totalScores = {3, 5, 10, 25, 0};

        // You can uncomment the lines below and check how this bottom-up solution executes without a time-out

        // totalScores = Arrays.copyOf(totalScores, totalScores.length + 1);
        // totalScores[totalScores.length - 1] = 50;

        for (int i = 0; i < totalScores.length; i++) {
            System.out.println((i + 1) + ".\tn: " + totalScores[i] +
                    "\n\n\tNumber of ways to reach the score: " + scoringOptions(totalScores[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
//time O(n) and space O(n)

/*
top down
private static long scoringOptionsRec(int n, long[] memo) {
		// We can not get a negative score, we return 0 for negative values
    	if (n < 0) return 0;

		// Check if a solution already exists in the array
		if (memo[n] != -1) return memo[n];

		// Else, we recursively calculate the solution for the
		// given value and store it in our solution array
		return memo[n] =
				scoringOptionsRec(n - 1, memo) + scoringOptionsRec(n - 2, memo) + scoringOptionsRec(n - 4, memo);
	}

	// Scoring options are 1, 2, and 4
	public static long scoringOptions(int n) {
		// Initializing our solution array
		long[] memo = new long[n + 1];
		Arrays.fill(memo, -1);

		// Set up the base case, 1 way to score 0
		memo[0] = 1;

		// Pass our array to the helper function
		return scoringOptionsRec(n, memo);
	}
 */
/*
bottom up 2
2D
 public int countWays(int n) {
        // Define the possible scores
        int[] scores = {1, 2, 4};
        int m = scores.length;

        // Create a DP table with (m+1) x (n+1)
        int[][] dp = new int[m + 1][n + 1];

        // Base case: There is one way to score 0 points (by not scoring)
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // Include the current score
                if (j >= scores[i - 1]) {
                    dp[i][j] = dp[i][j - scores[i - 1]];
                }
                // Exclude the current score
                dp[i][j] += dp[i - 1][j];
            }
        }

        // The answer is in dp[m][n]
        return dp[m][n];
    }
1D
public int countWays(int n) {
        // Define the possible scores
        int[] scores = {1, 2, 4};

        // Create a 1D DP array
        int[] dp = new int[n + 1];

        // Base case: There is one way to score 0 points (by not scoring)
        dp[0] = 1;

        // Fill the DP array
        for (int score : scores) {
            for (int j = score; j <= n; j++) {
                dp[j] += dp[j - score];
            }
            //if  score greater than j then existing value persist
        }

        // The answer is in dp[n]
        return dp[n];
    }


 */