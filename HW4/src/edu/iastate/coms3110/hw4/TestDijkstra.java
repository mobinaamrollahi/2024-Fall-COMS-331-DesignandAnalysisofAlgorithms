package edu.iastate.coms3110.hw4;

import java.util.HashMap;
import java.util.Map;

public class TestDijkstra {
    public static void main(String[] args) {
        // Step 1: Create the directed graph
        DirectedGraph<String> G = new DirectedGraph<String>();

        // Step 2: Add vertices
        System.out.println("Adding vertices...");
        G.addVertex("s");
        G.addVertex("a");
        G.addVertex("b");
        G.addVertex("c");
        G.addVertex("d");

        // Step 3: Add directed edges
        System.out.println("Adding edges...");
        G.addEdge("s", "a");
        G.addEdge("s", "c");
        G.addEdge("a", "c");
        G.addEdge("c", "a");
        G.addEdge("a", "b");
        G.addEdge("b", "d");
        G.addEdge("d", "b");
        G.addEdge("c", "d");

        // Step 4: Define edge weights
        System.out.println("Defining edge weights...");
        Map<Tuple<String, String>, Double> weights = new HashMap<Tuple<String, String>, Double>();
        weights.put(new Tuple<String, String>("s", "a"), 7.0);
        weights.put(new Tuple<String, String>("s", "c"), 2.0);
        weights.put(new Tuple<String, String>("a", "c"), 2.0);
        weights.put(new Tuple<String, String>("c", "a"), 3.0);
        weights.put(new Tuple<String, String>("a", "b"), 1.0);
        weights.put(new Tuple<String, String>("b", "d"), 4.0);
        weights.put(new Tuple<String, String>("d", "b"), 5.0);
        weights.put(new Tuple<String, String>("c", "d"), 5.0);


        // Step 5: Run Dijkstra's algorithm
        System.out.println("Running Dijkstra's algorithm...");
        Tuple<Map<String, Double>, Map<String, String>> result = G.dijkstras("s", weights);

        if (result == null) {
            System.out.println("Dijkstra's algorithm returned null. There may be an issue with the graph or weights.");
            return;
        }

        // Step 6: Extract distances and predecessors
        Map<String, Double> distances = result.getFirst();
        Map<String, String> preds = result.getSecond();

        // Print table headers
        System.out.printf("%-10s %-20s %-10s\n", "Vertex", "Distance", "Predecessor");
        System.out.println("-------------------------------------");

        // Print each vertex with its distance and predecessor
        for (String vertex : distances.keySet()) {
            // Ensure the predecessor is "null" if there is no predecessor
            String pred = preds.get(vertex) != null ? preds.get(vertex) : "null";
            // Print the vertex, its distance, and its predecessor in a formatted way
            System.out.printf("%-10s %-20s %-10s\n", vertex, distances.get(vertex), pred);
        }
    }
}
