#include "stdio.h"
#include "stdlib.h"
#include "string.h"
#include "fcntl.h"
#include "sys/mman.h"
#include "sys/shm.h"
#include "sys/stat.h"

int main()
{
	const int SIZE = 4096;
	const char *name = "OS";
	//shared mem file descriptor
	int shm_fd;
	//pointer to the shared memory obj
	void *ptr;
	
	//create teh shared mem obj
	shm_fd = shm_open(name,O_RDONLY, 0666);

	//memory map to the shared mem obj
	ptr = mmap(0,SIZE,PROT_READ, MAP_SHARED, shm_fd,0);

	//read from shared mem obj
	printf("%s",(char*)ptr);

	//remove shared mem obj
	shm_unlink(name);

	return 0;
}
