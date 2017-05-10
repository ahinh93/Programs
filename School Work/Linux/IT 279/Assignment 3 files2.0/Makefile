Sorts: Sorts.o SortsTest.o 
	g++ -o sort Sorts.o SortsTest.o 

Sorts.o: Sorts.h Sorts.cpp
	g++ -c Sorts.cpp

SortsTest.o: SortsTest.cpp Sorts.h
	g++ -c SortsTest.cpp

clean:
	rm sort *.o
