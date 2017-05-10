/**
 * @author Andrew Hinh copyright
 * 10/22/13
 * uid:816226600 
 */

package myUtil;
import java.util.*;

public class AStack<T> extends Object implements Stack<T> {
	
	private T[] myArray;
	int size;
	
	public AStack() {
		myArray = (T[]) new Object[10];
		size = 0;
	}
	
	public AStack(int capacity) {
		myArray = (T[]) new Object[capacity];
		size = 0;
	}
	
	public boolean empty() {
		return (size == 0);
	}
	
	public T peek() {
		if (empty()) {
			throw new EmptyStackException();
		}
		doubleCapacity();
		int i = 0;
		while (myArray[i+1] != null) {
			i++;
		}
		return myArray[i];
	}
	
	public T push(T item)  {
			doubleCapacity();
			int i = 0;
			while (myArray[i] != null) {
				i++;
			}
			myArray[i] = item;
			size++;
			return myArray[i];
		}
	
	public T pop() {
		doubleCapacity();
		if (empty()) 
			throw new EmptyStackException();
		else {
			int i = 0;
			T temp;
			while (myArray[i] != null) {
				i++;
			}
			temp = myArray[i-1];
			myArray[i-1] = null;
			size--;
			return temp;
		}
	}
	
	private void doubleCapacity() {

		if (size == myArray.length) {
			T[] twiceArray = (T[])new Object[myArray.length * 2];
			for (int k = 0; k < myArray.length; k++) {
				twiceArray[k] = myArray[k];
			}
			myArray = twiceArray;
		}
	}
	
	public int size() {
		int i = 0;
		while (myArray[i] != null) {
			i++;
		}
		return i;
	}
	
	
	
	
}
