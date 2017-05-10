ImageTest: Image.o ImageTest.o
	g++ -o ImageTest Image.o ImageTest.o 

Image.o: Image.cpp Image.h
	g++ -c Image.cpp
	
ImageTest.o: ImageTest.cpp Image.cpp Image.h
	g++ -c ImageTest.cpp
	
clean:
	rm *.o ImageTest

