import java.io.*;
import java.util.ArrayList;

/**
 * This program was created by Andrew Hinh and Brandon Burton for IT 328
 * @author ahinh,bmburt1
 *
 */
public class Main3SAT {
	
	public static void main(String args[]) {
        ArrayList<Graph> graphList;
        ArrayList<int[]> cnfList;

        //File file = new File("H:/IT328/Assignment1/graphs.txt");
        File file = new File("./cnfs.txt");
        FileInputStream fis = null;
        BufferedReader reader = null;

        graphList = new ArrayList<Graph>();
        cnfList = new ArrayList<int[]>();

        try {

            fis = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(fis));

            String line = "";

            while (!line.equals("0")) {

                line = reader.readLine();

                String[] split = line.split(" ");
                int graphSize = split.length - 1;
                int terms = Integer.valueOf(split[0]);
                int clauses = (split.length - 1) / 3;

                int[] cliqueList = new int[graphSize];
                for (int i = 1; i < graphSize + 1; i++) {
                    cliqueList[i - 1] = Integer.parseInt(split[i]);
                }

                //upper bound of calculation, after this, computation may take awhile
                if (clauses < 17) {
                    Graph g = new Graph(graphSize);

                    int k = 3;
                    for (int i = 0; i < cliqueList.length; i++) {

                        int val = Integer.valueOf(split[i]);
                        byte[] vertex = new byte[cliqueList.length];

                        for (int fill = 0; fill < cliqueList.length; fill++)
                            vertex[fill] = 0;

                        vertex[i] = 1;

                        for (int j = k; j < cliqueList.length; j += 3) {

                            for (int l = 0; l < 3; l++) {
//                                System.out.println(i + ", " + (j+l));
                                if (cliqueList[i] == cliqueList[j + l] * -1) {
                                    vertex[j + l] = 0;
                                } else if (cliqueList[i] != cliqueList[j + l] * -1) {
                                    vertex[j + l] = 1;
                                }
                            }


                            k += 3;
                        }

                        g.addVertex(i, vertex);
                        cnfList.add(cliqueList);

                        k = (((i + 1) / 3) + 1) * 3;
                    }

                    if (clauses < 26) {
                        System.out.println("3-CNF No." + (graphList.size() + 1) + ":[n=" + terms + " k=" + clauses + "]");
                        g.calculateMaximumClique();
                        System.out.println(calculate3CNF(cliqueList, g.getMaximumClique()) + " (" + g.getCalcTime()+"ms)");
                    }
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
            } catch (NullPointerException n) {
                n.printStackTrace();
            }
        }
    }

    public static String calculate3CNF(int[] cnfList, ArrayList<Integer> maxClique)
    {
        StringBuilder s = new StringBuilder();
        s.append("Assignments:[");
        for (int i = 0; i < maxClique.size(); i++)
        {
            int result = cnfList[maxClique.get(i)];
            if (result > 0)
            {
                s.append("A").append(i).append("=T ");
            }
            else
            {
                s.append("A").append(i).append("=F ");
            }

        }

        s.append("]");

        return s.toString();
    }
}
