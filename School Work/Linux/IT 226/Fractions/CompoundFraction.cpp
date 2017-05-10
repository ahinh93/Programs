#include "CompoundFraction.h"
#include <iostream>
using namespace std;


CompoundFraction::CompoundFraction(int a,int b,int c)
{
  whole = a;
  num = b;
  denom = c;
}

CompoundFraction::~CompoundFraction()
{

}

int CompoundFraction::getWhole()
{
  return whole;
}

int CompoundFraction::getNumerator()
{
  return num;
}

int CompoundFraction::getDenominator()
{
  return denom;
}
		
CompoundFraction CompoundFraction::add(const CompoundFraction& a) const
{
  int num1,denom1,num2,denom2,numresult,denomresult;
  
  num1 = denom*whole + num;
  denom1 = denom;
  
  num2 = a.denom*a.whole + a.num;
  denom2 = a.denom;
  
  numresult = num1*denom2 + num2*denom1;
  denomresult = denom1*denom2;
  
  return CompoundFraction(numresult/denomresult,numresult%denomresult,denomresult);
}

const CompoundFraction CompoundFraction::operator +(const CompoundFraction& a) const
{
int num1,denom1,num2,denom2,numresult,denomresult;
  
  num1 = denom*whole + num;
  denom1 = denom;
  
  num2 = a.denom*a.whole + a.num;
  denom2 = a.denom;
  
  numresult = num1*denom2 + num2*denom1;
  denomresult = denom1*denom2;
  
  return CompoundFraction(numresult/denomresult,numresult%denomresult,denomresult);
}
	
CompoundFraction CompoundFraction::subtract(CompoundFraction a)
{
  int num1,denom1,num2,denom2,numresult,denomresult;
  int w,n,d;
  
  num1 = denom*whole + num;
  denom1 = denom;
  
  num2 = a.denom*a.whole + a.num;
  denom2 = a.denom;
  
  numresult = num1*denom2 - num2*denom1;
  denomresult = denom1*denom2;
  
  w = numresult/denomresult;
  d = denomresult;
  if (numresult<0)
    n = -numresult % denomresult;
  else
    n = numresult % denomresult;
  
  return CompoundFraction(w,n,d);
}

CompoundFraction CompoundFraction::multiply(CompoundFraction a)
{
  int num1,denom1,num2,denom2,numresult,denomresult;
  int w,n,d;
  
  num1 = denom*whole + num;
  denom1 = denom;
  
  num2 = a.denom*a.whole + a.num;
  denom2 = a.denom;
  
  numresult = num1*num2;
  denomresult = denom1*denom2;
  
  w = numresult/denomresult;
  d = denomresult;
  if (numresult<0)
    n = -numresult % denomresult;
  else
    n = numresult % denomresult;
  
  return CompoundFraction(w,n,d);
}

CompoundFraction CompoundFraction::divide(CompoundFraction a)
{
  int num1,denom1,num2,denom2,numresult,denomresult;
  int w,n,d;
  
  num1 = denom*whole + num;
  denom1 = denom;
  
  num2 = a.denom*a.whole + a.num;
  denom2 = a.denom;
  
  numresult = num1*denom2;
  denomresult = denom1*num2;
  
  w = numresult/denomresult;
  d = denomresult;
  if (numresult<0)
    n = -numresult % denomresult;
  else
    n = numresult % denomresult;
  
  return CompoundFraction(w,n,d);
}

int CompoundFraction::compare(CompoundFraction a,CompoundFraction b)
{
  int num1,denom1,num2,denom2;
  
  num1 = denom*whole + num;
  denom1 = denom;
  
  num2 = a.denom*a.whole + a.num;
  denom2 = a.denom;
  
  if ((num1*denom2)<(num2*denom1))
  {
    return -1;
  }
  
  if ((num1*denom2)<(num2*denom1))
  {
    return 0;
  }
  
  return 1;
}

bool CompoundFraction::operator <(const CompoundFraction& b) const
{
   int num1,denom1,num2,denom2;
  
  num1 = denom*whole + num;
  denom1 = denom;
  
  num2 = b.denom*b.whole + b.num;
  denom2 = b.denom;
  
  if ((num1*denom2)<(num2*denom1))
  {
    return true;
  }
  
  return false;
}
	
int CompoundFraction::gcd(int num1,int num2)
{
  int a,b,r;
  
  if (num1<num2)
  {
    a = num2;
    b = num1;
  }
  else
  {
    a = num1;
    b = num2;
  }
  
  if (b==1)
    return 1;
  
  r = a%b;
  while (r>0)
  {
    a = b;
    b = r;
    r = a%b;
  }
	    
  return b;
}

void CompoundFraction::reduce()
{
  int divisor;
  
  divisor = gcd(num,denom);
  
  num = num/divisor;
  denom = denom/divisor;
}
