
public class GraphDisplayByInput {
    int[][] matrixDisplay;
    int vertex;


    public GraphDisplayByInput(int vertex) {
        this.vertex = vertex;
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
                System.out.print(matrixDisplay[i][j]+ " ");
            }
            System.out.println();
        }
        /*for (int i = 0; i < vertex; i++) {
            System.out.print("Vertex " + (i+1) + " is connected to:");
            for (int j = 0; j <vertex ; j++) {
                if(matrixDisplay[i][j]==1){
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }*/
    }

    public int[] readFile(){
        In in = new In("1Kints.txt");
        int[] a = in.readAllInts();
        return a;
    }

    public static void main(String[] args) {
        GraphDisplayByInput graph = new GraphDisplayByInput(4);

        graph.addGraphEdge(0, 1,6);

//        graph.addGraphEdge(0, 2,4);
//
//        graph.addGraphEdge(1, 2,2);
//
//        graph.addGraphEdge(1, 3,3);
//
//        graph.addGraphEdge(2, 3,5);

//        graph.addEdge(2, 5,0);
//
//        graph.addEdge(3, 5,0);
//
//        graph.addEdge(4, 6,0);
//
//        graph.addEdge(5, 2,0);
//
//        graph.addEdge(5, 4,0);
//
//        graph.addEdge(5, 6,0);


        graph.displayGraph();
    }
}