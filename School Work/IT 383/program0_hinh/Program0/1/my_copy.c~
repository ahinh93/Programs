#ifndef my_copy_c
#define my_copy_c
#include "stdio.h"
#include "stdlib.h"
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
	if(fopen(input,"r") == NULL)
	{
		printf("ERROR: FILE NOT FOUND\n");
		exit(0);
	}
	fp = fopen(input,"r");
	fp2 = fopen(output,"w");
	//buffer will hold contents of file from fread() call
	char buffer[10240];
	while(fread(buffer,sizeof(char),1,fp)==1)
	{
		fwrite(buffer, sizeof(char),1,fp2);
	}

	fclose(fp);
	fclose(fp2);

	return 0;
}


#endif
