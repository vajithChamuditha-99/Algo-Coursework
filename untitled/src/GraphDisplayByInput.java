import java.util.Scanner;

/**
 *
 * @author vajith
 */

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
    }

    public int[] readFile(){
        In in = new In("1Kints.txt");
        int[] a = in.readAllInts();
        return a;

    }

    public static void main(String[] args) {
        System.out.println("Enter no of vertices: ");
        Scanner scanner=new Scanner(System.in);
        int i= scanner.nextInt();
        GraphDisplayByInput graph = new GraphDisplayByInput(i);
        int mm=(i-1);
        int max=mm*i; //max number of edges
        for (int j=0;j<max;j++){
            System.out.println("Enter source (Want to exit press 9999) : ");
            int k= scanner.nextInt();
            if (k==9999){
                break;
            }else {
                System.out.println("Enter target: ");
                int l= scanner.nextInt();
                System.out.println("Enter capacity: ");
                int m= scanner.nextInt();
                graph.addGraphEdge((k-1), (l-1),(m));
            }

        }

        graph.displayGraph();
    }
}