import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class PowerOfTwo {

    private static int solve(final Input input) throws IOException {
        int n = input.nextInt();
        long x = 1;
        long y = 0;
        for (int i = 1; i <= n; i++) {
            x = x << 1;
            y += x;
        }
        System.out.println(y);
        return 1;
    }

    public static void main(String[] args) throws IOException {
        final PrintWriter output = new PrintWriter(System.out);
        final Input input = new Input(new BufferedReader(new InputStreamReader(System.in)));
        output.println(solve(input));
        input.close();
        output.close();
    }

    private static class Input {

        private final BufferedReader in;
        private final StringBuilder sb = new StringBuilder();
        private final static String SEPERATOR = " \n\r\t";

        public Input(final BufferedReader in) {
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
            return Integer.parseInt(next(), 10);
        }

        public void close() throws IOException {
            in.close();
        }
    }
}
