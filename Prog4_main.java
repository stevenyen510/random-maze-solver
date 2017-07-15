// Code written by: Steven Yen
// For: CIS 22C Summer 2016, Class Project #4
// Code purpose: main method that'll read in a maze from text fileInput
// or randomly generate a maze. The program then solves the maze
// calling bfs and dfs, then outputs the result.
// The maze is represented by a graph where each vertex represents a room
// and each edge represents a connection (open doors) between 2 adjacent rooms.

// Followed project assignment as guidance.
// this class definition includes the main method and all the supporting
// methods for importing the maze or randomly generating the maze.

//package cis22c_project4;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Prog4_main {

public static void main(String[] args) throws FileNotFoundException {

int n; //the size of maze (specifically the width)
int N; //total rooms in the maze (equal to n*n)
int[][] array1; //matrix where each row contains 4 binary values (0/1) indicating position of NSEW doors.

//int choice; 
//choice = 1;

//main decision between importing a maze or randomly generating one.
if(args.length>0)
{
        Scanner fileInput = new Scanner(new FileInputStream(args[0])); 
        
        n = fileInput.nextInt();
        System.out.println("Maze size is n="+n);
        N = n*n; //total number of rooms
        
        array1 = new int[N][4];
        
        for(int i=0;i<array1.length;i++)
        {
            for(int j=0;j<4;j++)
            {
                array1[i][j] = fileInput.nextInt();
            }
           
        }
        
        fileInput.close();
            
        //for(int i=0;i<N;i++){System.out.print(i+": ");printArray(array1[i]);} //printing the array/matrix of 1's and 0's for debug.
        
} else{ //randomly generating a maze.
    Scanner userInput = new Scanner(System.in);
    
    System.out.print("Enter size (width) of maze: "); //allow user to input size (specifically the width) of randomly generated maze.
    n = userInput.nextInt();
    N=n*n;
    
    array1 = randomMaze(n);
    
}           
        showMaze(array1);

        GraphADT G1 = array2Graph(array1); //create graph represented in adjacency list form for the maze.
        
        //G1.showTable(); //the output looks good.
        
        //Solving the maze by BFS and printing the output.
        System.out.println();
        SearchPath m3Soln = new SearchPath(G1,0);
        m3Soln.bfs_path(G1);
        System.out.println();
        
        //Solving the maze by DFS and printing the ouptut.
        SearchPath m2Soln = new SearchPath(G1,0);
        m2Soln.dfs_path(G1);
        //System.out.println();
        //System.out.print("parentList: ");
        //printArray(m2Soln.parentList);
             
    }

    
    //Supporting method that converts a matrix with 1's and 0's indicating position
    //of doors for each room into a graph where each node/vertex represents a room
    //and the edges represent a path (open door between 2 rooms).
    public static GraphADT array2Graph(int[][] A)
    {
        int N = A.length;
        int n=(int)Math.sqrt(A.length); //this line works. Checked.
        
        GraphADT maze1 = new GraphADT(N);
        
        
        for(int i=0; i<A.length;i++)
        {
            if(i==0) //if we're at the first room
            {
                if(A[i][1]==0){maze1.insertEdge(i,i+n);} //if south door open, connect to south room
                if(A[i][2]==0){maze1.insertEdge(i,i+1);} //if east door open, connect to east room                
            }
            else if(i==(N-1)) //if we're at the last room
            {
                if(A[i][0]==0){maze1.insertEdge(i,i-n);} //if north door open, connect to north room
                if(A[i][3]==0){maze1.insertEdge(i,i-1);} //if west door open, connect to west room               
            }
            else
            {
                if(A[i][0]==0){maze1.insertEdge(i,i-n);} //if north door open, connect to north room
                if(A[i][1]==0){maze1.insertEdge(i,i+n);} //if south door open, connect to south room
                if(A[i][2]==0){maze1.insertEdge(i,i+1);} //if east door open, connect to east room
                if(A[i][3]==0){maze1.insertEdge(i,i-1);} //if west door open, connect to west room
            }
        }
        
        return maze1;
    }
 
    //supporting method that prints the matrix of 1's and 0's indicating position of NSEW doors of a room.
    public static void printArray(int[] X){
    System.out.print("[");
    for(int i=0; i<X.length; i++)
    {System.out.print(X[i]+" ");}
    System.out.println("]");
}
    
