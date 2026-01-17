package DP.LCS;

/*
Leetcode Problem 1092: Shortest Common Supersequence
Given two strings, str1 and str2, find the length of the shortest string that has both the input
strings as subsequences.
Let’s assume that we have two strings as follows:
str1 = "yabc"
str2 = "xabc"
There can be multiple strings that have str1 and str2 as subsequences,
for example, "xyaabcc" and "xyabbc", but the shortest string that has
these input strings as subsequences is "xyabc".
Please note that the sequence of alphabets in the string can be altered.
 */
public class ShortestCommonSupersequence1D {
    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        // Create a 1D array to store lengths of longest common subsequence
        int[] dp = new int[n + 1];

        // Fill the DP array
        for (int i = 1; i <= m; i++) {
            int prev = 0; // To store the value of dp[i-1][j-1]
            for (int j = 1; j <= n; j++) {
                int temp = dp[j]; // Store current dp[j] value
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[j] = prev + 1; // Match found
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]); // No match
                }
                //To store previous value i-1,j-1
                //when we increment j to j+1 and i is same then we need to save j's previous value [i-1][j-1]
                prev = temp; // Update prev for next iteration
            }
        }

        // Build the shortest common supersequence from the DP array
        StringBuilder scs = new StringBuilder();
        int i = m, j = n;

        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                scs.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[j] == dp[j - 1]) {
                j--;
            } else {
                i--;
            }
        }

        // Add remaining characters of str1
        while (i > 0) {
            scs.append(str1.charAt(i - 1));
            i--;
        }

        // Add remaining characters of str2
        while (j > 0) {
            scs.append(str2.charAt(j - 1));
            j--;
        }

        // Since we built the SCS backwards, we need to reverse it
        return scs.reverse().toString();
    }

    public static void main(String[] args) {
        ShortestCommonSupersequence1D scs = new ShortestCommonSupersequence1D();
        String str1 = "abac";
        String str2 = "cab";
        System.out.println("Shortest Common Supersequence: " + scs.shortestCommonSupersequence(str1, str2));
    }
}
/*
Time complexity is same as 2D case O(m * n)
Space reduced to O(n), where n is the length of str2
 */