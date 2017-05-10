package grocerylistmaker;

import java.util.ArrayList;

public class GroceryItemList 
{
	private ArrayList<GroceryItem> list;
	
	public GroceryItemList()
	{
		list = new ArrayList<GroceryItem>();
	}
	
	public GroceryItemList(ArrayList<GroceryItem> list)
	{
		this.list = new ArrayList<GroceryItem>();
		for (int i = 0; i < list.size(); i++)
		{
			this.list.add(list.get(i));
		}
	}
	
	public void addGroceryItem(GroceryItem item)
	{
		list.add(item);
	}
	
	public void removeGroceryItem(GroceryItem item)
	{
		list.remove(item);
	}

	public ArrayList<GroceryItem> getList() 
	{
		return list;
	}

	public void setList(ArrayList<GroceryItem> list)
	{
		this.list = list;
	}
}
