/* Name:Surya Rani CruziD: srani@ucsc.edu Class: 12b and 12M Date: 2/14/19  
	Description: This throws a KeyNotFoundExceptionn which is a sublcass of RuntimeException if in the Dicitonary Adt the deletefunction is being called on a non existent key
	File Name: KeyNotFoundException.java
*/
public class KeyNotFoundException extends RuntimeException{
	public KeyNotFoundException(String s){
	super(s);
	}
}