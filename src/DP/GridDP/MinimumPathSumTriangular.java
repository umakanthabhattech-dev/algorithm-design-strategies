package DP.GridDP;


/*
leetcode 120: Minimum Path Sum in Triangular Array
You are given a triangular array/list 'TRIANGLE'. Your task is to return the minimum path sum to reach from the top to the bottom row.
The triangle array will have N rows and the i-th row, where 0 <= i < N will have i + 1 elements.
You can move only to the adjacent number of row below each step. For example, if you are at index j in row i,
then you can move to i or i + 1 index in row j + 1 in each step.
For Example :
If the array given is 'TRIANGLE' = [[1], [2,3], [3,6,7], [8,9,6,1]] the triangle array will look like:

1
2,3
3,6,7
8,9,6,10

For the given triangle array the minimum sum path would be 1->2->3->8. Hence the answer would be 14.
 */
class MinimumPathSumTriangular {
    // Function to find the minimum path sum in the triangle using dynamic programming
    static int minimumPathSum(int[][] triangle, int n) {
        // Create a 2D array to store intermediate results
        int dp[][] = new int[n][n];

        // Initialize the bottom row of dp with the values from the bottom row of the triangle
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle[n - 1][j];
        }

        // Starting from the second to last row, calculate the minimum path sum for each element
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                // Calculate the two possible paths: moving down or moving diagonally
                int down = triangle[i][j] + dp[i + 1][j];
                int diagonal = triangle[i][j] + dp[i + 1][j + 1];

                // Store the minimum of the two paths in dp
                dp[i][j] = Math.min(down, diagonal);
            }
        }

        // The result is stored at the top of dp array
        return dp[0][0];
    }

    public static void main(String args[]) {
        int triangle[][] = {{1},
                {2, 3},
                {3, 6, 7},
                {8, 9, 6, 10}};

        int n = triangle.length;

        // Call the minimumPathSum function and print the result
        System.out.println(minimumPathSum(triangle, n));
    }
}

/*
Time Complexity: O(N*N)
Space Complexity: O(N*N)
 */
