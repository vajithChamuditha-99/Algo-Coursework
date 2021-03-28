import java.util.Scanner;

/**
 *
 * @author vajith
 */

public class GraphDisplayByInput {
    int[][] matrixDisplay;
    int vertex;
    private static final Scanner scanner=new Scanner(System.in);
    private static int numOfVertices;

    public GraphDisplayByInput(int vertex) {
        this.vertex = vertex;
        matrixDisplay = new int[vertex][vertex];
    }

    public void addGraphEdge(int source, int target,int capacity) {
        //add edge
        matrixDisplay[source][target]=capacity;
    }

    public void deleteGraphEdge(int source, int target) {
        //add edge
        matrixDisplay[source][target]=0;
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

    public static void main(String[] args) {
        GraphDisplayByInput graphDisplayByInput=new GraphDisplayByInput(numOfVertices);
        System.out.println("Enter no of vertices: ");
        numOfVertices= scanner.nextInt();
        menu();
    }

    public static void menu(){
        GraphDisplayByInput graph = new GraphDisplayByInput(numOfVertices);
        System.out.println("------------------------------------------------");
        while (true){
            System.out.println();
            System.out.println("Press 1 to Display Graph");
            System.out.println("Press 2 to Add Edges");
            System.out.println("Press 3 to Delete Edges");
            System.out.println("Press 4 to Search Edges");
            System.out.println("Press 5 to Exit");
            System.out.print("Choice : ");
            int input=scanner.nextInt();
            switch (input){
                case 1:
                    graph.displayGraph();
                    break;
                case 2:
                    int maxCounting=(numOfVertices-1);
                    int maxEdgeNum=maxCounting*numOfVertices; //max number of edges
                    for (int j=0;j<maxEdgeNum;j++){
                        System.out.println("Enter source (Want to exit press 9999) : ");
                        int source= scanner.nextInt();
                        if (source==9999){
                            break;
                        }else {
                            System.out.println("Enter target: ");
                            int target= scanner.nextInt();
                            System.out.println("Enter capacity: ");
                            int capacity= scanner.nextInt();
                            graph.addGraphEdge((source-1), (target-1),(capacity));
                        }
                    }
                    System.out.println("Graph Updated!!!");
                    break;
                case 3:
                    System.out.println("Enter the Source: ");
                    int delSource= scanner.nextInt();
                    System.out.println("Enter the Target: ");
                    int delTarget= scanner.nextInt();
                    if (graph.matrixDisplay[delSource-1][delTarget-1]!=0){
                        graph.deleteGraphEdge((delSource-1), (delTarget-1));
                        System.out.println("Edge Deleted from " +delSource+ " to "+delTarget);
                    }else{
                        System.out.println("No Edges to Delete!!!...");
                    }
                    break;
                case 4:
                    System.out.println("Enter the Source: ");
                    int searchSource= scanner.nextInt();
                    System.out.println("Enter the Target: ");
                    int searchTarget= scanner.nextInt();
                    if (graph.matrixDisplay[searchSource-1][searchTarget-1]!=0){
                        System.out.println("Capacity of the Edge from " + searchSource + " to "+ searchTarget +" is "
                                + graph.matrixDisplay[searchSource-1][searchTarget-1] );
                    }else{
                        System.out.println("No Edges to Search!!!...");
                    }
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }
    }
}