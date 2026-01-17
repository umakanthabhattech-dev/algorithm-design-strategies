package Backtracking;

/*
Below code finds ALL possible ways to color a given graph using at most m colors
such that no two adjacent vertices share the same color..
It uses backtracking to explore all valid color assignments and prints each solution found.
 */
public class GraphColoringAllSolutions {

    static int V = 4;        // Number of vertices
    static int m = 3;        // Number of colors
    static int solutionCount = 0;

    // Check if color c can be assigned to vertex v
    static boolean isSafe(int v, int[][] graph, int[] color, int c) {
        for (int i = 0; i < V; i++) {
            if (graph[v][i] == 1 && color[i] == c) {
                return false;
            }
        }
        return true;
    }

    // Backtracking function to find ALL solutions
    static void graphColoringUtil(int[][] graph, int[] color, int v) {

        // If all vertices are colored, print solution
        if (v == V) {
            solutionCount++;
            System.out.print("Solution " + solutionCount + ": ");
            for (int i = 0; i < V; i++) {
                System.out.print(color[i] + " ");
            }
            System.out.println();
            return; // IMPORTANT: do NOT stop, continue searching
        }

        // Try all colors for vertex v
        for (int c = 1; c <= m; c++) {
            if (isSafe(v, graph, color, c)) {
                color[v] = c;                    // choose
                graphColoringUtil(graph, color, v + 1); // explore
                color[v] = 0;                    // backtrack
            }
        }
    }

    // Driver method
    public static void main(String[] args) {

        // Example graph (Adjacency Matrix)
        int[][] graph = {
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {1, 0, 1, 0}
        };

        int[] color = new int[V];

        graphColoringUtil(graph, color, 0);

        System.out.println("\nTotal number of solutions: " + solutionCount);
    }
}
