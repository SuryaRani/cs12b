#------------------------------------------------------------------------------ 
# Name:Surya Rani CruziD: srani@ucsc.edu Class: 12b and 12M Date: 2/10/19  
# Description: This makefile will create an executable binary file of charType and can clean all class files. It is also able to submit my files.  
# File name: Makefile
#------------------------------------------------------------------------------
FLAGS   = -std=c99 -Wall
SOURCES = charType.c
OBJECTS = charType.o
EXEBIN  = charType
SUBMIT= submit cmps012b-pt.w19 lab4

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	 gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	 gcc -c $(FLAGS) $(SOURCES)

clean :
	 rm -f $(EXEBIN) $(OBJECTS)

submit: $(SOURCES)
	 $(SUBMIT) $(SOURCES) README Makefile
    
check: 
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.w19/lab4/srani
