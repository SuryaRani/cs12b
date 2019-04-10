#------------------------------------------------------------------------------
# Name:Surya Rani CruziD: srani@ucsc.edu Class: 12b and 12M Date: 2/14/19  
# Makefile for CMPS 12B lab7
#------------------------------------------------------------------------------

# Variables
JAVAC      = javac 
MAINCLASS  = DictionaryClient
JAVASRC    = $(wildcard *.java)
SOURCES    = $(JAVASRC) Makefile README
CLASSES    = $(patsubst %.java, %.class, $(JAVASRC))
JARCLASSES = $(patsubst %.class, %*.class, $(CLASSES))
JARFILE    = $(MAINCLASS) 
SUBMIT 	   = submit cmps012b-pt.w19 lab7


all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(JARCLASSES)
	chmod +x $(JARFILE)
	rm Manifest

%.class: %.java
	$(JAVAC) $<

clean:
	rm *.class $(JARFILE)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)

check: $(SOURCES)
	ls  /afs/cats.ucsc.edu/class/cmps012b-pt.w19/lab7/srani