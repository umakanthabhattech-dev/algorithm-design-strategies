package DP.LCS;

/*
Leetcode 1143
Suppose you are given two strings.
You need to find the length of the longest common subsequence between these two strings.
If there is no common subsequence, then return 0.
Let’s say you have the following two strings:
“cloud”
“found”
The longest common subsequence between these two strings is “oud”, which has a length of 3
Also tarce the chars
 */
public class LongestCommonSubsequence1DPrint {
    public static String lcs(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // Create a 1D array to store lengths of longest common subsequence.
        int[] dp = new int[n + 1];

        // Build the dp array in bottom-up fashion
        for (int i = 1; i <= m; i++) {
            int prev = 0; // To store the value of dp[i-1][j-1]
            for (int j = 1; j <= n; j++) {
                int temp = dp[j]; // Store current dp[j] value
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[j] = prev + 1; // Characters match
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]); // Characters do not match
                }
                prev = temp; // Update prev for the next iteration
            }
        }

        // The length of the longest common subsequence
        int length = dp[n];

        // Reconstruct the LCS string
        StringBuilder lcsString = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcsString.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[j] == dp[j - 1]) {
                j--;
            } else {
                i--;
            }
        }


        // Since we built the LCS string backwards, reverse it
        return lcsString.reverse().toString();
    }

    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";
        String lcsResult = lcs(s1, s2);
        System.out.println("Length of LCS is " + lcsResult.length()); // Output: 4
        System.out.println("LCS is " + lcsResult); // Output: GTAB
    }
}
/*
time complexity of O(m * n) and a space complexity of O(n).
m=size of s1
n=size of s2
 */

