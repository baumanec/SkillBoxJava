import java.util.Scanner;

public class Main {

  private static EmailList emails = new EmailList();
  static String command = "";
  static String email = "";

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }
      String[] words = input.split("\\s", 2);
        for (String word : words) {
            if (word.equals("ADD") | word.equals("LIST")) {
                command = word;
            } else {
                email = word;
            }
        }
      if (command.equals("ADD")) {
        emails.add(email);
      }
      if (command.equals("LIST")) {
        System.out.println(emails.getSortedEmails());
      }
    }
  }
}
