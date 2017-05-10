import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This program was created by Andrew Hinh and Brandon Burton for IT 328
 * @author ahinh,bburt
 *
 */
public class MainISet {
	
	public static void main(String args[])
	{
		ArrayList<Graph> graphList;
		
		//File file = new File("H:/IT328/Assignment1/graphs.txt");
        //File file = new File("graphs.txt");
        FileInputStream fis = null;
		BufferedReader reader = null;
		int numVertices;
		int[][] graph;
		//Convert graphs.txt to new file and read in new file
		try {
			Scanner scan = new Scanner(new File("./graphs.txt"));
			FileWriter fw = new FileWriter(new File("./inverseGraphs.txt"));
			
			while(scan.hasNextLine())
			{
				numVertices = scan.nextInt();
				if(numVertices==0)
				{
					break;
				}
				
				graph = new int[numVertices][numVertices];
				for(int row = 0; row < numVertices; row++)
				{
					for(int col = 0; col < numVertices; col++)
					{
						graph[row][col] = scan.nextInt();
					}
				}

				//rewrite graph to InverseGraphs.txt
				fw.write(numVertices+"\r\n");
				for(int row = 0; row < numVertices; row++)
				{
					for(int col = 0; col < numVertices; col++)
					{
						if(row == col)
							fw.write(1 + " ");
						else if (graph[row][col] == 1)
							fw.write(0 + " ");
						else
							fw.write(1 + " ");													
					}
					fw.write("\r\n");
				}
			}
			fw.close();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		graphList = new ArrayList<Graph>();
		
		try {
			fis = new FileInputStream(new File("./inverseGraphs.txt"));
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
                    if (graphSize < 36)
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
