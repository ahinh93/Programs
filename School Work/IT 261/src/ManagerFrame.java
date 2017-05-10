import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class creates the user interface for managers to make changes within the company.
 * @author Landin Maupin and Andrew Hinh
 */
public class ManagerFrame extends JFrame implements ActionListener
{
	JTextArea infoBox;
	JTextArea companyDisplay;
	JTextArea viewSchedule;

	JTextField empNameField;
	JTextField jobNameField;
	JTextField jobDescField;
	JTextField wageField;
	JTextField wageRequest;
	JTextField IDRequest;
	JTextField removeIDField;
	JTextField schedIDField;
	JTextField dateField;
	JTextField hoursField;

	JLabel empNameRequest;
	JLabel jobDescRequest;
	JLabel authors;

	JButton newEmpButton;
	JButton saveEmpButton;
	JButton setHoursButton;
	JButton getHoursButton;
	JButton setWageButton;
	JButton scheduleButton;
	JButton removeEmpButton;

	JPanel mainButtonPanel;
	JPanel newEmpPanel;
	JPanel removeEmpPanel;
	JPanel setWagePanel;
	JPanel newSchedPanel;
	JPanel recHoursPanel;

	JScrollPane infoBox2;
	JScrollPane wageBox;
	ArrayList<Employee> companyArray;

	PrintStream appSched;
	PrintStream emps;
	
	int companySize; // carries number of existing employees
	int shiftSelect; // saves which shift manager creates schedule for
	public ManagerFrame()
	{
		super();
		setTitle("Manager");
		setSize(500,430);
		setResizable(false);

		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //terminate program upon closing, releases all memory
		
		//Create panel that will hold all the buttons
		mainButtonPanel = new JPanel();
		//This panel contains the main buttons to the left
		this.add(mainButtonPanel,BorderLayout.CENTER);
		mainButtonPanel.setLayout(new GridLayout(4,0));

		//This creates the text box at the bottom of screen
		infoBox = new JTextArea("Application created by the Abandoned House Ranchers: Landon Maupin & Andrew Hinh", 10,0);
		infoBox2 = new JScrollPane(infoBox);
		infoBox.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(infoBox2,BorderLayout.SOUTH);


		/*
		 * Employee button will take care of creating new employees and 
		 * adding it to the system
		 */
		newEmpButton = new JButton("Create Employee");
		mainButtonPanel.add(newEmpButton);
		newEmpButton.setActionCommand("createEmp");
		newEmpButton.addActionListener(this);

		setHoursButton = new JButton("Record Hours");
		mainButtonPanel.add(setHoursButton);
		setHoursButton.setActionCommand("recHours");
		setHoursButton.addActionListener(this);

		getHoursButton = new JButton ("Retrieve Hours");
		mainButtonPanel.add(getHoursButton);
		getHoursButton.setActionCommand("getHours");
		getHoursButton.addActionListener(this);

		setWageButton = new JButton("Set Employee Wage");
		mainButtonPanel.add(setWageButton);
		setWageButton.setActionCommand("setWage");
		setWageButton.addActionListener(this);

		scheduleButton = new JButton("Create Schedule");
		mainButtonPanel.add(scheduleButton);
		scheduleButton.setActionCommand("createSched");
		scheduleButton.addActionListener(this);

		removeEmpButton = new JButton("Fire Employee By ID");
		mainButtonPanel.add(removeEmpButton);
		removeEmpButton.setActionCommand("remEmp");
		removeEmpButton.addActionListener(this);

		saveEmpButton = new JButton("Save Changes to File");
		mainButtonPanel.add(saveEmpButton);
		saveEmpButton.setActionCommand("saveEmps");
		saveEmpButton.addActionListener(this);

		authors = new JLabel("<html><p>Written by the Abandoned House Ranchers	Landon Maupin and Andrew Hinh</p></html>");
		mainButtonPanel.add(authors);

		//This ArrayList carries all employees
		companyArray = new ArrayList<Employee>();
		
		//manage number of employees

		//Create database if it doesn't exist, name it AppSchedule.txt
		try 
		{
			appSched = new PrintStream(new FileOutputStream("AppSchedule.txt",true));
			emps = new PrintStream(new FileOutputStream("Employees.txt",true));

			//on startup, load all employees from database into application
			redrawInfoBox();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Error creating output file!");
			e.printStackTrace();			
		}

		//save employees to file
		emps.close();		

	}

