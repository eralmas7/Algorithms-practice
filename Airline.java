import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Airline {

    private static class Edge implements Comparable<Edge> {

        private final int source;
        private final int destination;
        private final double cost;

        public Edge(final int source, final int destination, final double cost) {
            this.source = source;
            this.destination = destination;
            this.cost = cost;
        }

        @Override
        public int compareTo(final Edge edge) {
            return Double.compare(this.cost, edge.cost);
        }

        public String toString() {
            return String.format("%d %d %.2f\n", source, destination, cost);
        }
    }

    private static class Subset {

        private int parent;
        private int rank;

        public Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    private static int find(final List<Subset> subsets, final int index) {
        if (subsets.get(index).parent != index) {
            subsets.get(index).parent = find(subsets, subsets.get(index).parent);
        }
        return subsets.get(index).parent;
    }

    private static void union(final List<Subset> subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
        if (subsets.get(xroot).rank < subsets.get(yroot).rank) {
            subsets.get(xroot).parent = yroot;
        } else if (subsets.get(xroot).rank > subsets.get(yroot).rank) {
            subsets.get(yroot).parent = xroot;
        } else {
            subsets.get(yroot).parent = xroot;
            subsets.get(xroot).rank++;
        }
    }

    private static String solveByKruskal(final Set<Edge> edges, final int verticesCount) {
        double cost = 0.;
        int count = 0;
        final List<Subset> subsets = new ArrayList<Subset>(verticesCount);
        for (int v = 0; v < verticesCount; ++v) {
            subsets.add(new Subset(v, 0));
        }
        final Iterator<Edge> iterator = edges.iterator();
        Edge nextEdge;
        int x, y;
        final StringBuilder stringBuilder = new StringBuilder(15 * verticesCount);
        while (count < verticesCount - 1) {
            nextEdge = iterator.next();
            x = find(subsets, nextEdge.source);
            y = find(subsets, nextEdge.destination);
            if (x != y) {
                stringBuilder.append(nextEdge.toString());
                count++;
                cost += nextEdge.cost;
                union(subsets, x, y);
            }
        }
        stringBuilder.append(String.format("%.2f", cost));
        return stringBuilder.toString();
    }

    private static String solve(final Input input) throws IOException {
        int vertices = input.nextInt();
        final int maxLines = input.nextInt();
        final Set<Edge> edges = new TreeSet<>();
        for (int i = 0; i < maxLines; i++) {
            edges.add(new Edge(input.nextInt(), input.nextInt(), input.nextDouble()));
        }
        return solveByKruskal(edges, vertices);
    }

    public static void main(String[] args) throws IOException {
        final PrintWriter output = new PrintWriter(System.out);
        final Input input = new Input(new BufferedReader(new InputStreamReader(System.in)));
        output.print(solve(input));
        input.close();
        output.close();
    }

    private static class Input {

        private final BufferedReader in;
        private final StringBuilder sb = new StringBuilder();
        private final static String SEPERATOR = " \n\r\t";

        public Input(BufferedReader in) {
            this.in = in;
        }

        public String next() throws IOException {
            sb.setLength(0);
            while (true) {
                int c = in.read();
                if (c == -1) {
                    return null;
                }
                if (SEPERATOR.indexOf(c) == -1) {
                    sb.append((char) c);
                    break;
                }
            }
            while (true) {
                int c = in.read();
                if (c == -1 || SEPERATOR.indexOf(c) != -1) {
                    break;
                }
                sb.append((char) c);
            }
            return sb.toString();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public void close() throws IOException {
            in.close();
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }
}
