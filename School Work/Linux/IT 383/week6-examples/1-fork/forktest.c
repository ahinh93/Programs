#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>
/* Be CAREFUL about buffering effect */
int main()
{

	pid_t pid;
	pid_t pid2;

	pid = fork();

	if (pid < 0) { /* error occurred */
		fprintf(stderr,"Fork Failed");
		return 1;
	}
	else if (pid == 0) {/* child process */
		fprintf(stderr,"[child]--->Child begins...pid=%d\n",getpid());
		fprintf(stderr,"[child]--->Child begins...ppid=%d\n",getppid());
		fflush(stderr);
		sleep(30);
		execlp("/bin/ls","ls",NULL);
		/*  this line will not be reached!!! */
		
	}
	else {/* pid > 0 */ /* parent process */
		/* parent will wait for the child to complete */
		fprintf(stderr,"[parent]--->Child's pid value=%d\n",pid);
		fprintf(stderr,"[parent]--->before calling wait(NULL)",pid);

		wait(NULL);
		fprintf(stderr,"[parent]--->after calling wait(NULL)",pid);
		fprintf(stderr,"[parent]--->Child complete\n");
		fflush(stderr);

	}
	return 0;
}
