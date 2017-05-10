#include "Sorts.h"
#include <iostream>
using namespace std;


void Sorts::insertion_sort(double *array,int length)
{
  for (int i=1;i<length;i++)
  {
    double tmp = array[i];
    int j = i;
    while ((j>=0) && (array[j]>tmp))
    {
			array[j+1] = array[j];
			j--;
    }
    array[j+1]= tmp;
  }
	cout<<"reached insertion sort"<<endl;
}

