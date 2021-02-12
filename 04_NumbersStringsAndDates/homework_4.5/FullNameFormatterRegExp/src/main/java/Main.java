import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {
    String patronymic;
    String lastName;
    String firstName;
    Scanner scanner = new Scanner(System.in);
    outerLoop:
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }
      Pattern pattern = Pattern.compile("[\\d<({—=$!|})?*+.>]");
      Matcher matcher = pattern.matcher(input);
      Pattern pattern1 = Pattern.compile("-");
      if (matcher.find()) {
        System.out.println("Введенная строка не является ФИО");
        break;
      }
      String[] words = input.split(" ", 4);
      for (int i = 0; i < words.length; i++) {
        for (int j = 0; j < words[i].length(); j++) {
          if ((words.length == 1)) {
            System.out.println("Введенная строка не является ФИО");
            break outerLoop;
          }
          if (words.length == 3) {
            Matcher matcher1 = pattern1.matcher(words[i]);
            if (Character.isLowerCase(words[i].charAt(0))) {
              System.out.println(
                  "Неверный формат ввода. Фамилия, имя и отчество должны начинаться с заглавной буквы");
              break outerLoop;
            }
            if (matcher1.find()) {
              int start = matcher1.start();
              if (Character.isLowerCase(words[i].charAt(start + 1))) {
                System.out.println(
                    "Неверный формат ввода. Вторая часть имени или фамилии должна начинаться с заглавной буквы");
                break outerLoop;
              }
              if (j != 0 && j != start + 1 && Character.isUpperCase(words[i].charAt(j))) {
                System.out.println(
                    "Неверный формат ввода: все буквы, кроме первой и первой после дефиса, должны быть в нижнем регистре");
                break outerLoop;
              }
            } else if (j != 0 && Character.isUpperCase(words[i].charAt(j))) {
              System.out.println(
                  "Неверный формат ввода: все буквы, кроме первой, должны быть в нижнем регистре");
              break outerLoop;
            }
          } else {
            if (j != 0 && Character.isUpperCase(words[words.length - 1].charAt(j))) {
              System.out.println(
                  "Неверный формат ввода: все буквы после первой должны быть в нижнем регистре");
              break outerLoop;
            }
          }
        }
      }
      lastName = words[0];
      firstName = words[1];
      if (words.length == 4) {
        patronymic = words[2] + " " + words[3];
      } else {
        patronymic = words[2];
      }
      System.out.println(
          "Фамилия: " + lastName + "\nИмя: " + firstName + "\nОтчество: "
              + patronymic);
    }
  }
}