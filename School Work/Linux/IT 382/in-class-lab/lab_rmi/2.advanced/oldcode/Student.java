import java.io.Serializable;
import java.util.*;
import java.io.*;


public class Student implements Serializable
{
	private String lastName;
	private String firstName;
	private int id;

	public Student()
	{
		lastName = null;
		firstName=null;
		id=0;
	}
	public Student(String lName, String fName, int anID)
	{
		lastName = lName;
		firstName = fName;
		id = anID;
	}

	public int getID()
	{
		return id;
	}

	public void setID(int anID)
	{
		id = anID;
	}
}
