import java.awt.*;
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author vajith
 *
 * TASK :- 03
 */


public class GraphRepresByFile {
    int[][] matrixDisplay;
    int vertex ;

    public GraphRepresByFile(int vertex) {
        this.vertex = vertex;
        matrixDisplay = new int[vertex][vertex];
    }

    public void addGraphEdge(int source, int target,int capacity) {
        //add edge
        matrixDisplay[source][target]=capacity;
    }

    public void displayGraph() {
        System.out.println();
        System.out.println("Graph Representation: Adjacency Matrix");
        System.out.println();
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j <vertex ; j++) {
                System.out.print(matrixDisplay[i][j]+ "   ");
            }
            System.out.println();
        }
    }
    public static void readFile() {
        try {
            Scanner scanner=new Scanner(chooseFile());
            //reading the first line of the file
            String[] lineOne=scanner.nextLine().trim().split(" ");
            //convert it to integer
            int lineOneInt=Integer.parseInt(lineOne[0]);
            System.out.println("Number of vertices: "+lineOneInt);
            System.out.println();
            GraphRepresByFile graphRepresByFile=new GraphRepresByFile(lineOneInt);
            //reading the next lines in the text file
            while(scanner.hasNext()){
                String[] lines=scanner.nextLine().trim().split( " ");
                int val=Integer.parseInt(lines[0]);
                int val2=Integer.parseInt(lines[1]);
                int val3=Integer.parseInt(lines[2]);
                System.out.println("Connected from "+(val)+" to "+(val2)+", capacity: "+val3);
                graphRepresByFile.addGraphEdge((val),(val2),val3);
            }
            graphRepresByFile.displayGraph();
        }catch (FileNotFoundException | NoSuchElementException | NumberFormatException fe){
            System.out.println("File not found to import Data or Issue with the data!!!...");
        }
    }

    public static File chooseFile(){
        FileDialog fileDialog=new FileDialog((Frame) null,"Select File");
        fileDialog.setMode(FileDialog.LOAD);
        fileDialog.setVisible(true);
        File[] files=fileDialog.getFiles();
        return files[0];
    }

    public static void main(String[] args)  {
        readFile();
    }
}
