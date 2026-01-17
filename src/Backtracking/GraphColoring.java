package Backtracking;

/*leetcode M-coloring decision problem
number of colors needed to color a graph such that no two adjacent vertices share the same color
example
Input: Graph with 5 vertices and edges [(0,1),(0,2),(1,2),(1,3),(2,3),(3,4)]
Output: Minimum number of colors needed is 3
Explanation: One possible coloring is:
Vertex 0 -> Color 1
Vertex 1 -> Color 2
Vertex 2 -> Color 3
Vertex 3 -> Color 1
Vertex 4 -> Color 2
*/

/*
Problem statement of below Java code:
Given an undirected graph and a number m,
determine if the graph can be colored with at most m colors
such that no two adjacent vertices of the graph are colored with the same color.
If such a coloring is possible, print the color assigned to each vertex.
 */
import java.util.Arrays;
/*Min no of colors*/
public class GraphColoring {
    static class Graph {
        private boolean[][] adjMatrix;
        private int numVertices;

        public Graph(int numVertices) {
            this.numVertices = numVertices;
            adjMatrix = new boolean[numVertices][numVertices];
        }

        public void addEdge(int i, int j) {
            adjMatrix[i][j] = true;
            adjMatrix[j][i] = true;
        }

        public boolean hasEdge(int i, int j) {
            return adjMatrix[i][j];
        }

        public int getVCount() {
            return numVertices;
        }
    }

    // Backtracking Coloring Utility Functions.
    public static boolean isSafe(int v, Graph g, int colors[], int cr) {
        for (int i = 0; i < g.getVCount(); i++) {
            if (g.hasEdge(v, i) && cr == colors[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean graphColoringUtil(Graph g, int m, int colors[], int v) {
        // if all vertices have a color then just true
        if (v == g.getVCount())
            return true;

        // try different colors for v
        for (int cr = 1; cr <= m; cr++) {
            // Check if assignment of color cr to v is fine
            if (isSafe(v, g, colors, cr)) {
                colors[v] = cr;
                // recur to assign colors to rest of the vertices
                if (graphColoringUtil(g, m, colors, v + 1))
                    return true;
                // If assigning color cr doesn't lead
                // to a solution then remove
                colors[v] = 0;
            }
        }

        // if no color can be assigned then return false
        return false;
    }

    // Main Backtracking Coloring Function
    public static void backTrackingColoring(Graph g, int m) {
        int v = g.getVCount();

        // color array
        int colors[] = new int[v];

        // initialize all color values to 0
        Arrays.fill(colors, 0);

        // call graphColoringUtil for vertex 0
        if (!graphColoringUtil(g, m, colors, 0)) {
            System.out.println("Solution does not exist");
        }

        printColors(colors);
    }

    public static void printColors(int[] colors) {
        for (int i = 0; i < colors.length; i++) {
            System.out.println("Vertex " + i + " --->  Color " + colors[i]);
        }
    }

    public static void main(String[] args) {
        int m = 5; // Number of colors
        Graph g = new Graph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        backTrackingColoring(g, m);
    }

}
/*
public class MColoring {

    // Number of vertices
    static int V = 4;

    // Check if it's safe to assign color c to vertex v
    static boolean isSafe(int v, int[][] graph, int[] color, int c) {
        for (int i = 0; i < V; i++) {
            if (graph[v][i] == 1 && color[i] == c) {
                return false;
            }
        }
        return true;
    }

    // Backtracking function
    static boolean graphColoringUtil(int[][] graph, int m, int[] color, int v) {
        // If all vertices are colored
        if (v == V) {
            return true;
        }

        // Try all colors for vertex v
        for (int c = 1; c <= m; c++) {
            if (isSafe(v, graph, color, c)) {
                color[v] = c;

                // Recur to assign colors to next vertex
                if (graphColoringUtil(graph, m, color, v + 1)) {
                    return true;
                }

                // Backtrack
                color[v] = 0;
            }
        }

        return false;
    }

    // Main function to solve the problem
    static boolean graphColoring(int[][] graph, int m) {
        int[] color = new int[V];

        if (!graphColoringUtil(graph, m, color, 0)) {
            System.out.println("Solution does not exist");
            return false;
        }

        // Print solution
        System.out.println("Solution exists. Coloring is:");
        for (int i = 0; i < V; i++) {
            System.out.print(color[i] + " ");
        }
        System.out.println();
        return true;
    }

    // Main method
    public static void main(String[] args) {
        // Example graph (Adjacency Matrix)
        int[][] graph = {
            {0, 1, 1, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 1},
            {1, 0, 1, 0}
        };

        int m = 3; // Number of colors
        graphColoring(graph, m);
    }
}

 */