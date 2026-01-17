package DP.PalindromicSubsequence;

/*
647. Palindromic Substrings
Given a string s, return the number of palindromic substrings in it.
Example 1:
Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".

Logic is same as longest palindromic substring but here we count all palindromic substrings.

 */
public class PalindromicSubstringCounter {

    public static int countSubstrings(String s) {
        int n = s.length();
        int count = 0;

        for (int i = 0; i < n; i++) {
            // Odd-length palindromes (center at i)
            count += expandFromCenter(s, i, i);

            // Even-length palindromes (center between i and i+1)
            count += expandFromCenter(s, i, i + 1);
        }

        return count;
    }

    private static int expandFromCenter(String s, int left, int right) {
        int count = 0;

        while (left >= 0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }

        return count;
    }

    // Driver code for testing
    public static void main(String[] args) {
        System.out.println(countSubstrings("abc"));   // 3
        System.out.println(countSubstrings("aaa"));   // 6
        System.out.println(countSubstrings("abbac")); // 7
    }
}




//public class PalindromicSubstringCounter {
//
//    public static void main(String[] args) {
//        String input = "abba"; // Example input
//        int count = countPalindromicSubstrings(input);
//        System.out.println("The total number of palindromic substrings in \"" + input + "\" is: " + count);
//    }
//
//    public static int countPalindromicSubstrings(String s) {
//        int n = s.length();
//        if (n == 0) return 0;
//
//        // DP table to store palindrome status
//        boolean[][] dp = new boolean[n][n];
//        int count = 0;
//
//        // All substrings of length 1 are palindromes
//        for (int i = 0; i < n; i++) {
//            dp[i][i] = true;
//            count++;
//        }
//
//        // Check for substrings of length 2
//        for (int i = 0; i < n - 1; i++) {
//            if (s.charAt(i) == s.charAt(i + 1)) {
//                dp[i][i + 1] = true;
//                count++;
//            }
//        }
//
//        // Check for substrings of length greater than 2
//        for (int length = 3; length <= n; length++) {
//            for (int i = 0; i < n - length + 1; i++) {
//                int j = i + length - 1; // End index of the substring
//                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
//                    dp[i][j] = true; // Mark as palindrome
//                    count++; // Increment count
//                }
//            }
//        }
//        return count; // Return the total count of palindromic substrings
//    }
//}


