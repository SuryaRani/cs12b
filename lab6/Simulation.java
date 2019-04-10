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
   //checks if each processor queue in the queue array is empty 
   private boolean processorsEmpty(Queue[] processor){
   	for(int g=0;g<processor.length; g+=){
   		if(processor[g].length() ==0){
 
   		}
   		else{
   			return false;
   		}
   	}
   	return true;
   }

	public static void main(String[] args) throws IOException{
		Scanner in = null;
    	PrintWriter outTrc = null;
    	PrintWriter outRpt = null;

    	if(args.length != 1){
         System.out.println("Usage: Simulation <input file>");
         System.exit(1);
      }

      	// open files
      in = new Scanner(new File(args[0]));
      //number of jobs
      int m = Integer.parseInt(in.nextLine());
      //initializing storage queue
      Queue storage = new Queue();
      //printing format for trc file
      System.out.println("Trace file: " + args[0] + ".trc");
      System.out.println(m + " Jobs:");
      //enqueueing the jobs into storage
      while(in.hasNextLine()){
      	storage.enqueue(getJob(in));
      }
      System.out.print(storage);
      System.out.println();
      //start of simulation
      for(int n =1;n<m;n++){
      	//initializing wait variables
      	int totalWait=0;
      	int maxWait=0;
      	int avgWait=0;

      	//initializing processor queues and time variable 
      	Queue[] processors = new Queue[n];
      	for(int i=0;i<n;i++){
      	processors[i] = new Queue(); 
      	}
      	int time =0;
      	//printing format 
      	System.out.println();
      	System.out.println("*****************************");
      	if(n>1){
      		System.out.println(n+" processors:" );
      	}else{
      	System.out.println(n +" processor:");
        }
        System.out.println("*****************************");
        
     	//comment this out later
     	int d=-7;
     	while(d<m){
      	//while(((Job)storage.peek()).getFinish()==-1 || storage.length()<m){
      		System.out.println("time=" + time);
      		System.out.println("0: " + storage);

      		//determining time
      		for(int k=0;k<n;k++){
      			if(time==0){
      				time=((Job)storage.peek()).getArrival();
      			}
      			if(processors[k].length()!=0 && storage.length()!=0){
      				if(((Job)storage.peek()).getArrival()<((Job)processors[k].peek()).getFinish() && ((Job)storage.peek()).getFinish()==-1){
      					time = ((Job)storage.peek()).getArrival();
      					break;
      				}
      				else{
      					time = ((Job)processors[k].peek()).getFinish();
      					break;
      				}
      			}else{
	      			while(k<n-1){
	      				if(processors[k].length()==0){
	      					break;
	      				}
	      				if(((Job)processors[k].peek()).getFinish() <= ((Job)processors[k+1].peek()).getFinish()){
	      					time = ((Job)processors[k].peek()).getFinish();
	      					break;
	      				}
	      				else{
	      					time = ((Job)processors[k+1].peek()).getFinish();
	      					break;
	      				}
	      			}
      			}
      		}

      		//printing out the processors
      		int j=0;
      		int i=1;
      			while(i<m && j<n){
      				System.out.println(i +": " +  processors[j]);
      				i++;
      				j++;
      			}
      			
      			//checking if jobs are finished and enqueueing back to storage if done 

      			for(int k=0;k<n;k++){
      				if(processors[k].length()!=0){
      					if(((Job)processors[k].peek()).getFinish()==time){
							storage.enqueue(processors[k].dequeue());
      					}
      				}
      			}

      			//queueing and dequeuing jobs (assinging jobs to processors)
      			if(n==1){
      				if(time ==((Job)storage.peek()).getArrival()){
      				processors[0].enqueue(storage.dequeue()); 
      			}
      			if(processors[0].length()!=0){
      				if(((Job)processors[0].peek()).getFinish()==-1){
      				((Job)processors[0].peek()).computeFinishTime(time);
      				}
      			}
      			}else{
      				//find the processor to put the job in 
      				int index=0;
      				for(int k=0;k<n-1;k++){
      					if(processors[k].length()>processors[k+1].length()){
      						index =k+1;
      					}
      					else{
      						index = k;
      					}
      				}
      				if(time ==((Job)storage.peek()).getArrival()){
      				processors[index].enqueue(storage.dequeue());
      				}
      				if(processors[index].length()!=0){
      					if(((Job)processors[index].peek()).getFinish()==-1){
      						((Job)processors[index].peek()).computeFinishTime(time);
      					}
      				}
      			}
      			
      		
      		
      			d++;
      		}
      		//printing last line 
      		System.out.println("time=" + time);
      		System.out.println("0: " + storage);
      		int a1=0;
      		int a2=1;
      			while(a2<m && a1<n){
      				System.out.println(a2 +": " +  processors[a1]);
      				a2++;
      				a1++;
      			}
      			//resetting the storage queue jobs final values
      			for(int u =0; u<m;u++){
      				if(((Job)storage.peek()).getFinish()!=1){
      				((Job)storage.peek()).resetFinishTime();
      				storage.enqueue(storage.dequeue());
      				}
      			}


      		
      	}
      	in.close();
      }

	
}


  


