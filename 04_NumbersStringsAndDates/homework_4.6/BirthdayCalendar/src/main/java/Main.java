import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Main {

  public static void main(String[] args) {

    int day = 31;
    int month = 12;
    int year = 1990;

    System.out.println(collectBirthdays(year, month, day));

  }

  public static String collectBirthdays(int year, int month, int day) {

    Calendar calendar = new GregorianCalendar(year, month - 1, day);
    Calendar currentCalendar = Calendar.getInstance();
    StringBuilder builder = new StringBuilder();
    if (currentCalendar.getTime().before(calendar.getTime())) {
      return "";
    }
    for (int i = 0; i
        <= Integer.parseInt(new SimpleDateFormat("yyyy").format(currentCalendar.getTime())) - year;
        i++) {
      builder
          .append(i)
          .append(" - ")
          .append(new SimpleDateFormat("dd.MM.yyyy").format(calendar.getTime())).append(" - ")
          .append(new SimpleDateFormat("EEE", Locale.US).format(calendar.getTime()))
          .append(System.lineSeparator());
      calendar.add(Calendar.YEAR, 1);
      if (currentCalendar.getTime().before(calendar.getTime())) {
        break;
      }
    }
    return String.valueOf(builder);
  }
}
