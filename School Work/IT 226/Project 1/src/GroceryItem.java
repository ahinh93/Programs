package grocerylistmaker;

public class GroceryItem {
	private String name, units;
	private boolean purchased;
	private int quantity;
	
	public GroceryItem()
	{
		name="";
		units="";
		purchased = false;
		quantity = 0;
	}
	
	public GroceryItem(String name)
	{
		this.name=name;
		units="";
		purchased = false;
		quantity = 0;
	}
	
	public GroceryItem(String name, String units, int quantity)
	{
		this.name=name;
		this.units=units;
		purchased = false;
		this.quantity = quantity;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getUnits()
	{
		return units;
	}
	
	public void setUnits(String units)
	{
		this.units = units;
	}

	public boolean isPurchased()
	{
		return purchased;
	}

	public void setPurchased(boolean purchased)
	{
		this.purchased = purchased;
	}

	public int getQuantity() 
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
}
