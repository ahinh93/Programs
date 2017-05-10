@Test
public void TestZero()
{
	assertEquals(Math.abs(0),0);
}

@Test
public void TestPositiveNumbers
{
	assertEquals(Math.abs(1),1);
	assertEquals(Math.abs(120934),120934);
	assertEquals(Math.abs(Integer.MAX_VALUE),Integer.MAX_VALUE);
}

@Test
public void TestNegativeNumbers
{
	assertEquals(Math.abs(-1),1);
	assertEquals(Math.abs(-120934),120934);
	//min_value = -2^31 while max_value=2^31-1 for MSB
	assertEquals(Math.abs(Integer.MIN_VALUE-1),Integer.MAX_VALUE);
}