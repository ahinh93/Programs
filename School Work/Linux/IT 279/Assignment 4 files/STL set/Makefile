SetTest: SetTest.o StlSet.o
	g++ -o SetTest SetTest.o StlSet.o

SetTest.o: SetTest.cpp
	g++ -c SetTest.cpp

StlSet.o: StlSet.h StlSet.cpp
	g++ -c StlSet.cpp
	
clean:
	rm SetTest *.o
