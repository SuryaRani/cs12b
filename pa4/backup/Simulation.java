import java.io.*;
import java.util.Scanner;
import java.text.DecimalFormat;

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
   	for(int g=0;g<processor.length; g++){
   		if(processor[g].length() ==0){
 
   		}
   		else{
   			return false;
   		}
   	}
   	return true;
   }

   private static int getLowestFinish(Queue[] processor){
   	int t =0;
   	if(processor[0].length() !=0){
     t=((Job)processor[0].peek()).getFinish();
	}
	else{
		for(int g=1;g<processor.length; g++){
			if(processor[g].length()!=0){
				t= ((Job)processor[g].peek()).getFinish();
			}
		}
	}
   	for(int g=0;g<processor.length; g++){
   		if(processor[g].length()!=0){
   			if(((Job)processor[g].peek()).getFinish() > t){
   				if(t ==-1){
   					t=((Job)processor[g].peek()).getFinish();
   				}
   			}
   			else{
   				t = ((Job)processor[g].peek()).getFinish();
   			}
    		}
    	}
    	return t;
	}

	private static int getStorageFinish(Queue store){
		if(store.length() !=0){
			return ((Job)store.peek()).getFinish();
		}
		return -1;
	}

	private static int getBestIndex(Queue[] processor){
		int index=0;
		int newIndex=0;
      		for(int k=0;k<processor.length-1;k++){
				if(processor[k].length()>processor[k+1].length()){
  					newIndex =k+1;
      			}
    			else{
     				newIndex = k;
      			}
      			if(processor[index].length() <= processor[newIndex].length()){

      			}
      			else{
      				index = newIndex;
      			}
      		}
      		return index;
	}

	private static int getTotalWait(Queue[] processor){
		int wait=0;
		int arrival=0;
		int finish=0;
		int firstArrival=0;
		int firstDuration=0;
		for(int i =0; i<processor.length;i++){
			if(processor[i].length()>1){
				for(int k =0;k<processor[i].length()-1;k++){
					if(finish == ((Job)processor[i].peek()).getFinish() && firstDuration == ((Job)processor[i].peek()).getDuration() && firstArrival== ((Job)processor[i].peek()).getArrival()){
						processor[i].enqueue(processor[i].dequeue());
					}
					else{
						finish = ((Job)processor[i].peek()).getFinish();
						firstDuration = ((Job)processor[i].peek()).getDuration();
						firstArrival=((Job)processor[i].peek()).getArrival();
						processor[i].enqueue(processor[i].dequeue());
						arrival = ((Job)processor[i].peek()).getArrival();
							if(finish > arrival){
							wait+=(finish - arrival);
							}
					}
				}
				processor[i].enqueue(processor[i].dequeue());
			}
		}
		
		return wait;
	}


	private static Queue makeBackup(Queue back){
		Queue b = new Queue();
		int y2=0;
		while(y2<back.length()){
		b.enqueue(back.peek());
		back.enqueue(back.dequeue());
		}
		return b;
	}

	public static void main(String[] args) throws IOException{
		Scanner in = null;
		Scanner in2 = null;
    	PrintWriter outTrc = null;
    	PrintWriter outRpt = null;

    	if(args.length != 1){
         System.out.println("Usage: Simulation <input file>");
         System.exit(1);
      }

      	// open files
      in = new Scanner(new File(args[0]));
      in2 = new Scanner(new File(args[0]));
      FileWriter fileWriter = new FileWriter(new File(args[0]+ ".trc"));
      outTrc = new PrintWriter(fileWriter);
      FileWriter rptWriter = new FileWriter(new File(args[0]+".rpt"));
      outRpt = new PrintWriter(rptWriter);


      //number of jobs
      int m = Integer.parseInt(in.nextLine());
      //initializing storage queue
      Queue storage = new Queue();
      Queue backup = new Queue();
      //printing format for trc file
      outTrc.println("Trace file: " + args[0] + ".trc");
      outTrc.println(m + " Jobs:");

      outRpt.println("Report file: " + args[0] + ".rpt");
      outRpt.println(m + " Jobs:");
      
      //enqueueing the jobs into storage and making backup
      int s=0;
      in2.nextLine();
      while(s<m){
      	storage.enqueue(getJob(in));
      	backup.enqueue(getJob(in2));
      	s++;
      }
      outTrc.println(storage);
      outRpt.println(storage);
      outRpt.println();
      outRpt.println("***********************************************************");
  
      //running the simulation n times which is 1 less than number of jobs 
      for(int n =1;n<m;n++){
      	//initializing wait variables
      	int totalWait=0;
      	int maxWait=0;
      	double avgWait=0.00;

      	//initializing processor queues and time variable 
      	Queue[] processors = new Queue[n];
      	for(int i=0;i<n;i++){
      	processors[i] = new Queue(); 
      	}
      	int time =0;
      	//printing format 
      	outTrc.println();
      	outTrc.println("*****************************");
      	if(n>1){
      		outTrc.println(n+" processors:" );
      	}else{
      		outTrc.println(n +" processor:");
        }
        outTrc.println("*****************************");



        
     	//comment this out later
     	//int d=-7;
     	//while(d<m){
        //keep going until the first element in the storae array is back with a computed finish time or while storage does not contain all jobs
      	while(getStorageFinish(storage)==-1 || storage.length()<m){
      		outTrc.println("time=" + time);
      		outTrc.println("0: " + storage);

      		//determining time
      		for(int k=0;k<n;k++){
      			if(time==0){
      				time=((Job)storage.peek()).getArrival();
      			}
      			if(processors[k].length()!=0 && storage.length()!=0){
      				if(((Job)storage.peek()).getArrival()<getLowestFinish(processors) && ((Job)storage.peek()).getFinish()==-1){
      					time = ((Job)storage.peek()).getArrival();
      					break;
      				}
      				else{
      					//get lowest finish here 
      					time = getLowestFinish(processors);
      					break;
      				}
      			}else{
      				//Determining time if storage is empty so finding the lowest finish in the processors
      				if(processors[k].length()!=0){
	      					time = getLowestFinish(processors);
	      					break;
	      			}else{
	      			if(storage.length() == m && ((Job)storage.peek()).getFinish() == -1){
	      				time = ((Job)storage.peek()).getArrival();
	      				break;
	      			}
	      			}
      			}
      		}

      		//printing out the processors
      		int j=0;
      		int i=1;
      			while(i<m && j<n){
      				outTrc.println(i +": " +  processors[j]);
      				i++;
      				j++;
      			}
      		outTrc.println();
      			
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
      				if(storage.length()!=0){
      					while( storage.length()!=0 && time ==((Job)storage.peek()).getArrival()){
      					processors[0].enqueue(storage.dequeue()); 
	      				}
	      			}
	      			else{
	      				time = ((Job)processors[0].peek()).getFinish();
	      				storage.enqueue(processors[0].dequeue());
	      				
	      			}
	
      			if(processors[0].length()!=0){
      				if(((Job)processors[0].peek()).getFinish()==-1){
      				((Job)processors[0].peek()).computeFinishTime(time);
      				}
      			}
      			}else{
      				//Checking if the first Job in each processor has their finish time computed and computes it if not done so already
      				for(int y=0; y<processors.length;y++){
      					if(processors[y].length() !=0){
	      					if(((Job)processors[y].peek()).getFinish()==-1){
	      						((Job)processors[y].peek()).computeFinishTime(time);
	      					}
      					}
      				}

      				//find the processor to put the job in 
      				while( storage.length()!=0 && time ==((Job)storage.peek()).getArrival()){
      				int index = getBestIndex(processors);
	      				if(time ==((Job)storage.peek()).getArrival()){
	      				processors[index].enqueue(storage.dequeue());
	      				//((Job)processors[index].peek()).computeFinishTime(time);
	      				}
	      				if(processors[index].length()!=0){
	      					if(((Job)processors[index].peek()).getFinish()==-1){
	      						((Job)processors[index].peek()).computeFinishTime(time);
	      					}
	      				}
      				}

      			}
      		
      			//d++;
      		}
      		maxWait = ((Job)storage.peek()).getWaitTime();
      		for(int v = 0; v<storage.length(); v++){
      			totalWait+= ((Job)storage.peek()).getWaitTime();
      			if(maxWait < ((Job)storage.peek()).getWaitTime()){
      				maxWait = ((Job)storage.peek()).getWaitTime();
      			}
      			storage.enqueue(storage.dequeue());
      		}


      		avgWait = (1.0 * totalWait)/(1.0 * m);
      		DecimalFormat df2 = new DecimalFormat("0.00");
      		//printing last line for trc
      		outTrc.println("time=" + time);
      		outTrc.println("0: " + storage);
      		int a1=0;
      		int a2=1;
      			while(a2<m && a1<n){
      				outTrc.println(a2 +": " +  processors[a1]);
      				a2++;
      				a1++;
      			}

      		String printAv = df2.format(avgWait);

      		//printing out the rpt file 
      		if(n==1){
      			outRpt.println(n + " processor: " + "totalWait=" + totalWait + ", maxWait="+ maxWait+ ", averageWait=" +printAv);
      		}else{
      		outRpt.println(n + " processors: " + "totalWait=" + totalWait + ", maxWait="+ maxWait+ ", averageWait=" +printAv);
      		}
 
      		//resetting the storage queue jobs final values
      		for(int e =0; e<m;e++){
      			Object obj = backup.dequeue();
      			storage.dequeue();
      			storage.enqueue(obj);
      			((Job)obj).resetFinishTime();
      			backup.enqueue(obj);
      		}

      		
      	}
      	//formatting for some reason we need a new line after the last last processor 
      	outTrc.println();

      	in.close();
      	in2.close();
        outRpt.close();
      	outTrc.close();
      }

	
}


  


