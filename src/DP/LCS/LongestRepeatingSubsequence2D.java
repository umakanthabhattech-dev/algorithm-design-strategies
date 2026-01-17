package DP.LCS;

/*
Given a string, you have to find the length of the longest subsequence that occurs at least twice and respects this
constraint: the characters that are re-used in each subsequence should have distinct indexes.
abbaba O/P = 3 , bbb
aabb O/P=2 ab
Solution is :
same as LCS but with condition i!=j when characters are same
Since we compare the string with itself, i != j ensures we don’t match a character with itself,
forcing the subsequence to come from two different positions.
 */
public class LongestRepeatingSubsequence2D {
    public static int longestRepeatingSubsequence(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                if (s.charAt(i - 1) == s.charAt(j - 1) && i != j) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][n];
    }

    public static void main(String[] args) {
        String s = "aabb";
        System.out.println(longestRepeatingSubsequence(s)); // Output: 2
    }
}
/*
      a  a  b  b
   0  0  0  0  0
a  0  0  1  1  1
a  0  1  1  1  1
b  0  1  1  1  2
b  0  1  1  2  2

 */