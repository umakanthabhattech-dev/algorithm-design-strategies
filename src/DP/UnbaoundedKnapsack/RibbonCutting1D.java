package DP.UnbaoundedKnapsack;

/*
Ribbon Cutting Problem
Given a ribbon of length n and a set of possible sizes,
cut the ribbon in sizes such that n is achieved with the
maximum number of pieces.
Example:
Input: n = 5, sizes = {1, 2, 3}
Output: 5 (1+1+1+1+1)
Explanation: The ribbon can be cut into five pieces of size 1.
Approach:
We can use a dynamic programming approach similar to the unbounded knapsack problem.
We create a DP array where dp[i] represents the maximum number of pieces
that can be obtained for a ribbon of length i. We iterate through all lengths from
1 to n and for each length, we check each size in the sizes array.
If the current length is greater than or equal to the size,
we update the DP array by taking the maximum of the current value and the value

 */
public class RibbonCutting1D {
    public static int maxPieces(int n, int[] sizes) {
        // Create a DP array initialized to 0
        int[] dp = new int[n + 1];
        dp[0] = 0; // Base case: 0 pieces for length 0

        // Fill the DP array
        for (int i = 1; i <= n; i++) {
            for (int size : sizes) {
                if (i >= size) {
                    dp[i] = Math.max(dp[i], dp[i - size] + 1);
                }
            }
        }

        return dp[n]; // Return the maximum pieces for length n
    }

    public static void main(String[] args) {
        int n = 5; // Length of the ribbon
        int[] sizes = {1, 2, 3}; // Possible sizes
        System.out.println("Maximum pieces: " + maxPieces(n, sizes)); // Output the result
    }
}
