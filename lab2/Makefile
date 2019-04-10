#------------------------------------------------------------------------------ 
# Name:Surya Rani CruziD: srani@ucsc.edu Class: 12b and 12M Date: 1/24/19  
# Description: This makefile will create an executable jar file of FileReverse and can clean all class files. It is also able to submit my files.  
# File name: Makefile
#------------------------------------------------------------------------------
JAVASRC = FileReverse.java
SOURCES    = README Makefile $(JAVASRC)
MAINCLASS  = FileReverse.java
CLASSES = FileReverse.class
JARFILE= Hello
SUBMIT= submit cmps012b-pt.w19 lab2

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	 rm $(CLASSES) $(JARFILE)

submit: $(SOURCES)
	    $(SUBMIT) $(SOURCES)

check:
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.w19/lab2/srani
