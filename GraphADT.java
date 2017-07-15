// Code written by: Steven Yen
// For: CIS 22C Summer 2016, Class Project #4
// Code purpose: ADT for the graph. Uses an adjacency lists stored in an array.
// The vertices/nodes for this graph are represented by integer keys. 
// The array indices represent the integer key value, and the associated linked-list
// indicate which vertices are connected to the current vertex.

// Referenced sample code provided on the companion website for 
// the book "Algorithms 4ed by Robert Sedgewick and Kevin Wayne."
// as a guidance.

//package cis22c_project4;

public class GraphADT {
    public int vertices; //total number of vertices/nodes in the graph.
    public int edges; //total number of edges.
    public IntList[] table; //array containing linked-lists as each elment. A
    //array index represent the key (or room number in our case), and the linked-list
    //contains the rooms that are connected to the current room.
    
    
    //Constructor. Intializes a graph containing number of elements equal to the 
    //input argument "size". Creates an array with empy linked-list as each element.
    public GraphADT(int size)
    {
        vertices = size;
        edges = 0;
        table = new IntList[vertices];
        
        for(int i=0;i<vertices; i++)
        {
            table[i]=new IntList();
        }
    }
    
    //insert a new edge connecting the vertices represented by the keys i and j.
    public void insertEdge(int i, int j){
        table[i].insert(j); //adds j to the adjacency list of i.
        table[j].insert(i); //adds i to the adjacency list of j.
        edges++;
    }
    
    //print the entire adjacency list showing what each vertices are connected to.
    public void showTable()
    {
        for(int i=0;i<vertices;i++)
        {
            System.out.print(i+": ");
            for(Object x: table[i]){
                System.out.print(x+" ");
            }
            System.out.println();
        }
    }
    
    //return boolean value indicating whether two vertices v and w are adjacent.
    public boolean areAdjacent(int v, int w)
    {
        return (this.table[v].find(w));
    }
    
    //remove edge connecting vertices v and w.
    public void removeEdge(int v, int w)
    {
        table[v].remove(w);
        table[w].remove(v);
    }
    
    //returns true/false indicating whether there's edge between v and w.
    public boolean containsEdge(int v, int w)
    {
        return (table[v].find(w));
    }
    
    //test client for GraphADT.
//    public static void main(String[] args){
//        GraphADT testGraph = new GraphADT(5);
//        
//        testGraph.insertEdge(0,2);
//        testGraph.insertEdge(1,3);
//        testGraph.insertEdge(0,4);
//        testGraph.insertEdge(2,3);
//        testGraph.insertEdge(2,3);
//        testGraph.insertEdge(2,3);
//        testGraph.showTable();
//        System.out.println("Contains edge (3,2)?"+testGraph.containsEdge(3, 2));
//        System.out.println("remove edge connecting 2,3:");
//        testGraph.removeEdge(2, 3);
//        testGraph.showTable();
//        System.out.println("Contains edge (3,2)?"+testGraph.containsEdge(3, 2));
//        
//    } 

}