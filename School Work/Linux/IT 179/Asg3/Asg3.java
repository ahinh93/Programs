package Asg3;
/* You should not modify this program  */
import myUtil.MyArrayList;

public class Asg3 {

	public static MyArrayList<Integer> addSome(int n) {
		MyArrayList<Integer> a =  new  MyArrayList<Integer>();
		// Add some Integers into to a
		for (int i=0; i<n; i++) {
			a.add(new Integer(i));
			a.add(a.size()-i, new Integer(i));
		}
		System.out.println(a.toString());
		// Try add's Exception
		try {
			a.add(a.size()+1, new Integer(999));
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Can't add item at "+(a.size()+1));
		}
		return a;
	}
	
	public static void removeSome(MyArrayList<Integer> a) { 
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
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.print("Can't remove item at "+a.size()+" from ");
			System.out.println(a.toString());
		}
	}
	
	public static void tryToArray(MyArrayList<Integer> a) {	
		Object[] o = a.toArray();
		System.out.print("Try toArray:");
		for (int i=0; i<o.length; i++)
			System.out.print(o[i].toString()+" ");
		System.out.print("\n");
	}
	
	public static void tryCopyConstructor(MyArrayList<Integer> a) {
		MyArrayList<Integer> b = new MyArrayList<Integer>(a);
		removeSome(b);
		System.out.println("Try copy constructor:"+b.toString());
	}
	
	public static void tryIndexOf(MyArrayList<Integer> a) {
		try {
			for (int i=0; i<=a.size(); i++) {
				Integer item= a.get(i);
				System.out.print("["+a.indexOf(item)+"]:"+item.toString()+" ");
			}
		}  catch (ArrayIndexOutOfBoundsException e) {
			System.out.print("["+a.indexOf(null)+"]:null ");
			System.out.println("["+a.indexOf(99)+"]:99");
		}
		
		for (int i=0; i<=a.size(); i++) {
			System.out.print("["+a.indexOf(i)+"]:"+i+" ");
		}
	}
	
	public static void main(String[] args) {
		if (args.length == 0)  {		
			System.out.println("Please provide an integer argument to the program.");
			System.exit(-1);
		}
		MyArrayList<Integer> a;
		a = addSome(Integer.parseInt(args[0]));
		removeSome(a);
		tryToArray(a);
		tryCopyConstructor(a);
		tryToArray(a);
		tryIndexOf(a);
	}
}
