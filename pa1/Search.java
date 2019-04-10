
//-----------------------------------------------------------------------------
// Name:Surya Rani CruziD: srani@ucsc.edu Class: 12b and 12M Date: 1/23/19  
// Description: This search method will take a file of words and be able to sort them alpabetically. AFter that then it will be able to binary search for a target word that the user enters.
// It then outputs if the word is indeed in the file of words or not, and gives the original index of the word. 
// File Name: Search.java
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
      File file = new File(args[0]);
      if(!file.exists()){
         System.err.println(args[0] + " (No such file or directory)");
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

      int f = 0;
      for (int d = 1; d<=lineCount; d++){
         lineNum[f] = d;
         f++;
      }

      mergeSort(lines,lineNum,0,lines.length -1);
      for(int i = 1; i < args.length; i++){
      if((binarySearch(lines,0,lines.length-1,args[i])) == -1){ 
         System.out.println(args[i] + " not found");
      }
      else{
         System.out.println(args[i] + " found on line " + lineNum[binarySearch(lines,0,lines.length-1,args[i])]);
      }
   }
}
   public static void mergeSort(String[] A, int[] lineNumber, int p, int r){
      int q;
      if(p < r) {
         q = (p+r)/2;
         // System.out.println(p+" "+q+" "+r);
         mergeSort(A,lineNumber,p, q);
         mergeSort(A,lineNumber, q+1, r);
         merge(A, lineNumber, p, q, r);
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