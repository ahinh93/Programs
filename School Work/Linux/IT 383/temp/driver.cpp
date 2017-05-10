#include "buffer.h"
#include <iostream>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <semaphore.h>

using namespace std;

#define VALUE_MAX 1000

int counter = 0;
buffer_item buffer[BUFFER_SIZE];
pthread_t tid1, tid2;
pthread_attr_t attr;
pthread_mutex_t mutex;
sem_t full, empty;


int insert_item(buffer_item item)
{
	//cout<<"insert called"<<endl;
	/*insert item into buffer return 0 if succesful, otherwise 		return -1 indicating an error */
	
	if(counter<BUFFER_SIZE)
	{
		buffer[counter] = item;
		counter++;
		return 0;
	}else{
		return -1;
	}

	

}

int remove_item(buffer_item *item)
{
	//cout<<"remove called"<<endl;
	/* remove object from the buffer placing it in item return 0 if 	succesful otherwise return -1 */

	if(counter>0)
	{
		*item = buffer[counter-1];
		counter--;
		return 0;
	}else{
		return -1;
	} 
}



void *producer(void *param)
{
	//cout<<"\nproducer created"<<endl;	
	buffer_item item;
	item = rand();

	while(true) {
	//sleep for a random period number
	int randSleep = rand()%10;
	//cout<<"producer sleep: "<<randSleep<<endl;
	sleep(randSleep);//how long should it sleep?
	//generate random number
	
	sem_wait(&empty); //cout<<"producer has the sem"<<endl;
	pthread_mutex_lock(&mutex);//cout<<"producer has the lock"<<endl;
	
	item = rand()%VALUE_MAX;
	
	if(insert_item(item))
	{
		cout<<"report error condition buffer full.\n";
	}else{
			cout<<"[producer thread ID: "<<pthread_self()<<" added an item (value: "<<item<<") to the buffer."<<endl;   			
	}
	//unlock mutex here
	pthread_mutex_unlock(&mutex);//cout<<"producer releases lock."<<endl;
	sem_post(&full);//cout<<"producer releases sem."<<endl;
	}
	
}

void *consumer(void *param)
{
	buffer_item item;

	//cout<<"\nconsumer called."<<endl;

	while(true) {
		//sleep for a random period of time
		int randSleep = rand()%10;
		sleep(randSleep);//whow long should it sleep?

		
		sem_wait(&full);//cout<<"consumer has sem full"<<endl;
		pthread_mutex_lock(&mutex);//cout<<"consumer has the lock;"<<endl;

		if(remove_item(&item))
		{
			cout<<"report error condition buffer empty.\n";
		}else{
			cout<<"[consumer thread ID: "<<pthread_self()<<" removed an item (value: "<<item<<") from the buffer."<<endl;   			
			//cout<<"consumer consumed "<<item<<endl;
		}
	pthread_mutex_unlock(&mutex);//cout<<"consumer releases the lock."<<endl;
	sem_post(&empty);//cout<<"consumer releases the sem."<<endl;
	}
}


int main(int argc, char *argv[])
{
/*
1. get command line arguments argv[1],argv[2],argv[3]
2. intialize buffer
3. create producer thread(s)
4. create consumer thread(s)
5. sleep
6. exit
*/

cout<<"How long to sleep: "<<argv[1]<<".\n";
int mainSleepTime = atoi(argv[1]);
cout<<"Number of producers: "<<argv[2]<<".\n";
int numProducers = atoi(argv[2]);
cout<<"Number of consumers: "<<argv[3]<<".\n";
int numConsumers = atoi(argv[3]);

//made buffer global

pthread_attr_init(&attr);
pthread_mutex_init(&mutex,NULL);

sem_init(&full,0,0);
sem_init(&empty,0,BUFFER_SIZE);

for(int i = 0;i<numProducers;i++)
{
	pthread_create(&tid1,&attr,producer,NULL); 

}

for(int i = 0;i<numConsumers;i++)
{
	pthread_create(&tid2,&attr,consumer,NULL);
}

sleep(mainSleepTime);
//cout<<"sleep over"<<endl;
//pthread_join(tid1,NULL);
//pthread_join(tid2,NULL);
cout<<"Main ends."<<endl;
exit(0);



}
