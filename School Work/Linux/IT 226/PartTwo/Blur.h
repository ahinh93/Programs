#ifndef BLUR_H
#define BLUR_H

class Blur : public ImageOperation
{
	public:
		Image doOperation(Image image);
};
#endif
