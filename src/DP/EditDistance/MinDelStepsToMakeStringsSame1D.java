package DP.EditDistance;

/*
Leetcode 583.Delete Operation for Two Strings:
Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
In one step, you can delete exactly one character in either string.
Example 1:
Input: word1 = "sea", word2 = "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea"
and another step to make "eat" to "ea".
 */
public class MinDelStepsToMakeStringsSame1D {

    public static void main(String[] args) {
        String word1 = "sea";
        String word2 = "eat";
        System.out.println(minDistance(word1, word2)); // Output: 2
    }

    public static int minDistance(String word1, String word2) {
        int lcsLength = lcs(word1, word2);
        return (word1.length() - lcsLength) + (word2.length() - lcsLength);
    }

    private static int lcs(String x, String y) {
        int m = x.length();
        int n = y.length();
        int[] dp = new int[n + 1];

        // Build the LCS table using a single array
        for (int i = 1; i <= m; i++) {
            int prev = 0; // To store the value of dp[i-1][j-1]
            for (int j = 1; j <= n; j++) {
                int temp = dp[j]; // Store current dp[j] value
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    dp[j] = prev + 1; // Update dp[j] for match
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]); // Update dp[j] for no match
                }
                prev = temp; // Update prev for the next iteration
            }
        }

        return dp[n]; // The length of LCS is in dp[n]
    }
}

