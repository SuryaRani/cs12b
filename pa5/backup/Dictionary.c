//------------------------------------------------------------------------------
// Name:Surya Rani CruziD: srani@ucsc.edu Class: 12b and 12M Date: 3/15/19  
// Description: This is a dictionary with the underlying ADT being a hash table
// File Name: Dictionary.c
//------------------------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

const int tableSize =101;

//--------------------------------------------------------------------------
//PRIVATE FUNCTIONS 

// NodeObj
typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* k, char* v) {
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);
   N->key = k;
   N->value = v;
   N->next = NULL;
   return(N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
      free(*pN);
      *pN = NULL;
   }
}

typedef struct DictionaryObj{
   Node* list;
   int numItems;
}DictionaryObj;

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
   int sizeInBits = 8*sizeof(unsigned int);
   shift = shift & (sizeInBits - 1);  // remainder of shift on division by sizeInBits
   if ( shift == 0 )
      return value;
   else
      return (value << shift) | (value >> (sizeInBits - shift)); // perform rotation
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {  // input points to first char in string
   unsigned int result = 0xBAE86554;  // = 10111010111010000110010101010100, begin with a random looking bit pattern
   while (*input) {                   // while *input is not '\0' (not end of string)
      result ^= *input++;                // result = result ^ *input (current char alters result) 
                                         // input++  (go to next char)
      result = rotate_left(result, 5);   // rotate result by fixed amount
   }
   return result;  // result is now a random looking bit pattern depending on input string
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
   return pre_hash(key)%tableSize;
}

Node findKey(Dictionary D, char* k){
   int index = hash(k);
   Node head = D->list[index];
   while(head!=NULL){
      if(strcmp(head->key,k)==0){
         return head;
      }
      head = head->next;
   }
   return NULL;
}

//--------------------------------------------------------------------------
//PUBLIC FUNCTIONS

// Dictionary
// Exported reference type
typedef struct DictionaryObj* Dictionary;

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
   Dictionary D = malloc(sizeof(DictionaryObj));
   assert(D!=NULL);
   D->list = calloc(tableSize,sizeof(NodeObj));
   assert(D->list!=NULL);
   D->numItems=0;
   return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
   if(pD!=NULL && *pD!=NULL){
      if(!isEmpty(*pD)){
         makeEmpty(*pD);
      }
      free((*pD)->list);
      free(*pD);
      pD = NULL;
   }
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
   if(D==NULL){
      fprintf(stderr,"Dictionary Error: Calling isEmpty on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return (D->numItems==0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
   if(D==NULL){
      fprintf(stderr,"Dictionary Error: Calling size() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return D->numItems;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
  Node N = findKey(D,k);
  if(N!=NULL){
    return N->value;
   }
   return NULL;
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
   if(findKey(D,k)!=NULL){
      fprintf(stderr,"Dictionary Error: Calling insert() on already existing key %s\n",k);
      exit(EXIT_FAILURE);
   }
   Node N = newNode(k,v);
   int index =hash(k);
  
   if(D->list[index]==NULL){
      D->list[index]=N;
   }
   else{
      N->next = D->list[index];
      D->list[index] = N;
   }
   D->numItems++;
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
   if(findKey(D,k)==NULL){
      fprintf(stderr, "Dictionary Error: Calling delete() on non existent key %s\n",k);
      exit(EXIT_FAILURE);
   }
   int index = hash(k);
   if(strcmp(D->list[index]->key,k)==0){
         Node poop = D->list[index];
         D->list[index] = D->list[index]->next;
         freeNode(&poop);
         poop=NULL;
   }
   else{
      Node head = D->list[index];
      Node before = D->list[index];
      head = head->next;
      while(head!=NULL){
         if(strcmp(head->key,k)==0){
            before->next = head->next;
            freeNode(&head);
            head=NULL;
         }
         else{
            before = head;
            head = head->next;
         }
      }
   }
   D->numItems--;
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
              "Dictionary Error: calling makeEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   if( D->numItems==0 ){
      return;
   }
   for(int i=0; i<tableSize;i++){
      Node current = D->list[i];
      D->list[i]=NULL;
      Node next;
      while(current!=NULL){
         if(current->next!=NULL){
            next = current->next;
         }
         else{
            next =NULL;
         }
         freeNode(&current);
         current = next;
      }
   }
   D->numItems=0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
              "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   for(int i=0; i<tableSize;i++){
      Node N= D->list[i];
      while(N!=NULL){
         fprintf(out,"%s %s\n",N->key,N->value);
         N=N->next;
      }
   }
}