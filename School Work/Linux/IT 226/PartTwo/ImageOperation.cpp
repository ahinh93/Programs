#include "ImageOperation.h"
#include "Blur.h"
#include "GetEdges.h"
#include "FlipVertical.h"
#include <string>
using namespace std;

ImageOperation* ImageOperation::makeOperation(string operName)
{
	if(operName.compare("blur")==0)
		return new Blur;
	else if (operName.compare("getedges")==0)
		return new GetEdges;
	else if (operName.compare("flipvertical")==0)
		return new FlipVertical;

}

