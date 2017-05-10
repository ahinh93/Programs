/**
 * This class creates all the content in Rectangle Frame
 * Andrew Hinh
 * @author ahinh
 *
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import javax.imageio.*;

public class RectangleFrame extends JFrame implements MouseInputListener,
		ActionListener, ItemListener {

	MouseInputAdapter mouseArea;
	JLabel container;
	JLabel checkBoxDisplay;
	JLabel fileSaveDisplay;
	JPanel mainButtonPanel;
	JPanel mainScreen;
	JButton saveImage;
	JButton clearDrawing;
	Graphics graph;
	ArrayList<Rectangle> myList = new ArrayList<Rectangle>();
	MouseInputListener mouse;
	
	
	public RectangleFrame() {

		/*
		 * Call the super constructor, set title to be rectangles, set size to be 500x500 and allow to be resizable
		 */
		super();
		setTitle("Rectangles");
		setSize(500, 500);
		setResizable(true);

		// have layout of screen be a border layout
		this.setLayout(new BorderLayout());

		
		/*
		 * Container is the region that will be drawn on, set it to be white and
		 * add it to this.center
		 */

		container = new JLabel();
		container.setBackground(Color.WHITE);
		container.setOpaque(true);
		container.addMouseListener(mouse);
		container.addMouseMotionListener((MouseMotionListener) mouse);
		
		this.add(container, BorderLayout.CENTER);

		
		
		// panel of buttons with be set south
		mainButtonPanel = new JPanel();
		mainButtonPanel.setLayout(new FlowLayout());
		this.add(mainButtonPanel, BorderLayout.SOUTH);
		/*
		 * Add border to south panel
		 */
		mainButtonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// add two checkboxes: save button and clear drawing button
		JCheckBox[] checkBoxes = new JCheckBox[2];

		checkBoxes[0] = new JCheckBox("Draw intersections");
		checkBoxes[0].setSelected(false);
		checkBoxes[0].setActionCommand("CB" + 1);
		checkBoxes[0].addItemListener(this);
		checkBoxes[1] = new JCheckBox("Draw union");
		checkBoxes[1].setSelected(false);
		checkBoxes[1].setActionCommand("CB" + 2);
		checkBoxes[1].addItemListener(this);

		//add the two buttons to the south panel
		mainButtonPanel.add(checkBoxes[0]);
		mainButtonPanel.add(checkBoxes[1]);

		// save button
		saveImage = new JButton("Save image");
		saveImage.addActionListener(this);
		saveImage.setActionCommand("save the image");

		mainButtonPanel.add(saveImage);

		// clear drawing button
		clearDrawing = new JButton("Clear drawing");
		clearDrawing.addActionListener(this);
		clearDrawing.setActionCommand("clear the drawing");
		
		mainButtonPanel.add(clearDrawing);
		

	}

	@Override
	public void paintComponents(Graphics g) 
	{
		super.paintComponents(g);
		int i = myList.size();
		g.setColor(Color.GREEN);
		g.fillRect(myList.get(i-1).getX(), myList.get(i-1).getY(), myList.get(i-1).getWidth(), myList.get(i-1).getHeight());
		g.drawRect(myList.get(i-1).getX(), myList.get(i-1).getY(), myList.get(i-1).getWidth(), myList.get(i-1).getHeight());
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		myList.add(new Rectangle(x,y,getWidth(),getHeight()));
		//updateDrawableRect(getWidth(), getHeight());
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		myList.add(new Rectangle(x,y,getWidth(),getHeight()));
		container.paintComponents(graph);
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		updateSize(e);

	}

	void updateSize(MouseEvent e) {
		int i = myList.size();
        int x = e.getX();
        int y = e.getY();
        myList.get(i-1).setX(x-this.getX());
        myList.get(i-1).setY(x-this.getY());
       
        //updateDrawableRect(getWidth(), getHeight());
        //Rectangle totalRepaint = myList.get(i).union(previouseRectDrawn); 
        repaint(myList.get(i-1).getX(), myList.get(i-1).getY(), myList.get(i-1).getWidth(), myList.get(i-1).getHeight());
    }
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		String who = ((JCheckBox) arg0.getItemSelectable()).getActionCommand();

		switch (who) 
		{
			case "CB1": {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					//insert draw intersect method here
	
				} else {
	
				}
				break;
			}
			case "CB2": {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					//insert draw union method here
				} else {
	
				}
				break;
			}
	
			//this will let the user save the image to their specified path
			case "Save file": {
				final JFileChooser fchooser = new JFileChooser(".");
				int retvalue = fchooser.showSaveDialog(RectangleFrame.this);
				if (retvalue == JFileChooser.APPROVE_OPTION) {
					File f = fchooser.getSelectedFile();
					saveImage.setText(f.getAbsolutePath());
				}
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getActionCommand()) {
		case "save the image":
			Container c = getContentPane();
			BufferedImage im = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
			c.paint(im.getGraphics());
			
			final JFileChooser fchooser = new JFileChooser(".");
			int retvalue = fchooser.showSaveDialog(RectangleFrame.this);
			if (retvalue == JFileChooser.APPROVE_OPTION)
			{
				File f = fchooser.getSelectedFile();				
				try {
					ImageIO.write(im,  "FILE",  new File(f.getAbsolutePath()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;

		case "clear the drawing":
			//set as black since no rectangles can be drawn, show that screen can be reset
			container.setBackground(Color.BLACK);
			break;

		case "draw intersections":
			for (int i = 0; i < myList.size(); i++)
			{
				
			}
			break;
		case "draw union": 
			for (int i = 0; i < myList.size(); i++)
			{
				
			}
			break;
		}
	}
	
//	public static BufferedImage createBI(int width, int height, Rectangle rect) 
//	{
//		
//	}
	public static void write(BufferedImage image, String location) 
	{
		image = new BufferedImage(100, 50, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = image.createGraphics();
	}


}
