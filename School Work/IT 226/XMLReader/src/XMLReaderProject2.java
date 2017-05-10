import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class XMLReaderProject2 
{
	public static void main(String args[])
	{
		createStringArray();
	}

	public static String[] createStringArray()
	{
		ArrayList<String> myArray = new ArrayList<String>();
		try 
		{
			Scanner in = new Scanner( new File("classification_guide.xml"));
			while(in.hasNextLine())
			{
				String currentLine = in.nextLine();
				if (currentLine.contains("item name"))
				{
					//format "&amp;" into " & "
					String temp = currentLine.substring(12,currentLine.length()-2);
					if (temp.contains("&amp;"))
					{
						temp = temp.replace("&amp;", " & ");
					}
					if (temp.contains("("))
					{
						int convert = Integer.parseInt(temp.substring(temp.indexOf('(',temp.indexOf(')'))));
						temp = temp.replace("("+convert+")", "("+Character.toString((char)convert) + ")");
					}

					myArray.add(temp);
					
					}
			}
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("Classification_guide.xml not found!");
		}
		//create String[] to return
		String[] completedArray = new String[myArray.size()];
		
		//System.out.println((Character.toString((char)233)));
		return completedArray;
	}
}
