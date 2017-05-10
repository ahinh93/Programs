TestImage: Image.o Blur.o FlipVertical.o GetEdges.o TestImage.o ToPPMFile.o FromPPMFile.o ImageOperation.o
	g++ -o TestImage Image.o Blur.o FlipVertical.o GetEdges.o TestImage.o ToPPMFile.o FromPPMFile.o ImageOperation.o

Image.o: Image.cpp Image.h
	g++ -c Image.cpp


ImageOperation.o: ImageOperation.h Image.h
	g++ -c ImageOperation.cpp
	
Blur.o: Image.h Blur.cpp Blur.h
	g++ -c Blur.cpp

FlipVertical.o: Image.h FlipVertical.cpp FlipVertical.h
	g++ -c FlipVertical.cpp
	
GetEdges.o: Image.h GetEdges.cpp GetEdges.h
	g++ -c GetEdges.cpp
	
TestImage.o: TestImage.cpp Image.cpp Image.h
	g++ -c TestImage.cpp

FromPPMFile.o: IOOperations.h FromPPMFile.cpp
	g++ -c FromPPMFile.cpp

ToPPMFile.o: IOOperations.h ToPPMFile.cpp
	g++ -c ToPPMFile.cpp

	
clean:
	rm *.o TestImage

