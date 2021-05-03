import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author Vajith Chamuditha
 * IIT ID :- 2019437
 * UoW ID :- W1761107
 *
 */

public class MaximumFlow {
    int[][] matrixDisplay;
    private static int noOfVertices;
    private static int count;

    public MaximumFlow() {
        //initializing the 2d array for the matrix
        matrixDisplay = new int[noOfVertices][noOfVertices];
    }

    public void addGraphEdge(int source, int target,int capacity) {
        //add edge
        matrixDisplay[source][target]=capacity;
    }

    public int findMaxFlow(int source, int target) {
        Stopwatch stopwatch1=new Stopwatch();
        //initializing residual graph
        int[][] residualGraphMatrix = new int[noOfVertices][noOfVertices];
        //initialize residual graph same as matrixDisplay graph
        for (int i = 0; i <noOfVertices ; i++) {
            System.arraycopy(matrixDisplay[i], 0, residualGraphMatrix[i], 0, noOfVertices);
        }
        //initialize array to store the paths
        int [] parentNodes = new int[noOfVertices];
        //initializing variable to store maximum flow
        int maximumFlow = 0;
        while(pathSearchBFS(residualGraphMatrix, source, target, parentNodes)){
            //checking the path is available from source to destination
            int flowStore = Integer.MAX_VALUE;
            int targetNode = target;
            while(targetNode!=source){
                int sourceNode = parentNodes[targetNode];
                //find the maximum flow which can be passed through the path (finding the minimum residual capacity)
                flowStore = Math.min(flowStore, residualGraphMatrix[sourceNode][targetNode]);
                //System.out.println("Total flow calculated is "+flowStore+ " by path "+ sourceNode+" to "  + targetNode);
                targetNode = sourceNode;
            }
            targetNode = target;
            while(targetNode!=source){
                //updating the residual graph array
                int sourceNode = parentNodes[targetNode];
                //reduce the flowStore capacity from the forward edge
                residualGraphMatrix[sourceNode][targetNode]-=flowStore;
                //add the flowStore capacity for the backward edge
                residualGraphMatrix[targetNode][sourceNode]+=flowStore;
                targetNode = sourceNode;
            }
            //updating the maximum flow by adding flowStore
            int currMaxFlow=maximumFlow;
            maximumFlow+=flowStore;
            System.out.println("Current flow: " + currMaxFlow +" + " +flowStore +" = "+ maximumFlow);
        }
        double elapsedTime=stopwatch1.elapsedTime();
        System.out.println("Time taken: "+elapsedTime+ " seconds.");
        //System.out.println("tt"+ stopwatch1.elapsedTime()*1000000);
        return maximumFlow;
    }
    public boolean pathSearchBFS(int [][] residualGraph, int sourceNode, int sinkNode, int [] parentArr){
        //initializing an array to store visited nodes
        boolean [] visited_nodes = new boolean[noOfVertices];
        //mark all the nodes as not visited
        for(int i=0; i<noOfVertices; i++)
            visited_nodes[i]=false;
        //Creating a integer queue for breadth first search
        Queue<Integer> BFS_queue = new LinkedList<Integer>();
        //visiting the node and change the boolean value to true to inform that is visited add it to queue
        visited_nodes[sourceNode] = true;
        BFS_queue.add(sourceNode);
        parentArr[sourceNode] = -1;
        while(!BFS_queue.isEmpty()){
            //removing the first element in the queue
            int element_u = BFS_queue.poll();
            //iterating through all the nodes to visit them
            for (int element_v = 0; element_v <noOfVertices ; element_v++) {
                //checking if edge does not visited yet and it has a capacity
                if(!visited_nodes[element_v] && residualGraph[element_u][element_v]>0) {
                    //visiting the node and change the boolean value to true to inform that is visited add it to queue
                    visited_nodes[element_v] = true;
                    BFS_queue.add(element_v);
                    parentArr[element_v] = element_u;
                }
            }
        }
        //check if destination is reached during BFS
        if (visited_nodes[sinkNode]){
            return true;
        }else {
            return false;
        }
    }

    public void displayGraph() {
        System.out.println();
        System.out.println("Graph Representation: Adjacency Matrix");
        //printing the graph as matrix horizontally and vertically
        for (int i = 0; i < noOfVertices; i++) {
            for (int j = 0; j <noOfVertices ; j++) {
                System.out.print(matrixDisplay[i][j]+ "   ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void calculate() {
        try {
            Scanner scanner=new Scanner(chooseFile());
            //reading the first line of the file and adding it to an array
            String[] lineOne=scanner.nextLine().trim().split(" ");
            //convert it to integer
            noOfVertices=Integer.parseInt(lineOne[0]);
            System.out.println();
            MaximumFlow maximumFlow=new MaximumFlow();
            //reading the next lines in the text file
            while(scanner.hasNext()){
                count++;
                //adding the read values from text to a string array
                String[] linesOfTheText=scanner.nextLine().trim().split( " ");
                //parsing the array values to variables as integers
                int source_value=Integer.parseInt(linesOfTheText[0]);
                int end_value=Integer.parseInt(linesOfTheText[1]);
                int capacity_value=Integer.parseInt(linesOfTheText[2]);
                System.out.println("Connected from "+(source_value)+" to "+(end_value)+", capacity: "+capacity_value);
                //adding values to the matrix array by addGraphEdge method
                maximumFlow.addGraphEdge((source_value),(end_value),capacity_value);
            }
            maximumFlow.displayGraph();
            int source = 0;
            int destination = noOfVertices-1;
            //sTime=stopwatch1.elapsedTime();
            int max_flow = maximumFlow.findMaxFlow(source,destination);
            System.out.println("Number of Edges: "+count);
            System.out.println("Number of Vertices: "+noOfVertices);
            System.out.println("Maximum possible flow from source: " + source + " to destination: " + destination
                    + " is: " + max_flow);
        }catch (FileNotFoundException | NoSuchElementException | NumberFormatException | ArrayIndexOutOfBoundsException fe){
            //for catch errors
            System.out.println("File not found to import Data or Issue with the data!!!...");
        }
    }

    public static File chooseFile(){
        //creating a file dialog to choose the file
        FileDialog fileDialog=new FileDialog((Frame) null,"Select File");
        fileDialog.setMode(FileDialog.LOAD);
        fileDialog.setVisible(true);
        File[] files=fileDialog.getFiles();
        return files[0];
    }

    public static void main(String[] args)  {
        //Stopwatch stopwatch=new Stopwatch();
        calculate();

    }
}

