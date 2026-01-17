package DP.UnbaoundedKnapsack;

import java.util.ArrayList;
import java.util.List;
public class RibbonCutting1DPrint {
    public static void main(String[] args) {
        int n = 5; // Length of the ribbon
        int[] sizes = {1, 2, 3}; // Possible sizes
        int[] dp = new int[n + 1];
        int[] lastSizeUsed = new int[n + 1];

        // Initialize the DP array
        for (int i = 0; i <= n; i++) {
            dp[i] = -1; // -1 indicates that length i cannot be achieved
        }
        dp[0] = 0; // Base case: 0 pieces for length 0

        // Fill the DP array
        for (int i = 1; i <= n; i++) {
            for (int size : sizes) {
                if (i >= size && dp[i - size] != -1) {
                    if (dp[i] < dp[i - size] + 1) {
                        dp[i] = dp[i - size] + 1;
                        lastSizeUsed[i] = size; // Store the size used
                    }
                }
            }
        }

        // The maximum pieces for length n
        int maxPieces = dp[n];

        // Print the result
        if (maxPieces == -1) {
            System.out.println("Cannot cut the ribbon into the desired lengths.");
        } else {
            System.out.println("Maximum pieces: " + maxPieces);
            System.out.print("Sizes used: ");
            printSizesUsed(lastSizeUsed, n);
        }
    }

    private static void printSizesUsed(int[] lastSizeUsed, int n) {
        List<Integer> sizesUsed = new ArrayList<>();
        while (n > 0) {
            int size = lastSizeUsed[n];
            sizesUsed.add(size);
            n -= size;
        }
        System.out.println(sizesUsed);
    }
}