/**
 * Written by Andrew Hinh, Dale Dominick, Yu Xi
 */
package grocerylistmaker;

import java.util.*;
import java.io.*;

public class textToXML 
{

	public textToXML()
	{
		
	}
	public static void main(String args[]) throws FileNotFoundException 
	{

		openAndReadFile();
	}
	
	public static String[] openAndReadFile()
	{
		FileWriter fw;
		ArrayList<String> itemArrayList = new ArrayList<String>();
		String[] returnArray;
		try 
		{
			//create scanner
			Scanner in = new Scanner(new File("grocery_browse_tree_guide.txt"));
			fw = new FileWriter("converted.xml");

			//Create buffered writer to write to xml file
			BufferedWriter bw = new BufferedWriter(fw);

			//convert each line as array then pass to xml converter
			//int counter = 0;
			while(in.hasNext())
			{
				String[] currentLine = readLine(in.nextLine());
				writeToXML(currentLine,bw);
				itemArrayList.add(currentLine[currentLine.length - 1]);
				
			}

			bw.close();
			in.close();
			returnArray = new String[itemArrayList.size()];
			return itemArrayList.toArray(returnArray);
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			String[] s = new String[1];
			s[0] = "No items!";
			return s;
		}

		//			catch (FileNotFoundException e)
		//			{
		//				System.out.print(e);
		//			}
		//			

	}

	/**
	 * This method will write to the XML being passed one line at a time in form of string array
	 * @param arr - string array that contains separated contents in each array
	 * @param bw - buffered writer that writes to xml file
	 */

	public static void writeToXML(String[] arr, BufferedWriter bw)
	{

		//if array only contains one item, print only itemName
		if (arr.length == 1)
		{
			try 
			{
				bw.write("<item name=\"" + arr[0] + "\">");
				bw.newLine();
				bw.write("</item>");
				bw.newLine();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//if array has more than one item, print itemName + labels
		else
		{
			//count how many labels this item will have
			int counter = arr.length - 1;
			try 
			{
				// item name will be the last item in line
				bw.write("<item name=\"" + arr[arr.length-1] + "\">");
				bw.newLine();
				//start from first element of array
				for(int k = 0; k<counter; k++)
				{
					bw.write("\t<label>" + arr[k] +"</label>");
					bw.newLine();
				}
				bw.write("</item>");
				bw.newLine();
				bw.newLine();

			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	/**
	 * This method takes in a line and returns an array with separated labels in each array
	 * @param s - a line from the .txt file
	 * @return String array where each string is a label
	 */
	public static String[] readLine(String s)
	{
		if (s.charAt(0)=='"')
		{
			s = s.substring(1,s.length()-1);
		}
		StringTokenizer st = new StringTokenizer(s,"/");
		String myArray[] = new String[st.countTokens()];
		//String nextToken;
		//boolean firstInLine = true;
		int counter = 0;

		
		while(st.hasMoreTokens())
		{
			myArray[counter] = st.nextToken();
			counter++;
		}
		
		return myArray;
		/*
		 * "Grocery & Gourmet Food/Baby Foods/Baby Formula"
		 * will become:
		 * [0] Grocery & Gourmet Food
		 * [1] Baby Foods
		 * [2] Baby Formula
		 */

	}
}
