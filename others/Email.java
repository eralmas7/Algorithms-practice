package hello;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Email {

    static class EmailComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return o1.substring(o1.indexOf("@")).compareTo(o2.substring(o2.indexOf("@")));
        }
    }

    public List<String> getAllEmails(String fileName) throws FileNotFoundException {
        Scanner s = new Scanner(new File(fileName));
        List<String> emails = new LinkedList<String>();
        String email;
        try {
            Pattern p = Pattern.compile("([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)");
            while ((email = s.findWithinHorizon(p, 0)) != null) {
                emails.add(email);
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
        Collections.sort(emails, new EmailComparator());
        return emails;
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.out.println(new Email().getAllEmails("data.txt"));
    }
}
