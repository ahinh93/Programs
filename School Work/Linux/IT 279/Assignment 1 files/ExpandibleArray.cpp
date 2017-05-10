#include "ExpandibleArray.h"
#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
#include <sstream>

using namespace std;

void ExpandibleArray::push_back(int num)
{	
	//insert int into array, ex: size is 3, so insert new element into array[3]
	myVector.push_back(num);

	cout<<"vector size: " << myVector.size() << " vector capacity: " << myVector.capacity()<<endl;
}

void ExpandibleArray::remove(int num)
{
	//check if array is empty
	if(myVector.empty())
		cout<<"Array is empty, nothing to remove."<<endl;
	//try to remove num	
	else
	{
		
		//find leftmost occurrence of num
		
		vector<int>::iterator location;
		location = find(myVector.begin(), myVector.end(), num);
		//num was found in vector, remove it and shift all elements 1 to the right
		if(location != myVector.end())
		{
					
			int index = (location - myVector.begin());
			//cout<<"removing " << num << " from index " << index + 1<<endl;
			myVector.erase(myVector.begin() + index);
			//check if vector is less than half full, if so, reduce capacity by half
			if(myVector.size() <= (myVector.capacity()/2) ) 
			{
				vector<int>(myVector).swap(myVector);
			}
			cout<<"Vector size after delete: " << myVector.size() << " Vector capacity after delete: " << myVector.capacity()<<endl;
		}
		//num was not found, do nothing
		else
			cout<<num<<" NOT found"<<endl;
	}
}

int& ExpandibleArray::operator [](int num) throw (int)
{
	if(num >= myVector.size() || num < 0)
	{
		cout<<"ERROR: invalid index entered: " << num << endl;
		throw (-1);
	}	
	return myVector[num];
}

string ExpandibleArray::toString()
{
	stringstream result;
	copy(myVector.begin(), myVector.end(), ostream_iterator<int>(result," "));
	return result.str().c_str();
}

