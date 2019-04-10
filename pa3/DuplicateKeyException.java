/* Name:Surya Rani CruziD: srani@ucsc.edu Class: 12b and 12M Date: 2/14/19  
	Description: This throws a DuplicateKeyException which is a sublcass of RuntimeException if in the Dicitonary Adt the insert function is being called on an already
	existing key. 
	File Name: DuplicateKeyException.java
*/
public class DuplicateKeyException extends RuntimeException{
	public DuplicateKeyException(String s){
	super(s);
	}
}