public class QueueTest{
	public static void main(String[] args){
	Queue q = new Queue();
	//q.dequeue();
	//q.peek();
	//q.dequeueAll();
	String word = "hello";
	String w = "goodbye";
	System.out.println(q.isEmpty());
	System.out.println(q.length());
	q.enqueue(word);
	System.out.println(q.isEmpty());
	System.out.println(q.length());
	System.out.println(q.peek());
	q.enqueue(w);
	System.out.println(q.length());
	//System.out.println(q.dequeue());
	//System.out.println(q.peek());
	System.out.println(q);
	//q.dequeueAll();
	q.dequeue();
	System.out.println(q);
	System.out.println(q.length());
	//q.peek();
	}
}