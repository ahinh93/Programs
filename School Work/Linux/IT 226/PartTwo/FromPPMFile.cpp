#include "IOOperations.h"
#include <iostream>
#include <string>
#include <string.h>
#include <fstream>
#include <stdio.h>
#include <stdlib.h>


Image FromPPMFile::doOperation(string filename)
{
	int width = 0;
	int height = 0;
	const char* cfilename;
	bool flag = true;
	
	cfilename = filename.c_str();

	string line;
	ifstream fin(cfilename, ios::in);
	if (fin.is_open())
	{
	
		if (fin >> line)
		{
			if (line.compare("P3") != 0)
			{
				
				fin.close();
				flag = false;
			}
		}
		if(flag)
		{
			if (fin >> line)
			{
				width = atoi(line.c_str());
			}
			if (fin >> line)
			{
				height = atoi(line.c_str());
			}

			int factor;
			if (fin >> line)
			{
				factor = atoi(line.c_str());
			}

			int i, j, k;
			Image myImage(width,height);
			for (i = 0; i<height; i++)
			{
				for (j = 0; j < width; j++)
				{
					float red, green, blue;
					int redInt, greenInt, blueInt;
	
					fin >> redInt;
					red = (float)redInt/factor;

					fin >> greenInt;
					green = (float)greenInt/factor;

					fin >> blueInt;
					blue = (float)blueInt/factor;
			
					myImage.setPixel(height-1-i, j, 0, red);
					myImage.setPixel(height-1-i, j, 1, green);
					myImage.setPixel(height-1-i, j, 2, blue);
				}
			}
			fin.close();

			return myImage;
		}
	}
}
	
