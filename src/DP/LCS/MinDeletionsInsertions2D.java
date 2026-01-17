package DP.LCS;

/*
Leetcode Problem: 583.
Minimum Number of Deletions and Insertions:
Given two strings, str1 and str2, find the minimum number of deletions and
insertions required to transform str1 into str2.
As Transform meaning deletion from str1 to addition to str2 so we use same solution as
our previous MinDelStepToMakeStringsSame problem
just return result differently
Solution:
1. Find the length of the Longest Common Subsequence (LCS) between str1 and str2.
2. The number of deletions required = length of str1 - length of LCS
3. The number of insertions required = length of str2 - length of LCS
Example:
Input: str1 = "heap", str2 = "pea"
Output: Minimum Deletions = 2, Minimum Insertions = 1
Explanation:
LCS of "heap" and "pea" is "ea" with length 2.
Deletions = 4 - 2 = 2 (remove 'h' and 'p' from "heap")
Insertions = 3 - 2 = 1 (insert 'p' to form "pea")
 */
public class MinDeletionsInsertions2D {
    public static int lcs(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        //Get Longest common subsequence
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    public static void minDeletionsAndInsertions(String s1, String s2) {
        int lcsLength = lcs(s1, s2);
        int deletions = s1.length() - lcsLength;
        int insertions = s2.length() - lcsLength;

        System.out.println("Minimum Deletions: " + deletions);
        System.out.println("Minimum Insertions: " + insertions);
    }

    public static void main(String[] args) {
        String str1 = "heap";
        String str2 = "pea";
        minDeletionsAndInsertions(str1, str2);
    }
}

