all: BigIntegerTest MyTester

BigIntegerTest: BigInteger.o BigIntegerTest.o 
	g++ -o BigIntegerTest BigInteger.o BigIntegerTest.o

MyTester: BigInteger.o MyTester.o
	g++ -o MyTester BigInteger.o MyTester.o

BigInteger.o: BigInteger.h BigInteger.cpp
	g++ -c BigInteger.cpp

BigIntegerTest.o: BigIntegerTest.cpp BigInteger.h
	g++ -c BigIntegerTest.cpp

MyTester.o: MyTester.cpp BigInteger.h
	g++ -c MyTester.cpp

clean:
	rm *.o BigIntegerTest MyTester
