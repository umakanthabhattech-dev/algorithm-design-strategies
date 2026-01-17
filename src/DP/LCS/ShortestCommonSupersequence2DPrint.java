package DP.LCS;

/*
Given two strings, str1 and str2, find the length of the shortest string that has both the input
strings as subsequences.
Let’s assume that we have two strings as follows:
str1 = "yabc"
str2 = "xabc"
There can be multiple strings that have str1 and str2 as subsequences,
for example, "xyaabcc" and "xyabbc", but the shortest string that has
these input strings as subsequences is "xyabc".
Please note that the sequence of alphabets in the string can be altered.
Also print the chars in the result

 */

public class ShortestCommonSupersequence2DPrint {

        // Function to return the Shortest Common Supersequence of X and Y
        public static String shortestCommonSupersequence(String X, String Y) {
            int m = X.length();
            int n = Y.length();

            // Step 1: Build LCS DP table
            int[][] dp = new int[m + 1][n + 1];

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }

            // Step 2: Backtrack to build SCS
            StringBuilder sb = new StringBuilder();
            int i = m, j = n;

            while (i > 0 && j > 0) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    sb.append(X.charAt(i - 1));
                    i--;
                    j--;
                } else if (dp[i - 1][j] > dp[i][j - 1]) {
                    sb.append(X.charAt(i - 1));
                    i--;
                } else {
                    sb.append(Y.charAt(j - 1));
                    j--;
                }
            }

            // Add remaining characters from X
            while (i > 0) {
                sb.append(X.charAt(i - 1));
                i--;
            }

            // Add remaining characters from Y
            while (j > 0) {
                sb.append(Y.charAt(j - 1));
                j--;
            }

            // Reverse the result
            return sb.reverse().toString();
        }

        public static void main(String[] args) {
            String X = "abac";
            String Y = "cab";

            String scs = shortestCommonSupersequence(X, Y);
            System.out.println("Shortest Common Supersequence: " + scs);
        }
    }
/*time Complexity: O(m*n)
Space Complexity: O(m*n)
 */
/*
Solution Explanation:
1. We first build a 2D DP table to find the length of the Longest Common Subsequence (LCS)
   between the two strings.
2. We then backtrack through the DP table to construct the Shortest Common Supersequence (SCS).
SCS length = m + n − LCS length

X = abac
Y = cab

X =   ab ac
Y = c ab

common sequence = ab
supersequence
cabac → reversed → "cabac"


If characters match :
You go diagonally up-left (i--, j--) → meaning these characters are part of the LCS → include once.

✔️ If characters don’t match :
You choose the direction from where the DP value came:

If dp[i-1][j] > dp[i][j-1], go up (X is not incuded in optimal but to print take from X)

Else go left (take from Y)

 */