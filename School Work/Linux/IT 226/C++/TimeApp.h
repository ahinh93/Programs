#ifndef TIMEAPP_H
#define TIMEAPP_H

class Time
{

	public:
	Time(); //default constructor that sets to midnight
	Time(int hours, int minutes, bool firstHalfOfDay);//constructor that takes: hours, minutes, & boolean
	Time(int militaryTime);//constructor that takes military time notation
	~Time();//destructor

	Time add(Time newTime);
	int getHours()const;
	int getMinutes()const;
	bool getFirstHalfOfDay()const;

	private:
	int hours;
	int minutes;
	bool firstHalfOfDay; //true = AM, false = PM
	
};

#endif