//This method generates a random maze. It takes as argument a positive
//integer n, and generates a random nxn mazeu using the algorithm provided in
//the lab 4 assignment. Using disjoint sets. This method uses the Math.random()
//method in the Math.lang package to randomly pick an adjacently room to connect to.   
public static int[][] randomMaze(int n){  //takes as input the side length of maze (not number of cells/rooms)

        int N=n*n; //total number of rooms in the maze. Based on the number of rooms, n, inputed.
        int dice; //temp variable storing the random value indicating which door to open.
        int x=0; //integer to store a randomly choosen room. 
               
        //initialize a disjoint set of room numbers, 0,1,2,...., N-1.
        DisjointSetADT DS2= new DisjointSetADT(N);
        
        //create an array to store the NSEW door positions for each room.
        int[][] newArray = new int[N][4];
        for(int i=0;i<N;i++)
        {
            for(int k=0;k<4;k++)
            {
                newArray[i][k]=1;
            }
        }
        
        //for(int i=0;i<N;i++)
        //{Steven_Yen_prog4.printArray(newArray[i]);}
        
        //x = (int) (Math.random()*N); //random number from 0 to N-1

        while(DS2.find(0)!=DS2.find(N-1))
        {
            x = (int) (Math.random()*N); //random number from 0 to N-1
            if(x==0) //the randomly chosen room is the start room (NW)
            {
                dice =(int) (Math.random()*2); //random number from 0 to 1 
                //indicating whether to open door to adjacent room to the east
                //or the adjacent room to the south.
             
                 switch (dice)
                    {
                     case 0:
                         DS2.union(DS2.find(x),DS2.find(x+1)); //union with room to east.
                         
                         //open door to east
                         newArray[x][2]=0;
                         newArray[x+1][3]=0;
                         //mazeNew.insertEdge(x, x+1);
                         break;
                     case 1:
                         DS2.union(DS2.find(x),DS2.find(x+n)); //union with room to south.
                         //mazeNew.insertEdge(x, x+n);

                         //open door to south
                         newArray[x][1]=0;
                         newArray[x+n][0]=0;
                         break;
                     default:
                         break;
                    }
            }
            else if(x==(N-1)) //room is the goal room (SE) or room #(N-1)
            {
                dice =(int) (Math.random()*2); //random number from 0 to 1
                //random value indicating whether to open door to west or north.
                
                switch (dice)
                    {
                    case 0:
                        DS2.union(DS2.find(x),DS2.find(x-1)); //union with room to west
                        //mazeNew.insertEdge(x, x-1);

                         //open door to west
                         newArray[x][3]=0;
                         newArray[x-1][2]=0;

                        break;
                    case 1:    
                        DS2.union(DS2.find(x),DS2.find(x-n)); //union with room to North
                        //mazeNew.insertEdge(x, x-n);

                         //open door to north
                         newArray[x][0]=0;
                         newArray[x-n][1]=0;

                        
                        break;
                    default:
                        break;
                    }
            }
            else if(x==(n-1)) //room is north east corner
            {
                dice =(int) (Math.random()*2); //random number from 0 to 1
                //indicating whether to open the door to the west or the south.
                
                switch (dice)
                    {
                    case 0:
                        DS2.union(DS2.find(x),DS2.find(x-1)); //union with room to west

                         //open door to west
                         newArray[x][3]=0;
                         newArray[x-1][2]=0;

                        break;
                    case 1:    
                        DS2.union(DS2.find(x),DS2.find(x+n)); //union with room to south
                      

                         //open door to south
                         newArray[x][1]=0;
                         newArray[x+n][0]=0;
                        
                        break;
                    default:
                        break;
                    }
            }
            else if(x==(N-n)) //room is south west corner
            {
                dice =(int) (Math.random()*2); //random number from 0 to 1
                
                switch (dice)
                    {
                    case 0:
                        DS2.union(DS2.find(x),DS2.find(x+1)); //union with room to east
                       
                         //open door to east
                         newArray[x][2]=0;
                         newArray[x+1][3]=0;

                        break;
                    case 1:    
                        DS2.union(DS2.find(x),DS2.find(x-n)); //union with room to North
                        
                         //open door to north
                         newArray[x][0]=0;
                         newArray[x-n][1]=0;

                        break;
                    default:
                        break;
                    }
            }
            else if(x<n) //room is on north wall
            {
                dice =(int) (Math.random()*3); //random number from 0 to 2
                
                switch (dice)
                    {
                    case 0:
                        DS2.union(DS2.find(x),DS2.find(x-1)); //union with room to west
                        
                        openDoor(newArray,x,3);
                        
                        break;
                    case 1:    
                        DS2.union(DS2.find(x),DS2.find(x+1)); //union with room to east
                        
                        openDoor(newArray,x,2);
                        break;
                    case 2:    
                        DS2.union(DS2.find(x),DS2.find(x+n)); //union with room to south
                        
                        openDoor(newArray,x,1);
                        break;
                    default:
                        break;
                    }
            }
            else if(x>(N-n))//room is on south wall
            {
                dice =(int) (Math.random()*3); //random number from 0 to 2
                
                switch (dice) 
                    {
                    case 0:
                        DS2.union(DS2.find(x),DS2.find(x-1)); //union with room to west
                        //mazeNew.insertEdge(x, x-1);
                        openDoor(newArray,x,3);
                        break;
                    case 1:    
                        DS2.union(DS2.find(x),DS2.find(x+1)); //union with room to east
                        //mazeNew.insertEdge(x, x+1);
                        openDoor(newArray,x,2);
                        break;
                    case 2:    
                        DS2.union(DS2.find(x),DS2.find(x-n)); //union with room to north
                        //mazeNew.insertEdge(x, x-n);
                        openDoor(newArray,x,0);
                        break;
                    default:
                        break;
                    }
            }            
            else if((x%n)==0) //room is on west wall
            {
                dice =(int) (Math.random()*3); //random number from 0 to 2
                
                switch (dice)
                    {
                    case 0:
                        DS2.union(DS2.find(x),DS2.find(x-n)); //union with room to north
                        //mazeNew.insertEdge(x, x-n);
                        openDoor(newArray,x,0);
                        break;
                    case 1:    
                        DS2.union(DS2.find(x),DS2.find(x+n)); //union with room to south
                        //mazeNew.insertEdge(x, x+n);
                        openDoor(newArray,x,1);
                        break;
                    case 2:    
                        DS2.union(DS2.find(x),DS2.find(x+1)); //union with room to east
                        //mazeNew.insertEdge(x, x+1);
                        openDoor(newArray,x,2);
                        break;
                    default:
                        break;
                    }
            }
            else if(((x+1)%n)==0) //room is on east wall
            {
                dice =(int) (Math.random()*3); //random number from 0 to 2
                
                switch (dice)
                    {
                    case 0:
                        DS2.union(DS2.find(x),DS2.find(x-n)); //union with room to north
                        //mazeNew.insertEdge(x, x-n);
                        openDoor(newArray,x,0);
                        break;
                    case 1:    
                        DS2.union(DS2.find(x),DS2.find(x+n)); //union with room to south
                        //mazeNew.insertEdge(x, x+n);
                        openDoor(newArray,x,1);
                        break;
                    case 2:    
                        DS2.union(DS2.find(x),DS2.find(x-1)); //union with room to west
                        //mazeNew.insertEdge(x, x-1);
                        openDoor(newArray,x,3);
                        break;
                    default:
                        break;
                    }
            }                
            else
            {
                dice =(int) (Math.random()*4); //random number from 0 to 3
                
                switch (dice)
                    {
                    case 0:
                        DS2.union(DS2.find(x),DS2.find(x-n)); //union with room to north
                        //mazeNew.insertEdge(x, x-n);
                        openDoor(newArray,x,0);
                        break;
                    case 1:    
                        DS2.union(DS2.find(x),DS2.find(x+n)); //union with room to south
                        //mazeNew.insertEdge(x, x+n);
                        openDoor(newArray,x,1);
                        break;
                    case 2:    
                        DS2.union(DS2.find(x),DS2.find(x-1)); //union with room to west
                        //mazeNew.insertEdge(x, x-1);
                        openDoor(newArray,x,3);
                        break;
                    case 3:    
                        DS2.union(DS2.find(x),DS2.find(x+1)); //union with room to east
                        //mazeNew.insertEdge(x, x+1);
                        openDoor(newArray,x,2);
                        break;
                    default:
                        break;
                    }
            }  
        
    }
        
    //DS2.printDSArray();
    //StdOut.println(mazeNew);
    
    newArray[0][0]=0; //open north door of start room
    newArray[(N-1)][1]=0; //open south door of goal room
    
    //for(int i=0;i<N;i++)
    //{Steven_Yen_prog4.printArray(newArray[i]);}
    

    return newArray;
    
    
}

