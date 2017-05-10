package myUtil;

import java.*;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * @author ahinh
 * copyright 
 * @param <T>
 */
public class MyArrayList<T> extends java.lang.Object implements MyList<T>
{
	  
	private int size;
	private T[] data;
	
	//default constructor
	public MyArrayList()
	{
		data = (T[]) new Object[10];
		size = 0;		
	}
	
	//user defined constructor
	public MyArrayList(int capacity)
	{
		data = (T[]) new Object[capacity];
		size = 0;
	}
	
	//copy constructor
	public MyArrayList(MyArrayList<T> ma)
	{
		data = (T[]) new Object[ma.data.length];
		size = ma.size;
		for (int i = 0; i < size; i++)
		{
			data[i] = ma.data[i];
		}
	}
	/**
	 * Helper method to double capacity of data array
	 */
	private void doubleCapacity(){
		int c = data.length * 2;
		T[] newList = (T[]) new Object [c];
		for(int i = 0; i < data.length; i++)
		{
			newList[i] = data[i];
		}
		data = newList;
	}
	
	/**
	 * Inserts item to specified position i of MyList
	 * @param i position to insert item
	 * @param item you want inserted into position i
	 */
	
	public void add(int i, T item)
	{
		//check to make sure requested index exists
		if (i < 0 || i > size)
		{
			throw new ArrayIndexOutOfBoundsException(i);
		}
		//double capacity if size is full
		if (size == data.length)
		{
			doubleCapacity();
		}
		for(int k = size; k > i; k--)
		{
			data[k] = data[k-1];
		}

		data[i] = item;
	}
	
	/**
	 * Adds item T to end of array
	 * @param item object you want inserted to end of array
	 */
	public void add(T item)
	{
		add(size,item);
		size++;
	}

	/**
	 * Returns object from index i
	 * @param i index requested from object array
	 * @return returns object from index i of array
	 */
	public T get(int i)
	{
		//validate the requested index exists
		if(i < 0 || i >= size)
			throw new ArrayIndexOutOfBoundsException(i);
		return data[i];
	}
	/**
	 * Finds the index of requested item, -1 if doesn't exist
	 * @param item item you are requested index of
	 * @return index of requested item, -1 if doesn't exist
	 */
	public int indexOf(T item)
	{
		for(int i = 0; i < data.length; i++)
		{
			if(data[i] == item)
			{
				return i;
			}
		}
		System.out.println("Requested item does not exist in array");
		return -1;
	}
	

	/**
	 * 
	 * @return
	 */
	public Iterator<T> iterator()
	{
		return new myIterator();
	}
	/**
	 * Removes item from specified index and returns that item
	 * @param i specified index user wants to remove and retrieve that item from
	 * @return object from specified index
	 */
	public T remove(int i)
	{
		//validate the requested index exists
		if(i < 0 || i >= size)
			throw new ArrayIndexOutOfBoundsException(i);
		T item = data[i];
		for(int k = 1; k < size-1; k++)
		{
			data[k] = data[k+1];
		}
		size--;
		return item;
	}
	/**
	 * Replaces object from index i with new object
	 * @param i index specified from user to switch
	 * @param item new item user wants in place of old item
	 * @return returns the same object user inputted
	 */
	public T set(int i, T item)
	{
		//validate the requested index exists
		if(i < 0 || i >= size)
			throw new ArrayIndexOutOfBoundsException(i);
		
		data[i] = item;
		return data[i];
	}
	/**
	 * Accessor method of obtaining size
	 * @return returns value of size
	 */
	public int size()
	{
		return this.size;
	}
	/**
	 * Returns an Object array of the same size as MyList that 
	 * contains the same elements in the same indices.
	 * @return object array
	 */
	public java.lang.Object[] toArray()
	{
		Object[] t = new Object[size];
		for(int i = 0; i < size; i++)
		{
			t[i] = data[i];
		}
		return t;
	}
	/**
	 * Returns an array that contains every element in MyList
	 * @param a user defined array to receive element
	 * @return array of elements
	 */
	public <K> K[] toArray(K[] a)
	{
		if (a == null)
			throw new NullPointerException("Argument cna't be null");
		if(a.length < size)
			a = (K[]) new Object[size];
		for(int i = 0; i < size; i++)
			a[i] = (K) data[i];
		return a;
	}
	
public class myIterator implements Iterator<T>
{
			
		private int theOne = 0, seenOne = -1;

		public boolean hasNext()
		{
			return (theOne<size);
		}

		public T next()
		{
			if (!hasNext())
				throw new NoSuchElementException();
			seenOne = theOne;
			theOne++;
			return 
					(T) data[seenOne];
		}

		public T remove(int i)
		{
			if (i < 0 || i >= size)
				throw new ArrayIndexOutOfBoundsException(i);
			T item = (T) data[i];
			for (int k = i; k < size - 1; k++)
			{
				data[k] = data[k+1];
			}
			size--;
			return item;
		}		
		public void remove ()
		{
			
		}
		
}

		
		
		
	
	/**
	 * Overrides default toString method that will return 
	 * concatenated elements of the array listed in order.
	 */
	@Override
	public java.lang.String toString()
	{
		System.out.print("[");
		for(int i = 0; i < size-1; i++)
		{
			System.out.print("" + data[i] + ",");
		}
		System.out.print("" + data[size] + "]");
		return "";
	}
	
}
