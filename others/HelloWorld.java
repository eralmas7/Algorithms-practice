package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class HelloWorld {

    private static int numberOfNodes;
    private static Stack<Integer> stack = new Stack<Integer>();

    public static void tsp(int adjacencyMatrix[][]) {
        numberOfNodes = adjacencyMatrix[1].length - 1;
        int[] visited = new int[numberOfNodes + 1];
        visited[1] = 1;
        stack.push(1);
        int element, dst = 0, i;
        int min = Integer.MAX_VALUE;
        boolean minFlag = false;
        System.out.print(1 + "\t");
        while (!stack.isEmpty()) {
            element = stack.peek();
            i = 1;
            min = Integer.MAX_VALUE;
            while (i <= numberOfNodes) {
                if (adjacencyMatrix[element][i] > 1 && visited[i] == 0) {
                    if (min > adjacencyMatrix[element][i]) {
                        min = adjacencyMatrix[element][i];
                        dst = i;
                        minFlag = true;
                    }
                }
                i++;
            }
            if (minFlag) {
                visited[dst] = 1;
                stack.push(dst);
                System.out.print(dst + "\t");
                minFlag = false;
                continue;
            }
            stack.pop();
        }
    }

    private static class Graph {

        int adjacency_matrix[][];
        int max;

        public void createMatrix(String graph) {
            String[] nodes = graph.split(",");
            String[] city;
            Integer maxNumber = Integer.MIN_VALUE;
            Integer city1, city2;
            List<Integer> strList = new ArrayList<Integer>((nodes.length + 1) * 2);
            for (String node : nodes) {
                city = node.split("#");
                city1 = Integer.valueOf(city[0]);
                city2 = Integer.valueOf(city[1]);
                maxNumber = Integer.max(Integer.max(maxNumber, city1), city2);
                strList.add(city1 - 1);
                strList.add(city2 - 1);
            }
            adjacency_matrix = new int[maxNumber][maxNumber];
            for (int i = 0; i < strList.size(); i += 2) {
                adjacency_matrix[strList.get(i)][strList.get(i + 1)] = 1;
            }
        }

        public int[][] getAdjacency_matrix() {
            return adjacency_matrix;
        }

        public int getMax() {
            return max;
        }
    }

    public static void main(String... arg) {
        int number_of_nodes;
        String graph = "1#2,2#3,1#11,3#11,4#11,4#5,5#6,5#7,6#7,4#12,8#12,9#12,8#10,9#10,8#9";
        Graph graph2 = new Graph();
        graph2.createMatrix(graph);
        number_of_nodes = graph2.getMax();
        int adjacency_matrix[][] = graph2.getAdjacency_matrix();
        for (int i = 1; i <= number_of_nodes; i++) {
            for (int j = 1; j <= number_of_nodes; j++) {
                if (adjacency_matrix[i][j] == 1 && adjacency_matrix[j][i] == 0) {
                    adjacency_matrix[j][i] = 1;
                }
            }
        }
        tsp(adjacency_matrix);
    }
}
