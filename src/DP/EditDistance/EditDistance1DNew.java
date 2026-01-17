package DP.EditDistance;

/*
leetcode 72. Edit Distance
Given two strings, str1 and str2, find the minimum edit distance required to convert
str1 into str2. Minimum edit distance is the minimum number of insertions, deletions
, or substitutions required to transform str1 into str2.
In DP table rows represents str1, columns represents str2.
/* Example:
Input: str1 = "kitten", str2 = "sitting"
Output: 3
Explanation:
kitten -> sitten (substitution of 'k' with 's')
sitten -> sittin (substitution of 'e' with 'i')
sittin -> sitting (insertion of 'g')

 */
public class EditDistance1DNew {

    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // Always iterate on the bigger string horizontally for memory efficiency
        if (m < n) {
            return minDistance(word2, word1);
        }

        // dp[j] represents dp[i-1][j] (the previous row’s value)
        int[] dp = new int[n + 1];

        // Base case: transforming "" to word2[0..j]
        for (int j = 0; j <= n; j++) {
            dp[j] = j;
        }

        for (int i = 1; i <= m; i++) {
            int prevDiagonal = dp[0]; // dp[i-1][0]
            dp[0] = i;                // current row base case (i deletions)
            for (int j = 1; j <= n; j++) {
                int temp = dp[j]; // store dp[i-1][j] before overwriting

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = prevDiagonal; // characters match → no cost
                } else {
                    int insert = dp[j - 1];
                    int delete = dp[j];
                    int replace = prevDiagonal;

                    dp[j] = 1 + Math.min(insert, Math.min(delete, replace));
                }

                prevDiagonal = temp; // move diagonal forward
            }
        }

        //print dp array
        for (int j = 0; j <= n; j++) {
            System.out.print(dp[j] + " ");
        }
        System.out.println();

        return dp[n];
    }


    public static void main(String[] args) {
        String a = "Saturday";
        String b = "Sundays";
        System.out.println(minDistance(a, b));  // Output: 4
    }
}
/*
WHY dp[0] = i;  in each outer loop?
2D DP table is
0 1 2 3 4 5 6
1 0 1 2 3 4 5
2 1 0 1 2 3 4
3 2 1 0 1 2 3
4 3 2 1 0 1 2

so dp[0] which is in 2D dp[i][0] represents converting first i chars of word1 to empty string so need i deletions
SO dp[0]=i at start of each outer loop


 */
