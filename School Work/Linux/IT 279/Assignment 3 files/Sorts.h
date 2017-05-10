#ifndef _SORTS_H_
#define _SORTS_H_

class Sorts
{
 public:
  	static void insertion_sort(double *array,int length);
	static void insertion_sort(double *array,int left, int right);

	static void heapsort(double *arr, int length);

	static void mergesort(double *arr, int threshold, int length);

	static void quicksort(double *arr, int threshold, int length);
	
private:
	static void heapify(double *arr,int length);
	static void percolateDown(double *arr, int index,int length);
	static void deleteMin(double *arr,int length);

	static void mergesort(double *arr, double *aux, int threshold, int i, int j);
	static void merge(double *arr, double *aux, int left, int middle, int right);

	static void quicksort(double *arr, int threshold, int left, int right);
	static int partition(double *arr, int left, int right, int pivot_index);
	static int pick_pivot(double *arr, int l, int r);
	static void swap(double *arr, int pivot_index, int right);
	static int median3(double *array, int left, int right);
};

#endif
