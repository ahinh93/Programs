Hashtable: Hashtable.o HashtableTest.o 
	g++ -o hash Hashtable.o HashtableTest.o 

Hashtable.o: Hashtable.h Hashtable.cpp
	g++ -c Hashtable.cpp

HashtableTest.o: HashtableTest.cpp Hashtable.h
	g++ -c HashtableTest.cpp

clean:
	rm hash *.o
