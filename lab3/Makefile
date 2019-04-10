#------------------------------------------------------------------------------ 
# Name:Surya Rani CruziD: srani@ucsc.edu Class: 12b and 12M Date: 1/29/19  
# Description: This makefile will create an executable binary file of FileReverse and can clean all class files. It is also able to submit my files.  
# File name: Makefile
#------------------------------------------------------------------------------
FLAGS   = -std=c99 -Wall
SOURCES = README Makefile FileReverse.c
OBJECTS = FileReverse.o
EXEBIN  = FileReverse
SUBMIT = submit cmps012b-pt.w19 lab3

all : $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) FileReverse.c

clean :
	rm -f $(EXEBIN) $(OBJECTS)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)
    
check: 
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.w19/lab3/srani
