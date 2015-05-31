package hello;

public class CodilityThree {

    public int solution(int X, int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int div = A.length / 2;
        int rightPointer = A.length;
        for (int i = 0; i < div && i < rightPointer - 1; i++) {
            if (A[i] == X) {
                int j = rightPointer - 1;
                for (; j > i; j--) {
                    if (A[j] != X) {
                        rightPointer = j;
                        break;
                    }
                }
                if (j < i) {
                    return -1;
                }
                if (j == i) {
                    break;
                }
            }
        }
        return rightPointer;
    }

    /**
     * Main method serves the purpose of unit testing
     * 
     * @param args
     */
    public static void main(String... args) {
        CodilityThree three = new CodilityThree();
        System.out.print(three.solution(5, new int[] {5, 5, 1, 7, 2, 3, 5}));
    }
}
