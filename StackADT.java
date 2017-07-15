// Code written by: Steven Yen
// For: CIS 22C Summer 2016, Class Project #4
// Code purpose: stack ADT for use in the DFS algorithm. Supports LIFO methods
// push to the top of the stack, and pop from the top of the stack.

// Referenced sample code provided on the companion website for 
// the book "Algorithms 4ed by Robert Sedgewick and Kevin Wayne."
// as a guidance.
  
//package cis22c_project4;
  
import java.util.Iterator;

public class StackADT implements Iterable{
    public int size; //number of nodes in list.
    public Node head; //front of the stack.
    
    //Interal class defintion for the nodes
    private class Node{
        public Integer value; //Integer value stored in the node
        public Node next; //reference to the next item in stack.
    }
    
    //default no-value constructor.
    StackADT(){
        head = null;
        size = 0;
    }
    
    //push a new node containing value x to the top of the stack.
    public void push(Integer x){
        
        Node newNode = new Node();
        newNode.value = x;
        
        newNode.next=head;
        head = newNode;
       
        size++; 
    }
    
    //remove a node from the top of the stack and return the value.
    public Integer pop(){
        Integer itemP = head.value;
        
        head = head.next;
        return itemP;
    }
    
    //return true false value indicating whether stack is empty.
    public boolean isEmpty(){return head==null;}
    
    //iterator to implementing the Iterator interface and allowing using the associated methods.
    public class myIterator implements Iterator{
        public Node iterCurrent;
        
        public myIterator(Node x)
        {
            iterCurrent = x;
        }
    
        public boolean hasNext()
        {return iterCurrent !=null;}
        
        public Integer next()
        {
            if(!hasNext()){System.out.println();}
            
            Integer temp;
            temp = (Integer) iterCurrent.value;
            iterCurrent = iterCurrent.next;
            return temp;
        }
        
    }
   
    public Iterator iterator(){
        return new myIterator(head);
    }

    
}
