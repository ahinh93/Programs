#include "Hashtable.h"

int main()
{
	Hashtable test;

	ifstream fin("randomNames.csv");

	if(fin.is_open())
	{
		while(!fin.eof())
		{
			string line;
			fin >> line;	
			string name, date;
			
			name = line.substr(0,line.find(","));
			date = line.substr(line.find(",")+1);
			//don't add any empty lines from text
			if(name != "" && date != "")
				test.push(name,date);
		}
		fin.close();
	}
}
