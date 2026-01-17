package DP.StringPartition;

import java.util.HashSet;
import java.util.Set;
/*
Leetcode Problem 139: Word Break
Given a string s and a dictionary of strings wordDict,
return true if s can be segmented into a space-separated
sequence of one or more dictionary words.
Note that the same word in the dictionary may be reused
multiple times in the segmentation.

Example 1:
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true

 */
public class WordBreakBottumUpDP1D {
    public boolean wordBreak(String s, Set<String> wordDict) {
        int n = s.length();
        //Store true if substring s[0...i] is able to segment or not
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // Base case: empty string can be segmented


        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                String word = s.substring(j, i);
                System.out.println(j + "->" + i + ":" + word);
                //System.out.println(word);
                if (dp[j] && wordDict.contains(word)) {
                    System.out.println("Found word: " + word + " from index " + j + " to " + i);
                    dp[i] = true;
                    break; // No need to check further if we found a valid segmentation
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        WordBreakBottumUpDP1D wb = new WordBreakBottumUpDP1D();
        Set<String> wordDict = new HashSet<>();
        wordDict.add("leet");
        wordDict.add("code");

        String s = "leetcode";
        boolean result = wb.wordBreak(s, wordDict);
        System.out.println("Can the string be segmented? " + result);
    }
}
/*
We run a for-loop through i which starts from index 1, and go through the length
of the string, i.e. len(s). Inside the loop, we run another loop for j which runs till i.
Inside the j loop, we check if the dp[j] is True
And if it is True, then we also check if the substring s[i:j] is present in the dictionary or not.
If s[i:j] is present, then we simply mark dp[i] as True and break from the loop

Time = O(N2)  as we compute substring of a given string tolat can be O(N3) in worst case
space=O(N)

 */