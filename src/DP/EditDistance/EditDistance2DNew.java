package DP.EditDistance;


public class EditDistance2DNew {

    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // dp[i][j] = edit distance between word1[0..i-1] and word2[0..j-1]
        int[][] dp = new int[m + 1][n + 1];

        // Base case 1: converting empty string → second string
        // Needs j insertions
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Base case 2: converting first string → empty string
        // Needs i deletions
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                // If characters match, no new cost — take diagonal
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Otherwise choose min(Insert, Delete, Replace)
                    int insert = dp[i][j - 1];      // Insert
                    int delete = dp[i - 1][j];      // Delete
                    int replace = dp[i - 1][j - 1]; // Replace

                    dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
                }
            }
        }

        //print dp table
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
//        String a = "Saturday";
//        String b = "Sundays";

        String a = "ball";
        String b = "baller";

        System.out.println("Edit Distance = " + minDistance(a, b));  // Output: 4
    }
}

//time complexity
//O(m*m) time and space complexity

/*
Logic to understand edit distance
b e n y -> e p h

0 1 2 3    0 1 2

if last letters are similar
then no operation just dont consider last letters to caclulate

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
