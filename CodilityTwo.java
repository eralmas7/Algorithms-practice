package hello;

public class CodilityTwo {

    public static void main(String args[]) {
        int[] current = {5, 4, -3, 2, 0, 1, -1, 0, 2, -3, 4, -5};
        // Check all odd or all even
        CodilityTwo co = new CodilityTwo();
        System.out.println(co.solution(current));
    }

    public int solution(int[] A) {
        int count = 0;
        boolean isCont = false;
        for (int i = 0; i < A.length - 2; i++) {
            if (isCont) {
                count++;
            } else {
                count = 0;
                isCont = false;
            }
            if ((A[i] >= 0 && A[i + 1] <= 0 && A[i + 2] >= 0) || (A[i] <= 0 && A[i + 1] >= 0 && A[i + 2] <= 0)) {
                isCont = true;
            }
        }
        return count - 1;
    }
    // public int solution(int[] input) {
    // if (input == null || input.length == 0) {
    // return -1;
    // }
    // int sum = 0;
    // final Integer[] result = new Integer[input.length];
    // for (int i = 0; i < input.length; i++) {
    // result[i] = new Integer(input[i]);
    // sum += input[i];
    // }
    // List<Integer> intList = Arrays.asList(result);
    // int max = Collections.max(intList);
    // int min = Collections.min(intList);
    // if (max == min) {
    // return -1;
    // }
    // int count = sum - input.length * min;
    // return count;
    // }
}
