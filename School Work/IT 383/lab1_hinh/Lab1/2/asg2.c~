#include "asg1.h"
void run()
{
	FILE *file;
	char *filename = "output.txt";

	/*open the file for writing*/
	file = fopen(filename, "r");
	if(file==NULL){
		fprintf(stderr,"FILE %s couldn't be opened\n",filename);
		exit(1);
	}
	
	/*loop while reading a line at a time from the file and printing*/
	while(1)
	{
		char buffer[80];
		fgets(buffer,80,file);
		/*if its the end of file, break out of the loop*/
		if(feof(file))
			break;

		printf("%s",buffer);
	}

	/*close the file*/
	fclose(file);
}
