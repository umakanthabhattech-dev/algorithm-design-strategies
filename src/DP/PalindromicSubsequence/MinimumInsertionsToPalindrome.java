package DP.PalindromicSubsequence;

/*
1312. Minimum Insertion Steps to Make a String Palindrome
Given a string s. In one step you can insert any character at any index of the string.
Return the minimum number of steps to make s palindrome.
Example 1:

Input: s = "zzazz"
Output: 0
Explanation: The string "zzazz" is already palindrome we do not need any insertions.
Example 2:

Input: s = "mbadm"
Output: 2
Explanation: String can be "mbdadbm" or "mdbabdm".
 */
//public class MinimumInsertionsToPalindrome {
//    public static int minInsertions(String s) {
//        int n = s.length();
//        int[][] dp = new int[n][n];
//
//        // Fill the dp table
//        for (int length = 1; length <= n; length++) {
//            for (int i = 0; i < n - length + 1; i++) {
//                int j = i + length - 1;
//                if (i == j) {
//                    dp[i][j] = 1; // Single character is a palindrome
//                } else if (s.charAt(i) == s.charAt(j)) {
//                    dp[i][j] = dp[i + 1][j - 1] + 2; // Characters match
//                } else {
//                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]); // Characters don't match
//                }
//            }
//        }
//
//        // Length of the longest palindromic subsequence
//        int lpsLength = dp[0][n - 1];
//        return n - lpsLength; // Minimum insertions needed
//    }
//
//    public static void main(String[] args) {
//        String s = "abcde";
//        System.out.println("Minimum insertions to make palindrome: " + minInsertions(s)); // Output: 4
//    }
//}

/*
Easy to remember as we know LCS concept
Minimum insertions = length of string − length of Longest Palindromic Subsequence (LPS)
minInsertions = n − LPS
string is = leetcode
reverse string = edocteel
Lcs of string and reverse string is = ete
LPS value is 3
so minimum insertions = 8 - 3 = 5
Here time complexity is O(n^2) and space complexity is O(n^2)
*/
public class MinimumInsertionsToPalindrome {

    public static int minInsertions(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        int n = s.length();

        int[][] dp = new int[n + 1][n + 1];

        // LCS between s and reverse(s)
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == rev.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int lps = dp[n][n];
        return n - lps;//final equation
    }

    public static void main(String[] args) {
        System.out.println(minInsertions("mbadm"));    // 2
        System.out.println(minInsertions("leetcode")); // 5
    }
}

/*
Solution 3
DP solution with explanation - interview friendly

public static int minInsertions(String s) {
    int n = s.length();
    int[][] dp = new int[n][n];

    for (int len = 2; len <= n; len++) {
        for (int i = 0; i + len - 1 < n; i++) {
            int j = i + len - 1;

            if (s.charAt(i) == s.charAt(j)) {
                dp[i][j] = dp[i + 1][j - 1];
            } else {
                dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j - 1]);
            }
        }
    }

    return dp[0][n - 1];
}

*/