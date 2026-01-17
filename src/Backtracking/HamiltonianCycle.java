package Backtracking;

/*
    A Hamiltonian Cycle (or Hamiltonian Circuit) is a cycle in an undirected
    or directed graph that visits each vertex exactly once and returns to the starting vertex.
    The problem of finding a Hamiltonian Cycle is NP-complete,
    meaning there is no known polynomial-time solution for all graphs.
    However, we can use backtracking to find a Hamiltonian Cycle in a given graph.
    The following Java code demonstrates how to find a Hamiltonian Cycle using backtracking.

 */

public class HamiltonianCycle {
    private int V; // Number of vertices
    private int[][] graph; // Adjacency matrix

    public HamiltonianCycle(int[][] graph) {
        this.graph = graph;
        this.V = graph.length;
    }

    // Utility function to check if the vertex can be added to the path
    private boolean isSafe(int v, int[] path, int pos) {
        // Check if this vertex is an adjacent vertex of the previously added vertex.
        if (graph[path[pos - 1]][v] == 0) {
            return false;
        }

        // Check if the vertex has already been included.
        for (int i = 0; i < pos; i++) {
            if (path[i] == v) {
                return false;
            }
        }
        return true;
    }

    // Recursive utility function to solve the Hamiltonian Cycle problem
    private boolean hamCycleUtil(int[] path, int pos) {
        // Base case: If all vertices are included in the path
        if (pos == V) {
            // And if there is an edge from the last included vertex to the first vertex
            return graph[path[pos - 1]][path[0]] == 1;
        }

        // Try different vertices as the next candidate in the Hamiltonian Cycle.
        for (int v = 1; v < V; v++) {
            if (isSafe(v, path, pos)) {
                path[pos] = v;

                // Recur to construct the rest of the path
                if (hamCycleUtil(path, pos + 1)) {
                    return true;
                }

                // If adding vertex v doesn't lead to a solution, remove it
                path[pos] = -1;
            }
        }
        return false;
    }

    // This function solves the Hamiltonian Cycle problem using backtracking.
    public boolean hamCycle() {
        int[] path = new int[V];
        for (int i = 0; i < V; i++) {
            path[i] = -1; // Initialize the path
        }

        // Let the first vertex in the path be 0
        path[0] = 0;

        // Start from the first vertex and try to construct the cycle
        if (!hamCycleUtil(path, 1)) {
            System.out.println("Solution does not exist");
            return false;
        }

        printSolution(path);
        return true;
    }

    // Utility function to print the solution
    private void printSolution(int[] path) {
        System.out.print("Hamiltonian Cycle: ");
        for (int i = 0; i < V; i++) {
            System.out.print(path[i] + " ");
        }
        System.out.print(path[0] + "\n"); // Print the starting vertex at the end
    }

    public static void main(String[] args) {
//        int[][] graph = {
//                {0, 1, 0, 1, 0},
//                {1, 0, 1, 1, 1},
//                {0, 1, 0, 0, 1},
//                {1, 1, 0, 0, 1},
//                {0, 1, 1, 1, 0}
//        };

        int graph[][] = {{0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 1},
                {0, 1, 1, 1, 0},
        };

        HamiltonianCycle hc = new HamiltonianCycle(graph);
        hc.hamCycle();
        int graph2[][] = {{0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 0},
                {0, 1, 1, 0, 0},
        };
        HamiltonianCycle hc2 = new HamiltonianCycle(graph2);
        hc2.hamCycle();

    }
}
