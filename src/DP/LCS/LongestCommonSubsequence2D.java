package DP.LCS;

/*
Suppose you are given two strings.
You need to find the length of the longest common subsequence between these two strings.
If there is no common subsequence, then return 0.
Let’s say you have the following two strings:
“cloud”
“found”
The longest common subsequence between these two strings is “oud”, which has a length of 3
 */
public class LongestCommonSubsequence2D {
    public static int lcs(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // Create a 2D array to store lengths of longest common subsequence.
        int[][] dp = new int[m + 1][n + 1];

        // Build the dp array in bottom-up fashion
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1; // Characters match
                } else {
                    /*
                     skip one character either from s1 or s2
                     take max of both possibilities
                     skip both because we are looking for common subsequence
                     IMP - we can skip characters from both strings as we are looking for subsequence
                     in contrast to distinct subsequence problem where we could only skip from one string
                     because we were trying to form one string from another.
                     */
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // Characters do not match
                }
            }
        }
        // Step 3: Backtracking to get LCS characters
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;

        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                // Character is part of LCS
                lcs.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;   // Move up
            } else {
                j--;   // Move left
            }
        }

        // The length of the longest common subsequence will be in dp[m][n]
        return dp[m][n];
    }

    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";
        System.out.println("Length of LCS is " + lcs(s1, s2)); // Output: 4
    }
}
/*
*
Printing the LCS is just backtracking from that table.
Start from:
i = m, j = n
Characters match
if A[i-1] == B[j-1]
This character is part of LCS
Add it to result
* Move diagonally
* i--, j--
If characters don’t match
* Check which direction to move :Move in the direction of the larger DP value:
* If dp[i-1][j] > dp[i][j-1]
* Move up
* i--
* Else
* Move left
* j--
* End when either i == 0 or j == 0
*
 */
