package DP.StringPartition;

import java.util.HashSet;
import java.util.Set;

/*
Given a string s and a dictionary of strings wordDict,
return true if s can be segmented into a space-separated
sequence of one or more dictionary words.
Note that the same word in the dictionary may be reused
multiple times in the segmentation.

Example 1:
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true

 */
public class WordBreakBottumUpDP2D {
    public boolean wordBreak(String s, Set<String> wordDict) {
        int n = s.length();
        boolean[][] dp = new boolean[n + 1][n + 1];

        // Initialize the dp array
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = false;
            }
        }

        // Base case: empty string can be segmented
        dp[0][0] = true;

        // Fill the dp array
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                String word = s.substring(j, i);
                System.out.println(j + "->" + i + ":" + word);
                if (dp[j][0] && wordDict.contains(word)) {
                    dp[i][0] = true;
                    break; // No need to check further if we found a valid segmentation
                }
            }
        }

//        System.out.println("DP Table:");
//        for (int i = 0; i <= n; i++) {
//            for (int j = 0; j <= n; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
        return dp[n][0];
    }

    public static void main(String[] args) {
        WordBreakBottumUpDP2D wb = new WordBreakBottumUpDP2D();
        Set<String> wordDict = new HashSet<>();
        wordDict.add("leet");
        wordDict.add("code");

        String s = "leetcode";
        boolean result = wb.wordBreak(s, wordDict);
        System.out.println("Can the string be segmented? " + result);
    }
}

