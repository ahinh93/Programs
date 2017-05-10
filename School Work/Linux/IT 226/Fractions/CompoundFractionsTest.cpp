#include <iostream>
using namespace std;
#include "CompoundFraction.h"

int main()
{
  CompoundFraction a(2,1,3);
  CompoundFraction b(4,2,5);
  CompoundFraction c(2,1,5);
  
  cout<< "a:"<<a.getWhole()<<" "<<a.getNumerator()<<"/"<<a.getDenominator()<<endl;
  cout<< "b:"<<b.getWhole()<<" "<<b.getNumerator()<<"/"<<b.getDenominator()<<endl;
  cout<< "c:"<<c.getWhole()<<" "<<c.getNumerator()<<"/"<<c.getDenominator()<<endl; 

  CompoundFraction d = a.add(b);
  d = a+b; //this will also work because + is overloaded
  cout<< "a+b:"<<d.getWhole()<<" "<<d.getNumerator()<<"/"<<d.getDenominator()<<endl; 
  d = a.subtract(b);
  cout<< "a-b:"<<d.getWhole()<<" "<<d.getNumerator()<<"/"<<d.getDenominator()<<endl; 
  d = b.subtract(a);
  cout<< "b-a:"<<d.getWhole()<<" "<<d.getNumerator()<<"/"<<d.getDenominator()<<endl; 
  d = a.multiply(b);
  cout<< "a*b:"<<d.getWhole()<<" "<<d.getNumerator()<<"/"<<d.getDenominator()<<endl; 
  d = a.divide(b);
  cout<< "a/b:"<<d.getWhole()<<" "<<d.getNumerator()<<"/"<<d.getDenominator()<<endl; 
  
  int comparevalue = a.compare(a,c);
  
  if (comparevalue<0)
    cout<<"a<c"<<endl;
  else if (comparevalue==0)
    cout<<"a=c"<<endl;
  else 
    cout<<"a>c"<<endl;

  if (a<b)
    cout << "a<b"<< endl;
  
  CompoundFraction e(4,15,21);
  
  cout<< "e:"<<e.getWhole()<<" "<<e.getNumerator()<<"/"<<e.getDenominator()<<endl; 
  
  e.reduce();
  
  cout<< "After reduction e:"<<e.getWhole()<<" "<<e.getNumerator()<<"/"<<e.getDenominator()<<endl; 

}
