import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class CoolNumbers {

  private static final List<String> coolNumbers = new ArrayList<>();

  public static List<String> generateCoolNumbers() {
    String number;
    ArrayList<String> letters = new ArrayList<>() {{
      add("А");
      add("В");
      add("Е");
      add("К");
      add("М");
      add("Н");
      add("О");
      add("Р");
      add("С");
      add("Т");
      add("У");
      add("Х");
    }};

    for (int i = 0; i < 2000000; i++) {
      number = Integer.toString((int) (Math.random() * 1000));
      if (number.length() == 1) {
        number = "00" + number;
      }
      if (number.length() == 2) {
        number = "0" + number;
      }
      Collections.shuffle((letters));
      String x = letters.get(0);
      String y = letters.get(1);
      String z = letters.get(2);
      String r = Integer.toString((int) (Math.random() * (199) + 1));
      if (Integer.parseInt(r) < 10) {
        r = "0" + r;
      }
      String coolNumber = x + number + y + z + r;
      coolNumbers.add(coolNumber);
    }
    return coolNumbers;
  }

  public static boolean bruteForceSearchInList(List<String> list, String number) {
    boolean isEquals = false;
    long startTime = System.nanoTime();
    for (String s : list) {
      if (s.equals(number)) {
        isEquals = true;
        break;
      }
    }
    long endTime = System.nanoTime();
    if (isEquals) {
      System.out
          .println("Поиск перебором. Номер найден, поиска занял " + (endTime - startTime) + "нс");
    } else {
      System.out.println(
          "Поиск перебором. Номер не найден, поиска занял " + (endTime - startTime) + "нс");
    }
    return isEquals;
  }

  public static boolean binarySearchInList(List<String> sortedList, String number) {
    boolean isFind = false;
    Collections.sort(sortedList);
    long startTime = System.nanoTime();
    if (Collections.binarySearch(sortedList, number) >= 0) {
      isFind = true;
    }
    long endTime = System.nanoTime();
    if (isFind) {
      System.out
          .println("Бинарный поиск. Номер найден, поиска занял " + (endTime - startTime) + "нс");
    } else {
      System.out
          .println("Бинарный поиск. Номер не найден, поиска занял " + (endTime - startTime) + "нс");
    }
    return isFind;
  }


  public static boolean searchInHashSet(HashSet<String> hashSet, String number) {
    boolean isFind = false;
    long startTime = System.nanoTime();
    if (hashSet.contains(number)) {
      isFind = true;
    }
    long endTime = System.nanoTime();
    if (isFind) {
      System.out
          .println("Поиск в HashSet. Номер найден, поиска занял " + (endTime - startTime) + "нс");
    } else {
      System.out.println(
          "Поиск в HashSet. Номер не найден, поиска занял " + (endTime - startTime) + "нс");
    }
    return isFind;
  }

  public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
    boolean isFind = false;
    long startTime = System.nanoTime();
    if (treeSet.contains(number)) {
      isFind = true;
    }
    long endTime = System.nanoTime();
    if (isFind) {
      System.out
          .println("Поиск в TreeSet. Номер найден, поиска занял " + (endTime - startTime) + "нс");
    } else {
      System.out.println(
          "Поиск в TreeSet. Номер не найден, поиска занял " + (endTime - startTime) + "нс");
    }
    return isFind;
  }

}
