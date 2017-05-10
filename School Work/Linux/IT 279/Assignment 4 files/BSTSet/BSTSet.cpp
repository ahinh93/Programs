#include "BSTSet.h"
#include <cstdlib>
#include <iostream>
#include <sstream>
#include <vector>

using namespace std;

BSTSet::BSTSet()
{
	root = NULL;
	//cout<<"BSTSet created"<<endl;
}

BSTSet::~BSTSet()
{
	destroy(root);
	//cout<<"entered general deconstructor"<<endl;
}

BSTSet::Node::Node(int datum)
{
	data = datum;
	height = 0;
	size = 0;
	left = NULL;
	right = NULL;
	//cout<<"specific node created"<<endl;
}

BSTSet::Node::~Node()
{
	left= NULL;
	right= NULL;
	//cout<<"node entered specific deconstructor"<<endl;
}

void BSTSet::destroy(Node * r)
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

void BSTSet::add(int num)
{	
	add(num,root);
}

string BSTSet::toString()
{
	string final = "";
	final += toString(root);
	string temp = ("{" + final + "}");
	return temp.substr(0, temp.length()-2) + "}";
}

string BSTSet::toString(Node * v)
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
int BSTSet::getSize()const
{
	return size(root);
}

int BSTSet::size(Node * r)const
{	
	return r == NULL ? 0 : r->size;
}

int BSTSet::height(Node * r)
{
	return r == NULL ? -1 : r->height;
}

BSTSet BSTSet::set_union(const BSTSet& a) const
{

	BSTSet resultSet;
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

BSTSet BSTSet::set_intersection(const BSTSet& a) const
{	
	BSTSet resultSet;
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
BSTSet BSTSet::set_difference(const BSTSet& a) const
{
	BSTSet resultSet;
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

void BSTSet::loadVector(vector<int> & t, Node* r) const
{
	if(r != NULL)
	{
		loadVector(t,r->left);
		t.push_back(r->data);
		loadVector(t,r->right);
	}
}

void BSTSet::add(int datum, Node *& r)
{

	//check to see if root is empty, if so then create root node
	if(r == NULL)
	{
		r = new Node(datum);
		
	}
	//if datum is less than node's data, send it to node's left
	else if(datum < r-> data)
	{
		add(datum, r->left);
		//cout<<"added " << datum << " to the left"<<endl;
	}
	else if(datum > r->data)
	{
		add(datum,r->right);
		//cout<<"added " << datum << " to the right"<<endl;		
	}
	
	r->height = max(height(r->left), height(r->right)) + 1;
	r->size = size(r->left) + size(r->right) + 1;
	//cout<<"successfully added to tree"<<endl;

}
