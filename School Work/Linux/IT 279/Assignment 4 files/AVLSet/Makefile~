BSTTest: BSTSetTest.o BSTSet.o
	g++ -o BSTSetTest BSTSetTest.o BSTSet.o

BSTSetTest.o: BSTSetTest.cpp
	g++ -c BSTSetTest.cpp

BSTSet.o: BSTSet.h BSTSet.cpp
	g++ -c BSTSet.cpp
	
clean:
	rm BSTSetTest *.o
