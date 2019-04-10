//-----------------------------------------------------------------------------
// charType.c
// Name:Surya Rani CruziD: srani@ucsc.edu Class: 12b and 12M Date: 2/10/19  
// Description: extracts alphabet, digit, punctuation, and whitespace characters from each line of the input file
// and places them in the output file and also shows how many of each. 
//
// Recall the program FileIO.c from lab3 used fscanf to parse words in
// a file and then process them.  However the function fscanf is not
// appropriate when you want to read an entire line from a file as a
// string.  In this program we use another IO function from stdio.h called 
// fgets() for this purpose.  Its prototype is:
//
//         char* fgets(char* s, int n, FILE* stream);
//
// fgets() reads up to n-1 characters from stream and places them in
// the character array ponted to by s.  Characters are read until either
// a newline or an EOF is read, or until the specified limit is reached.
// After the characters have been read, a null character '\0' is placed
// in the array immediately after the last character read.  A newline
// character in stream will be retained and placed in s.  If successful,
// fgets() returns the string s, and a NULL pointer is returned upon
// failure.  See fgets in section 3c of the unix man pages for more.
//
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>
#include<string.h>

#define MAX_STRING_LENGTH 100

// function prototype 
void extract_chars(char* s, char* a, char* d, char* p, char* w);

// function main which takes command line arguments 
int main(int argc, char* argv[]){
   FILE* in;        // handle for input file                  
   FILE* out;       // handle for output file                 
   char* line;      // string holding input line              
   char* alpha; // string holding all alpha-numeric chars 
   char* digits;
   char* punctuation;
   char* whitespace;
   int counter =0;

   // check command line for correct number of arguments 
   if( argc != 3 ){
      printf("Usage: %s input-file output-file\n", argv[0]);
      exit(EXIT_FAILURE);
   }

   // open input file for reading 
   if( (in=fopen(argv[1], "r"))==NULL ){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   // open output file for writing 
   if( (out=fopen(argv[2], "w"))==NULL ){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   // allocate strings line and alpha_num on the heap 
   line = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   alpha = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   digits =calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   punctuation = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   whitespace = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   assert( line!=NULL && alpha!=NULL && digits!=NULL && punctuation!=NULL && whitespace!=NULL);

   // read each line in input file, extract alpha-numeric characters 
   while( fgets(line, MAX_STRING_LENGTH, in) != NULL ){
      counter++;
      extract_chars(line, alpha, digits,punctuation,whitespace);
      fprintf(out,"line %d contains:\n",counter);
      if(strlen(alpha) == 1){
         fprintf(out,"%lu alphabetic character: %s\n",strlen(alpha),alpha);
      }
      else{
         fprintf(out,"%lu alphabetic characters: %s\n",strlen(alpha),alpha);
      }
     
      if(strlen(digits)==1){
         fprintf(out,"%lu numberic character: %s\n",strlen(digits),digits);
      }
      else{
         fprintf(out,"%lu numberic characters: %s\n",strlen(digits),digits);
      }
      if(strlen(punctuation)==1){
         fprintf(out,"%lu punctuation character: %s\n",strlen(punctuation),punctuation);
      }
      else{
         fprintf(out,"%lu punctuation characters: %s\n",strlen(punctuation),punctuation);
      }

       if(strlen(whitespace)==1){
          fprintf(out,"%lu whitespace character: %s\n",strlen(whitespace),whitespace);
      }
      else{
          fprintf(out,"%lu whitespace characters: %s\n",strlen(whitespace),whitespace);
      }
      
     


      printf("\n");
      //fprintf(out, "%s and %s and %s and %s\n", alpha,digits,punctuation,whitespace);
   }

   // free heap memory 
   free(line);
   free(alpha);
   free(digits);
   free(punctuation);
   free(whitespace);

   // close input and output files 
   fclose(in);
   fclose(out);

   return EXIT_SUCCESS;
}

// function definition 
void extract_chars(char* s, char* a, char* d, char* p, char* w){
   int i=0, j=0, x=0, y=0, z=0;
   while(s[i]!='\0' && i<MAX_STRING_LENGTH){
      if( isalpha(s[i]) ) {
         a[j++] = s[i];
      }
      if(isdigit((int) s[i])){
         d[x++] = s[i];
      }
      if(ispunct(s[i])) {
         p[y++] = s[i];
      }

      if(isspace(s[i])) {
         w[z++] = s[i];
      }

      i++;
   }
   a[j] = '\0';
   d[x] = '\0';
   p[y] = '\0';
   w[z] = '\0';
}
