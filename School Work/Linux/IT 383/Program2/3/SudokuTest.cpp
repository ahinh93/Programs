#include "Sudoku.h"

int main()
{
	pthread_t thread1_id;
	pthread_t thread2_id;
	pthread_t thread3_id;
	pthread_t thread4_id;
	pthread_t thread5_id;
	pthread_t thread6_id;
	pthread_t thread7_id;
	pthread_t thread8_id;
	pthread_t thread9_id;
	pthread_t thread10_id;
	pthread_t thread11_id;

	//create empty board
	Sudoku myBoard;
	//read board from file
	string input = "puzzle.txt";
	//stream that will open file
	ifstream myfile (input.c_str());
	string temp;
	while(!myfile.eof())
	{
		getline(myfile,temp);
		//cout<<temp<<endl;
		myBoard.insertLine(temp);
	}
/* //prints content of the board
	for(int i = 0; i < 9; i++)
	{
		for(int k = 0; k < 9; k++)
		{
			cout<< myBoard.board[i][k]<<" ";
		}
		cout<<endl;
	}*/

	struct char_print_parms thread1_args;

	return 0;
}
