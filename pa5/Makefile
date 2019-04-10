#------------------------------------------------------------------------------
# Makefile for DictionaryClient.c with macros
#------------------------------------------------------------------------------

ADT     = Dictionary
EXEBIN  = $(ADT)Client
SOURCES = $(ADT).c  $(ADT)Test.c $(ADT).h $(EXEBIN).c
OBJECTS = $(ADT).o $(EXEBIN).o 
HEADERS = $(ADT).h
SUBMIT  = submit cmps012b-pt.w19 pa5

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

check:
	valgrind --leak-check=full $(EXEBIN) 

submit:
	$(SUBMIT) $(SOURCES) README Makefile

subcheck:
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.w19/pa5/srani 