import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    outerloop:
    while (true) {
      String input = scanner.nextLine();
      String firstName = "";
      String lastName = "";
      String patronymic = "";
      int spaceCount = 0;
      if (input.equals("0")) {
        break;
      }
      for (int i = 0; i < input.length(); i++) {
        if (Character.isDigit(input.charAt(i))) {
          System.out.println("Введенная строка не является ФИО");
          break outerloop;
        }
        if (input.charAt(i) == ' ') {
          if (lastName.isEmpty()) {
            lastName = input.substring(0, i);
          } else {
            firstName = input.substring(0, i);
          }
          input = input.substring(i + 1);
          i = 0;
          spaceCount++;
          if (spaceCount > 2) {
            System.out.println("Введенная строка не является ФИО");
            break outerloop;
          }
        } else if (spaceCount == 2) {
          patronymic = input;
        }
      }
      if (spaceCount == 0) {
        System.out.println("Введенная строка не является ФИО");
        break;
      } else {
        System.out.println(
            "Фамилия: " + lastName.trim() + "\nИмя: " + firstName.trim() + "\nОтчество: "
                + patronymic.trim());
      }
    }
  }
}