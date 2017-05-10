import java.util.*;


public class WordManager 
{
	private TreeMap<Character,PrefixNode> manager;
	
	public WordManager()
	{
		manager = new TreeMap<Character,PrefixNode>();
	}
	
	public void add(Scanner scanner)
	{
		while (scanner.hasNext())
		{
			String str = scanner.next();
			String str2 = "";
			for (int i=0;i<str.length();i++)
			{
				if (Character.isLetter(str.charAt(i)))
					str2 = str2 + str.charAt(i);
			}
			if (str2.length()>0)
				addWord(str2);
		}
	}
	
	public void addWord(String ch)
	{
		int i;
		PrefixNode curr;
		
		String c = ch.toLowerCase();
		
		if (manager.containsKey(c.charAt(0)))
			curr = manager.get(c.charAt(0));
		else
		{
			curr = new PrefixNode();
			manager.put(c.charAt(0),curr);
		}
		
		
		for (i=1;i<c.length();i++)
		{
			curr.addWord(ch);
			curr = curr.getNode(c.charAt(i));
		}
		curr.addWord(ch);
	}
	
	public ArrayList<String> getWords(String prefix)
	{
		ArrayList<String> list;
		PrefixNode curr;
		int i;
		
		prefix = prefix.toLowerCase();
		
		if (prefix.length()<1)
		{
			return new ArrayList<String>();
		}
		curr = manager.get(prefix.charAt(0));
		
		
		for (i=1;i<prefix.length();i++)
		{
			curr = curr.getNode(prefix.charAt(i));
		}
		list = curr.getWords();
		return list;
	}
}
