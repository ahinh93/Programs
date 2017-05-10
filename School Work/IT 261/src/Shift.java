/**
 * This class represents a shift
 * @author ahinh
 *
 */
public class Shift 
{
	private int shiftNo; // key
	private double start_time; //written in hours
	private double end_time; //written in hours
	
	public Shift()
	{
		shiftNo = 0;
		start_time = 0;
		end_time = 0;
	}
	
	public Shift(int shiftNo)
	{
		if (shiftNo == 1)
		{
			this.shiftNo = 1;
			this.start_time = 0;
			this.end_time = 6;
		}
		if (shiftNo == 2)
		{
			this.shiftNo = 1;
			this.start_time = 6;
			this.end_time = 12;
		}
		if (shiftNo == 3)
		{
			this.shiftNo = 1;
			this.start_time = 12;
			this.end_time = 18;
		}
	}

	public int getShiftNo() {
		return shiftNo;
	}

	public void setShiftNo(int shiftNo) {
		this.shiftNo = shiftNo;
	}

	public double getStart_time() {
		return start_time;
	}

	public void setStart_time(double start_time) {
		this.start_time = start_time;
	}

	public double getEnd_time() {
		return end_time;
	}

	public void setEnd_time(double end_time) {
		this.end_time = end_time;
	}
	
	public double getScheduledHours()
	{
		return (end_time-start_time);
	}
	
	
}
