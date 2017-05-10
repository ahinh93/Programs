#ifndef _BSTSET_H_
#define _BSTSET_H_
#include "SetInterface.h"
#include <vector>

using namespace std;



class BSTSet: public SetInterface<BSTSet>
{
	class Node
	{
   		friend class BSTSet;
		public:
			Node(int datum);
			~Node();
		private:
			int data;
			int height;
			int size;
			Node * left;
			Node * right;
	};
	public:
		BSTSet();
		~BSTSet();
		
		void add(int num);
		string toString();
		int getSize() const;
		BSTSet set_union(const BSTSet& a) const;
		BSTSet set_intersection(const BSTSet& a) const;
		BSTSet set_difference(const BSTSet& a) const;

	private:
		Node * root;
		void add(int datum, Node*& r);
		void destroy(Node * r);
		int size(Node * r) const;
		int height(Node * r);
		string toString(Node * v);
		void loadVector(vector<int> & t, Node* r) const;
};
	

#endif