	/**
	 * This method creates all the actions called by the buttons
	 * @param arg0 - this determines which button calls which action
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {


		/*
		 *Handles creating new employee  
		 */
		if(arg0.getActionCommand() == "createEmp")
		{
			/* 1. create interface
			 * 2. receive new emp info
			 * 3. use info to create Employee and write to database
			 */
			newEmpPanel = new JPanel();
			empNameField = new JTextField(10);
			jobNameField = new JTextField(10);
			jobDescField = new JTextField(10);
			wageField = new JTextField("",10);

			newEmpPanel.setLayout(new BoxLayout(newEmpPanel,BoxLayout.Y_AXIS));

			newEmpPanel.add(new JLabel("Employee Name:"));
			newEmpPanel.add(empNameField);
			newEmpPanel.add(new JLabel("Job Name:"));
			newEmpPanel.add(jobNameField);
			newEmpPanel.add(new JLabel("Job Description:"));
			newEmpPanel.add(jobDescField);
			newEmpPanel.add(new JLabel("Hourly Wage ($):"));
			newEmpPanel.add(wageField);

			int newEmpResult = JOptionPane.showConfirmDialog(null, newEmpPanel, "Create New Employee", JOptionPane.OK_CANCEL_OPTION);

			if (newEmpResult == JOptionPane.OK_OPTION)
			{
				//String empName, String jobName, String jobDescription, double hourlyWage, ArrayList<Employee> companyArray)
				Employee tempPointer;
				try
				{
					tempPointer = new Employee(empNameField.getText(), jobNameField.getText(), jobDescField.getText(),this.round(Double.parseDouble(wageField.getText())));
					infoBox.setText(empNameField.getText() + " successfully added to company!\n" + empNameField.getText() + "'s employee ID: " + tempPointer.getEmpID());
					tempPointer.setEmpID(companyArray.size() +1);
					companyArray.add(tempPointer);
				}
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(ManagerFrame.this,"Invalid Wage Request!","",JOptionPane.ERROR_MESSAGE);
				}
			}



		}

		if(arg0.getActionCommand() == "recHours")
		{
			try
			{
				Scanner actHours = new Scanner(new File("ActualHours.txt"));
				viewSchedule = new JTextArea();
				while(actHours.hasNext())
				{
					viewSchedule.append(actHours.nextLine() + "\n");
				}
				actHours = new Scanner(new File("ActualHours.txt"));
				JScrollPane sPane = new JScrollPane(viewSchedule);
				
				JFrame temp = new JFrame();
				temp.add(sPane);
				temp.setSize(360, 400);
				temp.setVisible(true);
				
				ArrayList<String> actualHoursTxt = new ArrayList<String>();				
				recHoursPanel = new JPanel();
				recHoursPanel.setLayout(new BoxLayout(recHoursPanel,BoxLayout.Y_AXIS));
				IDRequest = new JTextField(10);
				dateField = new JTextField(10);
				hoursField = new JTextField(10);			
				
				recHoursPanel.add(new JLabel("Employee ID: "));
				recHoursPanel.add(IDRequest);
				recHoursPanel.add(new JLabel("Enter existing date: "));
				recHoursPanel.add(dateField);
				recHoursPanel.add(new JLabel("Total hours worked that shift: (decimal)"));
				recHoursPanel.add(hoursField);
				
				int recHoursResult = JOptionPane.showConfirmDialog(null, recHoursPanel, "Record Hours: ", JOptionPane.OK_CANCEL_OPTION);
				if (recHoursResult == JOptionPane.OK_OPTION)
				{
					//validate employee exists
					if(isInteger(IDRequest.getText()))
					{
						int IDSched = Integer.parseInt(IDRequest.getText());
						if(IDSched <= 0 || IDSched > companySize)
						{
							JOptionPane.showMessageDialog(ManagerFrame.this,"Invalid Employee ID!","",JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							boolean changed = false;
							//validate date exists
							while(actHours.hasNext())
							{
								String currentLine = actHours.nextLine();
								String outLine = "";
								if(currentLine.contains("ID:"+ IDSched) && currentLine.contains(dateField.getText()))
								{
									StringTokenizer st = new StringTokenizer(currentLine,"|");
									outLine += st.nextToken()+"|";
									outLine += st.nextToken()+"|";
									outLine += st.nextToken()+"|";
									outLine+= hoursField.getText()+ " hours";
									actualHoursTxt.add(outLine);
									changed = true;
									JOptionPane.showMessageDialog(ManagerFrame.this,"Hours successfully updated!","",JOptionPane.INFORMATION_MESSAGE);
								}
								else
								{
									actualHoursTxt.add(currentLine);
								}
							}
							//write array into file
							PrintStream ps3 = new PrintStream(new FileOutputStream("ActualHours.txt"));
							for(int i = 0; i < actualHoursTxt.size(); i++)
							{
								if(!actualHoursTxt.get(i).equals(""))
								{
									ps3.append(actualHoursTxt.get(i));
									ps3.println();
								}
							}
							ps3.close();
							
							if(!changed)
							{
								JOptionPane.showMessageDialog(ManagerFrame.this,"ERROR: Check entries again","",JOptionPane.ERROR_MESSAGE);
							}							
						}
					}
				}
				temp.setVisible(false);
				actHours.close();
			} 
			catch (FileNotFoundException e) 
			{
				JOptionPane.showMessageDialog(ManagerFrame.this,"ActualHours.txt not found!","",JOptionPane.ERROR_MESSAGE);
			}
			
			
		}

		//sets wage for existing employees
		if(arg0.getActionCommand() == "setWage")
		{
			setWagePanel = new JPanel();
			setWagePanel.setLayout(new BoxLayout(setWagePanel,BoxLayout.Y_AXIS));
			wageRequest = new JTextField(10);
			IDRequest = new JTextField(10);

			setWagePanel.add(new JLabel("New Wage: $"));
			setWagePanel.add(wageRequest);
			setWagePanel.add(new JLabel("Employee ID: "));
			setWagePanel.add(IDRequest);

			
			companyDisplay = new JTextArea();
			wageBox = new JScrollPane(companyDisplay);
			
			if (companyArray.size() == 0)
			{
				companyDisplay.append("No available employees to modify.");
			}
			for(int i = 0; i < companyArray.size(); i++)
			{
				if (companyArray.get(i) != null)
				{
					companyDisplay.append("Employee: " + companyArray.get(i).getEmpName() + "ID: " + companyArray.get(i).getEmpID()+"\n");
				}
			}
			companyDisplay.setBorder(BorderFactory.createLineBorder(Color.black));
			setWagePanel.add(wageBox);

			int wageResult = JOptionPane.showConfirmDialog(null, setWagePanel, "Set New Wage: ", JOptionPane.OK_CANCEL_OPTION);

			if (wageResult == JOptionPane.OK_OPTION)
			{
				//String empName, String jobName, String jobDescription, double hourlyWage, ArrayList<Employee> companyArray)
				Employee tempPointer;
				try
				{
					double newWage = Double.parseDouble(wageRequest.getText());
					int requestedID = Integer.parseInt(IDRequest.getText());

					if (requestedID > companyArray.size() || requestedID <= 0)
					{
						infoBox.setText("ERROR: Invalid ID requested!");
					}

					companyArray.get(requestedID-1).setHourlyWage(newWage);

					redrawInfoBox();
				}
				catch(NumberFormatException e)
				{
					infoBox.setText("ERROR: Invalid wage or ID entry!");
				}

			}
		}


		if(arg0.getActionCommand() == "getHours")
		{
			Scanner actHours;
			try
			{
				actHours = new Scanner(new File("ActualHours.txt"));
				viewSchedule = new JTextArea();
				while(actHours.hasNext())
				{
					viewSchedule.append(actHours.nextLine() + "\n");
				}
				actHours = new Scanner(new File("ActualHours.txt"));
				JScrollPane sPane = new JScrollPane(viewSchedule);
				
				JFrame temp = new JFrame();
				temp.add(sPane);
				temp.setSize(360, 400);
				temp.setVisible(true);
				
				JTextField IDRequest = new JTextField(10);
				JTextField dateRequest = new JTextField(10);
				JPanel getHoursPanel = new JPanel();
				getHoursPanel.setLayout(new BoxLayout(getHoursPanel,BoxLayout.Y_AXIS));
				
				getHoursPanel.add(new JLabel("Employee ID: "));
				getHoursPanel.add(IDRequest);
				getHoursPanel.add(new JLabel("Enter date: MM/DD/YYYY"));
				getHoursPanel.add(dateRequest);
				
				boolean found = false;
				
				int hoursResult = JOptionPane.showConfirmDialog(null, getHoursPanel, "Get Existing Employee's Hours: ", JOptionPane.OK_CANCEL_OPTION);

				if (hoursResult == JOptionPane.OK_OPTION)
				{
				
					while(actHours.hasNext())
					{
						String currentLine = actHours.nextLine();
						StringTokenizer st = new StringTokenizer(currentLine,"|");
						
						st.nextToken();
						String ID = st.nextToken();
						ID = ID.substring(3);
						String date = st.nextToken();
						String hours = st.nextToken();
						if(ID.equals(IDRequest.getText()) && date.equals(dateRequest.getText()))
						{
							for(int i = 0; i < companySize; i++)
							{
								if(companyArray.get(i).getEmpID() == Integer.parseInt(ID))
								{
									found = true;
									JOptionPane.showMessageDialog(ManagerFrame.this,companyArray.get(i).getEmpName() + " has worked " + hours + " on " + date,"",JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}
					if(!found)
					{
						JOptionPane.showMessageDialog(ManagerFrame.this,"ERROR: Employee not found!","",JOptionPane.ERROR_MESSAGE);
					}
				}

				temp.setVisible(false);
				
			} 
			catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
		}
		if(arg0.getActionCommand() == "createSched")
		{
			/* 1. create interface
			 * 2. receive new emp info
			 * 3. use info to create Employee and write to database
			 */
			schedIDField = new JTextField("",10);
			dateField = new JTextField("",10);
			newSchedPanel = new JPanel();
			newSchedPanel.setLayout(new BoxLayout(newSchedPanel,BoxLayout.Y_AXIS));
			
			newSchedPanel.add(new JLabel("Employee ID:"));
			newSchedPanel.add(schedIDField);
			newSchedPanel.add(new JLabel("Enter date for work MM/DD/YYYY:"));
			newSchedPanel.add(dateField);
			newSchedPanel.add(new JLabel("Select which shift to schedule: "));
			//Create dropdown selections for shifts
			String [] options = {"Morning 8AM - 12PM", "Afternoon 12PM - 4PM","Evening 4PM - 8PM"};
			JComboBox<String> combobox = new JComboBox<String>();
			combobox.setActionCommand("shift options");
			combobox.addActionListener(this);
			for (int i=0;i<options.length;i++)
			{
				combobox.addItem(options[i]);			
			}
			newSchedPanel.add(combobox);
			newSchedPanel.setLayout(new BoxLayout(newSchedPanel,BoxLayout.Y_AXIS));

			int newEmpResult = JOptionPane.showConfirmDialog(null, newSchedPanel, "Create Schedule", JOptionPane.OK_CANCEL_OPTION);

			if (newEmpResult == JOptionPane.OK_OPTION)
			{
				String enteredDate = dateField.getText();
				if(enteredDate.equals(""))
				{
					JOptionPane.showMessageDialog(ManagerFrame.this,"Please enter a date!","",JOptionPane.ERROR_MESSAGE);
				}
				
				if(!validJavaDate(enteredDate))
				{
					JOptionPane.showMessageDialog(ManagerFrame.this,"Invalid date entry!","",JOptionPane.ERROR_MESSAGE);
				}

				//validate employee exists
				if(isInteger(schedIDField.getText()) && validJavaDate(enteredDate))
				{
					int IDSched = Integer.parseInt(schedIDField.getText());
					if(IDSched <= 0 || IDSched > companySize)
					{
						JOptionPane.showMessageDialog(ManagerFrame.this,"Invalid Employee ID!","",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						//int empID, int shiftNo, String date, Employee emp, PrintStream ps
						for(int i = 0; i < companySize; i++)
						{
							if(IDSched == companyArray.get(i).getEmpID())
							{
								appSched.println();
								new Schedule(companyArray.get(i), shiftSelect+1, dateField.getText(), appSched);
								JOptionPane.showMessageDialog(ManagerFrame.this,companyArray.get(i).getEmpName() + " successfully scheduled to work on " + dateField.getText(),"",JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(ManagerFrame.this,"Invalid Employee ID!","",JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		if(arg0.getActionCommand() == "saveEmps")
		{
			writeEmps();
			JOptionPane.showMessageDialog(ManagerFrame.this,"Employees successfully saved!","",JOptionPane.INFORMATION_MESSAGE);
		}

		if(arg0.getActionCommand() == "remEmp")
		{
			removeEmpPanel = new JPanel();
			removeIDField = new JTextField(10);			


			removeEmpPanel.setLayout(new BoxLayout(removeEmpPanel,BoxLayout.Y_AXIS));

			removeEmpPanel.add(new JLabel("Employee ID to Remove:"));
			removeEmpPanel.add(removeIDField);

			int removeResult = JOptionPane.showConfirmDialog(null, removeEmpPanel, "Remove Employee by ID", JOptionPane.OK_CANCEL_OPTION);

			if (removeResult == JOptionPane.OK_OPTION)
			{
				try
				{
					if (isInteger(removeIDField.getText()))
					{
						int firedID = Integer.parseInt(removeIDField.getText());
						if(firedID <= 0 || firedID > companySize)
						{
							throw new NumberFormatException();
						}
						else
						{
							for(int k = companyArray.size()-1; k >= 0; k--)
							{
								if(companyArray.get(k).getEmpID() == firedID)
								{
									String fired = companyArray.get(k).getEmpName();
									companyArray.remove(k);
									JOptionPane.showMessageDialog(ManagerFrame.this,fired + " successfully fired!","",JOptionPane.PLAIN_MESSAGE);
								}
							}
						}
					}
					else
						throw new NumberFormatException();
				}
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(ManagerFrame.this,"Invalid ID Request for Deletion!","",JOptionPane.ERROR_MESSAGE);
				}
			}

		}
		
		if(arg0.getActionCommand() == "shift options")
		{
			JComboBox<? extends String> box = (JComboBox<? extends String>)arg0.getSource();
			shiftSelect = box.getSelectedIndex();			
		}		
		redrawInfoBox();
	}

	/**
	 * This method reads from AppSchedule.txt and then updates InfoBox from main screen to updated database
	 */
	private void redrawInfoBox()
	{
		try
		{
			Scanner in = new Scanner(new File("Employees.txt"));
			companySize = 0;
			//keep reading file until end, adding new employees to arraylist
			while(in.hasNext())
			{
				StringTokenizer st = new StringTokenizer(in.nextLine(),"|"); // '|' is delimiter
				String name = st.nextToken();
				Scanner scan = new Scanner(st.nextToken()); //id handler

				int id = Integer.parseInt(scan.next().substring(3));
				String tempJobName = st.nextToken();
				String tempJobDesc = st.nextToken();
				scan = new Scanner(st.nextToken());
				String wage = scan.next();
				Double tempWage = Double.parseDouble(wage.substring(1, wage.length()-3));

				boolean existsInArray = false;
				//adds employee to array if they don't already exist
				for(int i = 0; i < companyArray.size()&& !existsInArray; i++)
				{
					if(companyArray.get(i).getEmpName().equals(name))
					{
						existsInArray = true;
					}
				}
				//if doesn't exist, then add to array
				if(!existsInArray)
				{
					//String empName, String jobName, String jobDescription, double hourlyWage, ArrayList<Employee> companyArray)
					Employee temp = new Employee(name,tempJobName, tempJobDesc, tempWage);
					temp.setEmpID(id);
					companyArray.add(temp);
				}
			}	

			//formats array into infobox
			infoBox.setText("Name:\tEmp ID:\tJob Name:\tJob Desc:\tWage:\n");
			for(int i = 0; i < companyArray.size(); i++)
			{
				if(companyArray.get(i) != null)
				{	
					//wage formatter
					NumberFormat formatter = NumberFormat.getCurrencyInstance();
					String moneyString = formatter.format(companyArray.get(i).getWage());
					infoBox.append(companyArray.get(i).getEmpName()+"\t"+companyArray.get(i).getEmpID()+"\t"+companyArray.get(i).getJobName()
							+"\t"+companyArray.get(i).getJobDescription()+"\t"+moneyString+"\n");
					companySize++;
				}
			}
			infoBox.append("Total number of employees: " + companySize);

		}
		catch (FileNotFoundException e) 
		{
			infoBox.setText(e.getMessage());			
		}		
	}
	/**
	 * Helper method that formats user-entered wages into proper two-decimal places
	 * @param value - user entered double
	 * @return returns the rounded number to 2 decimal places
	 */
	private static double round(double value) {
		long factor = (long) Math.pow(10, 2);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
	/**
	 * This method writes most current employees of company to database
	 */
	private void writeEmps()
	{
		try 
		{
			PrintStream emps2 = new PrintStream(new FileOutputStream("Employees.txt"));
			for(int i = 0; i < companyArray.size(); i++)
			{
				if(companyArray.get(i)!=null)
				{
					emps2.print(companyArray.get(i).getEmpName()+"|ID:"+companyArray.get(i).getEmpID()+"|"+companyArray.get(i).getJobName()+"|"
							+companyArray.get(i).getJobDescription()+"|$"+companyArray.get(i).getWage()+"/hr");
					emps2.println();
				}
			}
			emps2.close();
		} 
		catch (FileNotFoundException e) 
		{
			infoBox.setText("File not found exception, could not write to Employees.txt");
		}

	}
	/**
	 * Checks if entered string is a valid number
	 * @param str
	 * @return - returns boolean of whether string is an integer or not
	 */
	private static boolean isInteger(String str)  
	{  
		try  
		{  
			int d = Integer.parseInt(str);  
		}  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
	}
	/**
	 * This method checks if the date entered is a valid date
	 * @param strDate - user entered date
	 * @return - returns boolean if date is valid or not
	 */
	private boolean validJavaDate(String strDate)
	{
		 // Check if date is 'null' 
	    if (strDate.trim().equals(""))
	    {
	        return true;
	    }
	    // Date is not 'null' 
	    else
	    {	       
	        SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
	        sdfrmt.setLenient(false);
	        
	        Date javaDate = null;
	        try
	        {
	            javaDate = (Date) sdfrmt.parse(strDate); 
	        }
	        catch (ParseException e)
	        {
	        	//invalid date
	            return false;
	        }
	        catch (ClassCastException e)
	        {
	        	
	        }
	        //valid date
	        return true;
	    }
	}
}
