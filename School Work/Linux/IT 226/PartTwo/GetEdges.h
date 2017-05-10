#ifndef GETEDGES_H
#define GETEDGES_H

class GetEdges : public ImageOperation
{

		
	public:
		Image doOperation(Image image);
		static void thresholdForDisplay(Image& image);
};
#endif
