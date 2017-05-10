import java.util.StringTokenizer;


public class test {
	public static void main(String[] args)
	{
		String a = "01/12/14";
		
		StringTokenizer st = new StringTokenizer(a,"/",false);
		while (st.hasMoreTokens())
		{
		System.out.println(st.nextToken());
		}
	}
}
