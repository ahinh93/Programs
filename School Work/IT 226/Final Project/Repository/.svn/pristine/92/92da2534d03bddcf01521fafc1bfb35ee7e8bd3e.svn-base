#include <iostream>
#include "Image.h"
using namespace std;


int main()
{



Image image(512, 1024);

image.fromPPMFile("earthmap.ppm");
image.toPPMFile("testToPPM.ppm");

Image i = image.getEdges();
Image i2 = image.blur();

image.flipVertical();
image.toPPMFile("Vertical.ppm");

i.toPPMFile("edges.ppm");
i2.toPPMFile("blur.ppm");


}
