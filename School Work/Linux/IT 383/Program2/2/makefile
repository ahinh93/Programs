all:  detatched primes revised-thread-create2 thread-create thread-create2

detatched: detatched.c
	gcc -lpthread -o detatched detatched.c

primes: primes.c
	gcc -lpthread -o primes primes.c

revised-thread-create2: revised-thread-create2.c
	gcc -pthread -o  revised-thread-create2 revised-thread-create2.c

thread-create: thread-create.c
	gcc -pthread -o  thread-create thread-create.c

thread-create2: thread-create2.c
	gcc -pthread -o thread-create2 thread-create2.c 

clean:
	rm -f *.o
