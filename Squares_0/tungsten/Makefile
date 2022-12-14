DEBUG ?=0
BUILDDIR=build/
CC=g++
TUNGSTEN_HOME:=$(shell cat TUNGSTEN_HOME)
TUNGSTEN_SRC:=$(wildcard $(TUNGSTEN_HOME)/*.h $(TUNGSTEN_HOME)/*.cc $(TUNGSTEN_HOME)/plasticine/*)
ifeq ($(DEBUG), 1)
  PLASTICINE_LIB:=$(TUNGSTEN_HOME)/lib/plasticine.o.DBG
else
  PLASTICINE_LIB:=$(TUNGSTEN_HOME)/lib/plasticine.o.OPT
endif
OBJ_FILE:=$(patsubst src/%.cc,$(BUILDDIR)/%.o,$(wildcard src/*.cc))

CPPFLAGS = --std=c++17 -MMD -fno-var-tracking-assignments
CPPFLAGS += -I$(TUNGSTEN_HOME)/parallelstl/include -I$(TUNGSTEN_HOME)/tbb/include
CPPFLAGS += -I$(TUNGSTEN_HOME)/plasticine/templates
CPPFLAGS += -I$(TUNGSTEN_HOME)/sparsity/templates
CPPFLAGS += -I$(TUNGSTEN_HOME)/binlog/include
#CPPFLAGS += -I$(TUNGSTEN_HOME)/DRAMSim2 
CPPFLAGS += -I$(TUNGSTEN_HOME)/ramulator/src -DRAMULATOR -DSIMTIME
CPPFLAGS += -I$(TUNGSTEN_HOME)/
CPPFLAGS += -Isrc/
CPPFLAGS += $(shell cat PSPEC)
LDFLAGS= -lstdc++fs -L$(TUNGSTEN_HOME)/ramulator -lramulator -lpthread
ifeq ($(DEBUG), 1)
	CPPFLAGS += -g -O0
	LDFLAGS += -g -O0
else
	CPPFLAGS += -g -O3
	LDFLAGS += -g -O3
endif

IDEAL_PLACE:=../plastisim/ideal.place
PROUTE_PLACE:=../plastisim/final.place
MERGE_LINK:=../plastisim/link.csv

.PHONY: all cp clean clean-local ideal mergelink $(PROUTE_PLACE)

all: tungsten mergelink ../plastisim/ideal.place

tungsten: $(PLASTICINE_LIB) $(OBJ_FILE) 
	$(CC) $(PLASTICINE_LIB) $(OBJ_FILE) $(LDFLAGS) -o tungsten 
	mkdir -p logs

$(BUILDDIR)%.o: src/%.cc Makefile $(PLASTICINE_LIB)
	mkdir -p $(BUILDDIR)
	$(CC) $(CPPFLAGS) -c $< -o $(BUILDDIR)$(notdir $@) 

$(PLASTICINE_LIB): $(TUNGSTEN_SRC)
	make -C ${TUNGSTEN_HOME} DEBUG=$(DEBUG) $(PLASTICINE_LIB)

clean: clean-local
	make -C ${TUNGSTEN_HOME} clean

clean-local:
	rm -f tungsten $(BUILDDIR)*
	rm ../plastisim/*_bb*

cp:
	cp -r $(TUNGSTEN_HOME)/plasticine/resources/* .

$(IDEAL_PLACE): ../plastisim/*.csv $(MERGE_LINK)
	python bin/idealroute.py -i /Top/idealnet -l ../plastisim/link.csv -p ../plastisim/ideal.place
	cp script_p2p script

$(PROUTE_PLACE): 
	cd ../ && bash proute.sh
	cp script_hybrid script

$(MERGE_LINK): tungsten
	cp script_link script
	./tungsten --gen-link
	tail -n +2 ../plastisim/link_bb.csv | cat ../plastisim/link_pir.csv - > ../plastisim/link.csv
	tail -n +2 ../plastisim/node_bb.csv | cat ../plastisim/node_pir.csv - > ../plastisim/node.csv
	tail -n +2 ../plastisim/src_link_bb.csv | cat ../plastisim/outlink_pir.csv - > ../plastisim/outlink.csv
	tail -n +2 ../plastisim/dest_link_bb.csv | cat ../plastisim/inlink_pir.csv - > ../plastisim/inlink.csv

ideal: $(IDEAL_PLACE)

proute: $(PROUTE_PLACE)

mergelink: $(MERGE_LINK) 

-include build/*.d
