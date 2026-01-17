package DP.GridDP;

/*
leetcode 64: Minimum Path Sum
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
Example 2:

Input: grid = [[1,2,3],[4,5,6]]
Output: 12

 */
class MinimumPathSum2D {
    // Function to calculate the minimum sum path in the matrix
    static int minSumPath(int n, int m, int[][] matrix) {
        int dp[][] = new int[n][m];

        // Iterate through the matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0)
                    dp[i][j] = matrix[i][j]; // If we're at the top-left cell, the minimum sum is its value
                else {
                    int up = matrix[i][j];
                    if (i > 0)
                        up += dp[i - 1][j]; // Add the value from above if it's not out of bounds
                    else
                        //can not use Integer.MAX_VALUE + something as it will overflow and give negative value
                        up += (int) Math.pow(10, 9); // Add a large value if out of bounds in the up direction

                    int left = matrix[i][j];
                    if (j > 0)
                        left += dp[i][j - 1]; // Add the value from the left if it's not out of bounds
                    else
                        left += (int) Math.pow(10, 9); // Add a large value if out of bounds in the left direction

                    // Store the minimum of the two possible paths
                    dp[i][j] = Math.min(up, left);
                }
            }
        }

        // The final result is stored in the bottom-right cell of the DP matrix
        return dp[n - 1][m - 1];
    }

    public static void main(String args[]) {
        // Define the matrix
        int matrix[][] = {
                {5, 9, 6},
                {11, 5, 2}
        };

        int n = matrix.length;
        int m = matrix[0].length;

        // Calculate and print the minimum sum path in the matrix
        System.out.println(minSumPath(n, m, matrix));
    }
}

/*
Time Complexity: O(N*M)
Space complexity: O(N*M)
 */

/*
class Solution {

    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[] prev = new int[m];

        for (int i = 0; i < n; i++) {
            int[] curr = new int[m];

            for (int j = 0; j < m; j++) {

                if (i == 0 && j == 0) {
                    curr[j] = grid[i][j];
                } else {
                    int up = grid[i][j];
                    if (i > 0) up += prev[j];
                    else up = (int) 1e9;

                    int left = grid[i][j];
                    if (j > 0) left += curr[j - 1];
                    else left = (int) 1e9;

                    curr[j] = Math.min(up, left);
                }
            }
            prev = curr;   // shift row
        }

        return prev[m - 1];
    }
}

 */