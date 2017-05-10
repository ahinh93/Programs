#include "TimeApp.h"
#include <iostream>
using namespace std;

Time::Time()
{
	//set time to midnight
	hours = 0;
	minutes = 0;
	firstHalfOfDay = true;
}

Time::Time(int hours, int minutes, bool firstHalfOfDay)
{
	bool isValidEntry = true;
	//carry over minutes into hours
	if(minutes>=60)
	{
		if (minutes >=100)
		{
			isValidEntry = false;
			cout << "Invalid number of minutes!" + endl;
		}
		
		hours = hours+1;
		minutes = minutes - 60;
	}

	if(hours > 0 && hours < 13)
	{
		
		this->hours = hours;
		this->minutes = minutes;
		this->firstHalfOfDay = firstHalfOfDay;
	}
	else
	{
		isValidEntry = false;
		cout << "Incorrect hours format!" + endl;
	}
	if(isValidEntry == false)//if either entry is invalid, set to midnight.
	{
		hours = 0;
		minutes = 0;
		firstHalfOfDay = true;
	}
}

Time::Time(int militaryTime)
{
	bool isValidMilitary = true;
	
	int firstTwo = (military time / 100);
	if (firstTwo < 0 || firstTwo > 24)
	{
		isValidMilitary = false;
	}

	int lastTwo = militaryTime % 100;
	if (lastTwo < 0 || lastTwo > 59)
	{
		isValidMilitary = false;
	}	

	if(isValidEntry == false)//if either entry is invalid, set to midnight.
	{
		hours = 0;
		minutes = 0;
		firstHalfOfDay = true;
	}
	else
	{
		if (firstTwo > 12)
		{
			firstTwo = firstTwo - 12;
			minutes = lastTwo;
			firstHalfOfDay = false;
		}
		else
		{
			hours = firstTwo;
			minutes = lastTwo;
			firstHalfOfDay = true;
		}
	}
}

int Time::getHours()const
{
	return hours;
}

int Time::getMinutes()const
{
	return minutes;
}

bool Time::getFirstHalfOfDay()const
{
	return firstHalfOfDay;
}
/*
Time Time::add(Time operand)
{

}*/