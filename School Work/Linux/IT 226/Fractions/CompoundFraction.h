#ifndef _COMPOUNDFRACTION_H_
#define _COMPOUNDFRACTION_H_

class CompoundFraction
{
 public:
  CompoundFraction(int a,int b,int c);
  ~CompoundFraction();

  CompoundFraction add(CompoundFraction a);
  const CompoundFraction operator +(const CompoundFraction& b) const;
  bool operator <(const CompoundFraction& b) const;
  CompoundFraction subtract(CompoundFraction a);
  CompoundFraction multiply(CompoundFraction a);
  CompoundFraction divide(CompoundFraction a);
  int compare(CompoundFraction a,CompoundFraction b);
  void reduce();
  int getWhole();
  inline int getNumerator();
  int getDenominator();
 private: 
  int gcd(int num1,int num2);

 private:
  int whole,num,denom;
};

#endif
