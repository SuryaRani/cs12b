//------------------------------------------------------------------------------
// Name:Surya Rani CruziD: srani@ucsc.edu Class: 12b and 12M Date: 3/15/19  
// Description: This is a dictionary test program that tests out my dictionary
// File Name: DictionaryTest.c
//------------------------------------------------------------------------------
#include<stdio.h>
#include<stdlib.h>
#include"Dictionary.h"
#include"Dictionary.c"

int main(){
	Dictionary d = newDictionary();
	printf("isEmpty = %d\n",isEmpty(d));
	printf("size = %d\n",size(d));

	insert(d,"1","Hello");
	insert(d,"2","Sup");
	insert(d,"3","YE");
	printf("isEmpty = %d\n",isEmpty(d));
	printf("size = %d\n",size(d));
	printf("%s\n",lookup(d,"1"));
	printf("%s\n",lookup(d,"2"));
	printf("%s\n",lookup(d,"3"));
	printDictionary(stdout,d);

	delete(d,"2");
	delete(d,"3");

	insert(d,"4","DJFDF");
	insert(d,"5","uendsf");
	insert(d,"6","TURNUP");
	insert(d,"7","TURNUP");
	insert(d,"8","TURNUP");

	delete(d,"7");
	//delete(d,"4");
	//delete(d,"6");
	//makeEmpty(d);
	printDictionary(stdout,d);
	printf("size = %d\n",size(d));

	freeDictionary(&d);
	return(EXIT_SUCCESS);
}