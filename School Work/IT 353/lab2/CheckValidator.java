package test;

public class CheckValidator {
	public static boolean isCounterfeit(String serial)
	{
		int zeroCount = 0;
		int nonZeroCount = 0;
		boolean isFake = false;
		if(serial.length() != 10)
			return true;		
		
		for(int i = 0; ((i < serial.length()) && !isFake); i++)
		{
			if(serial.charAt(i) == '0')
			{
				zeroCount++;
				nonZeroCount = 0;
			}
			else
			{
				nonZeroCount++;
				zeroCount=0;
			}
			if(!Character.isDigit(serial.charAt(i)))
			{
				isFake=true;
				break;
			}
			if((zeroCount == 4) || (nonZeroCount == 4))
			{
				isFake = true;
				break;
			}
		}
		if(isFake)
			return true;
			
		return false;
		
	}
}
