import java.util.*;

public class PrefixNode 
{
	private TreeSet<String> words;
	private TreeMap<Character,PrefixNode> children;
	
	public PrefixNode()
	{
		words = new TreeSet<String>();
		children = new TreeMap<Character,PrefixNode>(); 
	}
	
	public PrefixNode getNode(Character c)
	{
		if (children.containsKey(c))
		{
			return children.get(c);
		}
		else
		{
			PrefixNode newnode = new PrefixNode();
			children.put(c,newnode);
			return newnode;
		}
	}
	
	public void addWord(String word)
	{
		words.add(word);
	}
	
	public ArrayList<String> getWords()
	{
		ArrayList<String> alist = new ArrayList<String>();
		alist.addAll(words);
		return alist;
	}	
}


