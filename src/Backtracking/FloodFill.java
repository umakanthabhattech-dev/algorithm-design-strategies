package Backtracking;

/*
leetcode 733. Flood Fill
You are given an image represented by an m x n grid
of integers image, where image[i][j] represents
the pixel value of the image.
You are also given three integers sr, sc, and color.
Your task is to perform a flood fill on the image
starting from the pixel image[sr][sc].

To perform a flood fill:
Begin with the starting pixel and change its color to color.
Perform the same process for each pixel that is directly
adjacent (pixels that share a side with the original pixel,
either horizontally or vertically) and shares the
same color as the starting pixel.
Keep repeating this process by checking neighboring pixels
of the updated pixels and modifying their color if it
matches the original color of the starting pixel.
The process stops when there are no more adjacent
pixels of the original color to update.
Return the modified image after performing the flood fill.

Example 1:
Input:
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: From the center of the image
(with position (sr, sc) = (1, 1)), all pixels connected
by a path of the same color as the starting pixel are colored with the new color.

 */
public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int originalColor = image[sr][sc];
        // If the original color is the same as the new color, no need to fill
        if (originalColor == newColor) {
            return image;
        }
        // Start the flood fill process
        backtrack(image, sr, sc, originalColor, newColor);
        return image;
    }

    private void backtrack(int[][] image, int x, int y, int originalColor, int newColor) {
        // Check for out of bounds or if the current pixel is not the original color
        if (x < 0 || x >= image.length || y < 0 || y >= image[0].length || image[x][y] != originalColor) {
            return;
        }

        // Change the color of the current pixel
        image[x][y] = newColor;

        // Recursively call backtrack for the four adjacent pixels
        backtrack(image, x + 1, y, originalColor, newColor); // Down
        backtrack(image, x - 1, y, originalColor, newColor); // Up
        backtrack(image, x, y + 1, originalColor, newColor); // Right
        backtrack(image, x, y - 1, originalColor, newColor); // Left
    }

    public static void main(String[] args) {
        FloodFill floodFill = new FloodFill();
        int[][] image = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };
        int sr = 1; // Starting row
        int sc = 1; // Starting column
        int newColor = 2; // New color to fill

        int[][] result = floodFill.floodFill(image, sr, sc, newColor);

        // Print the resulting image
        for (int[] row : result) {
            for (int pixel : row) {
                System.out.print(pixel + " ");
            }
            System.out.println();
        }
    }
}
/*simple problem statement
ou are given a 2D grid (image) where each cell represents a pixel value.
You are also given a starting pixel (sr, sc) and a new color.
Your task is to recolor the starting pixel and all connected pixels
(connected 4-directionally: up, down, left, right)
that have the same original color as the starting pixel, changing them to the new color.
🔹 Constraints / Rules
-Only pixels connected up, down, left, or right are considered connected.
-Diagonal connections are not allowed.
-If the starting pixel already has the new color, return the image unchanged.
 */