package olds;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsonentProblem {

    private Scanner readTheFile(String fileName) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.exit(-1);
        }
        return scanner;
    }

    private String getNextInput(Scanner scanner) {
        return scanner.nextLine();
    }

    private int getTestCaseCount(Scanner scanner) {
        return scanner.nextInt();
    }

    public static void main(String args[]) {
        final ConsonentProblem problem = new ConsonentProblem();
        final Scanner scanner = problem.readTheFile(args[0]);
        final String[] solution = problem.resolveProblem(scanner, problem);
        int i = 1;
        File file = new File(args[1]);
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter(file));
            for (String status : solution) {
                out.println("Case #" + i++ + ": " + status);
            }
        } catch (IOException e) {
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    private String resolveProblem(List<String> list, int length) {
        int count = 0;
        String temp;
        boolean doContainVowel;
        for (String string : list) {
            for (int i = 0; i < string.length() - length + 1; i++) {
                doContainVowel = false;
                temp = string.substring(i, length + i);
                for (int j = 0; j < length; j++) {
                    if (temp.matches("[aeiou]")) {
                        doContainVowel = true;
                        break;
                    }
                }
                if (!doContainVowel) {
                    count++;
                    break;
                }
            }
        }
        return count + "";
    }

    private String[] resolveProblem(Scanner scanner, ConsonentProblem problem) {
        final int testCaseCount = problem.getTestCaseCount(scanner);
        scanner.nextLine();
        String nextInput;
        String word;
        int number;
        String[] inputs;
        String[] solution = new String[testCaseCount];
        List<String> list;
        int temp;
        for (int testCase = 0; testCase < testCaseCount; testCase++) {
            nextInput = getNextInput(scanner);
            inputs = nextInput.split(" ");
            word = inputs[0];
            number = Integer.valueOf(inputs[1]);
            temp = number;
            list = new ArrayList<String>();
            for (int count = 0; count < word.length(); count++) {
                for (int i = 0; i < word.length() - temp + 1; i++) {
                    list.add(word.substring(i, temp + i));
                }
                temp++;
            }
            solution[testCase] = resolveProblem(list, number);
        }
        return solution;
    }
}
