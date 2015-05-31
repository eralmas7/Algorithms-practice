package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaxSubArray {

    static int bestStartIndex = -1;
    static int bestEndIndex = -2;

    private static void max_subarray(List<Integer> list) {
        int currentSum = 0;
        int currentIndex = -1;
        int bestSum = 0;
        for (int i = 0; i < list.size(); i++) {
            int val = currentSum + list.get(i);
            if (val > 0) {
                if (currentSum == 0) {
                    currentIndex = i;
                }
                currentSum = val;
            } else {
                currentSum = 0;
            }
            if (currentSum > bestSum) {
                bestSum = currentSum;
                bestStartIndex = currentIndex;
                bestEndIndex = i;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testcases = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < testcases; i++) {
            int elementCount = Integer.parseInt(scanner.nextLine().trim());
            List<Integer> elements = new ArrayList<Integer>(elementCount);
            for (int j = 0; j < elementCount; j++) {
                elements.add(scanner.nextInt());
            }
            if (scanner.hasNext())
                scanner.nextLine();
            max_subarray(elements);
            int sum = 0;
            for (int j = bestStartIndex; j <= bestEndIndex; j++) {
                sum += elements.get(j);
            }
            int sum2 = 0;
            int ele;
            int min = Integer.MIN_VALUE;
            for (int j = 0; j < elements.size(); j++) {
                ele = elements.get(j);
                if (ele > 0)
                    sum2 += ele;
                if (ele > min) {
                    min = ele;
                }
            }
            System.out.println((sum == 0 ? min : sum) + " " + (sum2 == 0 ? min : sum2));
        }
        scanner.close();
    }
}
