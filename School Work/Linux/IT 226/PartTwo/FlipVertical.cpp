#include "ImageOperation.h"
#include "FlipVertical.h"

Image FlipVertical::doOperation(Image image)
{
	int i,j;
	float r,g,b;
	Image newImage(image);
	if((newImage.getWidth()<=0)||(newImage.getHeight()<=0))
		return newImage;
	for(i=0;i<newImage.getHeight()/2;i++)
	{
		for(j=0;j<newImage.getWidth();j++)
		{
			r = newImage.getPixel(i, j, 0);
			g = newImage.getPixel(i, j, 1);
			b = newImage.getPixel(i, j, 2);
			
			newImage.setPixel(i, j, 0, newImage.getPixel(newImage.getHeight()-1-i,j,0));
			newImage.setPixel(i, j, 1, newImage.getPixel(newImage.getHeight()-1-i,j,1));
			newImage.setPixel(i, j, 2, newImage.getPixel(newImage.getHeight()-1-i,j,2));
			
			newImage.setPixel(newImage.getHeight()-1-i, j, 0, r);
			newImage.setPixel(newImage.getHeight()-1-i, j, 1, g);
			newImage.setPixel(newImage.getHeight()-1-i, j, 2, b);
						
		}
	}
	return newImage;
}
