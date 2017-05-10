/* You should not modify this program  */
import myUtil.*;

import java.util.Iterator;

public class Asg4 {

	static MySLinkedList<Integer> addSome(int n) {
		MySLinkedList<Integer> a =  new  MySLinkedList<Integer>();
		// Add some Integers into to a
		for (int i=0; i<n; i++) {
			a.add(new Integer(i));
			a.add(a.size()-i, new Integer(i));
		}
		System.out.println(a.toString());
		// Try add's Exception
		try {
			a.add(a.size()+1, new Integer(999));
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Can't add item at "+(a.size()+1));
		}
		return a;
	}
	
	static void removeSome(MySLinkedList<Integer> a) { 
		// Remove some Integers from a
		int n =a.size()/2;
		while (a.size()>n) {
			System.out.print("remove "+a.remove(a.size()-1)+", ");
		}
		System.out.println("\nAfter remove:"+a.toString());
		// Try remove's Exception
		try {
			System.out.print("remove "+a.remove(a.size()-1)+", "); 
			System.out.print("remove "+a.remove(a.size())+", "); ;
		} catch (IndexOutOfBoundsException e) {
			System.out.print("Can't remove item at "+a.size()+" from ");
			System.out.println(a.toString());
		}
	}
	
	static void tryToArray(MySLinkedList<Integer> a) {	
		Object[] o = a.toArray();
		System.out.print("Try toArray:");
		for (int i=0; i<o.length; i++)
			System.out.print(o[i].toString()+" ");
		System.out.print("\n");
	}
	
	static void tryCopyConstructor(MySLinkedList<Integer> a) {
		MySLinkedList<Integer> b = new MySLinkedList<Integer>(a);
		removeSome(b);
		System.out.println("Try copy constructor:"+b.toString());
	}
	
	static void tryIndexOf(MySLinkedList<Integer> a) {
		try {
			for (int i=0; i<=a.size(); i++) {
				Integer item= a.get(i);
				System.out.print("["+a.indexOf(item)+"]:"+item.toString()+" ");
			}
		}  catch (IndexOutOfBoundsException e) {
			System.out.print("["+a.indexOf(null)+"]:null ");
			System.out.println("["+a.indexOf(99)+"]:99");
		}
		for (int i=0; i<=a.size(); i++) {
			System.out.print("["+a.indexOf(i)+"]:"+i+" ");
		}
	}
	
	static void getVector(int n, MyList<Integer> a) {
		if (a==null) return;
		for (int i=0;i<n;i++) a.add(Math.random()<0.5?0:1);	
	}
	
	static int productGet(MyList<Integer> x, MyList<Integer> y) {
		int result=0;
		for (int i=0;i<x.size();i++) result += (x.get(i)*y.get(i));
		return result;
	}
	
	static int productIter(MyList<Integer> x, MyList<Integer> y) {
		Iterator<Integer> iterx = x.iterator();
		Iterator<Integer> itery = y.iterator();
		int result=0;
		while (iterx.hasNext()) {
			result += (iterx.next()*itery.next());
		}
		return result;
	}
		
	static void tryIterator(int n, MyList<Integer> x, MyList<Integer> y) {
		long start, end;
		getVector(n,x);
		getVector(n,y);
		start=System.currentTimeMillis();
		productGet(x,y);
		end=System.currentTimeMillis();
		System.out.printf("\tUsing get():    %6d ms\n",end-start);
		start=System.currentTimeMillis();
		productIter(x,y);
		end=System.currentTimeMillis();
		System.out.printf("\tUsing Iterator: %6d ms\n",end-start);
	}
	
	static void tryIterator(MySLinkedList<Integer> a) {
			System.out.println("\nTry Iterator:\n"+a.toString());
			Iterator<Integer> iter = a.iterator();
			while (iter.hasNext()) {
				int i = iter.next();
				if (i%2 == 0) iter.remove();
			}
			System.out.print(a.toString());
			
			MyList<Integer> x,y;
			int n=50000;
			System.out.println("\nVector size:"+n+" ***************************");
			System.out.println("   MyArrayList<Integer>");
			x = new MyArrayList<Integer>();
			y = new MyArrayList<Integer>();
			tryIterator(n,x,y);
			System.out.println("   MySLinkedList<Integer>");
			x = new MySLinkedList<Integer>();
			y = new MySLinkedList<Integer>();
			tryIterator(n,x,y);
	}
	
	static void tryEquals(MySLinkedList<Integer> a) {
		System.out.println("\nTry equals:");
		MySLinkedList<Integer> b = new MySLinkedList<Integer>(a);
		a.remove(0);
		a.add(0,0);
		System.out.print(a.toString()+" and "+b.toString()+" are ");
		if (a.equals(b)) System.out.println("the same.");
		else System.out.println("not the same.");
		a.add(9);
		System.out.print(a.toString()+" and "+b.toString()+" are ");
		if (a.equals(b)) System.out.println("the same.");
		else System.out.println("not the same.");
}
	
	public static void main(String[] args) {
		if (args.length == 0)  {		
			System.out.println("Please provide an integer argument to the program.");
			System.exit(-1);
		}
		MySLinkedList<Integer> a;
		a = addSome(Integer.parseInt(args[0]));
		removeSome(a);
		tryToArray(a);
		tryCopyConstructor(a);
		tryToArray(a);
		tryIndexOf(a);
		tryIterator(a);
		tryEquals(a);
	}
}
