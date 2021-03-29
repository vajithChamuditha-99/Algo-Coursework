/**
 *
 * @author vajith
 *
 * TASK :- 02
 */

public class GraphRepresentation {
    int[][] matrixDisplay;
    int vertex;


    public GraphRepresentation(int vertex) {
        this.vertex = vertex;
        //initializing the 2d array
        matrixDisplay = new int[vertex][vertex];
    }

    public void addGraphEdge(int source, int target,int capacity) {
        //add edge
        matrixDisplay[source][target]=capacity;

        //add back edge for undirected graph
        //matrixDisplay[target][source] = capacity;
    }

    public void displayGraph() {
        System.out.println("Graph Representation: Adjacency Matrix");
        System.out.println();
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j <vertex ; j++) {
                System.out.print(matrixDisplay[i][j]+ "   ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GraphRepresentation graph = new GraphRepresentation(4);

        graph.addGraphEdge(0, 1,6);

        graph.addGraphEdge(0, 2,4);

        graph.addGraphEdge(1, 2,2);

        graph.addGraphEdge(1, 3,3);

        graph.addGraphEdge(2, 3,5);


        graph.displayGraph();
    }
}