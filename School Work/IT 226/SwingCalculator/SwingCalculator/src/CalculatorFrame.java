import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class CalculatorFrame extends JFrame implements ActionListener
{
	JTextField display;
	JLabel memDisplay;
	JPanel memPanel;
	JPanel memButtonPanel;
	JPanel mainButtonPanel;
	JPanel anotherPanel;
	JButton []memButtons;
	JButton [] specialButtons;
	JButton [] keypadButtons;
	JPanel keypadPanel;
	JPanel specialPanel;
	JButton []numberButtons;
	JButton []operatorButtons;
	
	public CalculatorFrame()
	{
		super();
		setTitle("Calculator");
		setSize(300,220);
		setResizable(false);
		
		this.setLayout(new BorderLayout());//borderlayout for the main screen
		
		//add the display to the north
		display = new JTextField("");
		display.setEnabled(false);
		display.setHorizontalAlignment(JLabel.RIGHT);
		this.add(display,BorderLayout.NORTH);
		
		
		
		//main button panel to the center
		mainButtonPanel = new JPanel();
	//	mainButtonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(mainButtonPanel,BorderLayout.CENTER);
		
		//main button panel
		
		mainButtonPanel.setLayout(new BorderLayout());
		
		
		anotherPanel = new JPanel();
		mainButtonPanel.add(anotherPanel,BorderLayout.CENTER);
		anotherPanel.setLayout(new BorderLayout());
		
		//memory panel to the west
		memPanel = new JPanel();
		mainButtonPanel.add(memPanel,BorderLayout.WEST);
		
		specialPanel = new JPanel();
		//	specialPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		anotherPanel.add(specialPanel, BorderLayout.NORTH);
		
		keypadPanel = new JPanel();
	//	keypadPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		anotherPanel.add(keypadPanel,BorderLayout.CENTER);
		
		//memory panel
		memPanel.setBorder(new EmptyBorder(5, 5, 5, 5) );
		memPanel.setLayout(new GridLayout(5,0,5,5));
		
		memDisplay = new JLabel("M");
		memDisplay.setHorizontalAlignment(JLabel.CENTER);
		memDisplay.setBorder(BorderFactory.createLoweredBevelBorder());
		memPanel.add(memDisplay,BorderLayout.NORTH);
		
		//memPanel.add(memDisplay,);
		
		

		
		memButtons = new JButton[4];
		for (int i=0;i<memButtons.length;i++)
		{
			memButtons[i] = new JButton(" ");
			memButtons[i].setMargin(new Insets(0,0,0,0));
			memPanel.add(memButtons[i]);
		}
		memButtons[0].setText("MC");
		memButtons[1].setText("MR");
		memButtons[2].setText("MS");
		memButtons[3].setText("M+");
		
		
		
		
			
		
		//special button panel
		
		specialPanel.setLayout(new GridLayout(0,3,5,5));
		specialPanel.setBorder(new EmptyBorder(5, 5, 0, 5) );
		specialButtons = new JButton[3];
		
		for (int i=0;i<specialButtons.length;i++)
		{
			specialButtons[i] = new JButton();
			specialButtons[i].setMargin(new Insets(0,0,0,0));
			specialPanel.add(specialButtons[i]);
		}
		
		specialButtons[0].setText("Backspace");
		specialButtons[1].setText("CE");
		specialButtons[2].setText("C");
		
		keypadPanel.setBorder(new EmptyBorder(5, 5, 5, 5) );
		keypadPanel.setLayout(new GridLayout(0,5,5,5));
		
		numberButtons = new JButton[10];
		for (int i=0;i<numberButtons.length;i++)
		{
			numberButtons[i] = new JButton(""+i);
			numberButtons[i].setActionCommand(""+i);
			numberButtons[i].setMargin(new Insets(0,0,0,0));
			numberButtons[i].addActionListener(this);
		}
		operatorButtons = new JButton[10];
		
		operatorButtons[0] = new JButton("+");
		operatorButtons[1] = new JButton("-");
		operatorButtons[2] = new JButton("*");
		operatorButtons[3] = new JButton("/");
		operatorButtons[4] = new JButton("=");
		operatorButtons[5] = new JButton(".");
		operatorButtons[6] = new JButton("+/-");
		operatorButtons[7] = new JButton("1/x");
		operatorButtons[8] = new JButton("%");
		operatorButtons[9] = new JButton("sqrt");
		
		for (int i=0;i<operatorButtons.length;i++)
		{
			operatorButtons[i].setMargin(new Insets(0,0,0,0));
		}
		
		//add to keypanel
		keypadPanel.add(numberButtons[7]);
		keypadPanel.add(numberButtons[8]);
		keypadPanel.add(numberButtons[9]);
		keypadPanel.add(operatorButtons[3]);
		keypadPanel.add(operatorButtons[9]);
		
		keypadPanel.add(numberButtons[4]);
		keypadPanel.add(numberButtons[5]);
		keypadPanel.add(numberButtons[6]);
		keypadPanel.add(operatorButtons[2]);
		keypadPanel.add(operatorButtons[8]);
		
		keypadPanel.add(numberButtons[1]);
		keypadPanel.add(numberButtons[2]);
		keypadPanel.add(numberButtons[3]);
		keypadPanel.add(operatorButtons[1]);
		keypadPanel.add(operatorButtons[7]);
		
		keypadPanel.add(numberButtons[0]);
		keypadPanel.add(operatorButtons[6]);
		keypadPanel.add(operatorButtons[5]);
		keypadPanel.add(operatorButtons[0]);
		keypadPanel.add(operatorButtons[4]);
		
		
		//menus
		
		JMenuBar menubar = new JMenuBar();
		
		JMenu viewMenu = new JMenu("View");
		JMenu editMenu = new JMenu("Edit");
		JMenu helpMenu = new JMenu("Help");
		
		menubar.add(viewMenu);
		menubar.add(editMenu);
		menubar.add(helpMenu);
		
		this.setJMenuBar(menubar);
		
		JMenuItem viewHelp = new JMenuItem("View help");
		JMenuItem about = new JMenuItem("About Calculator");
		
		helpMenu.add(viewHelp);
		helpMenu.add(about);
		helpMenu.add(editMenu);
		
				
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		display.setText(display.getText() + arg0.getActionCommand());
		
	}
	

}
