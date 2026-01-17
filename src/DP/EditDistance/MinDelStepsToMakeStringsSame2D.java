package DP.EditDistance;

/*
Delete Operation for Two Strings:
Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
In one step, you can delete exactly one character in either string.
Example 1:
Input: word1 = "sea", word2 = "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea"
and another step to make "eat" to "ea".
 */
public class MinDelStepsToMakeStringsSame2D {

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
        int[][] dp = new int[m + 1][n + 1];

        // Build the LCS table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }
}
