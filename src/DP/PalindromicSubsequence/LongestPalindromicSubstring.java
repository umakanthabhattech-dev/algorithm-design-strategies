package DP.PalindromicSubsequence;

/*
5. Longest Palindromic Substring
Given a string s, return the longest palindromic substring in s
Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"

 */
//public class LongestPalindromicSubstring {
//
//    public static void main(String[] args) {
//        String input = "babad"; // Example input
//        String result = longestPalindrome(input);
//        System.out.println("The longest palindromic substring of \"" + input + "\" is: \"" + result + "\"");
//    }
//
//    public static String longestPalindrome(String s) {
//        int n = s.length();
//        int[][] dp = new int[n][n];
//        int maxLength = 0;
//        int start = 0;
//
//        // Initialize the DP table
//        for (int i = 0; i < n; i++) {
//            dp[i][i] = 1; // Every single character is a palindrome of length 1
//            maxLength = 1; // Initialize maxLength to 1
//        }
//
//        // Check for palindromes of length 2
//        for (int i = 0; i < n - 1; i++) {
//            if (s.charAt(i) == s.charAt(i + 1)) {
//                dp[i][i + 1] = 2; // Two same characters form a palindrome of length 2
//                maxLength = 2; // Update maxLength
//                start = i; // Update start index
//            }
//        }
//
//        // Check for palindromes of length greater than 2
//        for (int len = 3; len <= n; len++) {
//            for (int i = 0; i < n - len + 1; i++) {
//                int j = i + len - 1; // End index of the substring
//                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] > 0) {
//                    dp[i][j] = dp[i + 1][j - 1] + 2; // Update length of palindrome
//                    if (dp[i][j] > maxLength) {
//                        maxLength = dp[i][j]; // Update maxLength
//                        start = i; // Update start index
//                    }
//                }
//            }
//        }
//
//        return s.substring(start, start + maxLength); // Return the longest palindromic substring
//    }
//}
/*
Time complexity O(N2)
space - O(N2)
 */

//DP solution for longest palindromic substring
//time complexity O(N2) space O(N2)
//public class LongestPalindromicSubstring {
//
//    public static String longestPalindrome(String s) {
//        int n = s.length();
//
//        // dp[i][j] = length of palindrome from index i to j
//        int[][] dp = new int[n][n];
//
//        int maxLen = 0;
//        int start = 0;
//
//        //in dp table, fill by
//        //i indicates start index
//        //j indicates end index
//        // difference = j - i (diagonal traversal)
//        for (int diff = 0; diff < n; diff++) {
//            for (int i = 0, j = i + diff; j < n; i++, j++) {
//
//                // Case 1: Single character
//                if (diff == 0) {
//                    dp[i][j] = 1;
//                }
//
//                // Case 2: Two characters
//                else if (diff == 1) {
//                    if (s.charAt(i) == s.charAt(j)) {
//                        dp[i][j] = 2;
//                    }
//                }
//
//                // Case 3: Length >= 3
//                else {
//                    if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] > 0) {
//                        dp[i][j] = dp[i + 1][j - 1] + 2;
//                    }
//                }
//
//                // Update answer if longer palindrome found
//                if (dp[i][j] > maxLen) {
//                    maxLen = dp[i][j];
//                    start = i;
//                }
//            }
//        }
//
//        //sustring from start to start+maxlen
//        //as substring's end index is exclusive
//        return s.substring(start, start + maxLen);
//    }
//
//    public static void main(String[] args) {
//        String s = "babad";
//        System.out.println(longestPalindrome(s));
//    }
//}
//DP solution youtube link -https://www.youtube.com/watch?v=fxwvVnBMN6I

//better approach is expand around center with O(1) space
//youtube for best solution https://www.youtube.com/watch?v=DK5OKKbF6GI&t=1049s

//time complexity O(N2) n (centers) × n (expansion) = O(n²) In the worst case (string like "aaaaa"),
// each expansion can go up to n
//space - O(1)
public class LongestPalindromicSubstring {

    // These track the best palindrome found so far
    private static int start = 0;
    private static int maxLength = 1;

    public static String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s; // boundary case
        }

        for (int i = 0; i < s.length(); i++) {
            // Case 1: Odd-length palindrome (single center)
            expandAroundCenter(s, i, i);

            // Case 2: Even-length palindrome (two centers)
            expandAroundCenter(s, i, i + 1);
        }

        return s.substring(start, start + maxLength);
    }

    private static void expandAroundCenter(String s, int left, int right) {
        while (left >= 0 &&
               right < s.length() &&
               s.charAt(left) == s.charAt(right)) {

            int currentLength = right - left + 1;

            if (currentLength > maxLength) {
                start = left;
                maxLength = currentLength;
            }

            left--;
            right++;
        }
    }

    // Test
    public static void main(String[] args) {
        String input = "babad";
        System.out.println(longestPalindrome(input)); // bab or aba
    }
}


