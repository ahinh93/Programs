/**
 * 10/22/13
 * @author Andrew Hinh copyright
 */
import myUtil.*;
import java.util.*;

/**
 * Program will determine if the line consisting of  brackets and parenthesis' are 'balanced'.
 */
public class Asg5 {
	
	public static void main(String[] args) {
		String[] theLine = args;
		//test that the line has at least one valid input
		testValidLine(theLine);
		
		AStack<String> myArray;
		myArray = new AStack<String>(theLine.length);
		
		testLine(myArray, theLine);
		
	}
	
	private static void testValidLine(String[] args) {
		if (args.length == 0)  {		
			System.out.println("Please provide a line containing { }, ( ), [ ], and < >.");
			System.exit(-1);
		}
		boolean validLine = false;
		String[] theLine = args;
		if (theLine[0].contains("{") || theLine[0].contains("}") || theLine[0].contains("[") || theLine[0].contains("]") || theLine[0].contains("(") || theLine[0].contains(")") || theLine[0].contains("<") || theLine[0].contains(">")) {
			validLine = true;
		}
		
		if (!validLine) {
			System.out.println("Line does not contain one of the following: { }, ( ), [ ], or < >.");
			System.exit(-1);
		}
	}
	
	private static void testLine(AStack theStack, String[] theLine) {
		//create array that will be used for stack
		
		int counter = 0;
		boolean balanced = true;
		//if entry is open bracket, push into stack
		while (balanced = true && counter < theLine[0].length()) {
			if (theLine[0].charAt(counter) == '(') {
				theStack.push(theLine[0].charAt(counter));
			}
			else if (theLine[0].charAt(counter) == '{') {
				theStack.push(theLine[0].charAt(counter));
			}
			else if (theLine[0].charAt(counter) == '[') {
				theStack.push(theLine[0].charAt(counter));
			}
			else if (theLine[0].charAt(counter) == '<') {
				theStack.push(theLine[0].charAt(counter));
			}
			
			//if entry is closing bracket, pop corresponding, otherwise error out
			
			if (theLine[0].charAt(counter) == '}') {
				if (!theStack.empty()) {
					if(theStack.pop() != '{') {
						balanced = false;
					}
				}
				else {
					balanced = false;
				}
			}
			else if (theLine[0].charAt(counter) == ')') {
				if (!theStack.empty()) {
					if(theStack.pop() != '(') {
						balanced = false;
					}
				}
				else {
					balanced = false;
				}
			}
			else if (theLine[0].charAt(counter) == ']') {
				if (!theStack.empty()) {
					if(theStack.pop() != '[') {
						balanced = false;
					}
				}
				else {
					balanced = false;
				}
			}
			else if (theLine[0].charAt(counter) == '>') {
				if (!theStack.empty()) {
					if(theStack.pop() != '<') {
						balanced = false;
					}
				}
				else { 
					balanced = false;
				}
			}
			counter++;
			
			if (counter == theLine[0].length()) {
				if (balanced) {
					System.out.println(theLine[0] + " is balanced.");
				} else
					System.out.println(theLine[0] + " is not balanced.");
			}
		}
		

	}
	
}
