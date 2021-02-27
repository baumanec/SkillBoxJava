import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {

    /*
    TODO:
     - реализовать методы класса CoolNumbers
     - посчитать время поиска введимого номера в консоль в каждой из структуры данных
     - проанализоровать полученные данные
     */
    public static List<String> coolNumbersList = new ArrayList<>(CoolNumbers.generateCoolNumbers());
    public static HashSet<String> coolNumbersHashSet = new HashSet<>(coolNumbersList);
    public static TreeSet<String> coolNumbersTreeSet = new TreeSet<>(coolNumbersList);

    public static void main(String[] args) {
        System.out.println(coolNumbersList);
        System.out.println("Введите номер автомобиля для поиска:");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            CoolNumbers.bruteForceSearchInList(coolNumbersList, input);
            CoolNumbers.binarySearchInList(coolNumbersList, input);
            CoolNumbers.searchInHashSet(coolNumbersHashSet, input);
            CoolNumbers.searchInTreeSet(coolNumbersTreeSet, input);
        }
    }
}
