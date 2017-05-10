package myUtil;

/**
 * MyList Interface for IT179, ISU.
 * 
 * Modified Sep. 14, 2013
 *  
 * @author Chung-Chih Li
 *	
 * @param <T> Generic type parameter.
 */
public interface MyList<T> extends Iterable<T> {
	
	/**
	 * Return the number of elements stored in this MyList.
	 * @return the number of elements stored in this MyList.
	 */
	public int size();
	
	/**
	 * Get the element at i 
	 * @param i is an int.
	 * @return the ith elements in this MyList, where i starts from 0.
	 * @exception IndexOutOfBoundsException will be thrown if parameter i is not a legitimate index.  
	 */
	public T get(int i); 
	
	/**
	 * Replace the element at i with new one provided by the parameter item. 
	 * @param i is an int.
	 * @param item is a T.
	 * @return the old item at i.
	 * @exception IndexOutOfBoundsException will be thrown if parameter i is not a legitimate index. 
	 */
	public T set(int i, T item);
	
	/**
	 * Find the index of the input item in this MyList. 
	 * @param item is a T.
	 * @return the index where the element is equal to item, -1 if not found.
	 */
	public int indexOf(T item);
	
	/**
	 * Add item to the end of this List.
	 * @param item is a T.
	 */
	public void add(T item);
	
	/**
	 * Add (insert) item to the ith position of this MyList.
	 * @param i is an int.
	 * @param item is a T.
	 * @exception IndexOutOfBoundsException will be thrown if parameter i is not an legitimate index. 
	 */
	public void add(int i, T item);
	
	
	/**
	 * Remove the item at the ith position from this MyArrayList.
	 * @param i is an int.
	 * @exception IndexOutOfBoundsException will be thrown if parameter i is not a legitimate index. 
	 */
	public T remove(int i);
	
	
	/**
	 * This method return an Object array of the same size that contains every element 
	 * in this MyList;
	 * @return an Object Array 
	 */
	public Object[] toArray();
	
	
	/**
	 * Returns an array that contains every element in this MyList. Note, the 
	 * runtime type of the returned array is that of the specified array (i.e. a)in 
	 * the argument.
	 *  
	 * If this MyList does not fits in the specified array, a new array will be
	 * created with the runtime type of the specified array and the size of this list.
	 * If the specified array is bigger than needed, then null will be filled in.
	 * 
	 * @param a is the specific array to receive the element
	 * @return the array that has received the elements.
	 * @exception  NullPointerException will be thrown, if the specified array is null.
	 */
	public <K> K[] toArray(K[] a);
	
	/**
	 *  Override toString method so it will return a String started and ended by [ and ],
	 *  respectively, in which all elements' results of their own toString() will be 
	 *  concatenated according to their order in the list. If there are more than one element, 
	 *  they should be separated by a comma. For example, [John,Tom,Jerry]. Note, the last
	 *  element should not be followed by a comma.  
	 *  
	 *  @return toString() of every element in a String.
	 */
	public String toString();
	
	//public Object clone();
	
}
