package grocerylistmaker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class XMLWriter 
{
	ArrayList<GroceryItem> list;
	
	public XMLWriter()
	{
		list = new ArrayList<GroceryItem>();
	}
	
	public String writeXML(ArrayList<GroceryItem> inputList)
	{
		//declare variables
		GroceryItem currentItem;
		String currentName, currentUnits;
		int currentQuantity;
		
		//try to write to file
		try
		{
			//create a new file called "test.xml" in the current working directory
			File file = new File(System.getProperty("user.dir")+"/test.xml");
			
			//if the file does not exist, create it
			if (!file.exists())
			{
				file.createNewFile();
			}
			
			//start a new BufferedWriter on the file
			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			//write <items> and a newLine since this will start every xml file
			bufferedWriter.write("<items>");
			bufferedWriter.newLine();
			
			//go through each item in the list and add it to the xml file
			for (int i = 0; i < inputList.size(); i++)
			{
				//get all the current variables
				currentItem = inputList.get(i);
				currentQuantity = currentItem.getQuantity();
				currentUnits = currentItem.getUnits();
				currentName = currentItem.getName();
				
				//write all of the current variables to the file with proper formatting
				bufferedWriter.write("\t<item quantity =\"" + currentQuantity + "\" units=\"" + currentUnits + "\">");
				bufferedWriter.newLine();
				bufferedWriter.write("\t\t<name>" + currentName + "</name>");
				bufferedWriter.newLine();
				bufferedWriter.write("\t</item>");
				bufferedWriter.newLine();
				
				//add an extra space between each item, unless it is the final item on the list
				if (i != inputList.size()-1)
				{
					bufferedWriter.newLine();
				}
			}
			//finally, write the </items> close tag and close the writer. The file is now written to the disk
			bufferedWriter.write("</items>");
			bufferedWriter.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return "Done";
	}
	
	public static void main (String[] args)
	{
		//create a test arraylist and add items to it
		ArrayList<GroceryItem> testList = new ArrayList<GroceryItem>();
		testList.add(new GroceryItem("Milk", "gallon", 2));
		testList.add(new GroceryItem("Eggs", "carton", 1));
		testList.add(new GroceryItem("Steak", "oz", 16));
		testList.add(new GroceryItem("Butter", "stick", 4));
		testList.add(new GroceryItem("Flour", "lb", 2));
		testList.add(new GroceryItem("Oranges", "lb", 2));
		
		//call the above method and print that it is done.
		XMLWriter xml = new XMLWriter();
		System.out.println(xml.writeXML(testList));
	}
}
