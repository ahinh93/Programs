#include <stdio.h>

int pid;
main()
{

//	sleep(60);
	__asm__(
		"movl $20, %eax \n"
		"call *%gs:0x10	\n" /* offset 0x10 is not fixed across the systems */
		"movl %eax, pid	\n"
	);

	printf("my PID is %d\n", pid);

}
