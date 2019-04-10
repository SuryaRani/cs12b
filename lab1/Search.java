
//-----------------------------------------------------------------------------
// LC.java
// The slick way to count the lines in a file.  
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class Search{

   public static void main(String[] args) throws IOException {

      // check number of command line arguments
      if(args.length < 2){
         System.err.println("Usage: Search file target1 [target2 ..]");
         System.exit(1);
      }
      
      // count the number of lines in file
      Scanner in = new Scanner(new File(args[0]));
      in.useDelimiter("\\Z"); // matches the end of file character
      String s = in.next();   // read in whole file as a single String
      in.close();
      String[] lines = s.split("\n");  // split s into individual lines
      int lineCount = lines.length;  // extract length of the resulting array
      int[] lineNum = new int[lineCount];


      mergeSort(lines,lineNum,0,lines.length -1);
      binarySearch(lines,0,lines.length-1,target); //need to fix this is lines even the right array

   }

   public static void mergeSort(String[] A, int[] lineNumber, int p, int r){
      int q;
      if(p < r) {
         q = (p+r)/2;
         // System.out.println(p+" "+q+" "+r);
         mergeSort(A,lineNum,p, q);
         mergeSort(A,lineNum, q+1, r);
         merge(A, lineNum, p, q, r);
      }
   }

   // merge()
   // merges sorted subarrays A[p..q] and A[q+1..r]
   public static void merge(String[] A, int[] lineNumber,int p, int q, int r){
      int n1 = q-p+1;
      int n2 = r-q;
      String[] L = new String[n1];
      String[] R = new String[n2];
      int[] Lnum = new int[n1];
      int[] Rnum = new int[n2];
      int i, j, k;

      for(i=0; i<n1; i++){
         Lnum[i] = lineNumber[p+i];
         L[i] = A[p+i];
      }
      for(j=0; j<n2; j++){ 
         Rnum[j] = lineNumber[q+j+1];
         R[j] = A[q+j+1];
      }

      i = 0; j = 0;
      for(k=p; k<=r; k++){
         if( i<n1 && j<n2 ){
            if( L[i].compareTo(R[j]) <0 ){
               A[k] = L[i];
               lineNumber[k] = Lnum[i];
               i++;
            }else{
               A[k] = R[j];
               lineNumber[k] = Rnum[j];
               j++;
            }
         }else if( i<n1 ){
            A[k] = L[i];
            lineNumber[k] = Lnum[i];
            i++;
         }else{ // j<n2
            A[k] = R[j];
            lineNumber[k] = Rnum[j];
            j++;
         }
      }
   }

 static int binarySearch(String[] A, int p, int r, String target){
      int q;
      if(p > r) {
         return -1;
      }else{
         q = (p+r)/2;
         if(target.compareTo(A[q])  == 0){
            return q;
         }else if(target.compareTo(A[q]) <0){
            return binarySearch(A, p, q-1, target);
         }else{ // target > A[q]
            return binarySearch(A, q+1, r, target);
         }
      }
   }

}