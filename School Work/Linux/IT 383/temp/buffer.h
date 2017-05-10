#ifndef BUFFER_H
#define BUFFER_H

#define BUFFER_SIZE 5

typedef int buffer_item;

class buffer
{
	public:
		buffer_item buffer[BUFFER_SIZE];
		int insert_item(buffer_item);
		int remove_item(buffer_item);	
};

#endif


