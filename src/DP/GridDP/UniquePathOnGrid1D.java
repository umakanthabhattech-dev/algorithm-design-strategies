package DP.GridDP;

/*
Leetcode number: 62
There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot
can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109
 */
public class UniquePathOnGrid1D {
    public int uniquePaths(int m, int n) {
        // Create a 1D array to store the number of unique paths for the current row
        int[] dp = new int[n];

        // Initialize the first row (only one way to reach any cell in the first row)
        for (int j = 0; j < n; j++) {
            dp[j] = 1;
        }

        // Fill the dp array for each row
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1]; // Update the current cell based on the left cell
            }
        }

        // The last element in dp array contains the total number of unique paths
        return dp[n - 1];
    }

    public static void main(String[] args) {
        UniquePathOnGrid1D up = new UniquePathOnGrid1D();
        int m = 3; // Number of rows
        int n = 7; // Number of columns
        System.out.println("Number of unique paths: " + up.uniquePaths(m, n)); // Output: 28
    }
}
/*
Time Complexity: O(M*N)
Space Complexity: O(N)
 */
