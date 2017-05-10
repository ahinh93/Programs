/**
 * Class Rectangle by Andrew Hinh
 * @author ahinh
 *
 */
public class Rectangle 
{

	private int height;
	private int width;
	private int x;
	private int y;
	
	public Rectangle(int x, int y, int width, int height)
	{
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() 
	{
		return ("x= " + this.x + ", y= " + this.y
		+ ", width= " + this.width + ", height= " + this.height);
	}

	public int getHeight() 
	{
		return this.height;
	}

	public int getWidth()
	{
		return this.width;
	}

	public int getX() 
	{
		return this.x;
	}

	public int getY() 
	{
		return this.y;
	}
	
	public boolean overlaps(Rectangle a)
	{
		//x's do not overlap at all, they don't intersect
		if(a.getX() > (this.x + this.getWidth())) {
			return false;
		}
		if(this.x > (a.getX() + a.getWidth())) {
			return false;
		}
		
		//if y's do not overlap at all, they don't intersect
		if(a.getY() > (this.y + this.getHeight())) {
			return false;
		}
		if(this.y > (a.getY() + a.getHeight())) {
			return false;
		}
		return true;
		
	}
	
	public Rectangle intersect(Rectangle a) 
	{
		//create temp to switch which is A, and which is THIS rectangle
		Rectangle temp = new Rectangle(x,y,width,height);
		
			
		if (a.overlaps(temp)) 
		{
			int tX, tY, tWidth, tHeight = 0;
			
			if (this.x > a.getX()) 
			{
				if ((this.y + this.height) < a.height) 
				{
					tX = this.x;
					tY = this.y;
					tWidth = this.x + this.width;
					tHeight = this.y + this.height;
					Rectangle xRect = new Rectangle(tX, tY, tWidth, tHeight);
					System.out.println(xRect.toString());
					return xRect;
				}
			tX = this.x;
			tY = this.y;
			tWidth = a.getWidth() - this.x;
			tHeight = a.getHeight() - this.y;
			Rectangle xRect = new Rectangle(tX, tY, tWidth, tHeight);
			System.out.println(xRect.toString());
			return xRect;
			}
			
			if (this.x < a.getX()) 
			{
				if ((this.y + this.height) < a.height) 
				{
					tX = this.x;
					tY = this.y;
					tWidth = this.x + this.width;
					tHeight = this.y + this.height;
					Rectangle xRect = new Rectangle(tX, tY, tWidth, tHeight);
					System.out.println(xRect.toString());
					return xRect;
				}
			tX = this.x;
			tY = this.y;
			tWidth = this.x - a.getWidth();
			tHeight = this.y - a.getHeight();
			Rectangle xRect = new Rectangle(tX, tY, tWidth, tHeight);
			System.out.println(xRect.toString());
			return xRect;
			}

		} else
			try {
				throw new InvalidRectangleIntersection("Rectangles do not intersect.");
			} 
			catch (InvalidRectangleIntersection e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		Rectangle fail = new Rectangle(0,0,0,0);
		return fail;
	}
	
	public static void main(String[] args) {
		//int x, int y, int width, int height
		Rectangle A = new Rectangle(0,0,5,5);
		Rectangle B = new Rectangle(10,10,0,0);
		
		if (A.overlaps(B)) 
		{
			System.out.println("true");
		}
		else
			System.out.println("false");
		
		//works if B.intersect(A) but not A.intersect(B)
		B.intersect(A);
	}
}
