import myUtil.*;
import java.util.*;
public class test {
	
	public static void main(String args[]) {
		double flow = 3;
		ExpDistribution nextCarArrive = new ExpDistribution(1/flow);
	for(int i = 0; i < 6; i++) {
		double waitNextArrive = nextCarArrive.next();
		System.out.println(nextCarArrive.toString());
	}
	}
}
