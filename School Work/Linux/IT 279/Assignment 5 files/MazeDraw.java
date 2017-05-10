import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;

public class MazeDraw extends JFrame implements MouseListener
{
    private int rows;
    private int cols;
    private int width,height;
    private String filename;
    private int block_size;
    private ArrayList<Integer> edges;
    private ArrayList<Integer> path_without_turns;
    private ArrayList<Integer> path_with_turns;
	private TreeSet<Integer> blocked;
    private int start_row,start_col,end_row,end_col;
private boolean drawn;
    BufferedImage vi;
    JLabel label;
    JScrollPane scroll;
    

    public MazeDraw()
    {
			super();
			width = 600;
			height = 600;
			setSize(width,height);
			this.setResizable(false);
			setTitle("Maze draw");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBackground(Color.WHITE);

			edges = new ArrayList<Integer>();
			path_without_turns = new ArrayList<Integer>();
			path_with_turns = new ArrayList<Integer>();
		
			label = new JLabel();
			scroll = new JScrollPane(label);
			this.add(scroll);
			label.addMouseListener(this);
		
			vi = null;
			blocked = new TreeSet<Integer>();
	
    }
    
    public int getRows()
    {
    	return rows;
    }
    
    public void setFilename(String filename)
    {
    	this.filename = filename;
    }

    public void setDimensions(int r,int c)
    {
    System.out.println("rows: "+ rows + " cols: "+cols);
		rows = r;
		cols = c;
		block_size = 550/(rows>cols?(rows):(cols));
		if (block_size<10)
			block_size = 10;
		//setSize(block_size*(cols)+100,block_size*(rows)+100);
		//	vi = new BufferedImage(block_size*(cols)+100,block_size*(rows)+100,BufferedImage.TYPE_INT_RGB);
		//label.setIcon(new ImageIcon(vi));
    }

    public void setStartEnd(int srow,int scol,int erow,int ecol)
    {
		start_row = srow;
		start_col = scol;
		end_row = erow;
		end_col = ecol;
    }

    public void setEdges(ArrayList<Integer> edges)
    {
		this.edges = new ArrayList<Integer>();
		for (int i=0;i<edges.size();i++)
			this.edges.add(edges.get(i));
    }

    public void setPathWithoutTurns(ArrayList<Integer> path)
    {
		this.path_without_turns = new ArrayList<Integer>();
		for (int i=0;i<path.size();i++)
			this.path_without_turns.add(path.get(i));

    }
    
    public void setPathWithTurns(ArrayList<Integer> path)
    {
		this.path_with_turns = new ArrayList<Integer>();
		for (int i=0;i<path.size();i++)
			this.path_with_turns.add(path.get(i));

    }

	public void setBlocked(TreeSet<Integer> blocked)
	{
		this.blocked = new TreeSet<Integer>(blocked);
	}
	
    public void paint(Graphics gr)
    {

		if (vi==null)
		{
			drawOffline();
			try
				{
				ImageIO.write(vi,"jpg",new File(filename + ".jpg"));
				}
			catch (IOException e)
				{
				}
				label.setIcon(new ImageIcon(vi));
			scroll.validate();
			label.invalidate();
		}
		//gr.drawImage(vi,0,0,this);
		
			
    }

		

