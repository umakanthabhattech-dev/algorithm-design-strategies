package DP.StringPartition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Leetcode Problem 472: Concatenated Words
Given an array of strings words (without duplicates), return all the concatenated words in the given list of words.
A concatenated word is defined as a string that is comprised entirely of at least two shorter words (not necessarily distinct) in the given array.
Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

 */
public class ConcatenatedWords {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> wordSet = new HashSet<>();
        for (String word : words) {
            wordSet.add(word);
        }

        List<String> result = new ArrayList<>();
        for (String word : words) {
            // Temporarily remove the word from the set to prevent self-concatenation
            wordSet.remove(word);
            if (canForm(word, wordSet)) {
                result.add(word);
            }
            // Add the word back to the set for further checks
            wordSet.add(word);
        }
        return result;
    }

    private boolean canForm(String word, Set<String> wordSet) {
        int n = word.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // Base case: empty string can be formed
        //each index value represents if the substring word[0:i] can be formed using words in the set
        // word = dogcatsdog word[0:3] = dog(0-2 in string) can be formed dp[3=""dog] = true
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                // Check if the substring word[j:i] is in the set and
                // if the prefix word[0:j] can be formed
                //logic is similar to word break problem
                // dp[j] is true means word[0:j] can be formed
                // word.substring(j, i) is the current substring we are checking
                // if both conditions are true, we can form word[0:i]
                // so we set dp[i] to true
                // This continues until we check the whole word
                //for dogcatsdog
                // d[j] = true for j=0,3,6
                if (dp[j] && wordSet.contains(word.substring(j, i))) {
                    dp[i] = true;
                    //dp[4] indicates that 0 to 4
                    break; // No need to check further
                }
            }
        }
        return dp[n]; // Only need to check if the whole word can be formed
    }


    public static void main(String[] args) {
        ConcatenatedWords cw = new ConcatenatedWords();
        //String[] words = {"cat", "cats", "dog", "dogs", "catdog"};
        String[] words = {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        List<String> concatenatedWords = cw.findAllConcatenatedWordsInADict(words);
        System.out.println(concatenatedWords); // Output: [catdog]
    }
}
/*
DP solution logic :
set [cats, dog, cats]
String = ""catsdogcats
dp[0] =true
i=0 = " " => always True
i=1 c
i=2 ca
i=3 cat
i=4 cats
dp[]
 0 1 2 3 4
"" c a t s
Logic i->4
i=1
   c => j=0,1
          0 " "
          1 c
i=2
   ca => j=0,1,2
i=3
   cat => j=0,1 2,3
i=4
   cats => j=0,1,2,3,4
           j=0 " "+ cats => dp[0] && str(0->i=4) => if true dp[i=4] break the i=4 loop
           j=1 " "c + ats => dp[1] && str(1->i=4)=> if true dp[i=4] break the i=4 loop
           j=2 " "ca + ts => dp[2] && str(2->i=4)=> if true dp[i=4] break the i=4 loop
           j=3 ""cat + s  => dp[3] && str(3->i=4)=> if true dp[i=4] break the i=4 loop
dp[n] is the answer
-------------------------------------------------------------

Time Complexity
The time complexity of the concatenated words algorithm can be analyzed as follows:
Outer Loop: We iterate through each word in the list, which takes O(W) time, where W is the number of words.
Dynamic Programming Check: For each word of length L, we use a nested loop to fill the dp array:
The outer loop runs L times (for each character in the word).
The inner loop runs up to L times (to check all possible prefixes).
Thus, the time complexity for checking each word is O(L^2).
Combining these, the overall time complexity is: O(W⋅L2)O(W⋅L2) where W is the number of words and L is the average length of the words.

Space ComplexityThe space complexity can be broken down into the following components:
HashSet: We use a HashSet to store the words, which takes O(W) space.
Dynamic Programming Array: The dp array used for each word has a size of L + 1, which takes O(L) space.
Thus, the overall space complexity is: O(W+L) where W is the number of words and L is the length of the longest word.

Summary
Time Complexity:  O(W⋅L2)O(W⋅L2)
Space Complexity: O(W+L)O(W+L)
 */
/*
For interviewers: Explanation of the approach
import java.util.*;

public class ConcatenatedWords {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {

        // Put all words in a set for O(1) lookup
        Set<String> wordSet = new HashSet<>(Arrays.asList(words));

        List<String> result = new ArrayList<>();

        // Memoization map: word -> true/false
        Map<String, Boolean> memo = new HashMap<>();

        for (String word : words) {

            // Remove current word to avoid using itself
            wordSet.remove(word);

            if (canForm(word, wordSet, memo)) {
                result.add(word);
            }

            // Add it back for next iterations
            wordSet.add(word);
        }

        return result;
    }

    private boolean canForm(String word, Set<String> wordSet, Map<String, Boolean> memo) {

        // If already solved, return stored result
        if (memo.containsKey(word)) {
            return memo.get(word);
        }

        // Try all possible prefix-suffix splits
        for (int i = 1; i < word.length(); i++) {

            String prefix = word.substring(0, i);
            String suffix = word.substring(i);

            // Case 1: prefix exists AND suffix exists
            if (wordSet.contains(prefix) && wordSet.contains(suffix)) {
                memo.put(word, true);
                return true;
            }

            // Case 2: prefix exists AND suffix can be broken further
            if (wordSet.contains(prefix) && canForm(suffix, wordSet, memo)) {
                memo.put(word, true);
                return true;
            }
        }

        memo.put(word, false);
        return false;
    }
}
1️⃣ Store all words in a HashSet
    -Fast lookup (O(1))
    -Helps quickly check if a prefix/suffix exists
2️⃣ Why remove the current word?
    wordSet.remove(word);
    Prevents self-concatenation
    Example: "cat" should not be formed using "cat" itself.
3️⃣ Core logic – canForm()
    We split the word at every index:
    for (int i = 1; i < word.length(); i++)
    Example for "catdog":
            "c" | "atdog"
            "ca" | "tdog"
            "cat" | "dog"
4️⃣ Two success conditions
✅ Condition 1
prefix ∈ set AND suffix ∈ set
 catdog → cat + dog
✅ Condition 2
prefix ∈ set AND suffix can be broken further
supermanhero → super + manhero
manhero → man + hero
5️⃣ Why memoization is needed
Map<String, Boolean> memo
Without memo:
-Same suffix checked again and again
-Very slow ❌
With memo:
-Once solved → reused
-Much faster
Time & Space Complexity
Let:
N = number of words
L = average word length
Time
Worst case: O(N × L³)
With memoization: much faster in practice
Space
O(N × L) (recursion + memo map)
*/