#ifndef my_copy_c
#define my_copy_c
#include "stdio.h"
#include "stdlib.h"
#include "fcntl.h"
#include "sys/stat.h"
int main(int argc, char *argv[])
{
	//check if user entered proper parameters, terminate if they didn't.
	if(argc != 3){
		printf("ERROR: program requires 2 parameters. 1 for input file and 1 for output file\n");
		exit(0);
	}

	//save file names
	char *input = argv[1];
	char *output = argv[2];
	
	//attempt to open input file
	FILE *fp;
	FILE *fp2;
	int fdesc;
	fdesc = open(input, O_RDONLY, S_IREAD);
	if(fdesc==-1)
	{
		printf("ERROR: %s NOT FOUND\n",input);
		exit(0);
	}
	char buffer[10240+1];
	int re;
	if(re==-1)
	{
		printf("ERROR: FILE DIDN'T READ\n");
		exit(0);
	}
	int fw = open(output, O_CREAT | O_RDWR, S_IWRITE | S_IREAD);
	if(fw==-1)
	{
		printf("ERROR: %s COULD NOT BE CREATED\n",output);
		exit(0);
	}

	//int wr = write(fdesc,buffer,re);
	//int wr;
	while(read(fdesc,buffer,1)==1)
	{
		
		if(write(fw,buffer,1)==-1)
		{
			printf("ERROR: %s COULD NOT BE WRITTEN\n",output);
			exit(0);
		}
	}

	
	close(re);
	close(fw);

	return 0;
}


#endif
