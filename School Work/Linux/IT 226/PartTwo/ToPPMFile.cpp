#include "IOOperations.h"
#include <iostream>
#include <string>
#include <string.h>
#include <fstream>
#include <stdio.h>
#include <stdlib.h>

using namespace std;

bool ToPPMFile::doOperation(string filename, Image image)
{
	const char* cfilename;
	
	cfilename = filename.c_str();

	int height, width;
	height = image.getHeight();
	width = image.getWidth();

	ofstream outputFile(cfilename);
		
	if (outputFile.is_open())
	{
		outputFile << "P3" << endl;
		outputFile << width << " " << height << endl;
		outputFile << "255" << endl;
		int i, j;
		for (i = 0; i < height; i++)
		{
			for (j = 0; j < width; j++)
			{
				outputFile << (int)(image.getPixel(height-1-i,j,0)*255) << " " << (int)(image.getPixel(height-1-i,j,1)*255) << " " << (int)(image.getPixel(height-1-i,j,2)*255) << endl;
			}
		}
		outputFile.close();
		return true;
	}
	else
	{
		return false;
	}
}
