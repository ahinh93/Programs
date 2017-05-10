//package com.bburton.it328.asg1;

import java.util.*;

public class Graph {

    ArrayList<ArrayList<Integer>> vertexMap;
    //    ArrayList<ArrayList<Integer>> candidateList;
//    Stack<Integer> currentClique = new Stack<Integer>();

    //remember the adjacency list
	private byte[][] edgeMatrix;

    //the size of the the matrix (n)
	private int vertexCount = 0;
    private int maxCliqueSize = 0;
    private int edgeCount = 0;
    private long calcTime = 0;

    private Set<Integer> maximalClique;
	
	public Graph(int size) {
		edgeMatrix = new byte[size][size];
//        candidateList = new ArrayList<ArrayList<Integer>>();
        vertexMap = new ArrayList<ArrayList<Integer>>();
		vertexCount = size;
        maximalClique = new HashSet<Integer>();
	}
	
	public void addVertex(int row, String connectedList) {
		String[] split = connectedList.split(" ");
//        ArrayList<Integer> arr = new ArrayList<Integer>();
        try {
            for (int i = 0; i < vertexCount; i++)
            {

                byte val = Byte.parseByte(split[i]);
                edgeMatrix[row][i] = val;
                if (val == 1 && row != i)
                    edgeCount+=1;

            }
        } catch (NumberFormatException nfe) {
            System.out.println("Error");
        }
//        vertexMap.add(arr);
		//edgeList.add(adjacencyList);
	}

    public void addVertex(int row, byte[] edgeList) {

//        ArrayList<Integer> arr = new ArrayList<Integer>();
        try {
            for (int i = 0; i < vertexCount; i++)
            {

                byte val = edgeList[i];
                edgeMatrix[row][i] = val;
                if (val == 1 && row != i)
                    edgeCount+=1;

            }
        } catch (NumberFormatException nfe) {
            System.out.println("Error");
        }
    }

    /**
     * This function will calculate the maximum clique
     */
    public String calculateMaximumClique() {

        Calendar cal = Calendar.getInstance();
        long timeBefore = System.currentTimeMillis();

        Stack<Integer> s = new Stack<Integer>();
        for (int i = 0; i < vertexCount; i++)
        {
            s.push(i);
        }

        recursiveFind(new Stack<Integer>(), s, new HashSet<Integer>());

        long timeAfter = System.currentTimeMillis();

        //System.out.println("The maximal clique is " + maximalClique);

        StringBuilder sb = new StringBuilder();
        sb.append("( "+vertexCount+", "+(edgeCount / 2)+") ");
        sb.append(maximalClique);
        sb.append(" (size=" + maximalClique.size()+", ");
        sb.append((timeAfter - timeBefore) + "ms)");

        calcTime = timeAfter - timeBefore;

        return sb.toString();
    }

    /**
     *
     * @param potentialStack
     * @param remainderStack
     * @param skipStack
     * @return
     */
    public synchronized void recursiveFind(Stack<Integer> potentialStack, Stack<Integer> remainderStack, Set<Integer> skipStack) {

//        uncomment this to see the calculation
//        StringBuilder s = new StringBuilder();
//        s.append(" P: ");
//        s.append(potentialStack);
//        s.append(" R: ");
//        s.append(remainderStack);
//        s.append(" S: ");
//        s.append(skipStack);
//        System.out.println(s.toString());


        if (remainderStack.size() == 0 && skipStack.size() == 0)
        {
            HashSet<Integer> clique = new HashSet<Integer>();
            clique.addAll(potentialStack);

            if (clique.size() > maximalClique.size()) {
//                System.out.println("The highest clique is now " + clique + "\n");
                maximalClique = clique;
            }

        }


        for (Integer pivotVertex : remainderStack)
        {
            //remainderStack.remove(pivotVertex);
//
//            if () {
//
//            }
//            else {
                Stack<Integer> newPotentialStack = new Stack<Integer>();
                Stack<Integer> newRemainderStack = new Stack<Integer>();
                Set<Integer> newSkipSet = new HashSet<Integer>();

                newPotentialStack.addAll(potentialStack);
                newPotentialStack.add(pivotVertex);

                newRemainderStack.addAll(getNeighbors(remainderStack, pivotVertex));
                newRemainderStack.remove(pivotVertex);

                newSkipSet.addAll(getNeighbors(skipStack, pivotVertex));
                //newSkipNodes.add(pivotVertex);

                recursiveFind(newPotentialStack, newRemainderStack, newSkipSet);

                skipStack.add(pivotVertex);
//            }
        }

    }

    /**
     * This method returns a data structure that holds
     * @param workingStack
     * @param vertex
     * @return
     */
    private Stack<Integer> getNeighbors(Stack<Integer> workingStack, int vertex) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < workingStack.size(); i++)
        {
            int j = workingStack.get(i);
            if (edgeMatrix[vertex][j] == 1) {
                stack.add(j);
            }
        }
        return stack;
    }

    /**
     *
     * @param workingSet
     * @param vertex
     * @return
     */
    private Stack<Integer> getNeighbors(Set<Integer> workingSet, int vertex) {
        Stack<Integer> stack = new Stack<Integer>();
        Integer[] candidates = workingSet.toArray(new Integer[workingSet.size()]);
        for (int i = 0; i < workingSet.size(); i++)
        {
            if (edgeMatrix[vertex][i] == 1) {
                stack.add(i);
            }
        }
        return stack;
    }
	
	public String toString() {
		//return "This graph has " + vertexCount + " vertices and " + edgeIndex.size() + " edges\n";
        //return "GX ( " + vertexCount + ", " + edgeIndex.size() + ") size=" + 0 + "\n";
        StringBuilder b = new StringBuilder();

        for (int i = 0; i < vertexCount; i++)
        {
            for (int j = 0; j < vertexCount; j++)
            {
                b.append(edgeMatrix[j][i]+",");
            }
            b.append("\n");
        }
        return b.toString();
	}

    public ArrayList<Integer> getMaximumClique() {

        ArrayList<Integer> maxClique = new ArrayList<Integer>();
        maxClique.addAll(maximalClique);
        return maxClique;
    }

    public long getCalcTime() {
        return calcTime;
    }

    public int getSize() {
        return vertexCount;
    }


//    private int calculateHighestDegreeVertex(Stack<Integer> stack) {
//
//        int highestDegree = 0;
//        int highestVertex = 0;
//
//        Integer[] availableArrays = stack.toArray(new Integer[stack.size()]);
//
//        //for every vertex in the array
//
////        for (int i = 0; i < vertexCount; i++) {
//        for (int i = 0; i < availableArrays.length; i++) {
//            int sum = 0;
//            for (int j = 0; j < vertexCount; j++) {
//                sum += (edgeMatrix[i][j] == 1) ? 1 : 0;
////                sum += (edgeMatrix[availableArrays[i]][j] == 1) ? 1 : 0;
//            }
//            if (sum > highestDegree) {
//                highestDegree = sum;
//                highestVertex = i;
//            }
//        }
//
//
//        System.out.println("Highest degree: " + highestDegree + " at vertex " + highestVertex);
//        return highestVertex;
//
//    }
//
//    private int getNeighborCount(int vertex) {
//
//        int sum = 0;
//        for (int j = 0; j < vertexCount; j++) {
//            sum += (edgeMatrix[vertex][j] == 1) ? 1 : 0;
////                sum += (edgeMatrix[availableArrays[i]][j] == 1) ? 1 : 0;
//        }
//
//        return sum;
//    }

}
