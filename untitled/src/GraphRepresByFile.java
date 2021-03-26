import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class GraphRepresByFile {
    int[][] matrixDisplay;
    private static final int vertex = 0;


//    public GraphRepresByFile(int vertex) {
//        this.vertex = vertex;
//        matrixDisplay = new int[vertex][vertex];
//    }

    public void addGraphEdge(int source, int target,int capacity) {
        //add edge
        matrixDisplay[source][target]=capacity;

    }

    public void displayGraph() {
        System.out.println("Graph Representation: Adjacency Matrix");
        System.out.println();

    }

    public static void readFile() throws FileNotFoundException {
        try {
            Scanner scanner=new Scanner(chooseFile());
            String[] lineOne=scanner.nextLine().trim().split(" ");
            int lineOneInt=Integer.parseInt(lineOne[0]);
            System.out.println("Number of vertices: "+lineOneInt);
            System.out.println();
            int [][] vertexArr=new int[lineOneInt][lineOneInt];
            vertexArr[1][2]=2;
            for (int i = 0; i < lineOneInt; i++) {
                for (int j = 0; j <lineOneInt ; j++) {
                    System.out.print(vertexArr[i][j]+ " ");
                }
                System.out.println();
            }


            while (scanner.hasNextLine()){
            }

        }catch (FileNotFoundException fe){
            System.out.println("File not found!!!...");
        }


    }

    public static File chooseFile(){
        FileDialog fileDialog=new FileDialog((Frame) null,"Select File");
        fileDialog.setMode(FileDialog.LOAD);
        fileDialog.setVisible(true);
        File[] files=fileDialog.getFiles();
        return files[0];
    }



    public static void main(String[] args) throws FileNotFoundException {
        readFile();
        //GraphRepresByFile graph = new GraphRepresByFile(6);
        //graph.addGraphEdge(2,2,2);
        //graph.displayGraph();
    }
}
