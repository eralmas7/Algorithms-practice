import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CandidateCode {

    static class Graph {

        private final Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();

        public void addEdge(Integer node1, Integer node2) {
            Set<Integer> adjacent = map.get(node1);
            if (adjacent == null) {
                adjacent = new LinkedHashSet<Integer>();
                map.put(node1, adjacent);
            }
            adjacent.add(node2);
        }

        public void addTwoWayVertex(Integer node1, Integer node2) {
            addEdge(node1, node2);
            addEdge(node2, node1);
        }

        public boolean isConnected(Integer node1, Integer node2) {
            final Set<Integer> adjacent = map.get(node1);
            if (adjacent == null) {
                return false;
            }
            return adjacent.contains(node2);
        }

        public List<Integer> adjacentNodes(Integer last) {
            final Set<Integer> adjacent = map.get(last);
            if (adjacent == null) {
                return new LinkedList<Integer>();
            }
            return new LinkedList<Integer>(adjacent);
        }
    }

    static Map<Integer, Integer> nodeRankMap;

    public static int visitCity(int[] input1, String[] input2) {
        if (input1 == null || input2 == null) {
            return -1;
        }
        if (input1.length == 1) {
            return input1[0];
        }
        final Graph graph = new Graph();
        String[] route;
        int node = 1;
        int count;
        nodeRankMap = new HashMap<Integer, Integer>();
        for (String input : input2) {
            nodeRankMap.put(node, input1[node - 1]);
            route = input.split("#");
            count = 1;
            for (String iroute : route) {
                if (iroute.equals("1")) {
                    graph.addTwoWayVertex(node, count);
                }
                count++;
            }
            node++;
        }
        final LinkedList<Integer> visited = new LinkedList<Integer>();
        visited.add(1);
        final Test max = new Test(-1, Integer.MAX_VALUE);
        breadthFirst(graph, visited, input2.length, max);
        return max.sum;
    }

    public static void main(String[] args) {
        System.out.println(visitCity(new int[] {1, 2, 1, 4}, new String[] {"0#1#1#1", "1#0#0#1", "1#0#0#1", "1#1#1#0"}));
        System.out.println(visitCity(new int[] {2, 2}, new String[] {"0#1", "1#0"}));
        System.out.println(visitCity(new int[] {1}, new String[] {"0"}));
        System.out.println(visitCity(null, null));
    }

    private static void breadthFirst(final Graph graph, final LinkedList<Integer> visited, int endNode, final Test max) {
        final List<Integer> nodes = graph.adjacentNodes(visited.getLast());
        for (Integer node : nodes) {
            if (visited.contains(node)) {
                continue;
            }
            if (node.equals(endNode)) {
                visited.add(node);
                Test v = printPath(visited);
                if (v.sum < max.sum) {
                    max.number = v.number;
                    max.sum = v.sum;
                }
                visited.removeLast();
                break;
            }
        }
        for (Integer node : nodes) {
            if (visited.contains(node) || node.equals(endNode)) {
                continue;
            }
            visited.addLast(node);
            breadthFirst(graph, visited, endNode, max);
            visited.removeLast();
        }
    }

    private static int formNumber(List<Integer> visited) {
        String str = "";
        for (Integer visit : visited) {
            str += nodeRankMap.get(visit);
        }
        return Integer.parseInt(str, 10);
    }

    private static Test algoLogic(List<Integer> visited) {
        final int number = formNumber(visited);
        int sum = 0;
        int myvisit;
        boolean isDivisible = true;
        for (Integer visit : visited) {
            myvisit = nodeRankMap.get(visit);
            sum += myvisit;
            if (number % myvisit != 0) {
                isDivisible = false;
                break;
            }
        }
        if (isDivisible) {
            return new Test(number, sum);
        }
        return new Test(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    private static Test printPath(List<Integer> visited) {
        return algoLogic(visited);
    }

    private static class Test {

        int number;
        int sum;

        Test(int number, int sum) {
            this.number = number;
            this.sum = sum;
        }
    }
}
