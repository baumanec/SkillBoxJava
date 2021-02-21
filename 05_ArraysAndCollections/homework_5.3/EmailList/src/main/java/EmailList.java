import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailList {

    public static final String WRONG_EMAIL_ANSWER = "Неверный формат email";
    TreeSet<String> emails = new TreeSet<>();

    public void add(String email) {
        email = email.toLowerCase();
        Pattern pattern = Pattern.compile("[\\d\\s<({—=$!|})?*+>_]");
        Matcher matcher = pattern.matcher(email);
        if (matcher.find() | !email.contains(".")) {
            System.out.println(WRONG_EMAIL_ANSWER);
        } else {
            emails.add(email);
        }
    }

    public List<String> getSortedEmails() {
        List<String> sortedEmails = new ArrayList<>(emails);
        for (int i = 0; i < emails.size(); i++) {
            Collections.sort(sortedEmails);
        }
        return sortedEmails;
    }

}
