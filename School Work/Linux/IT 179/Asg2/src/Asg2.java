/**
 * Written by Andrew Hinh
 * @author ahinh
 * Copyright
 * 9/5/13
 */
import java.util.*;
import java.io.*;

public class Asg2 
{
	static Scanner in = null;

	
	public static void main(String[] args)
	{
		String fileName = "/home/ahinh/IT179/Asg2/jj.txt";
		int counter = 1;
		
		try
		{
			File file = new File(fileName);
			in = new Scanner (file);		
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error in trying to find jj.txt");
		}


		//check to make sure scanner has another line to read, if not, then end
		while(in.hasNextLine())
		{
			//create variable to store line
			String s = in.nextLine();
			StringTokenizer tokens = new StringTokenizer(s);
			ArrayList<String> pos = new ArrayList<String>();
			int[] numArray;

			while (tokens.hasMoreTokens())
			{
				String sj = tokens.nextToken();
				pos.add(sj);
			}
			numArray = new int[pos.size()];

			for(int i = 0; i < numArray.length; i++)
			{
				numArray[i] = Integer.parseInt(pos.get(i));
			}
			
			if (isJollyJump(numArray) == -1)
			{
				System.out.println("Line " + counter + " is not a jolly jump.");
			}
			else
			{
				System.out.println("Line "  + counter + " is a jolly jump of size " + isJollyJump(numArray)+".");
			}
			counter++;

		}


	}

	/**
	 * Will check if line is a jolly jump returning the size if a jump exists,
	 * -1  if a jump doesn't exist.
	 * Will also return -1 if the difference between each jump in a line is repeated.
	 * @param myArray - the integer array that contains the numbers specifically from each line
	 * @return The size of the jolly jump, -1 if no jolly jump exists in that line
	 */
	private static int isJollyJump(int[] myArray)
	{
		int difference;
		boolean[] checkBoxArray = new boolean[myArray.length];

		for (int i = 0; i < myArray.length-1; i++)
		{
			difference = Math.abs(myArray[i]-myArray[i+1]);
			if (difference > myArray.length -1)
			{
				return -1;
			}
			if (checkBoxArray[difference])
			{
				return -1;
			}
			else
				checkBoxArray[difference] = true;	
		}
		return myArray.length;

	}
}


