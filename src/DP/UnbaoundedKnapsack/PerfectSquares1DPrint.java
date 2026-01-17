package DP.UnbaoundedKnapsack;

public class PerfectSquares1DPrint {

    public static int minPerfectSquares(int n) {
        // Step 1: Initialize the DP array
        int[] dp = new int[n + 1];
        int[] lastSquare = new int[n + 1]; // To track the last perfect square used

        // Step 2: Fill the DP array
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE; // Initialize with a large value
            for (int j = 1; j * j <= i; j++) {
                int square = j * j;
                if (dp[i] > dp[i - square] + 1) {
                    dp[i] = dp[i - square] + 1;
                    lastSquare[i] = square; // Track the last perfect square used
                }
            }
            // Display the DP table after processing each i
            displayDPTable(dp, i);
        }

        // Step 3: Print the perfect squares used
        printPerfectSquares(lastSquare, n);

        // Return the result
        return dp[n] == Integer.MAX_VALUE ? -1 : dp[n];
    }

    private static void displayDPTable(int[] dp, int currentIndex) {
        System.out.print("DP Table after processing " + currentIndex + ": ");
        for (int k = 0; k <= currentIndex; k++) {
            if (dp[k] == Integer.MAX_VALUE) {
                System.out.print("∞ "); // Display infinity for unachievable sums
            } else {
                System.out.print(dp[k] + " ");
            }
        }
        System.out.println(); // New line for better readability
    }

    private static void printPerfectSquares(int[] lastSquare, int n) {
        System.out.print("Perfect squares used to form " + n + ": ");
        while (n > 0) {
            if (lastSquare[n] == 0) break; // No perfect square used
            System.out.print(lastSquare[n] + " ");
            n -= lastSquare[n]; // Reduce n by the last perfect square used
        }
        System.out.println(); // New line for better readability
    }

    public static void main(String[] args) {
        int n = 12; // Example input
        int result = minPerfectSquares(n);
        System.out.println("Minimum number of perfect squares for " + n + " is: " + result);
    }
}
