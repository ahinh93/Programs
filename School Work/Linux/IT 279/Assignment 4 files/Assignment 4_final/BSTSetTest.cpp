#include "BSTSet.h"
#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>
using namespace std;

vector<int> fibonacci(int size);
vector<int> primes(int size);
bool isprime(int num);

int main()
{
	BSTSet a,b,c;
	int i;
	int limit = 30;
	
	vector<int> pr = primes(limit);

	std::random_shuffle(pr.begin(),pr.end());
		
	for (i=0;i<pr.size();i++)
	{
		a.add(pr[i]);
	}
	

	
	vector<int> fibs = fibonacci(limit);
	
	std::random_shuffle(fibs.begin(),fibs.end());
	
	for (i=0;i<fibs.size();i++)
	{
		b.add(fibs[i]);
	}
	
	for (i=0;i<=limit;i+=1)
	{
		c.add(i);
	}
	
	cout << "a (Primes < 50)   =" << a.toString() << endl;
	cout << "b (Fibonacci < 50)=" << b.toString() << endl;
	
	cout << "a U b (Fibonacci or prime)                           = " << a.set_union(b).toString() << endl;
	cout << "a ^ b (Fibonacci and prime)                          = " << a.set_intersection(b).toString() << endl;
	cout << "a \\ b (Prime but not fibonacci)                      = " << a.set_difference(b).toString() << endl;
	cout << "b \\ a (Fibonacci but not prime)                      = " << b.set_difference(a).toString() << endl;
	cout << "c \\ (a U b) (Neither fibonacci nor prime)            = " << c.set_difference(a.set_union(b)).toString() << endl;
}

vector<int> fibonacci(int size)
{
	int a,b,c;
	vector<int> result;
	
	result.push_back(0);
	if (size==1)
		return result;
	
	result.push_back(1);
	
		

	a = 0;
	b = 1;
	c = 1;
	while (c<size)
	{
		result.push_back(c);
		a = b;
		b = c;
		c = a+b;
		
	}
	return result;
}

vector<int> primes(int size)
{
	int i;
	vector<int> result;
	
	i=1;
	while (i<size)	
	{
		if (isprime(i))
			result.push_back(i);
		i++;
	}
	
	return result;
}

bool isprime(int num)
{
	int lim = (int)sqrt(num)+1;
	bool ans;
	int i;
	
	ans = true;
	i=2;
	while ((ans) && (i<=lim))
	{
		if (num%i==0)
			ans = false;
		i++;
	}
	return ans;
}
	

	
