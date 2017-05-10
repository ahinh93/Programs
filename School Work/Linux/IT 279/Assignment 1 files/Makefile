Tester: ExpandibleArray.o Tester.o
	g++ -o A1 ExpandibleArray.o Tester.o

ExpandibleArray.o: ExpandibleArray.h ExpandibleArray.cpp ArrayInterface.h
	g++ -c ExpandibleArray.cpp

Tester.o: Tester.cpp ExpandibleArray.h
	g++ -c Tester.cpp

clean:
	rm *.o A1
