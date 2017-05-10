#include "ImageOperation.h"
#include "GetEdges.h"
#include <cmath>
#include <iostream>

Image GetEdges::doOperation(Image image)
{
	float filterX[3][3] = {{-1,0,1},{-2,0,2},{-1,0,1}};
	float filterY[3][3] = {{1,2,1},{0,0,0},{-1,-2,-1}};
	Image edgeimage(image);
	int i,j,k,m,n;
	float sumX,sumY,mag;
    
	for (k=0;k<3;k++)
	{
		for (i=1;i<image.getHeight()-1;i++)
		{
			for (j=1;j<image.getWidth()-1;j++)
			{
				sumX = sumY = 0.0f;
				for (m=0;m<3;m++)
				{
					for (n=0;n<3;n++)
					{
						sumX = sumX + filterX[m][n]*image.getPixel(i+(m-1),j+(n-1),k);
						sumY = sumY + filterY[m][n]*image.getPixel(i+(m-1),j+(n-1),k);
						
					}
				}
				edgeimage.setPixel(i, j, k, abs(sumX+sumY));
				
			}
		}
	}
	
	for (i=0;i<edgeimage.getHeight();i++)
	{
		for (j=0;j<edgeimage.getWidth();j++)
		{
			mag = 0.0f;
			for (k=0;k<3;k++)
			{
				mag += edgeimage.getPixel(i,j,k)*edgeimage.getPixel(i,j,k);
			}
			mag = (float)sqrt(mag);
			
			for (k=0;k<3;k++)
			{
				edgeimage.setPixel(i,j,k,mag);

			}
		}
	}
	GetEdges::thresholdForDisplay(edgeimage);
	return edgeimage;
}
 void GetEdges::thresholdForDisplay(Image& image)
{

	int i,j,k;
	float min,max;
		
	for (k=0;k<3;k++) //for each channel
	{			
		min = max = image.getPixel(0,0,k);
		for (i=0;i<image.getHeight();i++)
		{
			for (j=0;j<image.getWidth();j++)
			{
				if (image.getPixel(i,j,k)>max)
					max = image.getPixel(i,j,k);
				
				if (image.getPixel(i,j,k)<min)
					min = image.getPixel(i,j,k);
			}
		}			
		for (i=0;i<image.getHeight();i++)
		{
			for (j=0;j<image.getWidth();j++)
			{
				image.setPixel(i,j,k,(image.getPixel(i, j,k)-min)/(max-min));
			}
		}
			
	}
}