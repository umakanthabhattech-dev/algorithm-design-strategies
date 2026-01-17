package Greedy;

import java.util.*;
/*
problem statement : Implement Dijkstra's algorithm to find the shortest path from a source vertex
to all other vertices in a weighted graph.
Dijkstra's Algorithm Explanation:
Dijkstra's algorithm is a greedy algorithm used to find the shortest path from a source vertex to all
other vertices in a weighted graph with non-negative edge weights.
The algorithm works by iteratively selecting the vertex with the smallest known distance
from the source, updating the distances to its neighbors, and repeating this process until
all vertices have been processed.
 */

class Dijkstra {

    static class Node {
        int vertex, weight;

        Node(int v, int w) {
            vertex = v;
            weight = w;
        }
    }

    static void dijkstra(List<List<Node>> graph, int src) {
        int V = graph.size();
        int[] dist = new int[V];

        // Set all distances to infinity
        Arrays.fill(dist, Integer.MAX_VALUE);

        // Distance to source = 0
        dist[src] = 0;

        // Min-heap based on distance
        //Maintain a priority queue to pick the vertex with the smallest distance from the source
        //contains nodes with their current shortest distance from the source
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        pq.add(new Node(src, 0));

        boolean[] visited = new boolean[V];

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            if (visited[u]) continue;
            visited[u] = true;

            // Relax all neighbors
            for (Node neighbour : graph.get(u)) {
                int v = neighbour.vertex;
                int w = neighbour.weight;
                System.out.println("dist[u] = " + dist[u] + ", weight = " + w);
                System.out.println("u = " + u + ", v = " + v + ", dist[v] = " + dist[v]);
                if (!visited[v] && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    System.out.println("Distance v dist[v] updated to " + dist[v]);
                    System.out.println("------------------------------");
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        // Print shortest distances
        System.out.println("Vertex\tDistance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t" + dist[i]);
        }
    }

    public static void main(String[] args) {

        int V = 6; // number of vertices (0 to 5)
        // Adjacency list representation of the graph
        //contains for each vertex a list of its neighboring vertices along with the weights of the edges connecting them
        List<List<Node>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // Add edges (u -> v with weight w)
        // Example graph similar to transcript explanation

        graph.get(0).add(new Node(1, 2));
        graph.get(0).add(new Node(2, 4));
        graph.get(1).add(new Node(2, 1));
        graph.get(1).add(new Node(3, 7));
        graph.get(2).add(new Node(3, 3));
        graph.get(2).add(new Node(4, 5));
        graph.get(3).add(new Node(4, 1));
        graph.get(4).add(new Node(5, 2));

        int source = 0;
        dijkstra(graph, source);
    }
}
//Steps of logic is explained here
//First create a Node class to represent each vertex and its associated weight.
//Second, initialize the graph as an adjacency list. contains for each vertex a list of its neighboring vertices along
// with the weights of the edges connecting them
//Third add nodes in queue with vertices and their distance from source
//Fourth, while the priority queue is not empty, extract the node with the smallest distance.
//Fifth, for each neighbor of the extracted node, check if a shorter path exists through the current node.
// If a shorter path is found, update the distance and add the neighbor to the priority queue.
//Finally, after processing all nodes, print the shortest distances from the source to each vertex


//Using adjacency matrix → O(n²)
//Using adjacency list + heap → O((V+E) log V)
//Time Complexity: O((V + E) log V) using a priority queue, where V is the number of vertices and E is the number of edges.
//Space Complexity: O(V) for storing distances and the priority queue.

// Disadvantages of Dijkstra's Algorithm:
// 1. Cannot handle negative weight edges: Dijkstra's algorithm assumes that once a vertex's shortest path is found, it cannot be improved. This assumption fails in the presence of negative weight edges, potentially leading to incorrect results.
// 2. Time complexity: While efficient for sparse graphs using priority queues, Dijkstra's algorithm can be slower for dense graphs compared to other algorithms like the Bellman-Ford algorithm.
// 3. Not suitable for all graph types: Dijkstra's algorithm is primarily designed for graphs with non-negative weights and may not be the best choice for all types of graphs or applications.
// 4. Requires more memory: The algorithm requires additional memory to store distances and priority queues, which can be a limitation for very large graphs.

