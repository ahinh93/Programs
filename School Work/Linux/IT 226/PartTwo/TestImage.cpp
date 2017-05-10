#include <iostream>
#include "Image.h"
#include "ImageOperation.h"
#include "IOOperations.h"

using namespace std;


int main()
{
	FromPPMFile fin;
	Image image = fin.doOperation("earthmap.ppm");

	ImageOperation * b=ImageOperation::makeOperation("blur");
        ImageOperation * fv=ImageOperation::makeOperation("flipvertical");
	ImageOperation * e=ImageOperation::makeOperation("getedges");
	

	Image newImage = fv->doOperation(b->doOperation(image));
	Image eImage = e->doOperation(image);
	Image vertImage = fv->doOperation(image);
	Image veryBlurredImage = b->doOperation(b->doOperation(b->doOperation(b->doOperation(b->doOperation(image)))));



	ToPPMFile fout;
	fout.doOperation("TestToPPMFile.ppm",image);
	fout.doOperation("Edges.ppm",eImage);
	fout.doOperation("Vertical.ppm", vertImage);
	fout.doOperation("fvb.ppm", newImage);
	fout.doOperation("vbi.ppm", veryBlurredImage);

	delete b;
	delete fv;
	delete e;

	b=NULL;
	fv=NULL;
	e=NULL;


}
