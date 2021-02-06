import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }
      input = input.replaceAll("[^0-9]", "");
      if (input.length() == 11) {
        if (input.charAt(0) == '7') {
          System.out.println(input);
        } else if (input.charAt(0) == '8') {
          System.out.println(input.replaceFirst("8", "7"));
        } else {
          System.out.println("Неверный формат номера");
        }
      } else if ((input.length() == 10) && (input.charAt(0) == '9')) {
        System.out.println("7" + input);
      } else if ((input.length() > 11) | (input.length() < 10)) {
        System.out.println("Неверный формат номера");
      }
    }
  }

}
