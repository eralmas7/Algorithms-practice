import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class A {

    public static boolean hasSum(List<Integer> array, int start, int end, int sum) {
        if (sum == 0)
            return true;
        if (start > end - 1) {
            return false;
        }
        return hasSum(array, start + 1, end, sum) || hasSum(array, start + 1, end, sum - array.get(start));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTestCases = Integer.parseInt(scanner.nextLine());
        int D, V;
        List<Integer> num = new ArrayList<Integer>(100);
        for (int i = 0; i < numberOfTestCases; i++) {
            D = scanner.nextInt();
            V = scanner.nextInt();
            scanner.nextLine();
            num.clear();
            for (int j = 0; j < D; j++) {
                num.add(scanner.nextInt());
            }
            int count = 0;
            int index;
            for (int k = 1; k <= V; k++) {
                index = Collections.binarySearch(num, k);
                if (index >= 0) {
                    continue;
                } else {
                    if (!hasSum(num, 0, ((index * -1) - 1), k)) {
                        num.add(k);
                        Collections.sort(num);
                        count++;
                    }
                }
            }
            System.out.println("Case #" + (i + 1) + ": " + count);
        }
        scanner.close();
    }
}
