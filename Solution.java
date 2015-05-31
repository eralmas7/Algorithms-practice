import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution {

    private final static String NEWLINE = System.getProperty("line.separator");
    private final static String SPACE = " ";

    public static String solve(Input input) throws IOException {
        final int lineCount = input.nextInt();
        if (!isValidInput(lineCount)) {
            return "";
        }
        int size = 0;
        final Map<String, List<String>> employeeHierarchy = new HashMap<>();
        List<String> tempEmployees;
        String manager, employee, topManager = null;
        for (int i = 1; i < lineCount; i++) {
            manager = input.next();
            employee = input.next();
            tempEmployees = employeeHierarchy.get(manager);
            if (tempEmployees == null) {
                tempEmployees = new ArrayList<>(2);
                employeeHierarchy.put(manager, tempEmployees);
                size += manager.length() + 2;
            }
            if (!tempEmployees.contains(manager)) {
                tempEmployees.add(employee);
                size += employee.length() + 1;
            }
            if (topManager == null) {
                topManager = manager;
            }
        }
        return getEmployeeHierarchy(employeeHierarchy, topManager, size);
    }

    private static boolean isValidInput(int count) {
        if (count <= 0) {
            return false;
        }
        return true;
    }

    private static String getEmployeeHierarchy(final Map<String, List<String>> employeeHierarchy, final String topManager, final int size) {
        if (topManager == null) {
            return "";
        }
        final Set<String> employeesTraversed = new HashSet<>();
        final StringBuilder stringBuilder = new StringBuilder(size);
        final Queue<String> managerQueue = new LinkedList<>();
        managerQueue.add(topManager);
        getEmployeeHierarchy(employeeHierarchy, managerQueue, employeesTraversed, stringBuilder);
        return stringBuilder.toString();
    }

    private static void getEmployeeHierarchy(final Map<String, List<String>> employeeHierarchy, final Queue<String> managerQueue, final Set<String> employeesTraversed, final StringBuilder stringBuilder) {
        final int managerQSize = managerQueue.size();
        if (managerQSize == 0) {
            return;
        }
        String name;
        for (int i = 0; i < managerQSize; i++) {
            name = managerQueue.poll();
            if (!employeesTraversed.contains(name)) {
                stringBuilder.append(name);
                stringBuilder.append(SPACE);
                if (employeeHierarchy.get(name) != null)
                    managerQueue.addAll(employeeHierarchy.get(name));
                employeesTraversed.add(name);
            }
        }
        stringBuilder.append(NEWLINE);
        getEmployeeHierarchy(employeeHierarchy, managerQueue, employeesTraversed, stringBuilder);
    }

    public static void main(final String[] args) throws IOException {
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
