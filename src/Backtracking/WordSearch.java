package Backtracking;

/*
 Backtracking
79. Word Search
Given an m x n grid of characters board and a string word,
return true if word exists in the grid.
The word can be constructed from letters of sequentially
adjacent cells, where adjacent cells are horizontally or
vertically neighboring. The same letter cell may not be used more than once.
Input: board = [["A","B","C","E"],
["S","F","C","S"],["A","D","E","E"]],
word = "ABCCED"
Output: true

 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int k) {
        int m = board.length;
        int n = board[0].length;

        // Check if we are out of bounds or if the character does not match
        if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] != word.charAt(k)) {
            return false;
        }

        // If we have matched all characters of the word
        if (k == word.length() - 1) {
            return true;
        }

        // Temporarily mark the cell as visited
        char temp = board[i][j];
        board[i][j] = '#'; // Mark as visited

        // Explore all possible directions: up, down, left, right
        boolean found = dfs(board, word, i - 1, j, k + 1) ||
                dfs(board, word, i + 1, j, k + 1) ||
                dfs(board, word, i, j - 1, k + 1) ||
                dfs(board, word, i, j + 1, k + 1);

        // Restore the original value in the board
        board[i][j] = temp;

        return found;
    }

    public static void main(String[] args) {
        WordSearch ws = new WordSearch();
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        //String word = "CSDBEA";
        System.out.println(ws.exist(board, word)); // Output: true
    }
}

//time complexity: O(m * n * 3^L)
//space complexity: O(L)
//L is the length of the word
//m is number of rows
//n is number of columns
//79. Word Search
//Given an m x n grid of characters board and a string word,
//return true if word exists in the grid.