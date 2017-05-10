/**
 * 11/14/13
 * @author ahinh
 *
 */
import myUtil.*;
import java.util.*;
public class Recursion {

	public static int fibonacciPower(int n) {
		if (n == 0) return 1;
		if (n == 1) return 2;
		
		return fibonacciPower(n-1) * fibonacciPower(n-2);
	}
	public static String reverse(String str) {
		
		if (str.equals("")) return "";
		return (str.charAt(str.length() -1) + reverse(str.substring(0,str.length()-1)));
	}
	
	public static int gcd(int a, int b) {
		int r = a%b;
		if (r == 0) return b;
		return gcd(b,r);
	}
	
	public static int[] pascalTriangle(int n) {
		int[] pt = new int[n+1];
		if (n==0) { pt[0] = 1; return pt;}
		int[] ppt = pascalTriangle(n-1);
		pt[0] = pt[n]= 1;
		for (int i = 1; i<ppt.length; i++) {
			pt[i]= ppt[i-1] + ppt[i];
		}
		return pt;
	}
	
	public static int bSearch(int[] a, int s, int e, int key) {
		//
		if (s > e) return -1;
		int m = ((s+e)/2);
		if (a[m] == key) return m;
		if (a[m] > key) return bSearch(a,s,m-1,key);
		return bSearch(a,m+1,e,key);
	}
	
	public static ArrayList<String> permute(String s) {
		ArrayList<String> perm = new ArrayList<String>();
		if (s.length() == 0) {
			perm.add(s); return perm;
		}
		ArrayList<String> temp = permute(s.substring(1));
		for (int i=0; i<temp.size(); i++) {
			String t = temp.get(i);
			char a = s.charAt(0);
			perm.add(a+t);
			for (int j = 0; j < t.length(); j++) {
				perm.add(t.substring(0, j+1) + a + t.substring(j+1));
			}
		}
		return perm;
	}
	public static ArrayList<String> powerSet(String s) {
		ArrayList<String> a = new ArrayList<String>();
		if (s.length() == 0) {
			a.add(s);
			return a;
		}
		a = powerSet(s.substring(1));
		String m = s.substring(0, 1);
		int n = a.size();
		for(int i=0; i < n; i++) {
			a.add(m+a.get(i));
		}
		return a;
		
	}
	public static ArrayList<String> choose(int n, String s) {
		ArrayList<String> a = new ArrayList<String>();
		if (n > s.length() || n < 0) return a;
		if (n == 0) {
			a.add("");
			return a;
		}
		a = choose(n,s.substring(1));
		ArrayList<String> sub = choose(n-1, s.substring(1));
		for(int i =0; i<sub.size(); i++) {
			a.add(s.charAt(0) + sub.get(i));
		}
		return a;
	}
}
