package DP.LCS;

/*
Given two strings, str1 and str2, return the number of times str2 appears in str1 as a subsequence.
Let’s assume that you have two strings as follows:
str1 = "bbagbag"
str2 = "bag"
Given two strings s and t, return the number of distinct subsequences of s which equals t.

The test cases are generated so that the answer fits on a 32-bit signed integer.

Example 1:

Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit


 */
public class DistinctSubsequences2D {
    public static int numDistinct(String S, String T) {
        int m = S.length();
        int n = T.length();
        int[][] dp = new int[m + 1][n + 1];

        // Base case: An empty T is a subsequence of any S
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        // Fill the dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    //use the character from S or skip it
                    //we cant skip character from T as we are trying to form T from S
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    //if does not match, skip the character from S
                    //IMP - can not skip character from T as we are trying to form T from S
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
//        String S = "rabbbit";
//        String T = "rabbit";
        String S = "babgbag";
        String T = "bag";
        System.out.println("Number of distinct subsequences: " + numDistinct(S, T));
    }
}
/*
Because subsequences are formed by deleting characters from the source string,
and skipping is how we model deletion in DP

 */
