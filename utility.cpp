#include<iostream>
#include<sstream>
#include<string>
using namespace std;

int main() {
	ostringstream ss;
	int n ;
	cin >> n;
	static int count = 0;
	


	for (int i = 666; ;i++) {
		ss.str("");	//초기화인듯
		ss << i;
		string num = ss.str();	
		if (num.find(string("666")) != string::npos) {
			count++;
		}

		if (count==n) {
			cout<<num;
			break;
		}
		
	}
	
	return 0;
}