#ifndef _STLSET_H_
#define _STLSET_H_

#include "SetInterface.h"
#include <set>
using namespace std;



class StlSet: public SetInterface<StlSet>
{
	public:
		StlSet();
		~StlSet();
		
		void add(int num);
		string toString();
		int getSize() const;
		StlSet set_union(const StlSet& a) const;
		StlSet set_intersection(const StlSet& a) const;
		StlSet set_difference(const StlSet& a) const;

	private:
		set<int> myset;
};
	

#endif
