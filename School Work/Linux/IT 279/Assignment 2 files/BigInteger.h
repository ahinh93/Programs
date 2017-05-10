#ifndef BIGFILE_H
#define BIGFILE_H
#include <string>
#include <iostream>
#include <sstream>

using namespace std;

class BigInteger
{
	class Node
	{
		friend class BigInteger;
		int data;
		Node* next;
		Node* prev;

		Node(int passedData, Node* n, Node* p)
		{
			data = passedData;
			next = n;
			prev = p;
		}
	};

	private:
		Node* head;
		Node* tail;
		bool isNegative;
		int size;
		
	public:
		BigInteger();
		~BigInteger();
		void init(string passedNum);//this function modifies the calling object
		string toString();
		BigInteger operator+(BigInteger bi);
		BigInteger operator=(BigInteger bi);
		
};
#endif
