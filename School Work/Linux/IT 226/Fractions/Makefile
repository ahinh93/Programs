CompoundFractions: CompoundFraction.o CompoundFractionsTest.o
		g++ -o CompoundFractions CompoundFraction.o CompoundFractionsTest.o

CompoundFraction.o: CompoundFraction.cpp
		g++ -c CompoundFraction.cpp

CompoundFractionsTest.o: CompoundFractionsTest.cpp
		g++ -c CompoundFractionsTest.cpp

clean: 
	rm CompoundFractions *.o
