import java.util.Scanner;

public class Main {

  private static final long sizeOfKB = 1024;
  private static final long sizeOfMB = (long) Math.pow(sizeOfKB, 2);
  private static final long sizeOfGB = (long) Math.pow(sizeOfKB, 3);

  public static void main(String[] args) {
    while (true) {
      System.out.println("Введите путь до папки:");
      Scanner scanner = new Scanner(System.in);
      String path = scanner.nextLine();
      long size = 0;
      try {
        size = FileUtils.calculateFolderSize(path);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
      if (size >= sizeOfKB && size < sizeOfMB) {
        System.out
            .println("Размер папки " + path + " составляет " + size / sizeOfKB + " КБ");
      }
      if (size >= sizeOfMB && size < sizeOfGB) {
        System.out
            .println("Размер папки " + path + " составляет " + size / sizeOfMB + " МБ");
      }
      if (size >= sizeOfGB) {
        System.out
            .println("Размер папки " + path + " составляет " + size / sizeOfGB + " ГБ");
      }
    }
  }
}
