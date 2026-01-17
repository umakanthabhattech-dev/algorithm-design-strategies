package Backtracking;
/*
M-coloring optimisation problem using backtracking.
Given an undirected graph and a number m, determine the minimum number of colors required to color
the graph such that no two adjacent vertices share the same color.
This implementation uses backtracking to find the chromatic number of the graph.
Time Complexity: O(m^V) in the worst case, where m is the number of colors and V is the number of vertices.
is this NP hard? yes
why? because we are trying all possible colorings
and checking for valid ones. so exponential time.
 */
class GraphColoringOpti {

    int V;
    int[][] graph;
    int[] color;

    GraphColoringOpti(int[][] graph) {
        this.graph = graph;
        this.V = graph.length;
        this.color = new int[V];
    }

    boolean isSafe(int v, int c) {
        for (int i = 0; i < V; i++) {
            if (graph[v][i] == 1 && color[i] == c)
                return false;
        }
        return true;
    }

    boolean colorGraph(int v, int m) {
        if (v == V)
            return true;

        for (int c = 1; c <= m; c++) {
            if (isSafe(v, c)) {
                color[v] = c;
                if (colorGraph(v + 1, m))
                    return true;
                color[v] = 0; // backtrack
            }
        }
        return false;
    }

    int findChromaticNumber() {
        for (int m = 1; m <= V; m++) {
            if (colorGraph(0, m))
                return m;
        }
        return V;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0,1,1,1},
                {1,0,1,0},
                {1,1,0,1},
                {1,0,1,0}
        };

        GraphColoringOpti gc = new GraphColoringOpti(graph);
        System.out.println("Chromatic Number = " + gc.findChromaticNumber());
    }
}
