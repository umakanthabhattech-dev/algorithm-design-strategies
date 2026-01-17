package DP.StringPartition;

/*
Leetcode Problem 132: Palindrome Partitioning II
Given a string s, partition s such that every
substring of the partition is a palindrome
Return the minimum cuts needed for a palindrome partitioning of s.
 */
public class PalindromePartitioningMinCuts1D {
    public int minCut(String s) {
        int n = s.length();
        if (n == 0) return 0;

        // dp[i] = minimum cuts needed for substring s[0...i]
        int[] dp = new int[n];
        // isPalindrome[i][j] = true if s[i...j] is a palindrome
        boolean[][] isPalindrome = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            int minCuts = i; // max cuts possible (cut every character)
            for (int j = 0; j <= i; j++) {
                if (s.charAt(j) == s.charAt(i) && (i - j <= 2 || isPalindrome[j + 1][i - 1])) {
                    isPalindrome[j][i] = true; // Update palindrome table
                    minCuts = (j == 0) ? 0 : Math.min(minCuts, dp[j - 1] + 1);
                }
            }
            dp[i] = minCuts; // Store minimum cuts needed for s[0...i]
        }
        return dp[n - 1]; // Return the minimum cuts needed for the entire string
    }

    public static void main(String[] args) {
        PalindromePartitioningMinCuts1D pp = new PalindromePartitioningMinCuts1D();
        String s = "aab";
        System.out.println("Minimum cuts needed: " + pp.minCut(s)); // Output: 1
    }
}

