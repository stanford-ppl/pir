CXX = g++
MAIN = hashlib.so

all: *.o
	$(CXX) -shared -Wl,-soname,$(MAIN) -o $(MAIN) $^

*.o: *.cpp *.h
	$(CXX) -std=c++17 -shared -c -fPIC $^

.PHONY: clean

clean:
	rm *.o
