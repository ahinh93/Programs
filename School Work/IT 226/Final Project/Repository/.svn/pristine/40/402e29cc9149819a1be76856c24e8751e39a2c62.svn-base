import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;


/**
 * This class represents a colored image. This is represented using a 3D array of widthxheightx3. Every pixel uses
 * three colors (red, green, blue) to represent the color at that pixel
 * @author ashesh
 *
 */

public class Image 
{
	private float [][][]pixels;
	private int width;
	private int height;
	
	public Image()
	{
		pixels = null;
		width = 0;
		height = 0;
	}
	
	/**
	 * Create a blank black image
	 * @param w
	 * @param h
	 */
	public Image(int w,int h)
	{
		width = w;
		height = h;
		pixels = new float[height][width][3];
		for (int i=0;i<height;i++)
		{
			for (int j=0;j<width;j++)
			{
				for (int k=0;k<3;k++)
				{
					pixels[i][j][k] = 0;
				}
			}
		}
	}
	
	/**
	 * The copy constructor
	 * @param I
	 */
	public Image(Image I)
	{
		int i,j,k;
		
		width = I.width;
		height = I.height;
		pixels = new float[height][width][3];
		for (i=0;i<height;i++)
		{
			for (j=0;j<width;j++)
			{
				for (k=0;k<3;k++)
					pixels[i][j][k] = I.pixels[i][j][k];
			}
		}
	}
	
	/**
	 * The getters for width and height of the image
	 * @return
	 */
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	
	/**
	 * This method reads an image using the text-based PPM file format. This file format is supported by 
	 * many image processing applications like Photoshop/Gimp etc. Since it is a simple text format, it is very easy to 
	 * read and write PPM files from within a program.
	 * @param filename
	 * @return
	 */
	
	public boolean fromPPMFile(String filename)
	{
		Scanner file;
		String s;
		int i,j;
		
		try
		{
			file = new Scanner(new FileInputStream(filename));
		}
		catch (FileNotFoundException e)
		{
			return false;
		}
		
		//read in the first string, "P3"
		
		s = file.next();
		
		if (s.compareTo("P3")!=0)
		{
			file.close();
			return false;
		}
		
		//next read in the width and height of the image
		width = file.nextInt();
		height = file.nextInt();
		
		//allocate the pixel array accordingly
		pixels = new float[height][width][3];
		
		//now read in the factor which is 255 most of the time
		file.nextInt();
		
		//now read in the actual image data. Remember that the image has to be flipped
		
		for (i=0;i<height;i++)
		{
			for (j=0;j<width;j++)
			{
				pixels[height-1-i][j][0] = (float)file.nextInt()/255;
				pixels[height-1-i][j][1] = (float)file.nextInt()/255;
				pixels[height-1-i][j][2] = (float)file.nextInt()/255;
			}
		}
		file.close();
		
		return true;
	}
	
	/**
	 * This method writes the current Image object in the text PPM file format.
	 * @param filename
	 * @return
	 */
	public boolean toPPMFile(String filename)
	{
		PrintWriter file;
		int i,j;
		
		try
		{
			file = new PrintWriter(new FileOutputStream(filename));			
		}
		catch (FileNotFoundException e)
		{
			return false;
		}
		
		//write the first string, "P3"
		
		file.println("P3");
		
		
		//next write the width and height of the image
		file.println(width + " " + height);
		
		//now write the factor which is 255 most of the time
		file.println("255");
		
		//now write the actual image data. Remember that the image has to be flipped
		
		for (i=0;i<height;i++)
		{
			for (j=0;j<width;j++)
			{
				file.println((int)(pixels[height-1-i][j][0]*255) + " " + (int)(pixels[height-1-i][j][1]*255) + " " + (int)(pixels[height-1-i][j][2]*255));
			}
		}
		file.close();
		
		return true;
	}
	
	/**
	 * This method flips the current image vertically
	 * @return
	 */
	public boolean flipVertical()
	{
		int i,j;
		float r,g,b;
		
		if ((width<=0) || (height<=0))
			return false;
		
		for (i=0;i<height/2;i++)
		{
			for (j=0;j<width;j++)
			{
				r = pixels[i][j][0];
				g = pixels[i][j][1];
				b = pixels[i][j][2];
				
				pixels[i][j][0] = pixels[height-1-i][j][0];
				pixels[i][j][1] = pixels[height-1-i][j][1];
				pixels[i][j][2] = pixels[height-1-i][j][2];
				
				pixels[height-1-i][j][0] = r;
				pixels[height-1-i][j][1] = g;
				pixels[height-1-i][j][2] = b;				
				
			}
		}
		return true;
	}
	
	/**
	 * This method reorganizes the numbers in an image so that they are between 0 and 1. Images can violate this 
	 * constraint as the result of an image processing operation. This method makes the image "displayable" again.
	 */
	
	public void thresholdForDisplay()
	{
		//this method brings all values to 0-255
		int i,j,k;
		float min,max;
		
		for (k=0;k<3;k++) //for each channel
		{
			min = max = pixels[0][0][k];
			for (i=0;i<height;i++)
			{
				for (j=0;j<width;j++)
				{
					if (pixels[i][j][k]>max)
						max = pixels[i][j][k];
					
					if (pixels[i][j][k]<min)
						min = pixels[i][j][k];
				}
			}			
			for (i=0;i<height;i++)
			{
				for (j=0;j<width;j++)
				{
					pixels[i][j][k] = (pixels[i][j][k]-min)/(max-min);
				}
			}
			
		}
		
	}
	

	/**
	 * This method returns an image that is obtained by starting with the current image and tracing all its edges.
	 * It uses the Sobel Edge detection algorithm to do this. The resulting image is B/W even if the source image is color	
	 * @return
	 */
	
	public Image getEdges()
	{
		//use the sobel edge detection filter
		float [][]filterX = {{-1,0,1},{-2,0,2},{-1,0,1}};
		float [][]filterY = {{1,2,1},{0,0,0},{-1,-2,-1}};
		Image edgeimage = new Image(this);
		int i,j,k,m,n;
		float sumX,sumY,mag;
		
		for (k=0;k<3;k++)
		{
			for (i=1;i<height-1;i++)
			{
				for (j=1;j<width-1;j++)
				{
					sumX = sumY = 0.0f;
					for (m=0;m<3;m++)
					{
						for (n=0;n<3;n++)
						{
							sumX = sumX + filterX[m][n]*pixels[i+(m-1)][j+(n-1)][k];
							sumY = sumY + filterY[m][n]*pixels[i+(m-1)][j+(n-1)][k];
						}
					}
					edgeimage.pixels[i][j][k] = Math.abs(sumX+sumY);
				}
			}
		}
		
		//now make the image B/W
		
		for (i=0;i<height;i++)
		{
			for (j=0;j<width;j++)
			{
				mag = 0.0f;
				for (k=0;k<3;k++)
					mag += edgeimage.pixels[i][j][k]*edgeimage.pixels[i][j][k];
				mag = (float)Math.sqrt(mag);
				for (k=0;k<3;k++)
					edgeimage.pixels[i][j][k] = mag;
			}
		}
		edgeimage.thresholdForDisplay();
		return edgeimage;
	}
	


	public static void main(String []args)
	{
		args = new String[] {"earthmap.ppm"};
		Image image = new Image();
		
		image.fromPPMFile(args[0]);
		
		Image edgeImage = image.getEdges();
		
		image.flipVertical();
		
		edgeImage.toPPMFile("edges.ppm");
		image.toPPMFile("flipped.ppm");
		
	}
	
}
