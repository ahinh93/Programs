#ifndef IOOPERATIONS_H
#define	IOOPERATIONS_H
#include "Image.h"


class ToPPMFile
{
	public:
		bool doOperation(const string filename, Image image);
};

class FromPPMFile
{
	public:
		Image doOperation(const string filename);
};	

#endif	
