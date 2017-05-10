#include "StlSet.h"
#include <sstream>
#include <vector>
#include <algorithm>
using namespace std;

StlSet::StlSet()
{
}

StlSet::~StlSet()
{
}

void StlSet::add(int num)
{
	myset.insert(num);
}

string StlSet::toString()
{
	stringstream ss;
	
	ss << "{";
	for (set<int>::iterator it=myset.begin();it!=myset.end();it++)
	{
		ss << *it << ",";
	}
	ss << "\b}";
	
	return ss.str();
}

int StlSet::getSize() const
{
	return myset.size();
}

StlSet StlSet::set_union(const StlSet& a) const
{
	vector<int> result(getSize() + a.getSize());
	StlSet resultSet;
	
	vector<int>::iterator it = std::set_union(myset.begin(),myset.end(),a.myset.begin(),a.myset.end(),result.begin());
	
	result.resize(it-result.begin());
	
	for (int i=0;i<result.size();i++)
	{
		resultSet.add(result[i]);
	}
	
	return resultSet;
}
	
StlSet StlSet::set_intersection(const StlSet& a) const
{
	vector<int> result(getSize() + a.getSize());
	StlSet resultSet;
	
	vector<int>::iterator it = std::set_intersection(myset.begin(),myset.end(),a.myset.begin(),a.myset.end(),result.begin());
	
	result.resize(it-result.begin());
	
	for (int i=0;i<result.size();i++)
	{
		resultSet.add(result[i]);
	}
	
	return resultSet;
}


StlSet StlSet::set_difference(const StlSet& a) const
{
	vector<int> result(getSize() + a.getSize());
	StlSet resultSet;
	
	vector<int>::iterator it =	std::set_difference(myset.begin(),myset.end(),a.myset.begin(),a.myset.end(),result.begin());
	
	result.resize(it-result.begin());
	
	for (int i=0;i<result.size();i++)
	{
		resultSet.add(result[i]);
	}
	
	return resultSet;
}
	
