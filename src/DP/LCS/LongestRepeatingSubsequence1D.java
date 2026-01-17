package DP.LCS;

/*
Longest Repeating Subsequence
Given a string, you have to find the length of the longest subsequence that occurs at least twice
and respects this
constraint: the characters that are re-used in each subsequence should have distinct indexes.
abbaba O/P = 3 , bbb
aabb O/P=2 ab

 */
public class LongestRepeatingSubsequence1D {
    public static int longestRepeatingSubsequence(String str) {
        int n = str.length();
        int[][] dp = new int[n + 1][n + 1];

        // Fill the dp table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // Check if characters match and indices are different
                if (str.charAt(i - 1) == str.charAt(j - 1) && i != j) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // The length of the longest repeating subsequence
        int length = dp[n][n];
        System.out.println("Length of Longest Repeating Subsequence: " + length);

        // Now, let's print the longest repeating subsequence
        printLRS(dp, str, n, n);
        return length;
    }

    private static void printLRS(int[][] dp, String str, int i, int j) {
        StringBuilder lrs = new StringBuilder();

        // Backtrack to find the LRS
        while (i > 0 && j > 0) {
            // If this character is part of LRS
            if (str.charAt(i - 1) == str.charAt(j - 1) && i != j) {
                lrs.append(str.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        // Since we constructed the LRS backwards, we need to reverse it
        lrs.reverse();
        System.out.println("Longest Repeating Subsequence: " + lrs.toString());
    }

    public static void main(String[] args) {
        //String str = "aabb";
        String str = "aaaaba";
        longestRepeatingSubsequence(str);
    }
}

/*
Time Complexity: O(n^2)
Space Complexity: O(n^2) why? because of dp array
Output:
Length of Longest Repeating Subsequence: 3
Longest Repeating Subsequence: aaa
 */