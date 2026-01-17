package DP.StringPartition;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class WordBreakTopDownDP {
    Map<String, Boolean> map = new HashMap<>();
    public boolean wordBreak(String s, List<String> wordDict) {
        if (wordDict.contains(s)) {
            return true;
        }
        if (map.containsKey(s)) {
            return map.get(s);
        }
        for(int i = 1; i< s.length(); i++) {
           String left = s.substring(0, i);
           if(wordDict.contains(left) && wordBreak(s.substring(i), wordDict)) {
               map.put(s, true);
               return true;
           }
        }
        map.put(s, false);
        return false;
    }

    public static void main(String args[]) {
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList(new String[]{"cats","dog","sand","and", "cat"});

        WordBreakTopDownDP dp = new WordBreakTopDownDP();
        if (dp.wordBreak(s, wordDict)) {
            System.out.println("result is True");
        } else {
            System.out.println("result is False");
        }
    }
}
/*
Time complexity - exponantial to O(n2)
space = O(1)
 */