#include "asg1.h"


int main()
{
	FILE *file;
	char *filename = "output.txt";

	/*open the file for writing*/
	file = fopen(filename, "w");
	if(file==NULL){
		fprintf(stderr,"FILE %s couldn't be opened\n",filename);
		exit(1);
	}
	
	/*write to the file*/
	fprintf(file, "Hello World!\n");

	/*close the file*/
	fclose(file);

	//run asg2.c
	run();
}
