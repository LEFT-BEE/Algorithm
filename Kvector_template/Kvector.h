#include<iostream>
using namespace std;
#pragma once
template<typename T>
class Kvector;

template<typename T>
bool operator==(const Kvector<T>& lhs, const Kvector<T>& rhs);

template<typename T>
bool operator!=(const Kvector<T>& lhs, const Kvector<T>& rhs);

template<typename T>
ostream& operator<<(ostream& os, const Kvector<T>& v);

template<typename T>
class Kvector {
protected:
	T* m;
	int len;

public:
	Kvector<T>(int sz = 0, T value = 0);
	Kvector<T>(const Kvector& v);
	virtual ~Kvector<T>() {
		cout << this << " : ~Kvector() \n";
		delete[] m;
	}

	virtual void println() const {
		for (int i = 0; i < len; i++) {
			cout << m[i] << " ";
		}
		cout << endl;
	}

	void clear() {
		delete[] m;
		m = nullptr;
		len = 0;
	}
	int size() { return len; }

	T sum()const {
		if (!len) return T();
		T s = m[0];
		for (int i = 1; i < len; i++) s += m[i];
		return s;
	}
	Kvector<T>& operator=(const Kvector<T>& v);
	friend bool operator==<>(const Kvector<T>& v, const  Kvector<T>& w);
	friend bool operator!=<>(const  Kvector<T>& v, const  Kvector<T>& w);
	T& operator[](int idx);
	const T& operator[](int idx)const;
	friend ostream& operator<<<>(ostream& os, const  Kvector<T>& v);
};

template <typename T>
Kvector<T>::Kvector(int sz, T value) {
	len = sz;
	if (sz == 0) m = nullptr;
	else m = new T[len];
	for (int i = 0; i < len; i++) {
		m[i] = value;
	}
	cout << this << " : Kvector( " << sz << ", " << value << ") \n";
}

template <typename T>
Kvector<T>::Kvector(const Kvector& v) {
	len = v.len;
	m = new T[len];
	for (int i = 0; i < len; i++) {
		m[i] = v.m[i];
	}
	cout << this << " : Kvector(* " << &v << ") \n";
}

template <typename T>
Kvector<T>& Kvector<T>::operator=(const Kvector& v) {
	if (len != v.len)
	{
		delete[] m;
		m = new int[v.len];
		len = v.len;
	}
	copy(v.m, v.m + len, m);
	return *this;
}

template <typename T>
T& Kvector<T>::operator[](int idx) {
	return m[idx];
}

template <typename T>
const T& Kvector<T>::operator[](int idx)const {
	return m[idx];
}

template <typename T>
bool operator!=(const Kvector<T>& lhs, const Kvector<T>& rhs) {
	return !(lhs == rhs);
}

template <typename T>
bool operator==(const Kvector<T>& lhs, const Kvector<T>& rhs) {
	if (lhs.len != rhs.len)
		return false;
	for (int i = 0; i < lhs.len;i++) {
		if (lhs.m[i] != rhs.m[i])
			return false;
	}
	return true;
}


template <typename T>
ostream& operator<<(ostream& os, const Kvector<T>& v) {
	for (int i = 0; i < v.len; i++) {
		os << v.m[i] << " ";
	}
	return os;
}



