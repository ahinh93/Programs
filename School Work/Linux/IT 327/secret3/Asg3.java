import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by Andrew Hinh for IT 327 
 * @author ahinh
 */
public class Asg3 {

	static String previous = "";

	public static void main(String[] args) {

		Object temp;
		String terminals[] = {"START","PROG","BLOCK","BODY","S","E","T","F"};

		HashSet<String> grammarOneFirst = new HashSet<String>();
		HashSet<String> grammarOneFollow = new HashSet<String>();

		Stack<Object> theStack = new Stack<Object>();


		//Grammar 1

		//if input is empty string, return empty the string
		theStack.push("START");

		while(!theStack.empty())
		{
			temp = theStack.pop();
			if(temp.toString().equals("$"))
				continue;

			boolean term = false;
			for(int i = 0; i < terminals.length && !term; i++)
			{
				if(temp.equals(terminals[i]))
					term = true;
			}

			if(term)
			{
				switch(previous)
				{
				case "PROG":
				{
					grammarOneFollow.add("#");
					break;
				}
				case "BLOCK":
				{
					grammarOneFollow.add("end");
					break;
				}
				case "BODY":
				{
					grammarOneFollow.add(";");
					break;
				}
				case "S":
				{
					grammarOneFollow.add("fi");
					grammarOneFollow.add("else");
					grammarOneFollow.add("then");
					grammarOneFollow.add("-");
					break;
				}
				case "E":
				{
					grammarOneFollow.add("+");
					grammarOneFollow.add("-");
					break;
				}
				case "T":
				{
					grammarOneFollow.add("*");
					grammarOneFollow.add("/");
					break;
				}
				case "F":
				{
					grammarOneFollow.add(")");
					grammarOneFollow.add("const");
					break;
				}
				default:
				{
					grammarOneFollow.add("$");
					break;
				}
				}

				switch(temp.toString())
				{
				case "START":
				{
					previous = "START";
					theStack.push("PROG");
					break;
				}
				case "PROG":
				{
					previous = "PROG";
					theStack.push("#");
					theStack.push("BLOCK");
					break;
				}
				case "BLOCK":
				{
					previous = "BLOCK";
					theStack.push("end");
					theStack.push("BODY");
					theStack.push("begin");
					break;
				}
				case "BODY":
				{
					previous = "BODY";
					theStack.push("S");
					theStack.push(";");
					theStack.push("BODY");
					break;
				}
				case "S":
				{
					previous = "S";
					theStack.push("i");
					theStack.push("fi");
					theStack.push("S");
					theStack.push("else");
					theStack.push("S");
					theStack.push("then");
					theStack.push("E");
					theStack.push("if");					
					break;
				}
				case "E":
				{
					previous = "E";
					theStack.push("T");
					theStack.push("-");
					theStack.push("+");
					theStack.push("E");
					break;
				}
				case "T":
				{
					previous = "T";
					theStack.push("F");
					theStack.push("*");
					theStack.push("/");
					theStack.push("T");
					break;
				}
				case "F":
				{
					previous = "F";
					theStack.push(")");
					theStack.push("const");
					theStack.push("E");
					theStack.push("(");
					break;
				}
				default :
				{
					grammarOneFirst.add(temp.toString());
					break;
				}
				}
			}

			//			Iterator<String> iter = grammarOneFirst.iterator();
			//			while(iter.hasNext())
			//			{
			//				System.out.println(iter.next());
			//			}

		}

		String terminals2[] = {"START","PROG","BLOCK","BODY","S","E","T","F"
				,"IFST","IFP","ASSI","V","EP","TP"};

		HashSet<String> grammarTwoFirst = new HashSet<String>();
		HashSet<String> grammarTwoFollow = new HashSet<String>();

		theStack = new Stack<Object>();
		//Grammar 2
		while(!theStack.empty())
		{
			temp = theStack.pop();
			if(temp.toString().equals("$"))
				continue;

			boolean term = false;
			for(int i = 0; i < terminals.length && !term; i++)
			{
				if(temp.equals(terminals[i]))
					term = true;
			}

			if(term)
			{
				switch(previous)
				{
				case "PROG":
				{
					grammarTwoFollow.add("#");
					break;
				}
				case "BLOCK":
				{
					grammarTwoFollow.add("end");
					break;
				}
				case "BODY":
				{
					break;
				}
				
				case "S":
				{
					break;
				}
				case "IFST":
				{
					grammarTwoFollow.add("then");
					break;
				}
				case "IFP":
				{
					grammarTwoFollow.add("fi");
					break;
				}				
				case "ASSI":
				{
					grammarTwoFollow.add("=");
					break;
				}
				case "E":
				{
					break;
				}
				
				case "EP":
				{
					grammarTwoFollow.add("lambda");
					break;
				}
				
				case "T":
				{
					break;
				}
				case "TP":
				{
					grammarTwoFollow.add("lambda");
					break;
				}
				case "F":
				{
					grammarTwoFollow.add(")");
					grammarTwoFollow.add("const");
					break;
				}
				default:
				{
					grammarTwoFollow.add("$");
					break;
				}
				}

				switch(temp.toString())
				{
				case "START":
				{
					previous = "START";
					theStack.push("PROG");
					break;
				}
				case "PROG":
				{
					previous = "PROG";
					theStack.push("#");
					theStack.push("BLOCK");
					break;
				}
				case "BLOCK":
				{
					previous = "BLOCK";
					theStack.push("end");
					theStack.push("BODY");
					theStack.push("begin");
					break;
				}
				case "BODY":
				{
					previous = "BODY";
					theStack.push("BODYP");
					theStack.push("S");
					break;
				}
				case "BODYP":
				{
					previous = "BODYP";
					theStack.push("BODYP");
					theStack.push("S");
					theStack.push("lambda");
					theStack.push(";");
					break;
				}
				
				case "S":
				{
					previous = "S";
					theStack.push("ASSI");
					theStack.push("IFST");
					theStack.push("BLOCK");
					break;
				}
				case "IFST":
				{
					previous = "IFST";
					theStack.push("IFP");
					theStack.push("S");
					theStack.push("then");
					theStack.push("E");
					theStack.push("if");
					break;
				}
				case "IFP":
				{
					previous = "IFP";
					theStack.push("fi");
					theStack.push("S");
					theStack.push("else");
					break;
				}
				case "ASSI":
				{
					previous = "ASSI";
					theStack.push("E");
					theStack.push("=");
					theStack.push("V");
					break;
				}
				case "V":
				{
					previous = "V";
					theStack.push("id");
					break;
				}
				case "E":
				{
					previous = "E";
					theStack.push("T");
					theStack.push("EP");
					break;
				}
				case "EP":
				{
					previous = "EP";
					theStack.push("EP");
					theStack.push("T");
					theStack.push("+");
					theStack.push("-");
					theStack.push("lambda");
					break;
				}
				case "T":
				{
					previous = "T";
					theStack.push("F");
					theStack.push("TP");
					break;
				}
				case "TP":
				{
					previous = "TP";
					theStack.push("TP");
					theStack.push("F");
					theStack.push("*");
					theStack.push("/");
					theStack.push("lambda");
					break;
				}
				case "F":
				{
					previous = "F";
					theStack.push("V");
					theStack.push(")");
					theStack.push("E");
					theStack.push("(");
					theStack.push("const");
					break;
				}
				default :
				{
					grammarTwoFirst.add(temp.toString());
					break;
				}
				}
			}

		}
	}


}
