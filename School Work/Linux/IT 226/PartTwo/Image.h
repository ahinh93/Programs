#ifndef IMAGE_H
#define IMAGE_H
#include <string>
using namespace std;

class Image 
{
	private:
		float***pixels;
		int width;
		int height;
		
	public:
		//constructors
		Image();
		~Image();
		Image(int w, int h);
		Image(const Image& i);
		
		//methods
		int getWidth()const;
		int getHeight()const;
		float getPixel(int row, int col, int color) const;
		void setPixel(int row, int col, int color, float value);
};	
#endif
