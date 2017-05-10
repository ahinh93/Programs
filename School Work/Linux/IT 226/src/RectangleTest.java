/**
 * Andrew Hinh
 * @author ahinh
 *
 */
import java.util.*;

public class RectangleTest {

	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int numOfRectangles = 0;
		System.out.println("How many rectangles do you wish to input?");
		numOfRectangles = in.nextInt();
		Rectangle[] myArray = new Rectangle[numOfRectangles];
		
		int tempX, tempY, tempWidth, tempHeight = 0;
		for (int i = 0; i < numOfRectangles; i++) {
		System.out.println("Rectangle " + (i+1) + ":");
		System.out.println("X:? ");
		tempX = in.nextInt();
		System.out.println("Y:? ");
		tempY = in.nextInt();
		System.out.println("Width:? ");
		tempWidth = in.nextInt();
		System.out.println("Height:? ");
		tempHeight = in.nextInt();
		
		myArray[i] = new Rectangle(tempX, tempY, tempWidth, tempHeight);
		}
		
		System.out.println("\n\n");
		for(int i = 0; i < numOfRectangles; i++) {
			System.out.println("Rectangle " + (i + 1) + myArray[i].toString());
		}
		
		int counter = 0;
		for(int i = 0; i < numOfRectangles -1; i++) {
			for (int k = 1; k < numOfRectangles -1; k++) {
				if (myArray[i].overlaps(myArray[k]))
				{
					counter++;
				}
			}
		}
		System.out.println("No of overlaps:" + counter);
		
		boolean checkWindow = false;
		Rectangle temp2 = new Rectangle(0,0,0,0);
		for(int i = 0; i < numOfRectangles -1 && !checkWindow; i++) {
			for (int k = 1; k < numOfRectangles -1 && !checkWindow; k++) {
				while(myArray[i].intersect(myArray[k]).overlaps(myArray[k+1]))
						{
							temp2 = myArray[i].intersect(myArray[k]);
							checkWindow=true;
						}
			}
		}
		if (!checkWindow)
			System.out.println("Rectangles have no common overlap");
		else
			System.out.println("Shared rectangle: " + temp2.toString());
		
	}

}
