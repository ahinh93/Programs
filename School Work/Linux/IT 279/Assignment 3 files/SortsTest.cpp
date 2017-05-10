#include "Sorts.h"
#include <iostream>
#include <fstream>
#include <cstring>
#include <time.h>
#include <cstdlib>

using namespace std;

int main(int argc,char *argv[])
{
	/*
	q controls number of elements in array to be build. 
	jumps by 100k
	to go from 100k to 1M, set q from 1 to 11
	*/
	for(int q = 1; q < 11; q++)
	{
		cout<<endl<<"----------------size of " << q*100<< "k----------------";
		//trial controls how many trials to be done; set from 1 to 4 for three trials
		for(int trial = 1; trial < 4; trial++)
		{		 
			/*
			controls threshold to be passed into quicksort and mergesort,  
			set thresh <= 1 or thresh <= 201
			*/
			for(int thresh = 0; thresh <= 1; thresh+=20)
			{
				if (argc!=3)
			  { 
				cout <<"Please enter file name."<<endl;
				return -1;
			  }
			  double *array;
			  int length;

			length = q*100000;//q is constant
			array = new double[length];
			srand(time(NULL));
			for (int i=0;i<length;i++)
				array[i]=(double)(rand() % RAND_MAX);
			ofstream fout;
			fout.open(argv[2]);
			fout << length<<endl;
			for (int i=0;i<length;i++)
			  fout << array[i] << endl;
	
			fout.close();

			fstream fin;

			fin.open(argv[2]);
			if (!fin.is_open())
			{
			  cout <<"Could not open file"<<endl;
			  return -1;
			}
	
			fin >>length;
			array = new double[length];
			for (int i=0;i<length;i++)
			  fin >> array[i];

			double time_taken=0;
			clock_t tinitial = clock();

			/*
			Switch letters depending on which sort you want to use
			Thresh is the value of threshold, controlled by forloop at the top
			*/
			if (strcmp(argv[1],"-i")==0)
				Sorts::insertion_sort(array,1,length);
			if (strcmp(argv[1],"-h")==0)
				Sorts::heapsort(array,length);
			if (strcmp(argv[1],"-m")==0)
				Sorts::mergesort(array,thresh,length);
			if (strcmp(argv[1],"-q")==0)
				Sorts::quicksort(array,thresh,length);

			clock_t tfinal = clock();
			time_taken = tfinal-tinitial;
		   
			time_taken = 1000.0*time_taken/CLOCKS_PER_SEC;

			//uncomment to print resulting array
			/*for (int i=0;i<length;i++)
			{
			  cout <<array[i]<<"\t";
			}*/
			cout<<"\nTrial " << trial << ": ";
			//only prints threshold if threshold is being tested
			if (thresh != 0)
				cout<<"Threshold: " << thresh<<" ";
			cout <<"The sorting took an average of "<<time_taken<<" ms. ";

			delete []array;
			}
		}
	}
	cout<<endl;
}
