import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Test;

public class companyTest {


	@Test
	public void testBasicConstructors()
	{
		try {
			PrintStream ps = new PrintStream(new File("report.txt"));

			ArrayList<Employee> companyArray = new ArrayList<Employee>();
			ArrayList<Shift> shiftArray = new ArrayList<Shift>();
	
			Employee AdamMonroe = new Employee("Adam Monroe", "Team Member", "Member of the team", 8.75, companyArray);
			Employee BethGreene = new Employee("Beth Greene", "Team Supervisor", "Supervisor for the team", 9.50, companyArray);
			Manager ChrisTucker = new Manager("Chris Tucker", "Team Manager", "Manager for the team", 13.00, companyArray);
			Employee AdamMonroe2 = new Employee("Adam Monroe", "Team Member", "Member of the team", 8.75,companyArray);

			//Punchout() is similar to the toString() method, prints all information
			AdamMonroe.printEmployeeReport(ps);
			BethGreene.printEmployeeReport(ps);
			ChrisTucker.printEmployeeReport(ps);
			AdamMonroe2.printEmployeeReport(ps);

			ps.close();
			//ensure all objects are instantiated
			assertFalse(AdamMonroe==null || BethGreene == null || ChrisTucker == null || AdamMonroe2 == null);		

		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateEmployeeThroughManager()
	{
		try
		{
			PrintStream ps = new PrintStream(new File("report2.txt"));

			ArrayList<Employee> companyArray = new ArrayList<Employee>();
			ArrayList<Shift> shiftArray = new ArrayList<Shift>();
	
			
			Employee BethGreene = new Employee("Beth Greene", "Team Supervisor", "Supervisor for the team", 9.50, companyArray);
			Manager ChrisTucker = new Manager("Chris Tucker", "Team Manager", "Manager for the team", 13.00, companyArray);
			
			ChrisTucker = new Manager("Chris Tucker", "Team Manager", "Manager for the team", 13.00, companyArray);
			Employee AdamMonroe = ChrisTucker.createNewEmployee("Adam Monroe", "Team Member", "Member of the team", companyArray);
			ChrisTucker.createNewEmployee("Beth Greene", "Team Member", "Member of the team", companyArray);
			ChrisTucker.createNewEmployee("Adam Monroe", "Team Member", "Member of the team", companyArray);
			
			Employee AdamMonroe2 = new Employee("Adam Monroe", "Team Member", "Member of the team", 8.75,companyArray);
	
			//Punchout() is similar to the toString() method, prints all information
			AdamMonroe.printEmployeeReport(ps);
			BethGreene.printEmployeeReport(ps);
			ChrisTucker.printEmployeeReport(ps);
			AdamMonroe2.printEmployeeReport(ps);
	
			ps.close();
			//ensure all objects are instantiated
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testCreateSchedule()
	{
		try
		{
			
			PrintStream ps = new PrintStream(new FileOutputStream("ScheduleArchive.txt"));
			ps.println("Employee ID\t\tDate\t\tHours Worked");
			ps = new PrintStream(new FileOutputStream("ScheduleArchive.txt",true));
			
			ArrayList<Employee> companyArray = new ArrayList<Employee>();
			ArrayList<Shift> shiftArray = new ArrayList<Shift>();
			
			Manager ChrisTucker = new Manager("Chris Tucker", "Team Manager", "Manager for the team", 13.00, companyArray);
			
			Employee AdamMonroe = ChrisTucker.createNewEmployee("Adam Monroe", "Team Member", "Member of the team", companyArray);
			Employee BethGreene = ChrisTucker.createNewEmployee("Beth Greene", "Team Member", "Member of the team", companyArray);
			Employee JohnSmith = ChrisTucker.createNewEmployee("John Smith", "Team Member", "Member of the team", companyArray);
			
			ChrisTucker.createSchedule(AdamMonroe.getEmpID(), 1, 8.63, 12.25, "04/16/14",ps);
			ChrisTucker.createSchedule(BethGreene.getEmpID(), 1, 8, 12.91, "04/17/14",ps);
			ChrisTucker.createSchedule(AdamMonroe.getEmpID(), 2, 6.1, 13.25, "04/16/14",ps);
			ChrisTucker.createSchedule(JohnSmith.getEmpID(), 3, 12, 18, "04/18/14",ps);
			ChrisTucker.createSchedule(ChrisTucker.getEmpID(), 2, 8.64, 12, "04/14/14",ps);
			ChrisTucker.createSchedule(AdamMonroe.getEmpID(), 2, 3, 7.27, "04/16/14",ps);
			
			ps.close();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
