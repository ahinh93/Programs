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
		bool fromPPMFile(const string filename);
		bool toPPMFile(const string filename);
		bool flipVertical();
		void thresholdForDisplay();
		Image getEdges()const;
		Image blur();
};	
#endif
