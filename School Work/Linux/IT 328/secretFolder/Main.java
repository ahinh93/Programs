//package com.bburton.it328.asg1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	
	public static void main(String args[])
	{
		ArrayList<Graph> graphList;
		
		//File file = new File("H:/IT328/Assignment1/graphs.txt");
        File file = new File("./graphsDense.txt");
		FileInputStream fis = null;
		BufferedReader reader = null;
			
		graphList = new ArrayList<Graph>();
		
		try {
			
			fis = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(fis));
			
			String line = reader.readLine();
			while (line != null) {
				int graphSize = Integer.valueOf(line);
				Graph g = new Graph(graphSize);
				for (int i = 0; i < graphSize; i++)
				{
					g.addVertex(i, reader.readLine());
				}

				line = reader.readLine();
				if (graphSize > 0)
				{
                    if (graphSize < 28)
                        System.out.println("G"+(graphList.size() + 1) + " " + g.calculateMaximumClique());


                    //now that the graph is populated correctly and work is done, add it to the list
					graphList.add(g);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					reader.close();
					fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
