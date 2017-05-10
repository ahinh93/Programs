#include "dslib.h"
int main()
{
	//test init
	stack *myStack = malloc(sizeof(stack));	
	printf("max capacity before init call: %d\n",myStack->cap);
	stack_init(myStack,10);
	printf("max capacity after init call: %d\n",myStack->cap);
	//test size
	printf("size of stack before pushes: %d\n",stack_size(myStack));
	//test push
	printf("attempting to push these integers in the following order: \n)");
	
	//seeding rand to make it truly 'random'
	srand(time(NULL));
	while(stack_size(myStack)!=myStack->cap)
	{
		int temp = rand()%RAND_MAX;
		printf("%d\n",temp);
		stack_push(myStack,temp);
	}
	printf("size of stack after pushes: %d\n",stack_size(myStack));
	//test pop
	printf("attempting to pop, reading off numbers as they're being popped: \n");
	while(stack_size(myStack)>0)
	{
		printf("%d\n",stack_pop(myStack));
	}
	printf("size of stack after pops: %d\n",stack_size(myStack));
	//test stack pop error catch
	printf("attempting to pop with empty stack...\n");
	stack_pop(myStack);
	//test deallocate
	stack_deallocate(myStack);	
	return 0;
}
