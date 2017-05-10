#ifndef EXPANDIBLEARRAY_H
#define EXPANDIBLEARRAY_H
#include <vector>
#include <string>

using namespace std;

class ExpandibleArray
{

	private:
		vector<int> myVector;

	public:
		void push_back(int num);
		void remove(int num);
		int& operator [](const int num) throw(int);
		string toString();

};
#endif