//supporting method to print the maze taking in as input a matrix of 1's and 0's
//indicating the position of the NSEW doors of a room.
public static void showMaze(int[][]A){ //print maze when given array of 1 and 0's
       //Printing the maze
       
      int n=(int)Math.sqrt(A.length);
               
        System.out.print("   ");
        for(int i=1;i<n;i++){System.out.print("_ ");}
        System.out.println();
        System.out.print("|");
              
        for(int i=0;i<A.length;i++)
        {
            if((i>0)&&((i%n)==0))
            {
                System.out.println();
                System.out.print("|");
            }
                        
//            if(array1[i][3]==1){System.out.print("|");}
//            else{System.out.print(" ");}
            
            if(A[i][1]==1){System.out.print("_");}
            else{System.out.print(" ");}
            
            if(A[i][2]==1){System.out.print("|");}
            else{System.out.print(" ");}
                      
        }
        
        System.out.println();        
        //End of maze print.
}

//Supporting method to be used by random maze generator. 
//This method simulates the act of opening doors to connect two adjacent rooms.
//it does so by modifiying the matrix of 1's and 0's indicating the NSEW door
//position of each room. It indicates open door by changing the position of doors
//connecting two rooms to open (represented by 0) in the matrix.
public static void openDoor(int[][] A, int x, int door)
{
    int n=(int)Math.sqrt(A.length); //this line works. Checked.
    //System.out.println("n="+n);
    switch (door)
    {
       
        case 2:
             //open door to east
             A[x][2]=0;
             A[x+1][3]=0;
             break;
        case 3:
            //open door to west
            A[x][3]=0;
            A[x-1][2]=0;
            break;
        case 0:
            //open door to north
            A[x][0]=0;
            A[x-n][1]=0;
            break;
        case 1:     
            //open door to south
            A[x][1]=0;
            A[x+n][0]=0; 
            break;
        default:
            break;
    }
}
    
    
}

