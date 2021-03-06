#include "stdio.h"
#include "stdlib.h"
#include "string.h"

int main()
{
	//prompt user for name of file
	printf("Please enter name of file (default: input.txt)\n");
	
	char buffer[10240];
	char line[1024];
	char input[40];
	char *token;
	char delims[] = "\t \n";
	
	int i;
	int size;
	int lineCount = 0;
	int wordCount = 0;

	scanf("%s", input);
	FILE *fd = fopen(input,"r");
	FILE *fd2 = fopen(input,"r");
	if(fd==NULL)
	{
		printf("ERROR: FILE NOT FOUND\n");
		exit(0);	
	}
	
	size =fread(buffer,sizeof(char),10240,fd);
	char test[size+1];
	/*
	for(i = 0; i < size; i++)
	{
		test[i] = buffer[i];
	}
	test[size] = '\0';*/
	strcpy(test,buffer);
	
	while(fgets(line, 1024, fd2))
	{
		lineCount++;
	}

	//begin tokenizing and count words
	for(token = strtok(test,delims);token!=NULL; token = strtok(NULL,delims))
	{
		printf("%s\n",token);
		wordCount++;
	}

	printf("Total words = %d\n",wordCount);
	printf("Total lines = %d\n",lineCount);
	return 0;
}
