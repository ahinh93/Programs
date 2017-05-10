#ifndef PQUEUE_H
#define PQUEUE_H
#include <string>
#include <map>

using namespace std;

class PQueue
{
	private:
		map<int,string> myMap;


	public:
		PQueue();
		~PQueue();
		void push(int priority,string item);
		string pop();
		void changePriority(string item, int new_p);


};
#endif
