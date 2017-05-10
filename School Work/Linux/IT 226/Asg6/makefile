PQueue: PQueue.o TestPQueue.o
	g++	-o	TestPQueue PQueue.o TestPQueue.o

PQueue.o: PQueue.cpp PQueue.h
	g++	-c	PQueue.cpp

TestPQueue.o: TestPQueue.cpp PQueue.cpp PQueue.h
	g++	-c	TestPQueue.cpp

clean:
	rm	*.o	TestPQueue
