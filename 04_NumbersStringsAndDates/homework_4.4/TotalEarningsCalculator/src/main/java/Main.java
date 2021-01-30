public class Main {
  public static void main(String[] args) {
    int vasyaIncome = 0;
    int petyaIncome = 0;
    int mashaIncome = 0;
    int total = 0;
    int space = 0;
    int indexOfSpace = 0;
    int i = 0;

    String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
    while (i < text.length()) {
      if (i == text.indexOf(' ')) {
        space++;
        indexOfSpace = i;
        String income = text.substring(0, indexOfSpace);
        text = text.substring(indexOfSpace + 1);
        i = i - income.length();
        if (space == 3) {
          vasyaIncome = Integer.valueOf(income);
        }
        if (space == 7) {
          petyaIncome = Integer.valueOf(income);
        }
        if (space == 12) {
          mashaIncome = Integer.valueOf(income);
        }
      }
      total = vasyaIncome + petyaIncome + mashaIncome;
      i++;
    }
    System.out.println(total);
  }
}