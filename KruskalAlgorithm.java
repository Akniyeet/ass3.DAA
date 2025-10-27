import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class KruskalAlgorithm {

    // Step 1: Read the input data (JSON)
    public static Root readInput(String fileName) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(new FileReader(fileName), Root.class);  // Parse as Root object
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Kruskal's Algorithm implementation
    public static KruskalResult kruskal(Graph graph) {
        UnionFind uf = new UnionFind(graph.nodes.length);
        Arrays.sort(graph.edges, (a, b) -> Integer.compare(a.weight, b.weight));

        int totalCost = 0;
        List<Edge> mstEdges = new ArrayList<>();
        int operationsCount = 0;

        // Process each edge
        for (Edge edge : graph.edges) {
            operationsCount++;
            int u = Arrays.asList(graph.nodes).indexOf(edge.from);
            int v = Arrays.asList(graph.nodes).indexOf(edge.to);

            if (uf.union(u, v)) {
                mstEdges.add(edge);  // Add edge to the List
                totalCost += edge.weight;
            }
        }

        // Calculate execution time
        long startTime = System.nanoTime();
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1000000.0;

        return new KruskalResult(mstEdges.toArray(new Edge[0]), totalCost, operationsCount, executionTime);
    }

    // Step 2: Output the results
    public static void generateOutput(Object result, String fileName) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(result, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Main method
    public static void main(String[] args) {
        // Read the input JSON file
        Root root = readInput("input_example.json");  // Read the root object

        // Verify if data is read successfully
        if (root != null) {
            System.out.println("Input data loaded successfully!");  // Debugging print
        } else {
            System.out.println("Failed to load input data.");  // Debugging print
        }

        // Prepare the result object to hold Kruskal's algorithm outputs
        List<Result> results = new ArrayList<>();

        // Process Kruskal's Algorithm for each graph
        if (root != null) {
            for (Graph graph : root.graphs) {
                System.out.println("Processing Graph ID: " + graph.id);  // Debugging print
                KruskalResult kruskalResult = kruskal(graph);

                // Add Kruskal's algorithm result for the graph
                results.add(new Result(graph.id, new InputStats(graph.nodes.length, graph.edges.length), kruskalResult));
                System.out.println("Graph ID " + graph.id + " processed.");  // Debugging print
            }
        }

        // Verify if results are being added
        System.out.println("Results processed: " + results.size());  // Debugging print

        // Write the result to an output JSON file
        generateOutput(new Output(results), "output_example.json");

        // Output message after the file is written
        System.out.println("Output written to output_example.json.");  // Debugging print
    }
}



