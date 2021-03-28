import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author vajith
 */


public class MaximumFlow {
    int[][] matrixDisplay;
    int vertex ;
    private static int lineOneInt;

    public MaximumFlow(int vertex) {
        this.vertex = vertex;
        matrixDisplay = new int[vertex][vertex];
    }

    public void addGraphEdge(int source, int target,int capacity) {
        //add edge
        matrixDisplay[source][target]=capacity;
    }

    public int findMaxFlow(int source, int sink) {
        //residual graph
        int[][] residualGraph = new int[vertex][vertex];

        //initialize residual graph same as original graph
        for (int i = 0; i <lineOneInt ; i++) {
            System.arraycopy(matrixDisplay[i], 0, residualGraph[i], 0, lineOneInt);
        }

        //initialize parent [] to store the path Source to destination
        int [] parent = new int[lineOneInt];

        int max_flow = 0; //initialize the max flow

        while(isPathExist_BFS(residualGraph, source, sink, parent)){
            //if here means still path exist from source to destination

            //parent [] will have the path from source to destination
            //find the capacity which can be passed though the path (in parent[])

            int flow_capacity = Integer.MAX_VALUE;
            int t = sink;
            while(t!=source){
                int s = parent[t];
                flow_capacity = Math.min(flow_capacity, residualGraph[s][t]);
                t = s;
            }

            //update the residual graph
            //reduce the capacity on fwd edge by flow_capacity
            //add the capacity on back edge by flow_capacity
            t = sink;
            while(t!=source){
                int s = parent[t];
                residualGraph[s][t]-=flow_capacity;
                residualGraph[t][s]+=flow_capacity;
                t = s;
            }

            //add flow_capacity to max value
            max_flow+=flow_capacity;
        }
        return max_flow;
    }


    public boolean isPathExist_BFS(int [][] residualGraph, int src, int dest, int [] parent){
        boolean pathFound = false;

        //create visited array [] to
        //keep track of visited vertices
        boolean [] visited = new boolean[vertex];

        //Create a queue for BFS
        Queue<Integer> queue = new LinkedList<>();

        //insert the source vertex, mark it visited
        queue.add(src);
        parent[src] = -1;
        visited[src] = true;

        while(!queue.isEmpty()){
            int u = queue.poll();

            //visit all the adjacent vertices
            for (int v = 0; v <lineOneInt ; v++) {
                //if vertex is not already visited and u-v edge weight >0
                if(!visited[v] && residualGraph[u][v]>0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        //check if dest is reached during BFS
        pathFound = visited[dest];
        return pathFound;
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
            lineOneInt=Integer.parseInt(lineOne[0]);
            System.out.println("Number of vertices: "+lineOneInt);
            System.out.println();
            MaximumFlow maximumFlow=new MaximumFlow(lineOneInt);
            //reading the next lines in the text file
            while(scanner.hasNext()){
                String[] lines=scanner.nextLine().trim().split( " ");
                int val=Integer.parseInt(lines[0]);
                int val2=Integer.parseInt(lines[1]);
                int val3=Integer.parseInt(lines[2]);
                System.out.println("Connected from "+(val)+" to "+(val2)+", capacity: "+val3);
                maximumFlow.addGraphEdge((val),(val2),val3);
            }
            maximumFlow.displayGraph();
            int source = 0;
            int destination = 3;
            int max_flow = maximumFlow.findMaxFlow(source,destination);
            System.out.println("Maximum flow from source: " + source + " to destination: " + destination
                    + " is: " + max_flow);
            System.out.println();
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

        System.out.println();
    }
}

