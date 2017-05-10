TestImage: Image.o TestImage.o
	g++ -o TestImage Image.o TestImage.o 

Image.o: Image.cpp Image.h
	g++ -c Image.cpp
	
TestImage.o: TestImage.cpp Image.cpp Image.h
	g++ -c TestImage.cpp
	
clean:
	rm *.o TestImage

