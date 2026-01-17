package DP.UnbaoundedKnapsack;

/*
Given a ribbon of length n and a set of possible sizes,
cut the ribbon in sizes such that n is achieved with the
maximum number of pieces.
 */
//public class RibbonCutting2D {
//    public static int maxPieces(int n, int[] sizes) {
//        int m = sizes.length;
//        // Create a 2D DP array
//        int[][] dp = new int[n + 1][m + 1];
//        // Initialize the DP array
//        for (int i = 0; i <= n; i++) {
//            dp[i][0] = 0; // No sizes available
//        }
//        for (int j = 0; j <= m; j++) {
//            dp[0][j] = 0; // Length 0
//        }
//        // Fill the DP array
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= m; j++) {
//                // Not taking the current size
//                dp[i][j] = dp[i][j - 1];
//                // Taking the current size if it fits
//                if (i >= sizes[j - 1]) {
//                    dp[i][j] = Math.max(dp[i][j], dp[i - sizes[j - 1]][j] + 1);
//                }
//            }
//        }
//        return dp[n][m]; // Return the maximum pieces for length n
//    }
//
//    public static void main(String[] args) {
//        int n = 7;//5; // Length of the ribbon
//        //int[] sizes = {1, 2, 3}; // Possible sizes
//        int[] sizes = {5, 2, 3};
//        System.out.println("Maximum pieces: " + maxPieces(n, sizes)); // Output the result
//    }
//}

public class RibbonCutting2D {
    public static int maxPieces(int n, int[] sizes) {
        int m = sizes.length;
        // Create a 2D DP array
        int[][] dp = new int[m + 1][n + 1];

        // Initialize the DP array
        for (int j = 0; j <= n; j++) {
            dp[0][j] = 0; // No sizes available
        }
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0; // Length 0
        }

        // Fill the DP array
        for (int i = 1; i <= m; i++) { // Loop over sizes
            for (int j = 1; j <= n; j++) { // Loop over lengths
                // Not taking the current size
                dp[i][j] = dp[i - 1][j];
                // Taking the current size if it fits
                if (j >= sizes[i - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - sizes[i - 1]] + 1);
                }
            }
        }
        //print dp array
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }
        return dp[m][n]; // Return the maximum pieces for length n
    }

    public static void main(String[] args) {
        int n = 7; // Length of the ribbon
        int[] sizes = {5, 2, 3}; // Possible sizes
        System.out.println("Maximum pieces: " + maxPieces(n, sizes)); // Output the result
    }
}