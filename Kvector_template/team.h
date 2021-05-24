#pragma once
#ifndef __TEAM__
#define __TEAM__
#include<iostream>
#include<string>

using namespace std;
class Team {
	string name;
	int wins;

public:
	Team(const string& n = "teamX" , int w = 0) : name(n) , wins(w){}
	Team& operator+=(const Team& rhs)
	{
		wins += rhs.wins;
		return *this;
	}
	friend Team operator+(Team a, const Team& b);
	friend bool operator==(const Team& a, const Team& b);
	friend bool operator!=(const Team& a, const Team& b);
	friend ostream& operator<<(ostream& os, const Team& a);

};




#endif