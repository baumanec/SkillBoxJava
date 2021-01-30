public class Main {

  public static void main(String[] args) {
    String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    for (int i = 0; i <= 51; i++) {
      char symbol = alphabet.charAt(i);
      int code = (int) symbol;
      System.out.println(symbol + " - " + code);
    }
  }
}
