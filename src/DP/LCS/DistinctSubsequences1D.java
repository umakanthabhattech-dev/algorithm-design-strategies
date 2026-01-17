package DP.LCS;

/*
leetcode 115: Distinct Subsequences (1D DP Optimization)
Given two strings, str1 and str2, return the number of times str2 appears in str1 as a subsequence.
Let’s assume that you have two strings as follows:
str1 = "bbagbag"
str2 = "bag"
The output will be 5, as "bag" appears in "bbagbag" in the following ways:
1. b(b) a g b a g
2. b b a(g) b a g
3. b b a g b(a) g
4. b b a g b a(g)
5. b(b) a g b(a) g
We can solve this problem using a dynamic programming approach with a 1D array to optimize space
 */
public class DistinctSubsequences1D {
    public static int numDistinct(String S, String T) {
        int m = S.length();
        int n = T.length();
        int[] dp = new int[n + 1];

        // Base case: An empty T is a subsequence of any S
        dp[0] = 1;

        // Fill the dp array
        for (int i = 1; i <= m; i++) {
            for (int j = n; j >= 1; j--) {
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        String S = "rabbbit";
        String T = "rabbit";
        System.out.println("Number of distinct subsequences: " + numDistinct(S, T));
    }
}
