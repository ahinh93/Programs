#include <sys/types.h>
#include <sys/ipc.h>
#include <stdio.h> 
#include <errno.h>
#include <string.h>

#include "msg.h"

#define MKEY1 0x1234
main()
{
	int stat;
	int id;

	printf("What's message ID?");
	scanf("%d",&id);
	printf("msg id=%d\n",id);
	msgp.id = 1;
	strcpy(msgp.message,"Hello, dude!");
	stat = msgsnd(id, &msgp, sizeof(msgp),0);

}
