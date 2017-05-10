import java.io.PrintStream;
import java.util.ArrayList;

/**
 * This class extends Employee.java and carries more attributes and methods.
 * @author AndrewHinh
 *
 */
public class Manager extends Employee
{
	public Manager()
	{
		super();
	}
	/**
	 * Special constructor taking employee name, job name, job description, hourly wage,
	 * @param empName
	 * @param jobName
	 * @param jobDescription
	 * @param hourlyWage
	 * @param companyArray
	 */
	public Manager(String empName, String jobName, String jobDescription, double hourlyWage, ArrayList<Employee> companyArray)
	{
		super(empName, jobName, jobDescription, hourlyWage, companyArray);
	}
	
	/**
	 * This method creates new employees and adds them to the companyArrayList
	 * @param empName
	 * @param jobName
	 * @param jobDescription
	 * @param companyArray
	 * @return
	 */
	public Employee createNewEmployee(String empName, String jobName, String jobDescription, ArrayList<Employee> companyArray)
	{
		Employee temp = new Employee(empName, jobName, jobDescription, companyArray);
		return temp;
	}
	/**
	 * This method sets an employees wage through a manager
	 * @param emp
	 * @param newWage
	 */
	public void setEmployeeWage(Employee emp, double newWage)
	{
		emp.setHourlyWage(newWage);
	}
	/**
	 * write to file the new schedule object
	 * @param shiftNo
	 * @param act_start
	 * @param act_end
	 * @param date
	 * @return
	 */
	public void createSchedule(int empID, int shiftNo, double act_start, double act_end, String date, PrintStream ps)
	{
		//int shiftNo, int empNo, int act_start, int act_end, String date)
		
		new Schedule(empID, shiftNo, act_start, act_end, date, ps);
		
	}

	
	
}
