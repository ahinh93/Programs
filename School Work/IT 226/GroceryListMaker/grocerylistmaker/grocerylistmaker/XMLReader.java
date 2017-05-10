package grocerylistmaker;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
 * Filename:   GroceryListXMLSAXParser.java
 * Create on Mar 15, 2014
 *
 * ULID:yxi
 * 
 */

/**
 * This is a class to read grocery data from an XML file.
 * 
 *
 * @author Yu Xi
 *
 */
public class XMLReader
{

	static boolean readGroceryFile(String filename,
			ArrayList<GroceryItem> grocery)

	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = null;
		try
		{
			parser = factory.newSAXParser();
		}
		catch (ParserConfigurationException e)
		{ // TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		MyHandler handler = new MyHandler();
		try
		{
			parser.parse(new File(filename), handler);
		}
		catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		grocery.addAll(handler.getGroceryDetails());

		return true;

	}
}

class MyHandler extends DefaultHandler
{

	private ArrayList<GroceryItem> groceryList;
	private GroceryItem groceryItem;

	private String data;

	public void startDocument()
	{
		System.out.println("Parsing started");
		groceryItem = null;
		data = "";
	}

	public void endDocument()
	{
		System.out.println("Parsing ended");
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes)
	{

		if (qName.equals("items"))
		{

			groceryList = new ArrayList<GroceryItem>();
			data = "";
		}

		if (qName.equals("item"))
		{

			groceryItem = new GroceryItem();

			groceryItem.setUnits(attributes.getValue("units"));
			groceryItem.setQuantity(Integer.parseInt(attributes
					.getValue("quantity")));

			data = "";
		}
	}

	public void endElement(String uri, String localName, String qName)
	{

		if (qName.equals("items"))
		{

			data = "";

		}
		if (qName.equals("item"))
		{

			groceryList.add(groceryItem);
			groceryItem = null;
			data = "";
		}

		if (qName.equals("name"))
		{
			String name = null;
			for (int i = 0; i < data.length(); i++)
			{
				if (Character.toString(data.charAt(i)).equals(" "))
				{
					name = data.substring(i + 1);
				}
			}
			System.out.println(name);
			groceryItem.setName(name);
			data = "";
		}

	}

	public void characters(char ch[], int start, int length)
			throws SAXException
	{
		if (data.length() > 0)
			data = data + " " + new String(ch, start, length);
		else
			data = new String(ch, start, length);
	}

	public ArrayList<GroceryItem> getGroceryDetails()
	{
		return groceryList;
	}
}
