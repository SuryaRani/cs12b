#------------------------------------------------------------------------------
# Makefile for DictionaryClient.c with macros
#------------------------------------------------------------------------------

ADT_NAME  = Dictionary
SOURCES   = $(ADT_NAME).c $(ADT_NAME)Client.c $(ADT_NAME)Test.c $(ADT_NAME).h 
OBJECTS   = $(ADT_NAME).o $(ADT_NAME)Client.o
HEADERS   = $(ADT_NAME).h
EXEBIN    = $(ADT_NAME)Client
FLAGS     = -std=c99 -Wall
SUBMIT    = submit cmps012b-pt.w19 pa5

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

check:
	valgrind --leak-check=full $(EXEBIN) 

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES) README Makefile

subcheck:
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.w19/pa5/srani 