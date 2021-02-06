import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    outerLoop:
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }
      String[] words = input.split(" ");
      for (int i = 0; i < words.length; i++){
        if (Character.isDigit(words[i].charAt(i))){
          System.out.println("Введенная строка не является ФИО");
          break outerLoop;
        }
        if ((words.length > 3) | (words.length == 1)) {
          System.out.println("Введенная строка не является ФИО");
          break outerLoop;
        }
      }
      String lastName = words[0];
      String firstName = words[1];
      String patronymic = words[2];
      System.out.println(
          "Фамилия: " + lastName.trim() + "\nИмя: " + firstName.trim() + "\nОтчество: "
              + patronymic.trim());
    }
  }

}