public void printAddressWithCollegeAve(Address addrs[]){
	boolean found = false;
	//transfer addresses to own array
	String streets[] = new String[addrs.length];
	for(int i = 0; i < addrs.length; i++){
		streets[i] = addrs[i].getStreet();
	}
	//rewrite all indecies to have lowercase
	//also remove all spaces
	for(int i = 0; i < streets.length; i++){
		streets[i] = streets[i].toLowerCase();
		streets[i] =  streets[i].replaceAll("\s","");
		if(streets[i].contains("collegeave")
		{
			System.out.println(addrs[i].getStreet() + "\n" + addrs[i].getCity()+", " +addrs[i].getState() +" " + addrs[i].getZip());
			found = true;
		}
	}
	if(!found)
		System.out.println("No addresses within array contain \"College Ave\"");
}