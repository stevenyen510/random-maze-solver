// Code written by: Steven Yen
// For: CIS 22C Summer 2016, Class Project #4
// Code purpose: DisjointSet ADT used for random maze generator.
// supports union and find operations on sets of integers.
// Impelmented in an array. The array index represent the key of a 
// specific element. The value contained in the element (indexed variable)
// if positive, represent the parent of the current node.
// if negative, means the current node is the root node, and the absolute value of the negative number
// represent the size of the disjoint set/tree.

// Referenced Berkeley CS61B lecture by Jonathan Shewchuk on disjoint sets as guidance.

//package cis22c_project4;

public class DisjointSetADT {
    
    int[] ForestArray; //the main array for disjoint set. See above comments for description.
    int size; //total elements contained in the disjoint sets (sum of size of all disjoint sets).
    
    
    //constructor for the disjoint set. The input s to be the total elements 
    //contained in the disjoint set. Initially, each element is in its own disjoint
    //set. Each array element is -1, indicating that it is a root to a tree containing
    //just one element. 
    DisjointSetADT(int s)
    {
        size = s;
        
        ForestArray = new int[size];
        
        for(int i=0;i<size;i++)
        {
         ForestArray[i]=-1;   
        }
    }
    
    
    //void method that unions two disjoint sets when given the root nodes of 
    //the two disjoint sets to be unioned.
    public void union(int r1, int r2)
    {
        if(r1==r2)
        {
            //System.out.println("Unioning with self. Do nothing.");
        }
        else
        {
            if(ForestArray[r2]<ForestArray[r1])
            {
                ForestArray[r2] = ForestArray[r1]+ForestArray[r2];
                ForestArray[r1] = r2;
            }
            else
            {
                ForestArray[r1] = ForestArray[r1]+ForestArray[r2];
                ForestArray[r2] = r1;
            }
        }
    }
    
    //Find method that returns the root of node of the disjoint set containing v.
    public int find(int v)
    {
        if(ForestArray[v]<0)
        {
            return v;
        }
        else
        {
            ForestArray[v] = find(ForestArray[v]);
            return ForestArray[v];
        }
    }
    
    //void method printing the array containing the disjoint set. 
    //used for troubleshooting. Note -1 means it is isolated.
    public void printDSArray()
    {
     System.out.print(" ");
     for(int i=0;i<size;i++)
     {
     System.out.printf("%3d ",i);
     }
     System.out.println(" ");
    
   
     System.out.print("[");
     
     for(int i=0;i<size;i++)
     {
         System.out.printf("%3d ",this.ForestArray[i]);
     }
     System.out.println("]");
     
    }
    
//Test client for the DisjointSet ADT. See Prog4_main for the main method for this assignment.
//public static void main(String[] args) {
//
//    DisjointSetADT DS1 = new DisjointSetADT(9);
//    DS1.printDSArray();
//    
//    DS1.union(5, 2);
//    DS1.printDSArray();
//    DS1.union(3, 7);
//    DS1.printDSArray();
// 
//
//    DS1.union(3, 5);
//    DS1.printDSArray();
//    System.out.println("DS1.find(4)="+DS1.find(4));
//    System.out.println("DS1.find(2)="+DS1.find(2));
//    DS1.union(DS1.find(4),DS1.find(2));
//    DS1.printDSArray();
//    
//    DS1.union(DS1.find(4),DS1.find(2));
//    DS1.union(DS1.find(4),DS1.find(2));
//    DS1.union(DS1.find(4),DS1.find(2));
//    DS1.union(DS1.find(4),DS1.find(2));
//    DS1.printDSArray();
//
//
//}
}