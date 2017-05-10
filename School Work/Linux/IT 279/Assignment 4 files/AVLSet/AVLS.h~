#ifndef _AVLSET_H_
#define _AVLSET_H_
#include "SetInterface.h"
#include <vector>

using namespace std;



class AVLSet: public SetInterface<AVLSet>
{
	class Node
	{
   		friend class AVLSet;
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
		AVLSet();
		~AVLSet();
		
		void add(int num);
		string toString();
		int getSize() const;
		AVLSet set_union(const AVLSet& a) const;
		AVLSet set_intersection(const AVLSet& a) const;
		AVLSet set_difference(const AVLSet& a) const;

	private:
		Node * root;
		void add(int datum, Node*& r);
		void rotateWithLeftChild(Node *& k2);
		void rotateWithRightChild(Node *& k2);
		void doubleWithLeftChild(Node * & k3);
		void doubleWithRightChild(Node * & k3);
		void destroy(Node * r);
		int size(Node * r) const;
		int height(Node * r);
		string toString(Node * v);
		void loadVector(vector<int> & t, Node* r) const;
};
	

#endif
