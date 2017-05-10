#include "dslib.h"

void stack_init(stack *s, int capacity)
{
	s->cap = capacity;
	s->currSize = 0;
	s->arr = malloc(sizeof(int)*capacity);
}

int stack_size(stack *s)
{
	return s->currSize;
}

int stack_pop(stack *s)
{
	if(stack_size(s)<=0)
	{
		printf("ERROR: ATTEMPTING TO POP EMPTY STACK. NOTHING TO POP\n");
		return -1;
	}
	else
	{
		int temp = s->arr[stack_size(s)-1];
		s->currSize--;
		return temp;
	}

}
void stack_push(stack *s, int e)
{
	//first check size of stack to max capacity, if full-> do nothing
	if(stack_size(s)!=s->cap)
	{
		s->arr[stack_size(s)]=e;
		s->currSize++;
	}
	else
	{
		printf("ERROR: stack already full. No action performed.\n");
	}
}
void stack_deallocate(stack *s)
{
	s = (stack*)realloc(s,sizeof(stack));
	free(s);
}
