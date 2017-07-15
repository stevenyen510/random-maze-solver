// Code written by: Steven Yen
// For: CIS 22C Summer 2016, Class Project #4
// Code purpose: Linked-list used in the Graph adjacency matrix. The elements
// in this linked-list are Integer values. This linked-list supports adding
// from the front/head. Can remove nodes by specific key values.

// Referenced sample code provided on the companion website for 
// the book "Algorithms 4ed by Robert Sedgewick and Kevin Wayne."
// as a guidance.

//package cis22c_project4;

import java.util.Iterator;

public class IntList implements Iterable{
    public int size; //number of nodes in list.
    public intNode head; //front of the list.
    
    //internal class definition for the nodes.
    private class intNode{
        public Integer value;
        public intNode next;    
    }
    
    //default no-value constructor.
    IntList(){
        head = null;
        size = 0;
    }
    
    //insert an element with value x to the current list.
    public void insert(Integer x){
        
        intNode newNode = new intNode();
        newNode.value = x;
        
        newNode.next=head;
        head = newNode;
       
        size++; 
    }
    
    //determine if the current list contains an element with value equal "someInt"
    public boolean find(int someInt){
        intNode temp = new intNode();
        
        if(head==null){return false;}
        else{
            temp = head;
            
            while(temp!=null)
            {
                if(someInt==temp.value){return true;}
                temp= temp.next;
            }
        }
        return false;
    }
    
    //impelmenting the Iterator interface.
    public class myIterator implements Iterator{
        public intNode iterCurrent;
        
        public myIterator(intNode x)
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
    
    //returns true/false indicating whether list is empty.
    boolean isEmpty(){return head==null;} 
   
    //remove the element at the head of the list.
    public boolean removeFront()
    {
        if(isEmpty()){
            System.out.println("List is empty, cannot remove.");
        }
        
        head = head.next;
        size--;
        if(size==0){head=null;}
        
        return true;
    }
     
    //remove items with value equal to v from the current list.
    public boolean remove(Integer v)
    {
        intNode current=null;
        intNode before=null;
        intNode after = null;
        
        if(head==null)
        {return false;}
             
        while(find(v))
        {
            if(v.equals(head.value))
            {
                removeFront();
                continue;
            }
            
            before = head;
            current = head.next;
            
            while(current!=null)
            {
                if(v.equals(current.value))
                {
                    before.next=current.next;
                }
                
                current = current.next;
            }    
        }
        return true;
    }
     
//    public static void main(String[] args){
//        IntList testList = new IntList();
//        testList.insert(1);
//        testList.insert(2);
//        testList.insert(3);
//        testList.insert(4);
//        testList.insert(5);
//        testList.insert(3);
//        testList.insert(3);
//        testList.insert(3);
//        
//        for(Object num: testList){System.out.println(num);}
//        
//        System.out.println("Remove 3's:");
//        testList.remove(3);
//        for(Object num: testList){System.out.println(num);}
//        }   
    
}
