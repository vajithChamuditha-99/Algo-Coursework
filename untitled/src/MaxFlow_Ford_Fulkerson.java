import java.util.LinkedList;
import java.util.Queue;

public class MaxFlow_Ford_Fulkerson {

    int vertices;
    int[][] graph;

    public MaxFlow_Ford_Fulkerson(int vertex, int[][] graph) {
        this.vertices = vertex;
        this.graph = graph;
    }

    public int findMaxFlow(int source, int sink) {
        //residual graph
        int[][] residualGraph = new int[vertices][vertices];

        //initialize residual graph same as original graph
        for (int i = 0; i <vertices ; i++) {
            System.arraycopy(graph[i], 0, residualGraph[i], 0, vertices);
        }

        //initialize parent [] to store the path Source to destination
        int [] parent = new int[vertices];

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
        boolean [] visited = new boolean[vertices];

        //Create a queue for BFS
        Queue<Integer> queue = new LinkedList<>();

        //insert the source vertex, mark it visited
        queue.add(src);
        parent[src] = -1;
        visited[src] = true;

        while(!queue.isEmpty()){
            int u = queue.poll();

            //visit all the adjacent vertices
            for (int v = 0; v <vertices ; v++) {
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


    public static void main(String[] args) {
        int vertices = 10;
//        int[][] graph = { {0, 10, 8, 0, 0, 0},
//                {0, 0, 5, 5, 0, 0},
//                {0, 4, 0, 0, 10, 0},
//                {0, 0, 9, 0, 10, 3},
//                {0, 0, 0, 6, 0, 14},
//                {0, 0, 0, 0, 0, 0}
//        };

//        int graph[][] =new int[][] { {0, 16, 13, 0, 0, 0},
//                {0, 0, 10, 12, 0, 0},
//                {0, 4, 0, 0, 14, 0},
//                {0, 0, 9, 0, 0, 20},
//                {0, 0, 0, 7, 0, 4},
//                {0, 0, 0, 0, 0, 0}
//        };
        int graph[][] =new int[][] {
                {0 ,  1 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  8 ,  0 },
                {0 ,  0 ,  1 ,  0 ,  0 ,  0 ,  0 ,  6 ,  0 ,  8},
                {0 ,  0 ,  0 ,  1 ,  0 ,  0 ,  4 ,  0 ,  6 ,  0},
                {0 ,  0 ,  0 ,  0 ,  1 ,  2 ,  0 ,  4 ,  0 ,  0},
                {0 ,  0 ,  0 ,  0 ,  0 ,  1 ,  2 ,  0 ,  0 ,  0},
                {0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  1 ,  0 ,  0 ,  0},
                {0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  1 ,  0 ,  0},
                {0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  1 ,  0},
                {0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  1},
                {0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0},


        };
        MaxFlow_Ford_Fulkerson g = new MaxFlow_Ford_Fulkerson(vertices, graph);
        int source = 2;
        int destination = 6;
        int max_flow = g.findMaxFlow(source,destination);
        System.out.println("Maximum flow from source: " + source + " to destination: " + destination
                + " is: " + max_flow);
    }
}