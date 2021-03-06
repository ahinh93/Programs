import java.util.StringTokenizer;


public class Main2 {
	static String currentToken  = null;
	static StringTokenizer st;
	static int parenthesisCount = 0;
	public static void main(String[] args) {

		String myString = "32+10";
		st = new StringTokenizer(myString+"$","+*)($",true);

		if(myString != null && !myString.equals(""))
		{
			currentToken = st.nextToken();
			System.out.println(E());
		}
		System.out.println("remaining parenthesis: " + parenthesisCount);
	}

	public static int E()
	{
		System.out.println("E: " + currentToken);
		if(currentToken.equals("(") || isNumeric(currentToken))
		{
			return T() * Eprime();
		}
		else
			throw new IllegalArgumentException();
	}

	private static int Eprime() 
	{
		System.out.println("Eprime: " + currentToken);
		if(currentToken.equals("+"))
		{
			currentToken = st.nextToken();
			return T() + Eprime();
		}
		else if(currentToken.equals(")") || currentToken.equals("$"))
			return 0;
		else
			throw new IllegalArgumentException();
	}

	private static int T() {
		System.out.println("T: " + currentToken);
		if(currentToken.equals("(") || isNumeric(currentToken))
			return F()*Tprime();
		else
			throw new IllegalArgumentException();
	}

	private static int Tprime() {
		System.out.println("Tprime: " + currentToken);
		if(currentToken.equals("*"))
		{
			currentToken = st.nextToken();
			return F() * Tprime();
		}
		else if(currentToken.equals("+") || currentToken.equals(")") || currentToken.equals("$"))
		{
//			if(currentToken.equals(")"))
//				parenthesisCount--;
			return 0;
		}
		else
			throw new IllegalArgumentException();
	}

	private static int F() {
		System.out.println("F: " + currentToken);
		if(currentToken.equals("("))
		{
//			parenthesisCount++;
			return 0;
		}
		else if(isNumeric(currentToken))
		{
			String temp = currentToken;
			//System.out.println(temp + " ");
			currentToken = st.nextToken();
			//next token
			return Integer.parseInt(temp);
		}
		else
			throw new IllegalArgumentException();
	}

	private static boolean isNumeric(String token) {
		try {
			Integer.parseInt(token);
			return true;
		}
		catch (NumberFormatException e) {
			// s is not numeric
			return false;
		}
	}

}
