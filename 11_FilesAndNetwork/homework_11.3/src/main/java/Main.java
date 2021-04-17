import java.io.IOException;
import java.util.Map;

public class Main {

  public static void main(String[] args) throws IOException {
    String filePath = "C:/Users/t-fre/Desktop/movementList.csv";
    Movements movements = new Movements(filePath);
    System.out.println("Сумма расходов: " + movements.getExpenseSum());
    System.out.println("Сумма доходов: " + movements.getIncomeSum());
    printMap(movements.getExpenseInfo());
  }

  private static void printMap(Map<String, Double> map) {
    System.out.println("Сумма расходов по организациям:");
    for (String key : map.keySet()) {
      System.out.println(key + " - " + map.get(key));
    }
  }
}
