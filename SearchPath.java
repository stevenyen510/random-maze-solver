// Code written by: Steven Yen
// For: CIS 22C Summer 2016, Class Project #4
// Code purpose: class definition for the DFS and BFS searches.

// Referenced the project assignment for pseudocode on DFS and BFS.
// Additionally, referenced sample code provided on the companion website for 
// the book "Algorithms 4ed by Robert Sedgewick and Kevin Wayne."
// as a guidance.

//package cis22c_project4;

public class SearchPath {
    
    public boolean[] visited; //an array containing true/false to track whether a room has been visited.
    public int count; //number of rooms visited in a given search path
    
    public int[] parentList; //list tracking which new room is visited from. 
    //this allows us to backtrack from the last room to the first room, finding
    //the shortest path based on the BFS/DFS search.
    
    //constructor for search path. Intializes the arrays for storing visited status and parent.
    SearchPath(GraphADT maze, int s){
        visited = new boolean[maze.vertices];
        parentList = new int[maze.vertices];
    }
   
    //DFS search based on algorithm provided in the lab 4 assignment.
    //Visits adjacent rooms in the order North, South, East, West.
    //takes as input an object of GraphADT (the maze) and outputs path
    //path to go from start room (0) to gaol room (N-1).
    public void dfs_path(GraphADT maze){
        StackADT S = new StackADT();
        int N = maze.vertices;
        int n = (int)Math.sqrt(N);
        
        
        
        S.push(0);
        visited[0]=true;
        System.out.print("Rooms visited by DFS: ");
        int i=0;
        int j=0;
        
        while (!S.isEmpty()){
            
            i = S.pop();
            
            visited[i]=true;
            System.out.print(" "+i);
            
            if(i==(N-1)){
                //System.out.println("Path found!");
                break;
            }
            
            //consider the adjacent rooms in the order north, south, east, west
            if((maze.table[i].find(i-n))&&(!visited[i-n]))
            {S.push(i-n); parentList[i-n]=i;}//visited[i-n]=true;System.out.print(" "+(i-n));} //north
            
            if((maze.table[i].find(i+n))&&(!visited[i+n]))
            {S.push(i+n); parentList[i+n]=i;}//visited[i+n]=true; System.out.print(" "+(i+n));} //south
            
            if((maze.table[i].find(i+1))&&(!visited[i+1]))
            {S.push(i+1); parentList[i+1]=i;}//visited[i+1]=true; System.out.print(" "+(i+1));} //east
            
            if((maze.table[i].find(i-1))&&(!visited[i-1]))
            {S.push(i-1); parentList[i-1]=i;}//visited[i-1]=true; System.out.print(" "+(i-1));} //west
            
            }
      
      System.out.println();
      
      
      //printing the path using the parentList array to backtrack.
      System.out.print("This is the path (in reverse): ");
      int k=N-1;
      while(k!=0){
          System.out.print(k+" ");
          k=parentList[k];
      }
      System.out.print(0+" ");
      System.out.println();
      
      //printing the path graphically.
     
      System.out.println("This is the path.");
      
      int[] shortestPath = new int[N];
      int x = N-1;
      shortestPath[x]=1;
      while(x!=0){
          shortestPath[parentList[x]]=1;
          x=parentList[x];
      }
      
      for(int idx=0;idx<N;idx++)
      {
          if((idx>0)&&((idx%n)==0)){System.out.println();}
          
          if(shortestPath[idx]==1)
          {System.out.print("X ");}
          else{System.out.print("  ");}
      }
              
      
     System.out.println();   
}
    
// BFS search based on algorithm provided in the lab 4 assignment. Visits 
// the adjacent rooms in the order North, South, East, West.
// Takes as input an object of GraphADT (the maze) and outputs path
// to go from start room (0) to goal room (N-1).
public void bfs_path(GraphADT maze){
    int N = maze.vertices;
    int n = (int)Math.sqrt(N);
    
    QueueADT Q = new QueueADT();
    
    Q.enqueue(0);
    visited[0] = true;
    System.out.print("Rooms visited by BFS: "+0);
    
    while(!Q.isEmpty()){
        int i = Q.dequeue();
        if(i==(N-1)){
            //System.out.println("Path found!");
            visited[i]=true;
            break;
        }
        
        //consider the adjacent rooms in the order north, south, east, west
        if((maze.table[i].find(i-n))&&(!visited[i-n])) //check north room
            {
                Q.enqueue(i-n); 
                visited[i-n]=true;
                System.out.print(" "+(i-n)); 
                parentList[i-n]=i; 
                if((i-n)==(N-1)){break;}
            }
        
        if((maze.table[i].find(i+n))&&(!visited[i+n])) //check south room
            {
                Q.enqueue(i+n); 
                visited[i+n]=true; 
                System.out.print(" "+(i+n)); 
                parentList[i+n]=i; //south
                if((i+n)==(N-1)){break;} 
            }
        
        if((maze.table[i].find(i+1))&&(!visited[i+1])) //check east room
            {
                Q.enqueue(i+1); 
                visited[i+1]=true; 
                System.out.print(" "+(i+1));
                parentList[i+1]=i;
                if((i+1)==(N-1)){break;}
            } //east
            
        
        if((maze.table[i].find(i-1))&&(!visited[i-1])) //check west room
            {
                Q.enqueue(i-1); 
                visited[i-1]=true; 
                System.out.print(" "+(i-1));
                parentList[i-1]=i;
                if((i-1)==(N-1)){break;}
            } //west
            //if((i-1)==(N-1)){break;}
     
    }
    
    System.out.println();
    System.out.print("This is the path (in reverse): ");
    int k=N-1;
    
    while(k!=0){
        
        System.out.print(k+" ");
        k=parentList[k];
    }
    System.out.print(0+" ");
    System.out.println();
    
     //printing the path graphically.
      
      System.out.println("This is the path.");
      
      int[] shortestPath = new int[N];
      int x = N-1;
      shortestPath[x]=1;
      while(x!=0){
          shortestPath[parentList[x]]=1;
          x=parentList[x];
      }
      
      for(int idx=0;idx<N;idx++)
      {
          if((idx>0)&&((idx%n)==0)){System.out.println();}
          
          if(shortestPath[idx]==1)
          {System.out.print("X ");}
          else{System.out.print("  ");}
      }
      
      System.out.println();
    
}
   
}

    
