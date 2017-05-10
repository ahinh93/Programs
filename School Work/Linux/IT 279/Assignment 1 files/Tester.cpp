#include "ExpandibleArray.h"
#include <iostream>
#include <vector>
#include <iterator>
#include <algorithm>

using namespace std;


main()
{

	ExpandibleArray ea;
	cout<<"Loading integers 0-9 into array"<<endl;//check contents of array	
	//load array with integers
	for(int i = 0; i < 10; i++)
	{
		ea.push_back(i);
	}
	cout<<"Array contains: " << ea.toString()<<endl;

	cout<<"Array at index 0 contains: " << ea[0]<<endl;//check specific index of array
	cout<<"Array at index 4 contains: " << ea[4]<<endl;
	cout<<"Array at index 9 contains: " << ea[9]<<endl;
	//unchecking line below will search for invalid index, and throw an exception
	//cout<<"Array at index 29 contains: " << ea[29]<<endl; 

	cout<<"Array contains: " << ea.toString()<<endl;

	ea.remove(8);
	ea.remove(2);
	ea.remove(5);
	ea.remove(4);
	ea.remove(8);	//this is a duplicate delete
	ea.remove(1);
	ea.remove(3);
	ea.remove(9);
	ea.remove(0);

	cout<<"Array contains: " << ea.toString()<<endl; //check contents of array	
	
	//modify contents of array directly
	cout<<"Change index 1 to -3456" <<endl;
	ea[1] = 29;
	cout<<"Change index 0 to 7555" <<endl;
	ea[0] = 7555;
	cout<<"Array contains: " << ea.toString()<<endl;

	cout<<"Attempt invalid index modification at index 3: " << endl;
	ea[3] = 1;
}
