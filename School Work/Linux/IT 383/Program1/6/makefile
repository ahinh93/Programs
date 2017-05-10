all:  shm_fileuploader shm_filedownloader

shm_fileuploader: shm_fileuploader.c
	gcc -o shm_fileuploader shm_fileuploader.c -lrt

shm_filedownloader: shm_filedownloader.c
	gcc -o shm_filedownloader shm_filedownloader.c -lrt

clean:
	rm -f *.o shm_filedownloader.o shm_fileuploader.o
