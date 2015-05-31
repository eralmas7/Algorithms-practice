package hello;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CodilityOne {

    public static void main(String args[]) {
        int[] current = new int[] {5, 3, 7, 1};
        CodilityOne o = new CodilityOne();
        System.out.println(o.solution(current));
    }

    public int solution(int[] input) {
        if (input == null || input.length == 0) {
            return -1;
        }
        final Integer[] result = new Integer[input.length];
        int oddCount = 0;
        for (int i = 0; i < input.length; i++) {
            result[i] = new Integer(input[i]);
            if (result[i] % 2 != 0) {
                oddCount++;
            }
        }
        if (oddCount != input.length) {
            return -1;
        }
        List<Integer> intList = Arrays.asList(result);
        int max = Collections.max(intList);
        int min = Collections.min(intList);
        if (max == min) {
            return -1;
        }
        int mean = (max + min) / 2;
        return max - mean;
    }
}
