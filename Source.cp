#include <iostream>
#include <vector>
#include <fstream>
#include <algorithm>
#include <tuple>
using namespace std;

class Library
{
public:

	int Books;
	int SignUp;
	int ShipPerDay;
	vector<int>Details;
	vector<int>Bookss;
	int num;

	Library(int Books, int SignUp, int ShipPerDay, vector<int>Bookss, int num)
	{
		this->Books = Books;
		this->SignUp = SignUp;
		this->ShipPerDay = ShipPerDay;
		this->num = num;
		for (int i = 0; i < Bookss.size(); i++)
		{
			this->Bookss.push_back(Bookss[i]);
		}
		Details.push_back(Books);
		Details.push_back(SignUp);
		Details.push_back(ShipPerDay);
	}

	vector<int> getDetails()
	{
		return Details;
	}

	int getSignUp()
	{
		return SignUp;
	}

	void setBooks(int Books)
	{
		this->Books = Books;
	}

	void setSignUp(int SignUp)
	{
		this->SignUp = SignUp;
	}

	void setShipPerDay(int ShipPerDay)
	{
		this->ShipPerDay = ShipPerDay;

	}

	void printDetails()
	{
		cout << "Books: " << Books << " SignUp: " << SignUp << " ShipPerDay: " << ShipPerDay << endl;

		cout << endl;
		for (int i = 0; i < Bookss.size(); i++)
		{
			cout << Bookss[i] << " "; // endl;
		}
		cout << endl;
	}
};

/*vector<tuple<int,int>> BookScore(int& numDays, int ship, Library Libraries, vector<int>Scores)
{
	numDays = numDays - Libraries.SignUp;
	int tempDays = numDays;
	int counter = -1;
	while (tempDays <= 0)
	{
		counter++;
		tempDays - ship;
	}

	int books = Libraries.Bookss.size();

	tuple <int, int> tup;
	vector<tuple<int, int>> final;

	for (int j = 0; j < Libraries.Bookss.size(); j++)
	{
		tup = make_tuple(Scores[Libraries.Bookss[j]], Libraries.Bookss[j]);
		final.push_back(tup);
	}

	sort(final.begin(), final.end());
	for (int i = 0; i < final.size(); i++)
	{
		cout << get<0>(final[i]) << " " << get<1>(final[i]) << " " << endl; /// << get<2>(arr[i]) << endl;
	}
	return final;

}*/

int main()
{

	int numBooks=-1, numLibraries=-1, numDays=-1;
	vector<int>scores;

	fstream in;
	in.open("d_tough_choices.txt");
	if (in.fail()) { cout << "File didn't open! " << endl; exit(1); }
	in >> numBooks >> numLibraries >> numDays;

	// cout << numBooks << " " << numLibraries << " " << numDays << endl;

	for (int i = 0; i < numBooks; i++)
	{
		int x = -1;
		in >> x;
		scores.push_back(x);
	}

	/*for (int i = 0; i < scores.size(); i++)
	{
		cout << scores[i] << endl;
	}*/

	//----------------------

	vector<Library> libraries;


	for (int i = 0; i < numLibraries; i++)
	{
		
		int x1 = -1, x2 = -1 , x3=-1;
		in >> x1;
		in >> x2;
		in >> x3;
		
		vector<int>bookss;
		for (int i = 0; i < x1; i++)
		{
			int bookTemp = -1;
			in >> bookTemp;
			bookss.push_back(bookTemp);
		}
		
		Library temp(x1, x2, x3, bookss, i);
		
		libraries.push_back(temp);
		bookss.clear();
	}
	
	/*for (int i = 0; i < libraries.size(); i++)
	{
		libraries[i].printDetails();
	}*/

	sort(libraries.begin(), libraries.end(), [](const Library& lhs, const Library& rhs) {
		return lhs.SignUp < rhs.SignUp;
		});


	//cout << numLibraries << endl;

	vector<tuple<int, int>>logger;

	/*for (int i = 0; i < libraries.size(); i++)
	{
		BookScore(numDays, libraries[i].ShipPerDay, libraries[i], scores);
		cout << endl;
	}*/
	
	ofstream out;
	out.open("output.txt");


	
	out << libraries.size() << endl;
	for (int i = 0; i < libraries.size(); i++)
	{
		out << libraries[i].num << " ";
		out << libraries[i].Books << endl;

		for (int j = 0; j < libraries[i].Bookss.size(); j++)
		{
			out << libraries[i].Bookss[j] << " ";
		}

		out << endl;
	}


	/*
	for (auto it = libraries.begin(); it != libraries.end(); it++) {
		cout << it->SignUp << endl;
	}*/

	return 0;
}