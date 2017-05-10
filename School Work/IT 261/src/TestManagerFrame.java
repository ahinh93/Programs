import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Test;
/**
 * This is a JUnit test for ManagerFrame and SwingManager
 * @author Landon Maupin and Andrew Hinh
 *
 */

public class TestManagerFrame 
{

	//This tests that employee objects can be created correctly
	@Test
	public void createEmployee() 
	{
		ArrayList<Employee> companyArray = new ArrayList<Employee>();
		
		Manager LandonMaupin = new Manager("Landon Maupin", "Team Manager", "Manager for the team", 13.00, companyArray);
		Employee MikeWazowski = LandonMaupin.createNewEmployee("Mike Wazowski", "Team Scarer", "Scares children", companyArray);
		
		assertTrue(MikeWazowski.getClass() == Employee.class);
	}
	
	@Test
	public void testCreateSchedule()
	{
		try
		{
			
			PrintStream ps = new PrintStream(new FileOutputStream("ManagerSchedule.txt"));
			
			ps = new PrintStream(new FileOutputStream("ManagerSchedule.txt",true));
			
			ArrayList<Employee> companyArray = new ArrayList<Employee>();
			ArrayList<Shift> shiftArray = new ArrayList<Shift>();
			
			Manager RickGrimes = new Manager("Rick Grimes", "Team Manager", "Manager for the team", 13.00, companyArray);
			
			Employee CarlGrimes = RickGrimes.createNewEmployee("Carl Grimes", "Team Member", "Member of the team", companyArray);
			Employee BethGreene = RickGrimes.createNewEmployee("Beth Greene", "Team Member", "Member of the team", companyArray);
			Employee DarylDixon = RickGrimes.createNewEmployee("Daryl Dixon", "Team Member", "Member of the team", companyArray);
			
			RickGrimes.createSchedule(CarlGrimes.getEmpID(), 1, 8.63, 12.25, "04/16/14",CarlGrimes,ps);
			RickGrimes.createSchedule(BethGreene.getEmpID(), 1, 8, 12.91, "04/17/14",BethGreene,ps);
			RickGrimes.createSchedule(CarlGrimes.getEmpID(), 2, 6.1, 13.25, "04/16/14",CarlGrimes,ps);
			RickGrimes.createSchedule(DarylDixon.getEmpID(), 3, 12, 18, "04/18/14",DarylDixon,ps);
			RickGrimes.createSchedule(RickGrimes.getEmpID(), 2, 8.64, 12, "04/14/14",RickGrimes,ps);
			RickGrimes.createSchedule(CarlGrimes.getEmpID(), 2, 3, 7.27, "04/16/14",CarlGrimes,ps);
			
			ps.close();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
