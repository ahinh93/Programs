#ifndef _SUDOKU_H
#define _SUDOKU_H

#include "stdio.h"
#include "stdlib.h"
#include "pthread.h"
#include "iostream"
#include "fstream"
#include "sstream"
#include "string.h"

using namespace std;

class Sudoku
{
	public:
		int board[9][9];
		Sudoku();
		void insertLine(string line);
		~Sudoku();
	private:
		
};
/* Parameters to print_function. */
class char_print_parms
{
    /* The character to print. */
    char character;
    /* The number of times to print it. */
    int count;
};
#endif
