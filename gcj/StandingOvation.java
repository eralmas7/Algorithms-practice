package olds;
import java.util.Scanner;

public class StandingOvation {

    private static int getNewAudienceCount(String string) {
        int count = 0, totalAudienceNeededClapping = 0, currentAudenceClapping;
        for (int i = 0; i < string.length(); i++) {
            currentAudenceClapping = string.charAt(i) - 48;
            if (count < i) {
                totalAudienceNeededClapping++;
                count++;
            }
            count += currentAudenceClapping;
        }
        return totalAudienceNeededClapping;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTestCases = Integer.parseInt(scanner.nextLine());
        String digits;
        for (int i = 0; i < numberOfTestCases; i++) {
            scanner.next();
            digits = scanner.next();
            System.out.println("Case #" + (i + 1) + ": " + getNewAudienceCount(digits));
        }
    }
}
