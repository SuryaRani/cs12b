/* Name:Surya Rani CruziD: srani@ucsc.edu Class: 12b and 12M Date: 2/22/19  
	Description:This is to test out the Dictionary ADT and make sure it is working properly 
	File Name: DictionaryTest.c
*/

#include<stdio.h>
#include<stdlib.h>
#include"Dictionary.h"

int main(){
	Dictionary d = newDictionary();
	printf("isEmpty = %d\n",isEmpty(d));
	printf("size = %d\n",size(d));
	insert(d,"1","23");
	//printf("isEmpty = %d\n",isEmpty(d));
	//printf("size = %d\n",size(d));
	//printDictionary(stdout,d);
	insert(d,"2","56");
	//printf(lookup(d,"1"));
	insert(d,"3","83");
	//insert(d,"1","23");
	printf("isEmpty = %d\n",isEmpty(d));
	printf("size = %d\n",size(d));
	printDictionary(stdout,d);
	makeEmpty(d);
	//delete(d,"1");
	//delete(d,"2");
	//delete(d,"3");
	printf("size = %d\n",size(d));
	printDictionary(stdout,d);
	freeDictionary(&d);
	return(EXIT_SUCCESS);
}