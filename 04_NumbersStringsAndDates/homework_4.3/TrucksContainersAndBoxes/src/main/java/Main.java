import java.util.Scanner;

public class Main {

  public static final int MAX_CONTAINERS = 12;
  public static final int MAX_BOXES = 27;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String boxes = scanner.nextLine();
    int truck = 0;
    int container = 0;
    if (Integer.parseInt(boxes) == 0) {
      System.out.println("Необходимо:");
      System.lineSeparator();
      System.out.println("грузовиков - " + truck + " шт.");
      System.lineSeparator();
      System.out.println("контейнеров - " + container + " шт.");
      System.lineSeparator();
    } else {
      ++truck;
      ++container;

      for (int box = 1; box <= Integer.parseInt(boxes); box++) {
        if (box == 1) {
          System.out.println("Грузовик: " + truck);
          System.lineSeparator();
          System.out.println("\tКонтейнер: " + container);
          System.lineSeparator();
        }

        while ((box % MAX_BOXES != 0) && (box <= Integer.parseInt(boxes))) {
          System.out.println("\t\tЯщик: " + box);
          box++;
        }
        if (box % MAX_BOXES == 0) {
          System.out.println("\t\tЯщик: " + box);
        }
        if ((container % MAX_CONTAINERS == 0) | (container > truck * MAX_CONTAINERS)) {
          truck++;
          System.out.println("Грузовик: " + truck);
          System.lineSeparator();
        }
        if ((box % MAX_BOXES == 0) && (box < Integer.parseInt(boxes))) {
          container++;
          System.out.println("\tКонтейнер: " + container);
          System.lineSeparator();
        }
      }
      System.out.println("Необходимо:");
      System.lineSeparator();
      System.out.println("грузовиков - " + truck + " шт.");
      System.lineSeparator();
      System.out.println("контейнеров - " + container + " шт.");
      System.lineSeparator();
    }
  }
}
