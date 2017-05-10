import java.io.PrintStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * This class represents an employee and contains characteristics for the employee class
 * @author AndrewHinh
 *
 */
public class Employee 
{
	private ArrayList<Schedule> personalSchedule;
	private String empName, jobName, jobDescription;
	private int empID; //This is a key attribute
	private double hourlyWage;
	private double hoursWorkedThisWeek;
	
	
	//default constructor
	public Employee()
	{
		empName = "n/a";
		jobName = "n/a";
		jobDescription = "n/a";
		empID = -1;	
		hourlyWage = -1;
		hoursWorkedThisWeek = -1;
	}
	
	public Employee(String empName, String jobName, String jobDescription, ArrayList<Employee> companyArray)
	{
		this.empName = empName;
		this.jobName = jobName;
		this.jobDescription = jobDescription;
		this.empID = assignKey(companyArray);
	}

	public Employee(String empName, String jobName, String jobDescription, double hourlyWage, ArrayList<Employee> companyArray)
	{
		this.empName = empName;
		this.jobName = jobName;
		this.jobDescription = jobDescription;
		this.hourlyWage = hourlyWage;
		companyArray.add(this);
		this.empID = assignKey(companyArray);
	}
	/**
	 * This method resets the employees hours worked back to 0.
	 */
	public void resetWeeklyHours()
	{
		hoursWorkedThisWeek = 0;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}
	
	public void setHourlyWage(double newWage)
	{
		this.hourlyWage = newWage;
	}
	/**
	 * This method sets hours and minutes to decimals for the employee
	 * @param hours
	 * @param minutes
	 * @return returns true if successfully entered, otherwise returns false
	 */
	public boolean punchIn(int hours, int minutes)
	{
		if (hours>24 || hours < 0 || minutes > 60 || minutes < 0)
		{
			return false;
		}
		
		minutes = (minutes / 60);
		
		this.hoursWorkedThisWeek += hours;
		this.hoursWorkedThisWeek += minutes;
		
		return true;
	}
	
	public String punchOut()
	{
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		String s = n.format(hourlyWage);
		
		return ("Employee ID: " + empID + "\nEmployee Name: " + empName + "\nJob Name: " + jobName +
				"\nJob Description: " + jobDescription + "\nHourly Wage: " + s + "\nHours worked this week: " + hoursWorkedThisWeek +"\n");
	}
	
	public void printEmployeeReport(PrintStream ps)
	{
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		String s = n.format(hourlyWage);
		
		ps.println("Employee ID: " + empID);
		ps.println("Employee Name: " + empName);
		ps.println("Job Name: " + jobName);
		ps.println("Job Description: " + jobDescription);
		ps.println("Hourly Wage: " + s);
		ps.println("Hours Worked This Week: " + hoursWorkedThisWeek);
		ps.println();
	}
	
	public int assignKey(ArrayList<Employee> companyArray)
	{
		empID = companyArray.indexOf(this) +1;
		return empID;
	}
	
}
