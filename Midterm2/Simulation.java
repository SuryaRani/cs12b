import java.io.*;
import java.util.Scanner;

public class Simulation{

//-----------------------------------------------------------------------------
//
// The following function may be of use in assembling the initial backup and/or
// storage queues.  You may use it as is, alter it as you see fit, or delete it
// altogether.
//
//-----------------------------------------------------------------------------

	 public static Job getJob(Scanner in) {
      String[] s = in.nextLine().split(" ");
      int a = Integer.parseInt(s[0]);
      int d = Integer.parseInt(s[1]);
      return new Job(a, d);
   }

	public static void main(String[] args){
		Scanner in = null;
    	PrintWriter out = null;

    	if(args.length != 1){
         System.out.println("Usage: Simulation <input file>");
         System.exit(1);
      }

      	// open files
      in = new Scanner(new File(args[0]));

      int m = in.nextLine();
      System.out.println(m);
      //for(int n =1;n<m;n++){

    //  }


}


  


}
