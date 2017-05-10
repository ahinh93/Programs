AVLTest: AVLSetTest.o AVLSet.o
	g++ -o AVLSetTest AVLSetTest.o AVLSet.o

AVLSetTest.o: AVLSetTest.cpp
	g++ -c AVLSetTest.cpp

AVLSet.o: AVLSet.h AVLSet.cpp
	g++ -c AVLSet.cpp
	
clean:
	rm AVLSetTest *.o
