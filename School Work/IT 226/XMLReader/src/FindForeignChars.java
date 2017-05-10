import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FindForeignChars {

	public static void main(String[] args)
	{
		try 
		{
			FileWriter fw = new FileWriter("Converted.txt");
			Scanner in = new Scanner(new File("grocery_browse_tree_guide.txt"));
			
			
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
