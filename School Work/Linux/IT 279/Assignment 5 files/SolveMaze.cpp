#include "SolveMaze.h"
#include "Graph.h"
#include <fstream>
#include <iostream>
#include <string>
using namespace std;
#include <cstring>

/*
This function is being called from the Java program MazeDraw, using the Java Native Interface (JNI).

It takes a single parameter relevant to us: a filename. When you run the Java program, you provide this same string as a parameter that it passes here. 

Using this file, this function uses three files:

1. "filename".txt : this contains the maze itself, along with the entry and exit cell numbers. It is your goal to find a shortest path between them. This file MUST BE PRESENT in the same folder as the rest of the files for this assignment. 

If this file is not present, use the provided "GenerateMaze" program to first generate this file.

2. "filename"-blocked.txt : If present, this file contains cells that your path must NOT pass through. This function basically disconnects these vertices from the graph, so that your path will never consider them. 

Due to this it is possible that there is no way to exit the maze. In this case the output in the two output files will simply be a path of length 0, and thus will not be shown by your Java program.

3. "filename"-out-without-turns.txt: This is one of the two files that this function will WRITE. In this file, the shortest path ignoring the number of turns will be written.

4. "filename"-out-with-turns.txt: This is the second of the two files that this function will WRITE. In this file, the shortest path that minimizes the number of turns will be written.

A preliminary way to check if your program is producing the correct paths is this: the path that minimizes the number of turns should not be SHORTER than the path that ignores turns. The two paths may be of the same length.
*/

JNIEXPORT jboolean JNICALL Java_SolveMaze_findpath
  (JNIEnv *env, jobject,jstring fname)
{
    
    int rows,cols;
    bool turns;

    ifstream fin;
    ofstream fout;
    
    const char *str = env->GetStringUTFChars(fname, 0);
    
    char alpha[200];
    
    strcpy(alpha,str);
    
    string filename(alpha);

    env->ReleaseStringUTFChars(fname, str);

    fin.open(string(filename + ".txt").c_str());
    if (!fin.is_open())
        return false;

    
	//read in the number of rows and columns
    fin>>rows;
    fin>>cols;

    Graph g(rows*cols); //make a graph with specified number of vertices and no edges

    int no_of_edges;

    //read in the number of edges

    fin >> no_of_edges;

    for (int i=0;i<no_of_edges;i++)
    {
        int i1,j1,i2,j2;
        int weight;

        fin >> i1 >> j1 >> i2 >> j2;

        //imagine the maze to be grid, then (i1,j1) translates to (i1*cols + j1) when giving cell numbers row-wise. This becomes the vertex number for that cell

        if (i1>i2)
            weight = 0;

        if (i1<i2)
            weight = 2;

        if (j1<j2)
            weight = 1;

        if (j1>j2)
            weight = 3;

/*
        weight is how many right turns to make starting from NORTH to face the other cell, 0 if (i2,j2) is  NORTH of (i1,j1), 1 if )i2,j2) is WEST of (i1,j1), 2 for SOUTH, 3 for EAST
        */
        g.setEdge(i1*cols + j1,i2*cols+j2,weight); 
        
        
        g.setEdge(i2*cols + j2,i1*cols+j1,(weight+2)%4); //opposite weight

    }

    //now get the starting and ending vertex

    int s_row,s_col,e_row,e_col,start_direction;

    fin >> s_row >> s_col >> e_row >> e_col;

    //determine the direction in which the user is facing when entering the maze, 0-2 for NWSE respectively

    if (s_row == 0)
        start_direction = 2;

    if (s_col==0)
        start_direction = 1;

    if (s_row==rows-1)
        start_direction = 0;

    if (s_col==cols-1)
        start_direction = 3;


	
	fin.close();
	
	//read in the blocked vertices, if present
	fin.open(string(filename+"-blocked.txt").c_str());
	
	if (fin.is_open())
	{
		int no_of_blocked_vertices;
		
		fin >> no_of_blocked_vertices;
		
		for (int i=0;i<no_of_blocked_vertices;i++)
		{
			int r,c;
			fin >> r >> c;
			
			g.blockVertex(r*cols + c);
		}
		fin.close();
	}


//get the path that minimizes distance and number of turns

    vector<int> path;

    path = g.getShortestPathWithTurns(s_row*cols+s_col,e_row*cols+e_col,start_direction);
 
    
    //write out to file
   
    fout.open(string(filename + "-out-with-turns.txt").c_str());

    if (!fout.is_open())
        return false;
    //finally, the path starting with its length
    fout <<path.size()<<endl;

    //the cells visited on that path from start to the end
    for (int i=0;i<path.size();i++)
    {
        fout <<path[i]/cols << " " <<path[i]%cols<<endl;
    }

    fin.close();
    fout.close();
    
    //get the shortest path ignoring number of turns
    
    fout.open(string(filename + "-out-without-turns.txt").c_str());

    if (!fout.is_open())
        return false;
        
    path = g.getShortestPathWithoutTurns(s_row*cols+s_col,e_row*cols+e_col);  
    
    //write out to file
    fout <<path.size()<<endl;

    //the cells visited on that path from start to the end
    for (int i=0;i<path.size();i++)
    {
        fout <<path[i]/cols << " " <<path[i]%cols<<endl;
    }  
    return true;
}
int main(){return 0;}
