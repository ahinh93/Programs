
BINARIES=MazeDraw.class SolveMaze.class libSolveMaze.so

all: $(BINARIES)

	
SolveMaze.h: SolveMaze.class
	javah SolveMaze

SolveMaze.class: MazeDraw.java
	javac MazeDraw.java

MazeDraw.class: MazeDraw.java
	javac MazeDraw.java
	
javaonly: MazeDraw.java
	javac MazeDraw.java

libSolveMaze.so: SolveMaze.o Graph.o
	g++ -shared -I/usr/local/java/jdk1.7.0_25/include -I/usr/local/java/jdk1.7.0_25/include/linux SolveMaze.o Graph.o -o libSolveMaze.so

Graph.o: Graph.cpp Graph.h PriorityQueue.h
	g++ -c Graph.cpp

SolveMaze.o: SolveMaze.cpp SolveMaze.h
	g++ -c SolveMaze.cpp
	
clean:
	rm $(BINARIES) *.o 
