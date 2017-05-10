test:  stack.o test.o dslib.h
	gcc -o test  stack.o test.o

stack.o: stack.c
	gcc -c stack.c

test.o: test.c
	gcc -c test.c

clean:
	rm -f *.o
