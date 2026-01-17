package DP.EditDistance;

/*
leetcode 72. Edit Distance
Given two strings, str1 and str2, find the minimum edit distance required to convert
str1 into str2. Minimum edit distance is the minimum number of insertions, deletions,
or substitutions required to transform str1 into str2.
In DP table rows represents str2, columns represents str1.
/* Example:
Input: str1 = "kitten", str2 = "sitting"
Output: 3
Explanation:
kitten -> sitten (substitution of 'k' with 's')
sitten -> sittin (substitution of 'e' with 'i')
sittin -> sitting (insertion of 'g')
 */
public class EditDistance1D {

    public static int minDistance(String string1, String string2) {
        int m = string1.length();
        int n = string2.length();

        // Create a 1D array to store the DP values for the current row
        int[] dp = new int[m + 1];

        // Initialize the dp array for the base case (converting an empty string)
        for (int j = 0; j <= m; j++) {
            dp[j] = j;
        }

        // Fill the dp array row by row
        for (int i = 1; i <= n; i++) {
            // Store the previous row's value before it gets overwritten
            int prev = dp[0];
            dp[0] = i;

            for (int j = 1; j <= m; j++) {
                // Store the current dp[j] before updating
                int temp = dp[j];  //used for next iteration next calculation on dp[j] value is dp[i-1][j-1]

                if (string2.charAt(i - 1) == string1.charAt(j - 1)) {
                    dp[j] = prev; // No operation needed
                } else {
                    dp[j] = Math.min(prev,      // Substitution
                            Math.min(dp[j],    // Insertion
                                    dp[j - 1])) + 1; // Deletion
                }

                // Update prev to the old dp[j] for the next iteration
                prev = temp;
            }
        }
        // The final answer is in dp[m], representing the last column of the last row
        return dp[m];
    }

    public static void main(String[] args) {
        String string1 = "kitten";
        String string2 = "sitting";

        int result = minDistance(string1, string2);
        System.out.println("The minimum edit distance is: " + result);
    }
}
/*
Time Complexity: O(m * n), as we still need to compute each entry.
Space Complexity: O(m), as we now use a 1D array instead of a 2D table.
 */

/*
Logic to understand edit distance
b e n y -> e p h

0 1 2 3    0 1 2

if last letters are similar
then no operation just dont consider last letters to calculate

1.Replace
b e n _y  ->  e p _h
0 1 2  3      0 1  3

if (y != h) //last letters are not similar

transorm "ben" in "beny" --- to -- "ep" and replace y by h
ben y => ep y => eph
-----------------------------------------------------------

2. insert

b e n y -> e p h

0 1 2 3    0 1 2

b e n _y -> e p _h

0 1 2  3    0 1   2

transform beny completely to ep and insert h
b e n y => e p => eph

---------------------------------

3. delete

b e n y -> e p h

0 1 2 3    0 1 2

b e n _y -> e p _h

0 1 2 _3    0 1 _2

transform ben to eph and delete y
ben y to => eph y=> eph

-------------------------------------
 */