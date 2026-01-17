package DP.EditDistance;

/*
Given two strings, str1 and str2, find the minimum edit distance required to convert
str1 into str2. Minimum edit distance is the minimum number of insertions, deletions,
or substitutions required to transform str1 into str2.
In DP table rows represents str2, columns represents str1.
if "last" chars are not equal
replace dp[i - 1][j - 1] grid
insertion dp[i - 1][j]
deletion dp[i][j - 1]
if j  str1=beny 0,1,2,3
   i  str2=eph 0,1,2
dp[2][3] =?
i=2 j=3
dp[i - 1][j] value is dp[1][3] is min operation used for put ep into -> beny so for ith row(2) insert h final eph
dp[i][j - 1] = dp[2][2] is min ops used for put eph into ben so on i, jth grid need to delete y to make eph
dp[i - 1][j - 1] = dp[1][2] value is min op needed to put ep into -> beny now on (i,j) need to replace y by h
refer https://www.youtube.com/watch?v=MiqoA-yF-0M
 */
public class EditDistance2D {
    public static int minDistance(String string1, String string2) {
        int m = string1.length();
        int n = string2.length();

        // Create a DP table with (n+1) rows and (m+1) columns
        int[][] dp = new int[n+1][m+1];

        // Initialize the first row and first column
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i; // Cost of converting string2's first i characters to an empty string
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j; // Cost of converting an empty string to string1's first j characters
        }

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (string2.charAt(i - 1) == string1.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // No operation needed
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1],   // Substitution
                            Math.min(dp[i - 1][j],     // Insertion
                                    dp[i][j - 1])) + 1; // Deletion
                }
            }
        }

        //print dp table
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        // The answer is the value in the bottom-right cell of the table
        return dp[n][m];
    }

    public static void main(String[] args) {
//        String string1 = "kitten";
//        String string2 = "sitting";

        String string1 = "ball";
        String string2 = "baller";
        int result = minDistance(string1, string2);
        System.out.println("The minimum edit distance is dp way:  " + result);
        //int result2 = editDist(string1, string2, string1.length(), string2.length());
        //System.out.println("The minimum edit distance is naive way:  " + result2);
    }

    /*
    bruit force
     */
    static int editDist(String str1, String str2, int m, int n) {
        if (m == 0 || n == 0) // If any one string is empty, then empty the other string.
            return m + n;

        // If last characters of both strings are same, ignore last characters.
        if (str1.charAt(m - 1) == str2.charAt(n - 1))
            return editDist(str1, str2, m - 1, n - 1);

        // If last characters are not same, consider all three operations:
        // (1) Insert last char of second into first.
        // (2) Remove last char of first.
        // (3) Replace last char of first with second.
        return 1 + min(editDist(str1, str2, m, n - 1), // Insert
                editDist(str1, str2, m - 1, n), // Remove
                editDist(str1, str2, m - 1, n - 1)); // Replace
    }

    static int min(int x, int y, int z) {
        x = Math.min(x, y);
        return Math.min(x, z);
    }
}

/*
Time complexity O(m*n)
space complexity O(m*n)
 */