    public void drawOffline()
    {
		int ox,oy;


		if (vi==null)
		{    
			vi = new BufferedImage(block_size*(cols)+100,block_size*(rows)+100,BufferedImage.TYPE_INT_RGB);	    
			label.setIcon(new ImageIcon(vi));
			scroll.validate();
			label.invalidate();
		}
		Graphics2D g = (Graphics2D)vi.getGraphics();
		
	//	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		

		g.setColor(Color.WHITE);
		g.fillRect(0,0,vi.getWidth(),vi.getHeight());

		g.setStroke(new BasicStroke(2));

		ox = 50;
		oy = 50;
		//first draw the original maze in black
		g.setColor(Color.BLACK);
		for (int i=0;i<rows+1;i++)
		{
			g.drawLine(ox,oy+block_size*i,ox+block_size*cols,oy+block_size*i);
		}

		for (int j=0;j<cols+1;j++)
		{
			g.drawLine(ox+block_size*j,oy,ox+block_size*j,oy+block_size*rows);
		}
		g.setColor(Color.WHITE);
		//	System.out.println("Size of edge array: "+edges.size());
		for (int i=0;i<edges.size();i+=4)
		{
			int i1 = edges.get(i);
			int j1 = edges.get(i+1);
			int i2 = edges.get(i+2);
			int j2 = edges.get(i+3);

			double x = ox + 0.5*block_size + 0.5*(j1+j2)*block_size;
			double y = oy + 0.5*block_size + 0.5*(i1+i2)*block_size;
			if (i1==i2)
			{

			g.drawLine((int)x,(int)(y-0.5*block_size),(int)x,(int)(y+0.5*block_size));
			}
			else if (j1==j2)
			{
			g.drawLine((int)(x-0.5*block_size),(int)y,(int)(x+0.5*block_size),(int)y);
			}
		}
		
		//draw blocked cells
		g.setColor(Color.RED);
		for (Iterator i=blocked.iterator();i.hasNext();)
		{
			int cell = (Integer)i.next();
			int row = cell/rows;
			int col = cell%rows;
			
			g.fillRect((int)(ox  + block_size*col + 1),(int)(oy + block_size*row + 1),block_size-1,block_size-1);
		}

		//mark the start and end vertices
		if ((start_row==0) && (end_row==rows-1))
		{
			g.drawLine(ox+start_col*block_size,oy,ox+(start_col+1)*block_size,oy);
			g.drawLine(ox+end_col*block_size,oy+(rows)*block_size,ox+(end_col+1)*block_size,oy+(rows)*block_size);
		}
		else if ((start_col==0) && (end_col==cols-1))
		{
			g.drawLine(ox,oy+start_row*block_size,ox,oy+(start_row+1)*block_size);
			g.drawLine(ox+(cols)*block_size,oy+end_row*block_size,ox+(cols)*block_size,oy+(end_row+1)*block_size);
		}

		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
		//draw the shortest path without turns
		g.setColor(new Color(1.0f,0.0f,0.0f,1.0f));
		g.setStroke(new BasicStroke(4));
		
		int prev1=0,prev2=0;

		if (path_without_turns.size()>=2)
		{
			prev1 = path_without_turns.get(0);
			prev2 = path_without_turns.get(1);
		}
		for (int i=2;i<path_without_turns.size();i+=2)
		{
			int curr1 = path_without_turns.get(i);
			int curr2 = path_without_turns.get(i+1);

			g.drawLine((int)(ox+0.5*block_size+prev2*block_size),(int)(oy+0.5*block_size+prev1*block_size),(int)(ox+0.5*block_size+curr2*block_size),(int)(oy+0.5*block_size+curr1*block_size));
			prev1 = curr1;
			prev2 = curr2;
		}
		
		//draw the shortest path with turns
		//g.setComposite(AlphaComposite.Src);
		g.setColor(new Color(0.0f,1.0f,0.0f,0.5f));
		g.setStroke(new BasicStroke(4));
		
		prev1=0;
		prev2=0;

		if (path_with_turns.size()>=2)
		{
			prev1 = path_with_turns.get(0);
			prev2 = path_with_turns.get(1);
		}
		for (int i=2;i<path_with_turns.size();i+=2)
		{
			int curr1 = path_with_turns.get(i);
			int curr2 = path_with_turns.get(i+1);

			g.drawLine((int)(ox+0.5*block_size+prev2*block_size),(int)(oy+0.5*block_size+prev1*block_size),(int)(ox+0.5*block_size+curr2*block_size),(int)(oy+0.5*block_size+curr1*block_size));
			prev1 = curr1;
			prev2 = curr2;
		}

		g.dispose();
		
    }
    
    public void mousePressed(MouseEvent e) 
    {
    }

    public void mouseReleased(MouseEvent e) 
    {
    	//find which cell the user clicked on
    	int x,y,row,col;
    	
    	vi = null;
    //	scroll.validate();
	//	label.invalidate();
		repaint();
		
    	x = e.getX();
    	y = e.getY();
    	
 //  	System.out.println("Mouse clicked: (" + x + "," + y + "). Dims are " + block_size*cols + " and "+block_size*rows);
    	
    	if ((x<50) || (x>(block_size*cols+50)) || (y<50) || (y>(block_size*rows+50)))
    		return;
    		
    	row = (y-50)/block_size;
    	col = (x-50)/block_size;
    	
 //   	System.out.println("row= " + row + ", col=" + col);
    	
    	//add it to the blocked nodes
    	if (blocked.contains(row*rows+col))
    		blocked.remove(row*rows+col);
    	else
    		blocked.add(row*rows+col);

    	
    	//write the blocked array to file
    	PrintWriter pw;
    	
    	pw = null;
    	try
    	{
    		pw = new PrintWriter(new FileOutputStream(filename+"-blocked.txt"));
    	}
    	catch (FileNotFoundException f)
    	{
    		return;
    	}
    	
    	if (pw==null)
    		return;
    	
    	pw.println(blocked.size());
    	for (Iterator i=blocked.iterator();i.hasNext();)
    	{
    		Integer cell = (Integer)i.next();
    		pw.println(cell/rows + " " + cell%rows);
    	}
    	pw.close();
    
    	
    	//re-solve the maze
    	SolveMaze c = new SolveMaze();
		
		c.findpath(filename);
		
		refreshPaths();
		
		
    	
    }
    
    private int getDirection(int pr,int pc,int cr,int cc)
    {
    	if (pr==cr) //left or right
    	{
    		if (pc>cc)
    			return 0;  //left
    		else
    			return 1; //right
    	}
    	else if (pc==cc) //bottom or top
    	{
    		if (pr>cr)
    		{
    			return 2; //bottom
    		}
    		else
    			return 3; //top
    	}
    	else
    		return -1; //invalid
    }
    
