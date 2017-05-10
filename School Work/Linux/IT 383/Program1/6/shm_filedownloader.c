#include "stdio.h"
#include "stdlib.h"
#include "string.h"
#include "fcntl.h"
#include "sys/mman.h"
#include "sys/shm.h"
#include "sys/stat.h"

int main()
{
	const char *name = "ahinh_filesharing";
	const int SIZE = 2048;
	int shm_fd;
	void *ptr;
	int temp;
	//open the shared memory object
	shm_fd = shm_open(name, O_RDONLY, 0666);

	//memory map the shared object
	ptr = mmap(0, SIZE, PROT_READ, MAP_SHARED, shm_fd, 0);

	if(ptr == (void *)-1)
	{
		//file not found
		printf("ERROR: NO CONTENT NAMED %s WAS FOUND ON THE SHARED MEMORY\n",name);
	}
	else
	{
		//file exists, print out size and save to directory
		fseek(ptr, 0L, SEEK_END);
		temp = ftell(ptr);
		printf("SIZE OF FILE: %d\nDownloading copy into output.txt\n",temp);
		fseek(ptr, 0L, SEEK_SET);
		
		FILE *f = fopen("output.txt", "w");
		fwrite(ptr, sizeof(char), temp, f);
		fclose(f);
	}
	return 0;
}
