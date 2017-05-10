import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;


public class DuplicateWeeder 
{
	public static void removeDuplicates2(ArrayList<Integer> arr)
	{
		TreeSet<Integer> tset = new TreeSet<Integer>();
		int i;
		
		for (i=0;i<arr.size();i++)
		{
			tset.add(arr.get(i));
		}
		
		Iterator<Integer> iter = tset.iterator();
		
		arr.clear();
		
		i = 0;
		while (iter.hasNext())
		{
			arr.add(iter.next());
		}
	}
	
	public static void removeDuplicates(ArrayList<Integer> arr)
	{
		ArrayList<Integer> newArr = new ArrayList<Integer>();
		
		boolean dup = false;
	
		for (int i=0;i<arr.size();i++)
		{
			if (newArr.size()>0)
			{
				for (int j=0;j<newArr.size();j++)
				{
					if (arr.get(i) == newArr.get(j))
					{
						dup = true;
					}
				}
				if (!dup)
				{
					newArr.add(arr.get(i));
				}
			}
			else
			{
				newArr.add(arr.get(i));
			}
			dup = false;
		}
		
		arr.clear();
		arr.addAll(newArr);
	}
}
