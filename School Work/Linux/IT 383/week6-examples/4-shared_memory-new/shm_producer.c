#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <sys/shm.h>
#include <sys/stat.h>

int main()
{
	/* the size (in bytes) of shared memory object */
	const int SIZE=4096;
	/* name of the shraed memory object */
	// const char *name = "OS"; ==> DO NOT use this name!!
	const char *name = "kwsuh_producer_consumer";
	/* strings written to shared memory */
	const char *message_0 = "Hello";
	const char *message_1 = "World!";

	/* shared memroy file descriptor */
	int shm_fd;
	/* pointer to shared memory object */
	void *ptr;
	
	/* create the shared memory object */
	shm_fd = shm_open(name, O_CREAT | O_RDWR, 0666);

	if (shm_fd < 0)
	{
		printf("shm_open failed: return code=%d\n",shm_fd);
		exit(-1);

	}

	
	/* configure the size of the shared memory object */
	ftruncate(shm_fd, SIZE);

	/* memory map the shared memory object */
	ptr = mmap(0,SIZE,PROT_WRITE, MAP_SHARED, shm_fd, 0);

	/* write to the shared memory object */
	sprintf(ptr, "%s", message_0);
	ptr += strlen(message_0);
	sprintf(ptr, "%s", message_1);
	ptr += strlen(message_1);

	return 0;
}


	
	
