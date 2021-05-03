
import java.io.File;
        import java.io.FileNotFoundException;
        import java.util.LinkedList;
        import java.util.Queue;
        import java.util.Scanner;

public class FordFulkersonAlgo {
    private int[] parent;
    private Queue<Integer> queue;
    private int numberOfVertices;
    private boolean[] visited;

    public static long startTime = System.nanoTime();

    public FordFulkersonAlgo(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        this.queue = new LinkedList<Integer>();
        parent = new int[numberOfVertices + 1];
        visited = new boolean[numberOfVertices + 1];
    }

    public boolean bfs(int source, int goal, int graph[][]) {
        boolean pathFound = false;
        int destination, element;

        for (int vertex = 1; vertex <= numberOfVertices; vertex++) {
            parent[vertex] = -1;
            visited[vertex] = false;
        }

        queue.add(source);
        parent[source] = -1;
        visited[source] = true;

        while (!queue.isEmpty()) {
            element = queue.remove();
            destination = 1;

            while (destination <= numberOfVertices) {
                if (graph[element][destination] > 0 && !visited[destination]) {
                    parent[destination] = element;
                    queue.add(destination);
                    visited[destination] = true;
                }
                destination++;
            }
        }
        if (visited[goal]) {
            pathFound = true;
        }
        return pathFound;
    }

    public int fordFulkerson(int graph[][], int source, int destination) {
        int u, v;
        int maxFlow = 0;
        int pathFlow;

        int[][] residualGraph = new int[numberOfVertices + 1][numberOfVertices + 1];
        for (int sourceVertex = 1; sourceVertex <= numberOfVertices; sourceVertex++) {
            for (int destinationVertex = 1; destinationVertex <= numberOfVertices; destinationVertex++) {
                residualGraph[sourceVertex][destinationVertex] = graph[sourceVertex][destinationVertex];
            }
        }

        while (bfs(source, destination, residualGraph)) {
            pathFlow = Integer.MAX_VALUE;
            for (v = destination; v != source; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, residualGraph[u][v]);
            }
            for (v = destination; v != source; v = parent[v]) {
                u = parent[v];
                residualGraph[u][v] -= pathFlow;
                residualGraph[v][u] += pathFlow;
            }
            maxFlow += pathFlow;
        }

        return maxFlow;

    }

    public static void main(String... arg) {
        int[][] graph;
        int numberOfNodes;
        int source;
        int sink;
        int maxFlow;

        try {
            File myObj = new File("algo.txt");
            Scanner myReader = new Scanner(myObj);

            numberOfNodes = myReader.nextInt();
            System.out.println("Number of nodes= " + numberOfNodes);

            graph = new int[numberOfNodes + 1][numberOfNodes + 1];
            int first = 0;
            int last = 0;
            int flow = 0;
            while (myReader.hasNext()) {
                first = myReader.nextInt();
                last = myReader.nextInt();
                flow = myReader.nextInt();
                graph[first + 1][last + 1] = flow;
            }
            myReader.close();
            for (int i=0;i<numberOfNodes;i++){
                for (int j = 0; j <numberOfNodes ; j++) {
                    System.out.print(graph[i][j]+ "   ");
                }
                System.out.println();
            }
            System.out.println();

/*
            Scanner scanner = new Scanner(System.in);
            System.out.println();
            System.out.println("Enter the source of the graph");
            source= scanner.nextInt() + 1;

            System.out.println("Enter the sink of the graph");
            sink = scanner.nextInt() + 1;*/
            source = 1;
            sink = numberOfNodes;
            FordFulkersonAlgo fordFulkersonAlgo = new FordFulkersonAlgo(numberOfNodes);
            maxFlow = fordFulkersonAlgo.fordFulkerson(graph, source, sink);
            System.out.println(" Max Flow is " + maxFlow);
            //scanner.close();

            //compile time tracking
            long endTime = System.nanoTime();

            long timeElapsed = endTime - startTime;

            System.out.println("Execution time in nanoseconds  : " + timeElapsed);

            System.out.println("Execution time in milliseconds : " +
                    timeElapsed / 1000000);


            System.out.println();
            System.out.println("graph = Adjency matrix representation  ");
            System.out.println();
//            for (int sourceVertex = 1; sourceVertex <= numberOfNodes; sourceVertex++) {
//                for (int destinationVertex = 1; destinationVertex <= numberOfNodes; destinationVertex++) {
//                    System.out.print(graph[sourceVertex][destinationVertex]);
//                }
//                System.out.println();
//            }


        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}