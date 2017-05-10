#ifndef _buffer_h_
#define _buffer_h_
#define BUFFER_SIZE 5

#include "stdio.h"
#include "stdlib.h"
#include "iostream"
#include "sstream"
#include "cstdlib"
#include "unistd.h"
#include "pthread.h"
#include "semaphore.h"

using namespace std;
typedef int buffer_item;

class buffer
{
	public:
		buffer_item buffer[BUFFER_SIZE];
		int insert_item(buffer_item item);
		int remove_item(buffer_item *item);

};


#endif
