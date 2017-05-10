import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;



public class WordFrame extends JFrame 
{
	JList words;
	AutoCompleteTextField textfield;

	
	public WordFrame()
	{
		super();
		this.setSize(500,800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.WHITE);
		this.setTitle("Word Complete");
		this.setLayout(new BorderLayout());
		JPanel openPanel = new JPanel();
		openPanel.setLayout(new FlowLayout());
		this.add(openPanel,BorderLayout.SOUTH);
		

		//add the open button
		JButton openButton = new JButton("Open");
		openButton.addActionListener(new ButtonListener());
		openButton.setActionCommand("Open button");
		openPanel.add(openButton);
		
		
		//the text field
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new FlowLayout());
		textfield = new AutoCompleteTextField(15);
		textPanel.add(textfield);
		this.add(textPanel,BorderLayout.NORTH);
	
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getActionCommand().equals("Open button"))
			{
				JFileChooser fileopen = new JFileChooser();
				
		        // Start in current directory
		        fileopen.setCurrentDirectory(new File("."));

				if (fileopen.showOpenDialog(WordFrame.this)==JFileChooser.APPROVE_OPTION)
				{
					Scanner in;
					
					try
					{
						in = new Scanner(new FileInputStream(fileopen.getSelectedFile().getAbsolutePath()));
					}
					catch (FileNotFoundException f)
					{
						JOptionPane.showMessageDialog(WordFrame.this,"File not found");
						return;
					}
					addWords(in);
					
				}
			}
		}
	}
	
	public void addWords(Scanner in)
	{
		ArrayList<String> words = new ArrayList<String>();
		while (in.hasNext())
		{
			String str = in.next();
			String str2 = "";
			for (int i=0;i<str.length();i++)
			{
				if (Character.isLetter(str.charAt(i)))
					str2 = str2 + str.charAt(i);
			}
			if (str2.length()>0)
				words.add(str2);
		}
		String [] wordsArray;
		wordsArray = words.toArray(new String[0]);
		
		textfield.addWords(wordsArray);
	}
	
	/*private class TypeListener implements DocumentListener
	{

		public void update() 
		{
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			String str = textfield.getText();
			guessedWords = manager.getWords(str);
			words.setListData(guessedWords.toArray(new String[0]));
			wordsScroll.invalidate();	
		}

		@Override
		public void changedUpdate(DocumentEvent arg0) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) 
		{
			// TODO Auto-generated method stub
			update();
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) 
		{
			// TODO Auto-generated method stub
			update();
		}
		
	}
	*/
	
		public static void main(String []args)
	{
		WordFrame frame = new WordFrame();
		frame.setVisible(true);
	}
}
