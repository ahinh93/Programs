import java.util.ArrayList;

public class Asg7 {

	static void testFabonacciPower() {
		System.out.printf("FabonacciPower : ");	
		for (int i=6;i<9;i++)  System.out.print(Recursion.fibonacciPower(i)+" ");
	}

	static void testReverse() {
		String  s="String reversal test.";
		System.out.printf("%n%n%s --> %s%n%n", s, Recursion.reverse(s));
	}
	
	public static void testPascalTriangle() {
		for (int n=0; n<=30; n += 5) {
			String str="(1+x)^"+n+":";
			int[] c = Recursion.pascalTriangle(n);
			for (int i =0;i<c.length;i++) str += " "+c[i]; 
			System.out.println(str);
		}
	}
	
	static void testGCD() {
		int a=1403734,b=346104;
		System.out.printf("%ngcd(%d,%d)=%d",a,b,Recursion.gcd(a,b));
		a=1373515; b=63393;
		System.out.printf("%ngcd(%d,%d)=%d",a,b,Recursion.gcd(a,b));
		System.out.printf("%ngcd(%d,%d)=%d\n",a,b,Recursion.gcd(b,a));
	}

	static int xn=0, a=1127, b=2743, m=9257; 
	static int flipCoin() {
		xn = ((a*xn)+b)%m;
		return xn;
	}
	
	static void testBSearcg() {
		int[] a = new int[3000];
		a[0] = flipCoin();
		for (int i=1;i<a.length;i++) a[i] = a[i-1]+flipCoin()%5+1; 
		for (int i=1;i<10;i++) {
			int key = a[0] + flipCoin();
			int at = Recursion.bSearch(a, 0, a.length-1, key);
			System.out.printf("%n%d = a[%d]",key, at);			
		}
		System.out.println();
	}
	
	static void testPowerSet() {	
		System.out.println("\nPowersets");
		String s ="";
		ArrayList<String> ps;
		ps = Recursion.powerSet(s);
		System.out.println(s+" "+ps.size()+":"+ps.toString());
		s ="abc";
		ps = Recursion.powerSet(s);
		System.out.println(s+" "+ps.size()+":"+ps.toString());
		s ="abcd";
		ps = Recursion.powerSet(s);
		System.out.println(s+" "+ps.size()+":"+ps.toString());
		s ="abcdef";
		ps = Recursion.powerSet(s);
		System.out.println(s+" "+ps.size()+":"+ps.toString());
	}
	
	static void testChoose() {
		String s = "abcdef";
		ArrayList<String> C;
		System.out.println("\nChoose from "+s); 
		for (int i=0;i<10;i++){
			C = Recursion.choose(i, s);
			System.out.print("("+s.length()+","+i+")"+C.size());
			if (C.size() == 0) 
				System.out.println();
			else
				System.out.println(":"+C.toString());
		}
	}
	
	static void testPermute() {	
		String s = "abc";
		System.out.print("\nPermutation\n"); 
		ArrayList<String> permS = Recursion.permute(s);
		System.out.println(s.toString()+" "+permS.size()+":"+permS.toString());
		s = "abcd";
		permS = Recursion.permute(s);
		System.out.println(s.toString()+" "+permS.size()+":"+permS.toString());
		s = "12345";
		permS = Recursion.permute(s);
		System.out.println(s.toString()+" "+permS.size()+":"+permS.toString());
	}
	
	public static void main(String[] args) {		
		testFabonacciPower(); 
		testReverse();
		testPascalTriangle();
		testGCD();
		testBSearcg();
		testPowerSet();
		testChoose();
		testPermute();
	}
	
}
