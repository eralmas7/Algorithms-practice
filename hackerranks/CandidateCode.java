package hello;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class CandidateCode {

    private static final String HASH = "#";

    static class Vertex {

        public int label;
        public boolean wasVisited;

        public Vertex(int lab) {
            label = lab;
            wasVisited = false;
        }
    }

    static class Graph {

        private final int MAX_VERTS = 1001;
        private Vertex vertexList[];
        private int adjMat[][];
        private int nVerts;
        private Stack<Integer> theStack;

        public Graph() {
            vertexList = new Vertex[MAX_VERTS];
            adjMat = new int[MAX_VERTS][MAX_VERTS];
            nVerts = 0;
            theStack = new Stack<Integer>();
        }

        public void addVertex(int lab) {
            vertexList[nVerts++] = new Vertex(lab);
        }

        public void addEdge(int start, int end) {
            adjMat[start][end] = 1;
            adjMat[end][start] = 1;
        }

        public int dfs(int start) {
            int count = 0;
            int vertex;
            if (!vertexList[start].wasVisited) {
                vertexList[start].wasVisited = true;
                theStack.push(start);
                while (!theStack.isEmpty()) {
                    vertex = getAdjUnvisitedVertex(theStack.peek());
                    if (vertex == -1) {
                        theStack.pop();
                    } else {
                        vertexList[vertex].wasVisited = true;
                        theStack.push(vertex);
                        count = Math.max(count, theStack.size());
                    }
                }
            }
            return count;
        }

        public void clearVisitedFlag() {
            for (int j = 0; j < nVerts; j++) {
                vertexList[j].wasVisited = false;
            }
        }

        public int getAdjUnvisitedVertex(int v) {
            for (int j = 0; j < nVerts; j++)
                if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false)
                    return j;
            return -1;
        }
    }

    public static int maxno_city(String[] input1) {
        if (input1 == null || input1.length == 0) {
            return 0;
        }
        final Set<Integer> set = new HashSet<Integer>();
        final Graph theGraph = new Graph();
        String[] cities;
        int city1, city2;
        for (String input : input1) {
            cities = input.split(HASH);
            city1 = Integer.valueOf(cities[0]) - 1;
            city2 = Integer.valueOf(cities[1]) - 1;
            theGraph.addEdge(city1, city2);
            set.add(city1);
            set.add(city2);
        }
        for (int city : set) {
            theGraph.addVertex(city);
        }
        int max = 0;
        for (int city : set) {
            max = Math.max(max, theGraph.dfs(city));
            if (max == set.size()) {
                return max;
            }
            theGraph.clearVisitedFlag();
        }
        return max;
    }

    public static void main(String[] args) {
        String[] str = new String[1000];
        for (int i = 1; i <= 1000; i++) {
            str[i - 1] = i + "#" + (i + 1);
        }
        long start = System.currentTimeMillis();
        System.out.println(maxno_city(str));
        System.out.println((System.currentTimeMillis() - start));
        // long start = System.currentTimeMillis();
        // System.out.println(maxno_city(new String[] {"1#2", "2#3", "1#11", "3#11", "4#11", "4#5",
        // "5#6", "5#7", "6#7", "4#12", "8#12", "9#12", "8#10", "9#10", "8#9"}));
        // System.out.println((System.currentTimeMillis() - start));
    }
}
