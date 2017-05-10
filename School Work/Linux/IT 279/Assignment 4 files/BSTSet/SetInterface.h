#ifndef SETINTERFACE_H_
#define SETINTERFACE_H_

#include <string>
using namespace std;

template <class T>
class SetInterface
{
public:
		virtual ~SetInterface()
		{
		}
		
		virtual int getSize() const=0;
		
		virtual void add(int num)=0;
		virtual string toString()=0;
		virtual T set_union(const T& a) const=0;
		virtual T set_intersection(const T& a) const=0;
		virtual T set_difference(const T& a) const=0;
};
#endif
