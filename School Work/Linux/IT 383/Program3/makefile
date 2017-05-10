buffer: test.o 
	g++ -lpthread -o buffer test.o 

test.o: test.cpp buffer.h
	g++ -lpthread -c test.cpp

clean:
	rm buffer *.o
