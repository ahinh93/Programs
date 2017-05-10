package grocerylistmaker;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GroceryListFrame extends JFrame{

	JPanel topPanel, middlePanel, bottomPanel, topPanelGrid;
	JMenuBar fileMenuBar;
	JMenu fileMenuBarContents;
	JMenuItem fileNewItem, fileOpenItem;
	AutoCompleteTextField autoTextField;
	JTextField unitsTextField, quantityTextField;
	JButton addButton;
	JComboBox<String> suggestionBox;
	JLabel unitsLabel, quantityLabel, itemNameLabel;
	
	GroceryListFrame()
	{
		super();
		setTitle("Grocery List");
		setResizable(false);
		setSize(500, 700);
		
		this.setLayout(new BorderLayout());
		
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		
		middlePanel = new JPanel();
		middlePanel.setLayout(new GridLayout());
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		
		this.add(topPanel, BorderLayout.NORTH);
		this.add(middlePanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		//create file menubar
		fileMenuBar = new JMenuBar();
		fileMenuBarContents = new JMenu("File");
		fileNewItem = new JMenuItem("New List");
		fileOpenItem = new JMenuItem("Open");
		fileMenuBarContents.add(fileNewItem);
		fileMenuBarContents.add(fileOpenItem);

		fileMenuBar.add(fileMenuBarContents);
		this.setJMenuBar(fileMenuBar);
		//textToXML wordPopulator = new textToXML();
		
		autoTextField = new AutoCompleteTextField(10);
		unitsTextField = new JTextField(10);
		quantityTextField = new JTextField(10);
		
		
		autoTextField.addWords(textToXML.openAndReadFile());
		
		addButton = new JButton("Add to List");
		
		//topPanel.add(autoTextField, BorderLayout.NORTH);
		
		topPanelGrid = new JPanel();
		topPanelGrid.setLayout(new GridLayout(2, 3));
		itemNameLabel = new JLabel("Grocery Item");
		unitsLabel = new JLabel("Units");
		quantityLabel = new JLabel("Quantity");

		//suggestionBox = new JComboBox<String>(new String[]{"Option 1", "Option 2"});
		
		topPanelGrid.add(itemNameLabel);
		topPanelGrid.add(unitsLabel);
		topPanelGrid.add(quantityLabel);
		
		topPanelGrid.add(autoTextField);
		topPanelGrid.add(unitsTextField);
		topPanelGrid.add(quantityTextField);
		
		
		topPanel.add(topPanelGrid, BorderLayout.CENTER);
		
		topPanel.add(addButton, BorderLayout.SOUTH);
		
		//suggestionBox.
		
		
	}
}
