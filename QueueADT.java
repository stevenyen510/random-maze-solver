// Code written by: Steven Yen
// For: CIS 22C Summer 2016, Class Project #4
// Code purpose: Queue ADT for use in the BFS algorithm. Supports FIFO methods
// enqueue to the end, and dequeue from the front.

// Referenced sample code provided on the companion website for 
// the book "Algorithms 4ed by Robert Sedgewick and Kevin Wayne."
// as a guidance.

//package cis22c_project4;

import java.util.Iterator;

public class QueueADT 
{
    //internal class definition for the nodes.
    private class Node{
        public int item; //integer value contained in current node.
        public Node next; //reference to the next item.
        
        Node(){item=0;next=null;} //default no input constructor
        
        Node(Node n1){item=n1.item;next=n1.next;} //constructor with object argument
        
        Node(int num, Node nodeIn){item=num; next=nodeIn;} //default intializing instance vars.
        
    }
    
    public Node head; //the front of the queue
    public Node tail; // the rear of the queue
    
    int size=0; //size of queue 
    //char item; //item removed
    
    QueueADT(){
    head = null;
    tail = null;
    }
    
    boolean enqueue(int num)
    {
       Node newNode = new Node(num,null);
        
       if(head==null&&tail==null)
       {
           head=newNode;
           tail=newNode;
       }
       else
       {
        tail.next =newNode;
        tail=newNode;
       }

        size++;
        
        return true;
   }   
    
    int dequeue()
    {
        int x;
        if(isEmpty()){
            System.out.println("Queue is empty, cannot dequeue.");
        }
        
        x = head.item; //store item removed to x.
        head = head.next;

        size--;
        
        if(size==0) {tail=null; head=null;}
        //System.out.println("dequeued "+item+", front index set to: "+front+". (rear= "+rear+").");
        //print();
        return x;
    }
    
    
    void print(){
        Node temp = new Node();
        
        if(head==null)
        {System.out.println("Contents of Queue:     ");//empty
        }
        else
        {
        temp = head;
        
        System.out.print("Contents of Queue: ");
        while(temp!=null)
        {
            System.out.print(temp.item+" ");
            temp=temp.next;
        }
        System.out.println();
        }
    }
    
    boolean isEmpty(){return head==null;}

    //test client method
//    public static void main(String[] args){
//    
//    QueueADT QX = new QueueADT();
//    QX.enqueue(1);
//    QX.enqueue(2);
//    QX.enqueue(3);
//    QX.enqueue(4);
//    QX.enqueue(5);
//    QX.enqueue(6);
//    System.out.println("Dequeued: "+QX.dequeue());
//    System.out.println("Dequeued: "+QX.dequeue());
//    System.out.println("Dequeued: "+QX.dequeue());
//    System.out.println("Dequeued: "+QX.dequeue());
//    QX.print();
//    
//    }   
    
    
}
