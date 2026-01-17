package DP.LCS;

/*
Given two strings s1 and s2, you have to find the length of the Longest Common Substring (LCS) in both these strings.
Let’s say we have two strings, “helloworld” and “yelloword”,
there are multiple common substrings, such as “llo”, “ello”, “ellowor”, “low”, and “d”.
The longest common substring is “ellowor”, with length 7.
We have two conditions:
if(S1[i-1] != S2[j-1]), the characters don’t match, therefore the consecutiveness of characters is broken.
So we set the cell value (dp[i][j]) as 0.
if(S1[i-1] == S2[j-1]), then the characters match and we simply set its value to 1+dp[i-1][j-1].
We have done so because dp[i-1][j-1] gives us the longest common substring till the last cell
character (current strings -{matching character}). As the current cell’s character is matching we are
  adding 1 to the consecutive chain.
Note: dp[n][m] will not give us the answer; rather the maximum value in the
entire dp array will give us the length of the longest common substring.
This is because there is no restriction that the longest common substring is present at the end of both the strings.
 Same as LCS but reset to 0 when not match
 */

class LongestCommonSubstring2D {
    // Function to find the length of the Longest Common Substring (LCS)
    static int lcs(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        // Create a 2D array to store LCS lengths
        int[][] dp = new int[n + 1][m + 1];
        int ans = 0; // Initialize a variable to store the maximum LCS length

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // If the characters at the current indices are the same, extend the LCS
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    int val = 1 + dp[i - 1][j - 1];
                    dp[i][j] = val;
                    ans = Math.max(ans, val); // Update the maximum LCS length
                } else {
                    dp[i][j] = 0; // Reset LCS length if characters don't match
                }
            }
        }
        System.out.println("DP Table:");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        return ans; // Return the length of the Longest Common Substring (LCS)
    }

    public static void main(String args[]) {
        String s1 = "abcjklp";
        String s2 = "acjkp";

        // Call the lcs function and print the result
        System.out.println("The Length of Longest Common Substring is " + lcs(s1, s2));
    }
}