    public void refreshPaths()
    {
    	Scanner sc = null;
		try
		{
			sc = new Scanner(new FileInputStream(filename + "-out-with-turns.txt"));
		}
		catch (FileNotFoundException ef)
		{			

		}
			
		ArrayList<Integer> path = new ArrayList<Integer>();
		
		if (sc!=null)
		{
			int path_length = sc.nextInt();
			
			
			for (int i=0;i<2*path_length;i++)
			{
				int ed = sc.nextInt();
				path.add(ed);
			}
			sc.close();
			sc = null;
		}
		this.setPathWithTurns(path);
		
		//figure out the number of turns
		int numTurns = 0;
		int currDirection; //0 for left, 1 for right, 2 for down, 3 for up
		if (path.size()>2)
		{
			currDirection = getDirection(path.get(0),path.get(1),path.get(2),path.get(3));
			for (int i=2;(i+2)<path.size();i+=2)
			{
				int newdir = getDirection(path.get(i),path.get(i+1),path.get(i+2),path.get(i+3));
				if (currDirection != newdir)
					numTurns++;
				currDirection = newdir;
			}
		}
		System.out.println("-------------------");
		System.out.println("Optimized for turns: Length=" + path.size()/2 + " turns=" + numTurns);
		
		try
		{
			sc = new Scanner(new FileInputStream(filename + "-out-without-turns.txt"));
		}
		catch (FileNotFoundException f)
		{
		}
			
		path = new ArrayList<Integer>();
		
		if (sc!=null)
		{
			int path_length = sc.nextInt();			
			for (int i=0;i<2*path_length;i++)
			{
				int ed = sc.nextInt();
				path.add(ed);
			}
			sc.close();
			
		}
		this.setPathWithoutTurns(path);
		
		//figure out the number of turns
		numTurns = 0;
		
		if (path.size()>2)
		{
			currDirection = getDirection(path.get(0),path.get(1),path.get(2),path.get(3));
			for (int i=2;(i+2)<path.size();i+=2)
			{
				int newdir = getDirection(path.get(i),path.get(i+1),path.get(i+2),path.get(i+3));
				if (currDirection != newdir)
					numTurns++;
				currDirection = newdir;
			}
		}
		System.out.println("Unoptimized for turns: Length=" + path.size()/2 + " turns=" + numTurns);
		System.out.println("-------------------");
		
		vi =null;
		scroll.validate();
		label.invalidate();
		repaint();
	}

    public void mouseEntered(MouseEvent e) 
    {
    }

    public void mouseExited(MouseEvent e) 
    {
    }

    public void mouseClicked(MouseEvent e) 
    {
    }

    
    public static void main(String []args)
    {
		String filename = args[0];
		Scanner sc =  null;
		
//		System.out.println(System.getProperty("java.library.path"));
		

		
		try
		{
			sc = new Scanner(new FileInputStream(filename + ".txt"));
		}
		catch (FileNotFoundException e)
		{
		}
		MazeDraw frame = new MazeDraw();

		frame.setFilename(filename);

		if (sc!=null)
		{
			//first get the size of the maze
			int maze_rows = sc.nextInt();
			int maze_cols = sc.nextInt();
			
			frame.setDimensions(maze_rows,maze_cols);
			
			int no_of_edges=0;
			if (sc.hasNext())
				no_of_edges = sc.nextInt();

			ArrayList<Integer> edges = new ArrayList<Integer>();

			for (int i=0;i<4*no_of_edges;i++)
			{
				int e = sc.nextInt();
				edges.add(e);
			}

			frame.setEdges(edges);

			//pick the start and end vertex
			int srow,scol,erow,ecol;
			srow = sc.nextInt();
			scol = sc.nextInt();
			erow = sc.nextInt();
			ecol = sc.nextInt();

			frame.setStartEnd(srow,scol,erow,ecol);

		/*	int path_length = 0;
			
			sc.close();
			
			sc = null;
			try
			{
				sc = new Scanner(new FileInputStream(filename + "-out.txt"));
			}
			catch (FileNotFoundException e)
			{
			}
			
			ArrayList<Integer> path = new ArrayList<Integer>();
			
			if (sc!=null)
			{
				path_length = sc.nextInt();
				for (int i=0;i<2*path_length;i++)
				{
					int e = sc.nextInt();
					path.add(e);
				}
				sc.close();
				
			}
			frame.setPath(path);
		*/
			
			
			
			sc = null;	
			try
			{
				sc = new Scanner(new FileInputStream(filename + "-blocked.txt"));
			}
			catch (FileNotFoundException e)
			{
			}
			System.out.println("Reached here" );
			TreeSet<Integer> blocked = new TreeSet<Integer>();
			if (sc!=null)
			{
				int no_of_blocked = sc.nextInt();

				

				for (int i=0;i<no_of_blocked;i++)
				{
					int row = sc.nextInt();
					int col = sc.nextInt();
					blocked.add(row*frame.getRows()+col);
				}
				sc.close();				
			}
			frame.setBlocked(blocked);
			
		}

		System.loadLibrary("SolveMaze");
		SolveMaze c = new SolveMaze();
	
		c.findpath(filename);

		frame.refreshPaths();

		frame.setVisible(true);
		frame.repaint();
		System.out.println("Done loading");
	}

		
}	    

		
class SolveMaze
{
	public native boolean findpath(String mazefile);
}
