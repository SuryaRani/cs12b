/* Name:Surya Rani CruziD: srani@ucsc.edu Class: 12b and 12M Date: 2/14/19  
	Description: This Dictionary ADT is able to insert strings and delete them and is able to print them out as well. It is also able to lookup certain key strings.
	It can also return the size of the dictionary Adt meaning how many items are in it. 
	File Name: Dicitonary.java
*/

public class Dictionary implements DictionaryInterface{
	// private inner Node class
   private class Node {
      String key;
      String value;
      Node next;

      Node(String k, String v){
         key =k;
         value = v;
         next = null;
      }
   }

   private Node head;
   private int numItems;

   public Dictionary(){
   	head = null;
   	numItems = 0;
   }

   private Node findKey(String key){
   	Node N = head;

   	while(N!=null){
   		if(N.key.equals(key)){
   			return N;
   		}
   		else{
   			N = N.next;
   		}
   	}
   	return null;
   }

	public boolean isEmpty(){
		return(numItems ==0);
	}

	public int size(){
		return numItems;
	}

	public String lookup(String key){
		Node N= findKey(key);
		if(N!=null){
			return N.value;
		}
		return null;
	}

	public void insert(String key,String value) throws DuplicateKeyException{
		if(lookup(key) != null){
			throw new DuplicateKeyException("Dictionary Error: insert() called on already existing key: " + key);
		}
		else{
			Node N = new Node(key,value);
			N.next = head;
			head=N;
			numItems++;
		}
	}

	public void delete(String key) throws KeyNotFoundException{
		boolean deleted = false;
		if(lookup(key)==null){
			throw new KeyNotFoundException("Dictionary Error: delete() called on non existent key: " + key);
		}
		else{
			if(numItems ==1){
				head = null;
			}
			else{
				Node N = findKey(key);
				Node before = head;
				Node after = N.next;

				while(!deleted){
					if(before.key.equals(key)){
						head = after;
						deleted = true;
					}
					else{
						if(before.next.key.equals(key)){
							before.next = after;
							deleted =true;
						}
						else{
							before = before.next;
						}
					}
				}
			}
			numItems--;
		}
	}

	public void makeEmpty(){
		head = null;
		numItems = 0;
	}

	public String toString(){
		return myString(head);
	}

	 private String myString(Node H){
      if(H==null ){
         return "";
      }else{
         return (myString(H.next) + H.key + " " + H.value + "\n");
      }
   }

}