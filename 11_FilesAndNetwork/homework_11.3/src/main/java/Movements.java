import java.io.IOException;
import java.util.TreeMap;

public class Movements {

  Parser parser = new Parser();
  String pathMovementsCsv;

  public Movements(String pathMovementsCsv) {
    this.pathMovementsCsv = pathMovementsCsv;
  }

  public double getExpenseSum() {
    try {
      return parser.parseProductCsv(pathMovementsCsv).get(0).expenseSum;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 0.0;
  }

  public double getIncomeSum() {
    try {
      return parser.parseProductCsv(pathMovementsCsv).get(0).incomeSum;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 0.0;
  }

  public TreeMap<String, Double> getExpenseInfo() throws IOException {
    return parser.parseProductCsv(pathMovementsCsv).get(0).expenseInfo;
  }
}
