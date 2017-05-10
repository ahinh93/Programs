import java.io.PrintStream;
import java.text.DecimalFormat;


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
	
	public Schedule(int empID, int shiftNo, double act_start, double act_end, String date, PrintStream ps)
	{
		this.empID = empID;
		this.shiftNo = shiftNo;
		this.act_start = act_start;
		this.act_end = act_end;
		this.date = date;
		
		currentShift = new Shift(shiftNo);
		DecimalFormat df = new DecimalFormat("#.00");
		
		
		ps.println(empID + "\t\t " + date + "\t\t " + df.format((act_end-act_start)));
		ps.println();
	}
	
	public double getActualHoursWorked()
	{
		return (act_end - act_start);
	}
	
	public double getScheduledHours()
	{
		return (currentShift.getSetHours());
	}
}
