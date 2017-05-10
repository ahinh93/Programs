import java.util.StringTokenizer;

import 
public class newAsg6 
{
	
	
	public double evaluate(String infix) 
	{
		StringTokenizer st = new StringTokenizer(infix);
		String spacedLine = "";
		
		if(st.hasMoreTokens())
		{
			for (int i = 0; i < a.length(); i++) {

				if (a.charAt(i) == '(' || a.charAt(i) == ')' || 
						a.charAt(i) == '*' || a.charAt(i) == '/' || 
						a.charAt(i) == '+' || a.charAt(i) == '-' || 
						a.charAt(i) == '^') {
					spacedLine += (" " +a.charAt(i) + " ");			
				}
				else {
					spacedLine+= a.charAt(i);
				}
			}
		}
		
	}

}
