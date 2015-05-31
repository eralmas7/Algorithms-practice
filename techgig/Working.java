/**
 * Created with IntelliJ IDEA. User: Ravi Date: 9/30/13 Time: 6:57 AM To change this template use
 * File | Settings | File Templates.
 */
public class Working {

    public static void main(String[] args) {
        int num[] = {6, 5};
        String array[] = {"X#O#O#O#O#O", "X#O#O#O#O#X", "X#O#O#O#O#X", "X#O#O#O#X#X", "X#O#O#O#X#X"};
        System.out.println(printMaxSubMatrixIndex(array, num));
        String array1[] = {"X#O#O#O#X#O", "X#O#O#O#X#X", "X#O#O#O#X#X", "X#O#X#O#O#X", "X#O#X#O#O#X"};
        String array4[] = {"X#O#O#O#X#O", "X#O#O#O#X#X", "X#O#O#O#X#X", "X#O#X#O#O#X"};
        String array2[] = {"X#O#O#O#O#O", "X#O#O#O#O#X", "X#O#O#O#O#X", "X#O#O#O#X#X", "X#O#O#O#X#X"};
        String array3[] = {"X#O#O#O#O#O", "X#O#O#O#O#X", "X#O#O#O#O#X", "X#O#O#O#O#X", "X#O#O#O#X#X"};
        String array5[] = {"X#O#O#O#O", "X#O#O#O#O", "X#O#O#O#O", "X#O#O#O#X", "X#O#O#O#X"};
        System.out.println(printMaxSubMatrixIndex(array1, num));
        System.out.println(printMaxSubMatrixIndex(array2, num));
        System.out.println(printMaxSubMatrixIndex(array3, num));
        System.out.println(printMaxSubMatrixIndex(array4, num));
        int[] num2 = {5, 5};
        System.out.println(printMaxSubMatrixIndex(array5, num2));
    }

    public static int printMaxSubMatrixIndex(String[] bMatrix, int[] num) {
        if (num.length != 2 || bMatrix.length != num[1]) {
            return -1;
        }
        int[][] temp = new int[num[1]][num[0]];
        int i, j;
        for (i = 0; i < bMatrix.length; i++) {
            if (bMatrix[i].length() / 2 + 1 != num[0]) {
                return -1;
            }
            temp[i][0] = bMatrix[i].charAt(0) == 'O' ? 1 : 0;
        }
        int index = 0;
        for (i = 0; i < bMatrix[0].length(); i += 2) {
            temp[0][index++] = bMatrix[0].charAt(i) == 'O' ? 1 : 0;
        }
        int max = 0;
        index = 1;
        for (i = 1; i < bMatrix.length; i++) {
            for (index = 1, j = 2; j < bMatrix[i].length(); j += 2, index++) {
                int minEntry = Math.min(temp[i][index - 1], temp[i - 1][index]);
                minEntry = Math.min(minEntry, temp[i - 1][index - 1]);
                if (bMatrix[i].charAt(j) == 'O') {
                    temp[i][index] = minEntry + 1;
                    if (temp[i][index] > max) {
                        max = temp[i][index];
                    }
                } else {
                    temp[i][index] = 0;
                }
            }
        }
        return max;
    }
}
