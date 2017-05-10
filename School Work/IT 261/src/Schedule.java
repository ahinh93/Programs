import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * This class brings Employee.java and Shift.java together and stores information
 * about each day
 * @author ahinh
 *
 */
public class Schedule
{

	
	private int shiftNo;
	private double act_start;
	private double act_end;
	private String date;
	Shift currentShift;
	private int empID;
	
	public Schedule(int empID, int shiftNo, double act_start, double act_end, String date, Employee emp, PrintStream ps)
	{
		this.empID = empID;
		this.shiftNo = shiftNo;
		this.act_start = act_start;
		this.act_end = act_end;
		this.date = date;
		
		currentShift = new Shift(shiftNo);

		switch(shiftNo)
		{
			case 1:
			{
				ps.println(emp.getEmpName() + "|ID:" + emp.getEmpID() + "|"+ date + "|" + "Morning Shift" );
				ps.println();
				break;
			}
			case 2:
			{
				ps.println(emp.getEmpName() + "|ID:" + emp.getEmpID() + "|"+ date + "|" + "Afternoon Shift" );
				ps.println();
				break;
			}
			case 3:
			{
				ps.println(emp.getEmpName() + "|ID:" + emp.getEmpID() + "|"+ date + "|" + "Evening Shift" );
				ps.println();
				break;
			}
		}
	}
	public Schedule(Employee emp, int shiftNo, String date, PrintStream ps)
	{
		this.empID = empID;
		this.shiftNo = shiftNo;
		this.date = date;
		
		currentShift = new Shift(shiftNo);

		switch(shiftNo)
		{
			case 1:
			{
				ps.println(emp.getEmpName() + "|ID:" + emp.getEmpID() + "|"+ date + "|" + "Morning Shift" );
				break;
			}
			case 2:
			{
				ps.println(emp.getEmpName() + "|ID:" + emp.getEmpID() + "|"+ date + "|" + "Afternoon Shift" );
				break;
			}
			case 3:
			{
				ps.println(emp.getEmpName() + "|ID:" + emp.getEmpID() + "|"+ date + "|" + "Evening Shift" );
				break;
			}
		}
	}
	
	public double getActualHoursWorked()
	{
		return (act_end - act_start);
	}
	
	public double getScheduledHours()
	{
		return (currentShift.getScheduledHours());
	}
}
