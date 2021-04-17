import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Parser {

  List<Client> parseProductCsv(String filePath) throws IOException {
    double income;
    double expense;
    String info;
    Client client = new Client();
    client.expenseInfo = new TreeMap<>();
    List<Client> clients = new ArrayList<>();
    List<String> fileLines = Files.readAllLines(Paths.get(filePath));
    for (String fileLine : fileLines) {
      if (fileLine.contains("Тип счёта")) {
        continue;
      }
      String[] splitText = fileLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
      for (int i = 0; i < splitText.length; i++) {
        if (splitText[i].contains("\"")) {
          splitText[i] = splitText[i].replaceAll("\"", "");
          splitText[i] = splitText[i].replaceAll(",", "\\.");
        }
      }
      String[] splitInfo = splitText[splitText.length - 3].split("\\s+", 4);
      expense = Double.parseDouble(splitText[splitText.length - 1]);
      income = Double.parseDouble(splitText[splitText.length - 2]);
      if (splitInfo[1].trim().length() == 6) {
        info = splitInfo[2].trim();
      } else {
        info = splitInfo[1].trim();
      }
      if (expense != 0) {
        if (client.expenseInfo.containsKey(info)) {
          client.expenseInfo.put(info, client.expenseInfo.get(info) + expense);
        } else {
          client.expenseInfo.put(info, expense);
        }
      }
      client.incomeSum += income;
      client.expenseSum += expense;
    }
    clients.add(client);
    return clients;
  }
}
