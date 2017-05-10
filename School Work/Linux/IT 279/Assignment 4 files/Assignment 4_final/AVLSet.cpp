#include "AVLSet.h"
#include <cstdlib>
#include <iostream>
#include <sstream>
#include <vector>

using namespace std;

AVLSet::AVLSet()
{
	root = NULL;
	//cout<<"BSTSet created"<<endl;
}

AVLSet::~AVLSet()
{
	destroy(root);
	//cout<<"entered general deconstructor"<<endl;
}

AVLSet::Node::Node(int datum)
{
	data = datum;
	height = 0;
	size = 0;
	left = NULL;
	right = NULL;
	//cout<<"specific node created"<<endl;
}

AVLSet::Node::~Node()
{
	left= NULL;
	right= NULL;
	//cout<<"node entered specific deconstructor"<<endl;
}

void AVLSet::destroy(Node * r)
{
	//cout<<"node entered DESTROY method"<<endl;
	if(r == NULL)
		{
			//cout<<"Deleting null root"<<endl;
			return;
		}
	if(r->left != NULL)
		destroy(r->left);
	if(r->right != NULL)
		destroy(r->right);
	delete r;
}

void AVLSet::add(int num)
{	
	add(num,root);
}

string AVLSet::toString()
{
	string final = "";
	final += toString(root);
	string temp = ("{" + final + "}");
	return temp.substr(0, temp.length()-2) + "}";
}

string AVLSet::toString(Node * v)
{
	stringstream ss;
	if(v == NULL)
		return "";
	if(v != NULL)
	{
		ss << toString(v->left);
		ss << v->data;
		ss<<",";
		ss << toString(v->right);
	}
	return ss.str();
}
int AVLSet::getSize()const
{
	return size(root);
}

int AVLSet::size(Node * r)const
{	
	return r == NULL ? 0 : r->size;
}

int AVLSet::height(Node * r)
{
	return r == NULL ? -1 : r->height;
}

AVLSet AVLSet::set_union(const AVLSet& a) const
{

	AVLSet resultSet;
	vector<int> firstVect;
	vector<int> secondVect;
	vector<int> resultVect;
	loadVector(firstVect, root);
	loadVector(secondVect, a.root);
	for(int i = 0; i < firstVect.size(); i++)
	{
		resultVect.push_back(firstVect[i]);
	}
	for(int i = 0; i < secondVect.size(); i++)
	{
		resultVect.push_back(secondVect[i]);
	}

	for(int i = 0; i < resultVect.size(); i++)
	{
		resultSet.add(resultVect[i]);
	}
	return resultSet;
}

AVLSet AVLSet::set_intersection(const AVLSet& a) const
{	
	AVLSet resultSet;
	vector<int> firstVect;
	vector<int> secondVect;
	loadVector(firstVect, root);
	loadVector(secondVect, a.root);
	for(int i = 0; i < firstVect.size(); i++)
	{
		for(int k = 0; k < secondVect.size(); k++)
		{
			if(firstVect[i] == secondVect[k])
			{
				resultSet.add(firstVect[i], resultSet.root);
			}
		}
	}
	return resultSet;
}
AVLSet AVLSet::set_difference(const AVLSet& a) const
{
	AVLSet resultSet;
	vector<int> firstVect;
	vector<int> secondVect;
	vector<int> resultVect;
	loadVector(firstVect, root);
	loadVector(secondVect, a.root);
	for(int i = 0; i < firstVect.size(); i++)
	{
		bool found = false;
		for(int k = 0; k < secondVect.size(); k++)
		{
			if(firstVect[i] == secondVect[k])
			{
				found = true;
			}
		}
		if(!found)
		{
			resultSet.add(firstVect[i]);
			found = false;
		}
	}

	return resultSet;
}

void AVLSet::loadVector(vector<int> & t, Node* r) const
{
	if(r != NULL)
	{
		loadVector(t,r->left);
		t.push_back(r->data);
		loadVector(t,r->right);
	}
}

void AVLSet::add(int datum, Node *& r)
{

	//check to see if root is empty, if so then create root node
	if(r == NULL)
	{
		r = new Node(datum);
		//cout<<"created new node for "<< datum<<endl;
		if(root == NULL)
		{
			//cout<<"appointed "<< datum << " as new root node"<<endl;
			root = r;
		}
	}
	//if datum is less than node's data, send it to node's left
	else if(datum < r-> data)
	{
		add(datum, r->left);
		//cout<<"added " << datum << " to the left"<<endl;
		if(height(r->left)-height(r->right) == 2)
		{
			//cout<<"entered left imbalance"<<endl;
			if(datum < r->left->data)
				rotateWithLeftChild(r);
			else
				doubleWithLeftChild(r);
		}
	}
	else if(datum > r->data)
	{
		add(datum,r->right);
		//cout<<"added " << datum << " to the right"<<endl;
		if(height(r->right) - height(r->left) == 2)
		{
			//cout<<"entered right imbalance"<<endl;
			if(r->right->data < datum)
				rotateWithRightChild(r);
			else
				doubleWithRightChild(r);
		}
	}
	
	r->height = max(height(r->left), height(r->right)) + 1;
	r->size = size(r->left) + size(r->right) + 1;
	//cout<<"successfully added to tree"<<endl;

}

void AVLSet::rotateWithLeftChild(Node *& k2)
{
	Node *k1 = k2->left;
	k2->left = k1->right;
	k1->right = k2;
	k2->height = max(height(k2->left), height(k2->right)) + 1;
	k1->height = max(height(k1->left), k2->height) +1;
	k2 = k1;
}

void AVLSet::rotateWithRightChild(Node * & k2)
{
	Node *k1 = k2->right;
	k2->right = k1-> left;
	k1->left = k2;
	k2-> height = max( height(k2->right), height(k2->left)) +1;
	k1-> height = max(height(k1->right), k2->height) +1;
	k2->size = size(k2->left) +size(k2->right) + 1;
	k1->size = size(k1->left) +size(k1->right) + 1;
	k2=k1;
}

void AVLSet::doubleWithLeftChild(Node * & k3)
{
	rotateWithRightChild(k3->left);
	rotateWithLeftChild(k3);
	k3->size = size(k3->left) +size(k3->right) + 1;
}

void AVLSet::doubleWithRightChild(Node * & k3)
{
	rotateWithLeftChild(k3->right);
	rotateWithRightChild(k3);
	k3->size = size(k3->left) +size(k3->right) + 1;
}

