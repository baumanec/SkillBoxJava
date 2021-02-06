public class Main {

  public static void main(String[] args) {
    String text = "Вася заработал";
    System.out.println(calculateSalarySum(text));
  }

  public static int calculateSalarySum(String text) {
    int salary;
    int totalSalary = 0;
    String[] parts = text.split(",\\s+");
    for (int i = 0; i < parts.length; i++) {
      String word = parts[i].replaceAll("[^0-9]", "");
      if (word.equals("")) {
        return 0;
      } else {
        salary = Integer.parseInt(word);
        totalSalary += salary;
      }
    }
    return totalSalary;
  }
}