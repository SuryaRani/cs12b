#------------------------------------------------------------------------------ 
# Name:Surya Rani CruziD: srani@ucsc.edu Class: 12b and 12M 
#------------------------------------------------------------------------------
JAVASRC = HelloUser.java HelloUser2.java
SOURCES    = README Makefile $(JAVASRC)
MAINCLASS  = HelloUser2
CLASSES = HelloUser.class HelloUser2.class
JARFILE= Hello
SUBMIT= submit cmps012b-pt.w19 lab1 README Makefile HelloUser.java HelloUser2.java

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
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.w19/lab1/srani