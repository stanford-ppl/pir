SRC_EXT=dot
OBJ_EXT=pdf
SOURCES=$(wildcard *.dot)
OBJECTS=$(SOURCES:.dot=.pdf)
TESTSRC=$(wildcard Test*.dot)

all: $(SOURCES) $(OBJECTS)

test: $(TESTSRC) $(TESTSRC:.dot=.pdf) 

%.pdf : %.dot
	dot -Tpdf $< > $@
	open $@

.PHONY : clean test all

clean:
	\rm -f $(OBJECTS)
