all: shm_producer shm_consumer

shm_producer: shm_producer.c
	gcc -o shm_producer shm_producer.c -lrt

shm_consumer: shm_consumer.c
	gcc -o shm_consumer shm_consumer.c -lrt

clean:
	rm *.o shm_producer shm_consumer
