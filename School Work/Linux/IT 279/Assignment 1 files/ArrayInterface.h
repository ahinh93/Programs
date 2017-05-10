#ifndef _ARRAYINTERFACE_H_
#define _ARRAYINTERFACE_H_

#include <string>
using namespace std;

class ArrayInterface
{
	public:
	ArrayInterface(){}
	virtual ~ArrayInterface(){}
	virtual void push_back(int num)=0;
	virtual void remove(int num)=0;
	virtual int& operator[](int i) throw(int)=0;
	virtual string toString()=0;
};

#endif