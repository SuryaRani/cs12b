//-----------------------------------------------------------------------------
// QueueInterface.java
// interface for the Queue ADT
//-----------------------------------------------------------------------------

public class Queue implements QueueInterface{

   // private inner Node class
   private class Node {
      Object object;
      Node next;

      Node(Object o){
         object = o;;
         next = null;
      }
   }

   private Node head;
   private Node tail;
   private int numItems;

   public Queue(){
      numItems =0;
      head = null;
      tail = null;
   }

   // isEmpty()
   // pre: none
   // post: returns true if this Queue is empty, false otherwise
   public boolean isEmpty(){
       return (numItems ==0);
   }

   // length()
   // pre: none
   // post: returns the length of this Queue.
   public int length(){
      return numItems;
   }

   // enqueue()
   // adds newItem to back of this Queue
   // pre: none
   // post: !isEmpty()
   public void enqueue(Object newItem){
      Node N = new Node(newItem);
      if(numItems==0){
         head = N;
         tail = head;
      }
      else{
         tail.next = N;
         tail = tail.next;
      }
      numItems++;
   }

   // dequeue()
   // deletes and returns item from front of this Queue
   // pre: !isEmpty()
   // post: this Queue will have one fewer element
   public Object dequeue() throws QueueEmptyException{
     if( numItems==0 ){
         throw new QueueEmptyException("cannot dequeue() empty queue");
      }
      Object obj = head.object;
      head = head.next;
      if(numItems==1){
         tail =null;
      }
      numItems--;
      return obj;

   }

   // peek()
   // pre: !isEmpty()
   // post: returns item at front of Queue
   public Object peek() throws QueueEmptyException{
      if( numItems==0 ){
         throw new QueueEmptyException("cannot peek() empty queue");
      }
      return head.object;
   }

   // dequeueAll()
   // sets this Queue to the empty state
   // pre: !isEmpty()
   // post: isEmpty()
   public void dequeueAll() throws QueueEmptyException{
      if( numItems==0 ){
         throw new QueueEmptyException("cannot peek() empty queue");
      }
      numItems =0;
      head = null;
      tail = null;
   }

   // toString()
   // overrides Object's toString() method
   public String toString(){
      Node N;
      StringBuffer sb = new StringBuffer();
      for(N=head;N!=null;N=N.next){
         sb.append(N.object + " ");
      }
      return new String(sb);
   }
}