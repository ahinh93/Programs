#include "Sorts.h"
#include <iostream>
#include <stdlib.h>
using namespace std;


void Sorts::insertion_sort(double *array,int length)
{
  for (int i=1;i<length;i++)
  {
    double tmp = array[i];
    int j = i;
    while ((j>=1) && (array[j-1]>tmp))
    {
		array[j] = array[j-1];
		j--;
    }
    array[j]= tmp;
  }
}

void Sorts::insertion_sort(double *array,int left, int right)
{
	for (int i=left;i<right;i++)
	{
		double tmp = array[i];
		int j = i;
		while ((j>=left) && (array[j-1]>tmp))
		{
			array[j] = array[j-1];
			j--;
		}
		array[j]= tmp;
	}
}

void Sorts::heapsort(double *arr,int length)
{
	for(int i = length/2; i >= 1; i--)
	{
		percolateDown(arr, i, length);
	}
	heapify(arr,length);
}

void Sorts::heapify(double *arr,int length)
{
	int index;
	double temp;

	for (index = length; index >= 2; index--)
	{
		temp = arr[index];
		arr[index] = arr[1];
		arr[1] = temp;
		percolateDown(arr,1,index-1);
	}
}

void Sorts::percolateDown(double *arr, int index,int length)
{
	double temp = arr[index];
	int child = 2 * index;
	while(child <= length)
	{
		if((child < length) && (arr[child+1] > arr[child]))
		{
			child++;
		}
		if(temp > arr[child])
			break;
		else if(temp <= arr[child])
		{
			arr[child/2] = arr[child];
			child *= 2;
		}
	}
	arr[child/2] = temp;
	return;
}

void Sorts::deleteMin(double *arr,int length)
{
	double temp = arr[1];
	arr[1] = arr[length];
	length--;
	percolateDown(arr,1,length);
}
void Sorts::mergesort(double *arr,int threshold,int length)
{
	double *aux = new double[length];
	mergesort(arr, aux, threshold, 0, length-1);
	delete []aux;
}

void Sorts::mergesort(double *arr, double *aux, int threshold, int i, int j)
{
	int length = j-i+1;
	//if threshold is 0, always do mergesort. otherwise compare threshold

	if(i < j)
	{
		if((i + threshold > j)&&(threshold!=0))
				insertion_sort(arr,i,j);
		else
			{
				int m = ((i+j) / 2);
				mergesort(arr,aux,threshold,i,m);
				mergesort(arr,aux,threshold,m+1,j);
				merge(arr,aux,i,m+1,j);		
			}
	}
}

void Sorts::merge(double *arr, double *aux, int left, int middle, int right)
{
	int i = middle - 1;
    int j = left;
    int k = right-left + 1;

    while(left <= i && middle <= right)
        if(arr[left]< arr[middle])
            aux[j++] = arr[left++];
        else
            aux[j++] = arr[middle++];

    while(left <= i)
        aux[j++] = arr[left++];

    while(middle <= right)
        aux[j++] = arr[ middle++];

    for(int i = 0; i < k; i++, right--)
        arr[right] = aux[right];
}
void Sorts::quicksort(double *arr,int threshold,int length)
{
	quicksort(arr,threshold,0,length-1);
}

void Sorts::quicksort(double *arr, int threshold, int left, int right)
{
	if(left < right)
	{
		if((left + threshold > right)&&(threshold!=0))
				insertion_sort(arr,left,right);
		else
			{
				//pick pivot using median 3
				int pivot_index = median3(arr,left,right);
				//function below chooses pivot using random call
				//int pivot_index = pick_pivot(arr,left,right);
				pivot_index = partition(arr,left,right,pivot_index);
				quicksort(arr,threshold,left,pivot_index-1);
				quicksort(arr,threshold,pivot_index+1,right);			
			}
	}
}

int Sorts::partition(double *arr, int left, int right, int pivot_index)
{
	swap(arr,pivot_index,right);
	int pivot = arr[right];	
	int i = left - 1;
	for(int j=left; j<right; j++)
	{
		if(arr[j]<=pivot)
		{
			i++;
			swap(arr,i,j);
		}
	}
	swap(arr,i+1,right);
	return i+1;
}

int Sorts::pick_pivot(double *arr, int l, int r)
{
	int var = (float)rand()/RAND_MAX;
	return (l+var*(r-l));
}

void Sorts::swap(double *arr,int pivot_index, int right)
{
	double temp = arr[pivot_index];
	arr[pivot_index] = arr[right];
	arr[right] = temp;
}

int Sorts::median3(double *array, int left, int right)
{
	int a,b,c;
	a = left;
	b = (left+right)/2;
	c = right;
	double temp = max(min(array[a],array[b]),min(max(array[a],array[b]),array[c]));

	if(temp == array[a])
		return a;
	if(temp == array[b])
		return b;
	if(temp == array[c])
		return c;
}
