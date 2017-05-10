#include "stdio.h"
#include "stdlib.h"
#include "string.h"
#include "fcntl.h"
#include "sys/mman.h"
#include "sys/shm.h"
#include "sys/stat.h"

int main()
{
	struct filesharing_struct
	{
		char uploaded;//flag variable
		int size;
		char buffer[2048];//content
	};
	struct filesharing_struct temp;
	const char *name = "ahinh_filesharing";
	const int SIZE = 2048;
	int shm_fd;
	void *ptr;
	//typecast struct
	//struct my *d = (struct my *)malloc(sizeof (temp);

	//create shared memory object
	shm_fd = shm_open(name, O_CREAT | O_RDWR, 0666);
	//configure size of shared mem object to be 2MB
	ftruncate(shm_fd, SIZE);
	//memory map the shared mem obj
	ptr = mmap(0,SIZE, PROT_WRITE, MAP_SHARED, shm_fd, 0);	
	
	printf("Type in the name of the file that will be shared. Ex. /bin/ls\n");

	char input[50];
	int charsEntered;
	charsEntered = scanf("%s", input);
	printf("you typed: %s\n", input);

	//read file from system and copied to memory location.

	//update flag and size fields

	return 0;
}
