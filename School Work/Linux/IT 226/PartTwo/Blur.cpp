#include "ImageOperation.h"
#include "Blur.h"


Image Blur::doOperation(Image image)
{
	int i,j;
	float 	topLeftRed, topLeftGreen, topLeftBlue, 
		topCenterRed, topCenterGreen, topCenterBlue,
		topRightRed, topRightGreen, topRightBlue,
		middleLeftRed, middleLeftGreen, middleLeftBlue,
		middleCenterRed, middleCenterGreen, middleCenterBlue,
		middleRightRed, middleRightGreen, middleRightBlue,
		bottomLeftRed, bottomLeftGreen, bottomLeftBlue,
		bottomCenterRed, bottomCenterGreen, bottomCenterBlue,
		bottomRightRed, bottomRightGreen, bottomRightBlue;
	
	int width = image.getWidth();
	int height = image.getHeight();
	Image newImage(width, height);
	for(i = 0; i < height; i++)
	{
		for(j = 0; j < width; j++)
		{
			//pixel is in the top row
			if(i==0)
			{
				
				//3 pixels above = 0
				topLeftRed = topLeftGreen = topLeftBlue = 
				topCenterRed = topCenterGreen = topCenterBlue =
				topRightRed = topRightGreen= topRightBlue =0;
				
				
				//pixel is in the top left corner
				if(j == 0)
				{
					
					//all pixels to the left = 0
					middleLeftRed = middleLeftGreen = middleLeftBlue=
					bottomLeftRed = bottomLeftGreen = bottomLeftBlue = 0;
				}
				else
				{
					middleLeftRed = image.getPixel(i,j-1,0);
					middleLeftGreen = image.getPixel(i,j-1,1);
					middleLeftBlue = image.getPixel(i,j-1,2);
					if (i+1 < height)
					{
						bottomLeftRed = image.getPixel(i+1,j-1,0);
						bottomLeftGreen = image.getPixel(i+1,j-1,1);
						bottomLeftBlue = image.getPixel(i+1,j-1,2);
					}
					else
					{
						bottomLeftRed = bottomLeftGreen = bottomLeftBlue = 0;
					}
				}
				
				
				//double check that j+1 exists
				if (j + 1 < width)
				{
					middleRightRed = image.getPixel(i,j+1,0);
					middleRightGreen = image.getPixel(i,j+1,1);
					middleRightBlue = image.getPixel(i,j+1,2);
				}
				else
				{
					middleRightRed = middleRightGreen = middleRightBlue = 0;
				}
				if (i+1 < height)
				{
					bottomCenterRed = image.getPixel(i+1,j,0);
					bottomCenterGreen = image.getPixel(i+1,j,1);
					bottomCenterBlue = image.getPixel(i+1,j,2);
				}
				else
				{
					bottomCenterRed = bottomCenterGreen = bottomCenterBlue = 0;
				}
				if (j+1 < width && i+1 < height)
				{
					bottomRightRed = image.getPixel(i+1,j+1,0);
					bottomRightGreen = image.getPixel(i+1,j+1,1);
					bottomRightBlue = image.getPixel(i+1,j+1,2);
				}
				else
				{
					bottomRightRed = bottomRightGreen = bottomRightBlue = 0;
				}
			}
					
			//bottom row
			else if(i==height-1)
			{
				//3 pixels below = 0
				bottomLeftRed = bottomLeftGreen = bottomLeftBlue = 
				bottomCenterRed = bottomCenterGreen = bottomCenterBlue =
				bottomRightRed = bottomRightGreen= bottomRightBlue =0;
				
				
				//pixel is in the bottom left corner
				if(j == 0)
				{
					//all pixels to the left = 0
					middleLeftRed = middleLeftGreen = middleLeftBlue=
					topLeftRed = topLeftGreen = topLeftBlue = 0;
				}
				else
				{
					middleLeftRed = image.getPixel(i,j-1,0);
					middleLeftGreen = image.getPixel(i,j-1,1);
					middleLeftBlue = image.getPixel(i,j-1,2);
					//check to make sure that row above exists
					if (i-1 > -1)
					{
						topLeftRed = image.getPixel(i-1,j-1,0);
						topLeftGreen = image.getPixel(i-1,j-1,1);
						topLeftBlue = image.getPixel(i-1,j-1,2);
					}
					else
					{
						topLeftRed = topLeftGreen = topLeftBlue = 0;
					}
				}
				
				
				//double check that j+1 exists
				if (j + 1 < width)
				{
					middleRightRed = image.getPixel(i,j+1,0);
					middleRightGreen = image.getPixel(i,j+1,1);
					middleRightBlue = image.getPixel(i,j+1,2);
				}
				else
				{
					middleRightRed = middleRightGreen = middleRightBlue = 0;
				}
				if (i-1 < -1)
				{
					topCenterRed = image.getPixel(i-1,j,0);
					topCenterGreen = image.getPixel(i-1,j,1);
					topCenterBlue = image.getPixel(i-1,j,2);
				}
				else
				{
					topCenterRed = topCenterGreen = topCenterBlue = 0;
				}
				if (j+1 < width && i-1 < -1)
				{
					topRightRed = image.getPixel(i-1,j+1,0);
					topRightGreen = image.getPixel(i-1,j+1,1);
					topRightBlue = image.getPixel(i-1,j+1,2);
				}
				else
				{
					topRightRed = topRightGreen = topRightBlue = 0;
				}
			}
			
			//left column
			else if(j==0)
			{
				//3 pixels left = 0
				topLeftRed = topLeftGreen = topLeftBlue = 
				middleLeftRed = middleLeftGreen = middleLeftBlue =
				bottomLeftRed = bottomLeftGreen= bottomLeftBlue =0;
				
				
				//pixel is in the top left corner
				if(i == 0)
				{
					//all pixels above = 0
					topCenterRed = topCenterGreen = topCenterBlue=
					topRightRed = topRightGreen = topRightBlue = 0;
				}
				else
				{
					topCenterRed = image.getPixel(i-1,j,0);
					topCenterGreen = image.getPixel(i-1,j,1);
					topCenterBlue = image.getPixel(i-1,j,2);
					if (j+1 < width)
					{
						topRightRed = image.getPixel(i-1,j+1,0);
						topRightGreen = image.getPixel(i-1,j+1,1);
						topRightBlue = image.getPixel(i-1,j+1,2);
					}
					else
					{
						topRightRed = topRightGreen = topRightBlue = 0;
					}
				}
				
				
				//double check that i+1 exists
				if (i + 1 < height)
				{
					bottomCenterRed = image.getPixel(i+1,j,0);
					bottomCenterGreen = image.getPixel(i+1,j,1);
					bottomCenterBlue = image.getPixel(i+1,j,2);
				}
				else
				{
					bottomCenterRed = bottomCenterGreen = bottomCenterBlue = 0;
				}
				if (j+1 < width)
				{
					middleRightRed = image.getPixel(i,j+1,0);
					middleRightGreen = image.getPixel(i,j+1,1);
					middleRightBlue = image.getPixel(i,j+1,2);
				}
				else
				{
					middleRightRed = middleRightGreen = middleRightBlue = 0;
				}
				if (j+1 < width && i+1 < height)
				{
					bottomRightRed = image.getPixel(i+1,j+1,0);
					bottomRightGreen = image.getPixel(i+1,j+1,1);
					bottomRightBlue = image.getPixel(i+1,j+1,2);
				}
				else
				{
					bottomRightRed = bottomRightGreen = bottomRightBlue = 0;
				}
			}
			
			//left column
			else if(j == width - 1)
			{
				//3 pixels right = 0
				topRightRed = topRightGreen = topRightBlue = 
				middleRightRed = middleRightGreen = middleRightBlue =
				bottomRightRed = bottomRightGreen= bottomRightBlue =0;
				
				
				//pixel is in the top right corner
				if(i == 0)
				{
					//all pixels above = 0
					topCenterRed = topCenterGreen = topCenterBlue=
					topRightRed = topRightGreen = topRightBlue = 0;
				}
				else
				{
					topCenterRed = image.getPixel(i-1,j,0);
					topCenterGreen = image.getPixel(i-1,j,1);
					topCenterBlue = image.getPixel(i-1,j,2);
					if (j-1 > -1)
					{
						topLeftRed = image.getPixel(i-1,j-1,0);
						topLeftGreen = image.getPixel(i-1,j-1,1);
						topLeftBlue = image.getPixel(i-1,j-1,2);
					}
					else
					{
						topLeftRed = topLeftGreen = topLeftBlue = 0;
					}
				}
				
				
				//double check that i+1 exists
				if (i + 1 < height)
				{
					bottomCenterRed = image.getPixel(i+1,j,0);
					bottomCenterGreen = image.getPixel(i+1,j,1);
					bottomCenterBlue = image.getPixel(i+1,j,2);
				}
				else
				{
					bottomCenterRed = bottomCenterGreen = bottomCenterBlue = 0;
				}
				if (j-1 > -1)
				{
					middleLeftRed = image.getPixel(i,j-1,0);
					middleLeftGreen = image.getPixel(i,j-1,1);
					middleLeftBlue = image.getPixel(i,j-1,2);
				}
				else
				{
					middleLeftRed = middleLeftGreen = middleLeftBlue = 0;
				}
				if (j-1 > -1 && i+1 < height)
				{
					bottomLeftRed = image.getPixel(i+1,j-1,0);
					bottomLeftGreen = image.getPixel(i+1,j-1,1);
					bottomLeftBlue = image.getPixel(i+1,j-1,2);
				}
				else
				{
					bottomLeftRed = bottomLeftGreen = bottomLeftBlue = 0;
				}
			}
			else
			{
				topLeftRed = image.getPixel(i-1,j-1,0);
				topLeftGreen=image.getPixel(i-1,j-1,1);
				topLeftBlue=image.getPixel(i-1,j-1,2);
				
				topCenterRed=image.getPixel(i-1,j,0);
				topCenterGreen=image.getPixel(i-1,j,1);
				topCenterBlue=image.getPixel(i-1,j,2);
				
				topRightRed=image.getPixel(i-1,j+1,0);
				topRightGreen=image.getPixel(i-1,j+1,1);
				topRightBlue=image.getPixel(i-1,j+1,2);
				
				middleLeftRed=image.getPixel(i,j-1,0);
				middleLeftGreen=image.getPixel(i,j-1,1);
				middleLeftBlue=image.getPixel(i,j-1,2);
				
				middleRightRed=image.getPixel(i,j+1,0);
				middleRightGreen=image.getPixel(i,j+1,1);
				middleRightBlue=image.getPixel(i,j+1,2);
				
				bottomLeftRed=image.getPixel(i+1,j-1,0);
				bottomLeftGreen=image.getPixel(i+1,j-1,1);
				bottomLeftBlue=image.getPixel(i+1,j-1,2);
				
				bottomCenterRed=image.getPixel(i+1,j,0);
				bottomCenterGreen=image.getPixel(i+1,j,1);
				bottomCenterBlue=image.getPixel(i+1,j,2);
				
				bottomRightRed=image.getPixel(i+1,j+1,0);
				bottomRightGreen=image.getPixel(i+1,j+1,1);
				bottomRightBlue =image.getPixel(i+1,j+1,2);
			}
			
			middleCenterRed=image.getPixel(i,j,0);
			middleCenterGreen=image.getPixel(i,j,1);
			middleCenterBlue=image.getPixel(i,j,2);

			newImage.setPixel(i,j,0,(.0625*topLeftRed)+(.125*topCenterRed)+(.0625*topRightRed)+
					(.125*middleLeftRed)+(.25*middleCenterRed)+(.125*middleRightRed)+
					(.0625*bottomLeftRed)+(.125*bottomCenterRed)+(.0625*bottomRightRed));
		
			newImage.setPixel(i,j,1,(.0625*topLeftGreen)+(.125*topCenterGreen)+(.0625*topRightGreen)+
					(.125*middleLeftGreen)+(.25*middleCenterGreen)+(.125*middleRightGreen)+
					(.0625*bottomLeftGreen)+(.125*bottomCenterGreen)+(.0625*bottomRightGreen));
		
			newImage.setPixel(i,j,2,(.0625*topLeftBlue)+(.125*topCenterBlue)+(.0625*topRightBlue)+
					(.125*middleLeftBlue)+(.25*middleCenterBlue)+(.125*middleRightBlue)+
					(.0625*bottomLeftBlue)+(.125*bottomCenterBlue)+(.0625*bottomRightBlue));



		}
		
	}

	return newImage;

}
