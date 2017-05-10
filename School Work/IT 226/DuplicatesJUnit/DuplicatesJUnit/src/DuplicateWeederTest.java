import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.TreeSet;

import org.junit.Test;


public class DuplicateWeederTest {
	
	TreeSet<Integer> answer;

	private boolean verify(ArrayList<Integer> arr)
	{
		TreeSet<Integer> tset = new TreeSet<Integer>();
		
		for (int i=0;i<arr.size();i++)
		{
			if (tset.contains(arr.get(i)))
				return false;
			tset.add(arr.get(i));
		}
		if (arr.size()!=answer.size())
			return false;
		return true;
	}
	@Test
	public void testWithDuplicates() 
	{
		int []arr = {1,2,4,3,5,5,6};
		
		ArrayList<Integer> alist = new ArrayList<Integer>();
		
		for (int i=0;i<arr.length;i++)
			alist.add(arr[i]);
		
		answer = new TreeSet<Integer>(alist);
		
		
		DuplicateWeeder.removeDuplicates(alist);
		
		boolean result = verify(alist);
		
		
		assertTrue(result);
		
	}
	
	@Test
	public void testWithoutDuplicates() 
	{
		int []arr = {1,2,4,3,5,6};
		
		ArrayList<Integer> alist = new ArrayList<Integer>();
		
		for (int i=0;i<arr.length;i++)
			alist.add(arr[i]);
		
		answer = new TreeSet<Integer>(alist);
		
		
		DuplicateWeeder.removeDuplicates(alist);
		
		boolean result = verify(alist);
		
		
		assertTrue(result);
		
	}
	
	@Test
	public void testEmptyArrayList() 
	{
		ArrayList<Integer> alist = new ArrayList<Integer>();
		
		
		answer = new TreeSet<Integer>(alist);
		
		
		DuplicateWeeder.removeDuplicates(alist);
		
		boolean result = verify(alist);
		
		
		assertTrue(result);
		
	}
	
	@Test
	public void testWithAllSame() 
	{
		int j;
		
		for (j=0;j<20;j++)
		{
			ArrayList<Integer> alist = new ArrayList<Integer>();
			
			for (int i=0;i<j;i++)
				alist.add(1);
			
			answer = new TreeSet<Integer>(alist);
			
			
			DuplicateWeeder.removeDuplicates(alist);
			
			boolean result = verify(alist);
			
			
			assertTrue(result);
		}
		
	}
	
	@Test
	public void testWithLargeArray() 
	{
		
		ArrayList<Integer> alist = new ArrayList<Integer>();
		
		for (int i=0;i<100000;i++)
			alist.add((int)(Integer.MAX_VALUE*Math.random()));
		
		answer = new TreeSet<Integer>(alist);
		
		
		DuplicateWeeder.removeDuplicates(alist);
		
		boolean result = verify(alist);
		
		
		assertTrue(result);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testWithNull() 
	{
		
		ArrayList<Integer> alist = new ArrayList<Integer>();
		answer = new TreeSet<Integer>();
		
		for (int i=0;i<1000000;i++)
		{
			alist.add((int)(Integer.MAX_VALUE*Math.random()));
			answer.add(alist.get(i));
		}
		
		
		
		
		DuplicateWeeder.removeDuplicates(null);
		
		boolean result = verify(alist);
		
		
		assertTrue(result);
		
	}


}
