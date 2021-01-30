import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    String firstName = "";
    String lastName = "";
    String patronymic = "";

    Scanner scanner = new Scanner(System.in);
    outerloop:
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }
      for (int i = 0; i < input.length(); i++) {
        if (Character.isDigit(input.charAt(i))) {
          System.out.println("Введенная строка не является ФИО");
          break outerloop;
        }
      }
      if (input.indexOf(' ') == -1) {
        System.out.println("Введенная строка не является ФИО");
        break;
      } else {
        lastName = input.substring(0, input.indexOf(' '));
        input = input.substring(input.indexOf(' ') + 1);
      }
      if (input.indexOf(' ') == -1) {
        System.out.println("Введенная строка не является ФИО");
        break;
      } else {
        firstName = input.substring(0, input.indexOf(' '));
        input = input.substring(input.indexOf(' ') + 1);
      }
      if (input.indexOf(' ') == -1) {
        patronymic = input;
      } else {
        System.out.println("Введенная строка не является ФИО");
        break;
      }
      System.out.println(
          "Фамилия: " + lastName.trim() + "\nИмя: " + firstName.trim() + "\nОтчество: "
              + patronymic.trim());
    }
  }
}