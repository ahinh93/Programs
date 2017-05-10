#include "Image.h"
#include <iostream>
#include <string>
#include <string.h>
#include <fstream>
#include <stdio.h>
#include <stdlib.h>
#include <cmath> 


using namespace std;
Image::Image()
{
	pixels = NULL;
	width = 0;
	height = 0;
}

Image::~Image()
{
	int i,j,k;
	for (i = 0; i < height; i++)
	{
		for (j = 0; j < width; j++)
		{
			
			delete[] pixels[i][j];
			pixels[i][j] = NULL;
		}
		delete[] pixels[i];
		pixels[i] = NULL;
	}
	delete[] pixels;
    pixels=NULL;
}

Image::Image(int w, int h)
{
	width = w;
	height = h;
	pixels = new float**[height];	
	int i, j, k;
	for (i = 0; i < height; i++)
	{
		pixels[i] = new float*[width];
		for (j = 0; j < width; j++)
		{
			pixels[i][j] = new float[3];
			for (k = 0; k < 3; k++)
			{
				pixels[i][j][k] = 0;
			}
		}
	}
}

Image::Image(const Image& I)
{
	int i, j, k;
	width = I.getWidth();
	height = I.getHeight();
	
	pixels = new float**[height];
	for (i = 0; i<height; i++)
	{
		pixels[i] = new float*[width];
		for (j = 0; j < width; j++)
		{
			pixels[i][j] = new float[3];
			for (k = 0; k<3; k++)
			{
				pixels[i][j][k] = I.pixels[i][j][k];
			}
		}
	}
}

int Image::getWidth() const
{
	return width;
}
	
int Image::getHeight() const
{
	return height;
}

float Image::getPixel(int row, int col, int color)const
{
	if (row < 0 || row > height - 1 || col < 0 || col > width - 1 || color < 0 || color > 2)
	{
		return -1;
	}
	
	return pixels[row][col][color];
}

void Image::setPixel(int row, int col, int color, float value)
{
	if (row >= 0 && row < height && col >= 0 && col < width && color >=0 && color < 3)
	{
		pixels[row][col][color] = value;
	}
}


