package DP.PalindromicSubsequence;

/*
Leetcode problem 516: Longest Palindromic Subsequence
Given a string s, find the longest palindromic subsequence's length in s.
Example 1:

Input: s = "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb"
 */
//public class LongestPalindromicSubsequence {
//    public static int longestPalindromicSubseq(String s) {
//        int n = s.length();
//        int[][] dp = new int[n][n];
//
//        // Every single character is a palindrome of length 1
//        for (int i = 0; i < n; i++) {
//            dp[i][i] = 1;
//        }
//
//        // Build the table
//        for (int length = 2; length <= n; length++) {
//            for (int i = 0; i < n - length + 1; i++) {
//                int j = i + length - 1;
//                if (s.charAt(i) == s.charAt(j) && length == 2) {
//                    dp[i][j] = 2;
//                } else if (s.charAt(i) == s.charAt(j)) {
//                    dp[i][j] = dp[i + 1][j - 1] + 2;
//                } else {
//                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
//                }
//            }
//        }
//
//        return dp[0][n - 1]; // The length of the longest palindromic subsequence
//    }
//
//    public static void main(String[] args) {
//        String s = "BBABCBCAB";
//        System.out.println("Length of LPS is: " + longestPalindromicSubseq(s)); // Output: 7
//    }
//}


public class LongestPalindromicSubsequence {

    public static int longestPalindromeSubseq(String s) {

        // Step 1: Original string length
        int n = s.length();

        // Step 2: Reverse the string
        String rev = new StringBuilder(s).reverse().toString();

        // Step 3: DP array for LCS
        // dp[i][j] = LCS length of s[0..i-1] and rev[0..j-1]
        int[][] dp = new int[n + 1][n + 1];

        // Step 4: Fill DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                // If characters match
                if (s.charAt(i - 1) == rev.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }
                // If characters don't match
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Step 5: Final answer
        return dp[n][n];
    }

    public static void main(String[] args) {
        //String s = "bbab";
        String s = "BBABCBCAB";
        System.out.println(longestPalindromeSubseq(s)); // Output: 4
    }
}


