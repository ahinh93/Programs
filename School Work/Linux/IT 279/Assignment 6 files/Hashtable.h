#ifndef DATE_H
#define DATE_H
#include "fstream"
#include "stdio.h"
#include "string"
#include "vector"
#include "iostream"

using namespace std;


class Hashtable
{
	class Date
	{
		friend class Hashtable;
		public:	
			Date();
			Date(string name, int day, int month, int year)
			{
				this->name = name;
				this->day = day;
				this->month = month;
				this->year = year;
			};
		private:
			int day;
			int month;
			int year;
			string name;
			Date *hash;
	};
	//private variables
	private:
		Date **table;
		int primeArr[17984];
		int size;
		int capacity;
		static const double loadFactor = 0.45;
	//private functions
	private:
		int hash(int day, int month, int year);
		int rehash(int oldCapacity);
		void quadProbe(Date *d);
	//public functions
	public:
		Hashtable();
		~Hashtable();
		void push(string name, string date);
		//add rehash function?
	
};
#endif
