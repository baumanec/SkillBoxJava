import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {

  public static void main(String[] args) {

    int day = 31;
    int month = 12;
    int year = 1990;

    System.out.println(collectBirthdays(year, month, day));

  }

  public static String collectBirthdays(int year, int month, int day) {
    int i = 0;
    LocalDate birthday = LocalDate.of(year, month, day);
    LocalDate currentDay = LocalDate.now();
    StringBuilder builder = new StringBuilder();
    if (birthday.isAfter(currentDay)) {
      return "";
    }
    while (!birthday.isAfter(currentDay)) {
      builder
          .append(i)
          .append(" - ")
          .append(birthday.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))).append(" - ")
          .append(birthday.format(DateTimeFormatter.ofPattern("EEE", Locale.US)))
          .append(System.lineSeparator());
      birthday = birthday.plusYears(1);
      i++;
    }
    return String.valueOf(builder);
  }
}
