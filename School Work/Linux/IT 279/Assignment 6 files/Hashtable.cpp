#include "Hashtable.h"
#include "sstream"
#include "math.h"

Hashtable::Hashtable()
{
	
	//populate primeArr from .txt file
	string temp = "primes-to-200k.txt";
	ifstream fin(temp.c_str());

	if(fin.is_open())
	{
		while(!fin.eof())
		{
			for(int i = 0; i < 17984; i++)
			{
				fin>>primeArr[i];
			}
		}
		fin.close();
	}
	//table has 0 valid entries
	size = 0;
	//table starts at capacity 11
	capacity = 11;
	table = new Date*[capacity];
	for(int i = 0; i < capacity; i++)
	{
		table[i] = NULL;
	}
}

Hashtable::~Hashtable()
{
	for(int i = 0; i < capacity; i++)
	{
		if(table[i] != NULL)
		{
			delete table[i];
		}
	}
	delete[] table;
}

void Hashtable::push(string name, string date)
{
	//convert date to day, month, year
	int tempDay, tempMonth, tempYear;
	
	istringstream iss(date);
	istringstream convert;
	string token;

	//load values into the three ints then prepare to run it through hash function
	int count = 0;
	while(getline(iss,token, '/'))
	{
		if(count == 0)
		{
			istringstream(token) >> tempDay;
			count++;
		}
		else if(count == 1)
		{
			istringstream(token) >> tempMonth;
			count++;
		}
		else
		{
			istringstream(token) >> tempYear;
			count++;
		}
	}

	//add info to next available position to array
	//check if filled capacity is greater than or equal to .45
	//use trunc to account for implicit decimals in double, to compare doubles
	if(trunc((double)size*100/capacity) >= trunc(loadFactor*100))
	{
		//resize & rehash
		//first create new hash table
			//calculate next prime that's at least double current capacity
		int oldCapacity = capacity;
		capacity *=2;

		bool found = false;
		for(int i = 0; (i < (sizeof(primeArr)/sizeof(primeArr[0])) && !found); i++)
		{
			if(capacity < primeArr[i])
			{
				capacity = primeArr[i];
				found = true;
			}
		}

		rehash(oldCapacity);

		//print out new capacity
		cout<<"capacity: "<<capacity<<endl;
		int key = hash(tempDay,tempMonth,tempYear);
		size++;
	}
	else
	{
		//cout<<"LESS THAN FACTOR || DON'T RESIZE"<<endl;
		//add directly to table
		
		//use hash() to create value and insert to location
		int	key = hash(tempDay,tempMonth,tempYear);
		
		//try to add to Table[key];
		if(table[key] == NULL)
		{
			//insert to table[key]
			Date *d = new Date(name, tempDay, tempMonth, tempYear);
		}
		else
		{
			//quad probe to appropriate index
			Date *d = new Date(name, tempDay, tempMonth, tempYear);
			quadProbe(d);
		}		

		size++;
	}
}

int Hashtable::hash(int tempDay, int tempMonth, int tempYear)
{
	//assumes year spans from 0 to 2300
	//return (tempMonth*pow(12,1)+tempDay*pow(31,2)+tempYear*pow(2300,3)%size) ;
	int key = ((int)trunc(tempMonth*pow(2300,1)) + (int)trunc(tempDay*pow(2300,2)) + (int)trunc(tempYear*pow(2300,3))) % capacity;
	if (key < 0)
	{
		key += capacity;
	}

	return key;

}

int Hashtable::rehash(int oldCapacity)
{
	Hashtable **newTable;
	//create new table for deep copy
	for(int i = 0; i < capacity; i++)
	{
		newTable[i] = NULL;
	}
	//insert old entries to new table using new hash size
	for(int i = 0; i < oldCapacity; i++)
	{
		if(table[i] != NULL)
		{
			//int newKey = hash(table[i]->day, table[i]->month,table[i]->year);
			//cout<<"new key: " << newKey<<endl;
		}
	}	
}

void Hashtable::quadProbe(Date *d)
{
	bool placed = false;
	for(int i = 1; (i < capacity && !placed); i*=2)
	{
		int location = (int)trunc(pow(i,2)) % capacity;
		if(table[location] == NULL)
		{
			table[location] = d;
			placed = true;
		}
	}
}
